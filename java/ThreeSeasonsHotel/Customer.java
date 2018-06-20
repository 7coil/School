
/**
 * @file Customer
 * This is an object which interfaces with the database to provide customer
 * data for the program.
 * 
 * @author Leondro Lio
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
  // ----------------------------------------------------------------------
  // Class variables
  // ----------------------------------------------------------------------
  // fields in db-table.
  private int iID;
  

  private int iNumRows;

  // DB materials.
  private String sql;
  private String sz;

  private PreparedStatement sqlStatement = null;
  private ResultSet resultSet = null;

  private Session dbSession;

  // ----------------------------------------------------------------------
  // Constructor
  // ----------------------------------------------------------------------
  public Customer() {
    reset();
    this.dbSession = new Session();
    dbSession.connect();
    if (dbSession.isConnected() == false) {
      System.err.println(this.getClass().getName() + ":: failed to connect to DB for table.");
    }
    countRows();
    return;
  }

  // ----------------------------------------------------------------------
  // Setters
  // ----------------------------------------------------------------------
  private void reset() {
    iID = 0;

    iNumRows = -1; // to spot the difference between empty table & no methods run.
    sz = null;
    sql = null;
    sqlStatement = null;
    resultSet = null;
    return;
  }

  // ----------------------------------------------------------------------
  // to determine how many rows exists in this table.
  // this will allow for fixed-loop iterations instead in other methods
  public int countRows() {
    if (iNumRows < 0) {
      try {
        System.err.println(this.getClass().getName() + ":: DB connected := " + dbSession.isConnected());

        sql = "SELECT count(*) AS rowCount FROM customers;";

        sqlStatement = dbSession.getConnection().prepareStatement(sql);
        resultSet = sqlStatement.executeQuery();
        resultSet.next();

        iNumRows = resultSet.getInt("rowCount");
        System.out.println(this.getClass().getName() + ":: Found " + iNumRows + " rows in the DB-table.");

        resultSet.close();
      } catch (SQLException se) {
        System.err.println(this.getClass().getName() + ":: SQL error:: " + se);
        se.printStackTrace();
      } catch (Exception e) {
        System.err.println(this.getClass().getName() + ":: Error:: " + e);
        e.printStackTrace();
      } finally {
      }
    }
    return (this.iNumRows);
  }
  // ----------------------------------------------------------------------
  // Getters
  // ----------------------------------------------------------------------

  // ----------------------------------------------------------------------
  // Allows the entire record to be collected based upon either the RoomId
  // or the RoomNumber fields.
  // Uses a boolean (bId) to determine which mode it is to be set for:
  // true - by primary key (RoomId)
  // false - by main usage field (RoomNumber)
  public void collectRecord(boolean bId, int input) {
    try {
      System.err.println(this.getClass().getName() + ":: DB connected := " + dbSession.isConnected());

      sql = "SELECT * FROM rooms";

      if (bId == true) {
        // searching by primary key (id)
        sql = sql + " WHERE ID = ?;";
      } else {
        // searching by room number
        sql = sql + " WHERE roomNumber = ?;";
      }
      System.err.println(this.getClass().getName() + ":: about to exec:= " + sql);

      sqlStatement = dbSession.getConnection().prepareStatement(sql);
      sqlStatement.setInt(1, input);
      resultSet = sqlStatement.executeQuery();
      
      if (resultSet.next()) {
        iID = resultSet.getInt("ID");
      }

      resultSet.close();
    } catch (SQLException se) {
      System.err.println(this.getClass().getName() + ":: SQL error:: " + se);
      se.printStackTrace();
    } catch (Exception e) {
      System.err.println(this.getClass().getName() + ":: Error:: " + e);
      e.printStackTrace();
    } finally {
    }
    return;
  }

  // ----------------------------------------------------------------------
  // to use the PK.
  public void collectRecordById(int id) {
    collectRecord(true, id);
    return;
  }

  // ----------------------------------------------------------------------
  // to use the main field.
  public void collectRecordByNumber(int iRoomNumber) {
    collectRecord(false, iRoomNumber);
    return;
  }

  // ----------------------------------------------------------------------
  // utilities
  // ----------------------------------------------------------------------
  public void display() {
    sz = "::display()";
    sz = sz + "\n\tID := " + iID;

    System.out.println(this.getClass().getName() + sz);
    return;
  }

  // ----------------------------------------------------------------------
  // test rig
  // ----------------------------------------------------------------------
  public static void main(String[] args) {
    Customer customer = new Customer();

    return;
  }
}
