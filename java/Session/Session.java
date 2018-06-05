/**
 * @file: Session.java
 * This is to create and handle the SQL connection from within Java.
 * The idea is that it will be permanent.
 * Based upon work that I did in the past use DB2 & C++
 * Java generates a lot of potential errors.
 * Some of these are handled.  
 * @author: Amritpal
 * @bugs:
 * @link: http://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
 * @link: https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
 * @link: https://mariadb.com/kb/en/mariadb/set-transaction/
 *
 */
import java.sql.* ;

public class Session
{
	// ----------------------------------------------------------------------
	// Class variables
	// ----------------------------------------------------------------------
	// JDBC driver name and database URL
	final String JDBC_DRIVER = "org.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost:3306/c9";

	// Database credentials
	final String USER = "leondrolio";
	final String PASS = "";
	Connection conn = null;
	boolean bConnected ;

	// ----------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------
	public Session()
	{
		reset() ;
		return ;
	}

	// ----------------------------------------------------------------------
	// Setters
	// ----------------------------------------------------------------------
	private void reset()
	{
		this.bConnected = false ;
		return ;
	}

	public void connect() 
	{
		try
		{
			System.err.println( this.getClass().getName() + ":: Connecting to database...");
			DriverManager.getDriver( DB_URL ) ;
			conn = DriverManager.getConnection( DB_URL , USER , PASS ) ;
			if ( conn != null )
			{
				this.bConnected = true ;
			}
			System.err.println( this.getClass().getName() + ":: successful connection to DB." ) ;
		}
		catch ( SQLException se )
		{ 
			System.err.println( this.getClass().getName() + "::SQL error:: " + se ) ;
			se.printStackTrace() ;
		}
		catch ( Exception e )
		{
			System.err.println( this.getClass().getName() + "::Error:: " + e ) ;
			e.printStackTrace() ;
		}
		finally
		{
		}
		return ;
	}

	public void disconnect()
	{
		try
		{
			this.conn.close() ;
			this.bConnected = false ;
			System.err.println( this.getClass().getName() + ":: disconnected." ) ;
		}
		catch ( SQLException se )
		{
			System.err.println( this.getClass().getName() + ":: SQL error " + se ) ;
			se.printStackTrace() ;
		}
		catch ( Exception e )
		{
			System.err.println( this.getClass().getName() + ":: error " + e ) ;
			e.printStackTrace() ;
		}
		finally
		{
		}
		return ;
	}

	public void commit()
	{
		// this will commit the logical unit of work... if mySQL supports it.
		// will need to use the InnoDB when creating the tables
		// as well as the transaction type.
		// as our system is not distributed or running in parallel, it could be skipped.
	}

	public void rollback()
	{
		// If I use a transaction, have to allow for the failing conditions.
	}
	
	// ----------------------------------------------------------------------
	// Getters
	// ----------------------------------------------------------------------
	public boolean isConnected()
	{
		return ( this.bConnected ) ;
	}

	public Connection getConnection()
	{
		return ( this.conn ) ;
	}

	// ----------------------------------------------------------------------
	// test rig
	// ----------------------------------------------------------------------
	public static void main( String [] args )
	{
		Session foo = new Session() ;
		foo.connect() ;
		if ( foo.isConnected() == true )
		{
			System.out.println( "Session.main():: am now talking to DB." ) ;
		}
		else
		{
			System.out.println( "Session.main():: failed to talk to DB." ) ;
		}
		foo.disconnect() ;
		return ;
	}
}

