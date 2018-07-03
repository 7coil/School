
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

public class RoomBookings {
  // ----------------------------------------------------------------------
  // Class variables
  // ----------------------------------------------------------------------
  // fields in table
  private int ID;
  private int BookingID;
  private int RoomID;

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
  public RoomBookings() {
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
    ID = 0;
    BookingID = 0;
    RoomID = 0;

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

        sql = "SELECT count(*) AS rowCount FROM roomBookings;";

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
  public void collectRecord(int input) {
    try {
      System.err.println(this.getClass().getName() + ":: DB connected := " + dbSession.isConnected());

      sql = "SELECT * FROM roomBookings;";

      System.err.println(this.getClass().getName() + ":: about to exec:= " + sql);

      sqlStatement = dbSession.getConnection().prepareStatement(sql);
      resultSet = sqlStatement.executeQuery();

      if (resultSet.next()) {
        ID = resultSet.getInt("ID");
        BookingID = resultSet.getInt("BookingID");
        RoomID = resultSet.getInt("RoomID");
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
  // utilities
  // ----------------------------------------------------------------------
  public void display() {
    sz = "::display()";
    sz = sz + "\n\tID := " + ID;
    sz = sz + "\n\tBookingID := " + BookingID;
    sz = sz + "\n\tRoomID := " + RoomID;

    System.out.println(this.getClass().getName() + sz);
    return;
  }

  // ----------------------------------------------------------------------
  // test rig
  // ----------------------------------------------------------------------
  public static void main(String[] args) {
    var roomBookings = new RoomBookings();
    roomBookings.collectRecord(2);
    roomBookings.display();

    return;
  }
}
