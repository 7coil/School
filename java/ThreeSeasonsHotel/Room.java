
/**
 * @file: Room.java
 * This is the object which encapsulates all access to the DB-table Room.
 * Connection to the DB is taken care of via Session.java.
 * This will not allow you to create new rooms, but it will let you scan
 * the database for whatever exists.
 * Some fields can be updated (price, facilities, etc.).
 * @author: Amritpal 
 * @bugs: note the use of resultSet.next() to advance to the correct result
 * 			as the JDBC driver does not appear to support TYPE_FORWARD_ONLY.
 * 			This means that resultSet.next() must be called immediately after
 * 			the sqlStatement.() otherwise it can time out as well.
 * 		I did not make use of prepared-statements which are quicker
 * 		than the dynamically built ones for fetching by passed parameters.
 * 		There is no explicit call to dbSession.disconnect() ;
 * @link: http://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
 * 
 *
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
  // ----------------------------------------------------------------------
  // Class variables
  // ----------------------------------------------------------------------
  // fields in db-table.
  private int iID;
  private int iRoomNumber;
  private int iFloor;
  private int iCost;
  private int iOccupancy;

  private int singleBed;
  private int doubleBed;
  private int tripleBed;
  private int queenBed;
  private int kingBed;
  private int twinBed;

  private boolean bEnsuite;
  private boolean bMinibar;
  private boolean bJacuzzi;
  private boolean bSeaview;
  private boolean bHoneymoon;
  private boolean bFamily;

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
  public Room() {
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
    iRoomNumber = 0;
    iFloor = 0;
    iCost = 0;
    iOccupancy = 0;
    bEnsuite = false;
    bMinibar = false;
    bJacuzzi = false;
    bSeaview = false;
    bHoneymoon = false;
    bFamily = false;

    singleBed = 0;
    doubleBed = 0;
    tripleBed = 0;
    queenBed = 0;
    kingBed = 0;
    twinBed = 0;

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

        sql = "SELECT count(*) AS rowCount FROM rooms;";

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
  // This will collect the primary key if the requested room number exists
  // in the database table.
  // This means the index can be used thereafter.
  public boolean getByRoomNumber(int iReqRoom) {
    boolean bRC = false;
    try {
      System.err.println(this.getClass().getName() + ":: DB connected := " + dbSession.isConnected());

      sql = "SELECT ID, roomNumber FROM rooms WHERE roomNumber = ? LIMIT 1;";

      System.err.println(this.getClass().getName() + ":: about to exec:= " + sql);

      sqlStatement = dbSession.getConnection().prepareStatement(sql);
      sqlStatement.setInt(1, iReqRoom);
      resultSet = sqlStatement.executeQuery();
      if (resultSet.next()) {
        this.iRoomNumber = iReqRoom;
        this.iID = resultSet.getInt("ID");
      } else {
        System.err.println(this.getClass().getName() + ":: resultSet := " + resultSet);
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
    return (bRC);
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
        iRoomNumber = resultSet.getInt("roomNumber");
        iFloor = resultSet.getInt("floor");
        iCost = resultSet.getInt("cost");
        iOccupancy = resultSet.getInt("occupancy");
        bEnsuite = resultSet.getBoolean("ensuite");
        bMinibar = resultSet.getBoolean("minibar");
        bJacuzzi = resultSet.getBoolean("jacuzzi");
        bSeaview = resultSet.getBoolean("seaView");
        bHoneymoon = resultSet.getBoolean("honeyMoon");
        bFamily = resultSet.getBoolean("family");

        singleBed = resultSet.getInt("singleBed");
        doubleBed = resultSet.getInt("doubleBed");
        tripleBed = resultSet.getInt("tripleBed");
        queenBed = resultSet.getInt("queenBed");
        kingBed = resultSet.getInt("kingBed");
        twinBed = resultSet.getInt("twinBed");
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
  
  public void write() {
    boolean bRC = false;
    try {
      System.err.println(this.getClass().getName() + ":: DB connected := " + dbSession.isConnected());

      sql = "REPLACE INTO `tsh`.`rooms` (`ID`, `roomNumber`, `floor`, `cost`, `occupancy`, `singleBed`, `doubleBed`, `tripleBed`, `queenBed`, `kingBed`, `twinBed`, `ensuite`, `minibar`, `jacuzzi`, `seaView`, `family`, `honeyMoon`) VALUES"
          + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

      System.err.println(this.getClass().getName() + ":: about to exec:= " + sql);

      sqlStatement = dbSession.getConnection().prepareStatement(sql);
      sqlStatement.setInt(1, this.getiID());
      sqlStatement.setInt(2, this.getiRoomNumber());
      sqlStatement.setInt(3, this.getiFloor());
      sqlStatement.setInt(4, this.getiCost());
      sqlStatement.setInt(5, this.getiOccupancy());
      sqlStatement.setInt(6, this.getSingleBed());
      sqlStatement.setInt(7, this.getDoubleBed());
      sqlStatement.setInt(8, this.getTripleBed());
      sqlStatement.setInt(9, this.getQueenBed());
      sqlStatement.setInt(10, this.getKingBed());
      sqlStatement.setInt(11, this.getTwinBed());
      sqlStatement.setBoolean(12, this.isbEnsuite());
      sqlStatement.setBoolean(13, this.isbMinibar());
      sqlStatement.setBoolean(14, this.isbJacuzzi());
      sqlStatement.setBoolean(15, this.isbSeaview());
      sqlStatement.setBoolean(16, this.isbHoneymoon());
      sqlStatement.setBoolean(17, this.isbFamily());
      resultSet = sqlStatement.executeQuery();

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

  public int getiID() {
    return iID;
  }

  public void setiID(int iID) {
    if (iID < 0) throw new RuntimeException("The ID must be greater than 0");
    this.iID = iID;
  }

  public int getiRoomNumber() {
    return iRoomNumber;
  }

  public void setiRoomNumber(int iRoomNumber) {
    this.iRoomNumber = iRoomNumber;
  }

  public int getiFloor() {
    return iFloor;
  }

  public void setiFloor(int iFloor) {
    this.iFloor = iFloor;
  }

  public int getiCost() {
    return iCost;
  }

  public void setiCost(int iCost) {
    this.iCost = iCost;
  }

  public int getiOccupancy() {
    return iOccupancy;
  }

  public void setiOccupancy(int iOccupancy) {
    this.iOccupancy = iOccupancy;
  }

  public int getSingleBed() {
    return singleBed;
  }

  public void setSingleBed(int singleBed) {
    this.singleBed = singleBed;
  }

  public int getDoubleBed() {
    return doubleBed;
  }

  public void setDoubleBed(int doubleBed) {
    this.doubleBed = doubleBed;
  }

  public int getTripleBed() {
    return tripleBed;
  }

  public void setTripleBed(int tripleBed) {
    this.tripleBed = tripleBed;
  }

  public int getQueenBed() {
    return queenBed;
  }

  public void setQueenBed(int queenBed) {
    this.queenBed = queenBed;
  }

  public int getKingBed() {
    return kingBed;
  }

  public void setKingBed(int kingBed) {
    this.kingBed = kingBed;
  }

  public int getTwinBed() {
    return twinBed;
  }

  public void setTwinBed(int twinBed) {
    this.twinBed = twinBed;
  }

  public boolean isbEnsuite() {
    return bEnsuite;
  }

  public void setbEnsuite(boolean bEnsuite) {
    this.bEnsuite = bEnsuite;
  }

  public boolean isbMinibar() {
    return bMinibar;
  }

  public void setbMinibar(boolean bMinibar) {
    this.bMinibar = bMinibar;
  }

  public boolean isbJacuzzi() {
    return bJacuzzi;
  }

  public void setbJacuzzi(boolean bJacuzzi) {
    this.bJacuzzi = bJacuzzi;
  }

  public boolean isbSeaview() {
    return bSeaview;
  }

  public void setbSeaview(boolean bSeaview) {
    this.bSeaview = bSeaview;
  }

  public boolean isbHoneymoon() {
    return bHoneymoon;
  }

  public void setbHoneymoon(boolean bHoneymoon) {
    this.bHoneymoon = bHoneymoon;
  }

  public boolean isbFamily() {
    return bFamily;
  }

  public void setbFamily(boolean bFamily) {
    this.bFamily = bFamily;
  }

  public int getiNumRows() {
    return iNumRows;
  }

  public void setiNumRows(int iNumRows) {
    this.iNumRows = iNumRows;
  }

  // ----------------------------------------------------------------------
  // utilities
  // ----------------------------------------------------------------------
  public void display() {
    sz = "::display()";
    sz = sz + "\n\tID := " + iID;
    sz = sz + "\n\tRoomNumber := " + iRoomNumber;
    sz = sz + "\n\tFloor := " + iFloor;
    sz = sz + "\n\tCost := " + iCost;
    sz = sz + "\n\tOccupancy := " + iOccupancy;
    sz = sz + "\n\tEnsuite := " + bEnsuite;
    sz = sz + "\n\tMinibar := " + bMinibar;
    sz = sz + "\n\tJacuzzi := " + bJacuzzi;
    sz = sz + "\n\tSeaview := " + bSeaview;
    sz = sz + "\n\tHoneymoon := " + bHoneymoon;
    sz = sz + "\n\tFamily := " + bFamily;
    sz = sz + "\n\tsingleBed := " + singleBed;
    sz = sz + "\n\tdoubleBed := " + doubleBed;
    sz = sz + "\n\ttripleBed := " + tripleBed;
    sz = sz + "\n\tqueenBed := " + queenBed;
    sz = sz + "\n\tkingBed := " + kingBed;
    sz = sz + "\n\ttwinBed := " + twinBed;

    System.out.println(this.getClass().getName() + sz);
    return;
  }

  // ----------------------------------------------------------------------
  // test rig
  // ----------------------------------------------------------------------
  public static void main(String[] args) {
    Room room = new Room();

    room.setiID(25565);

    return;
  }
}
