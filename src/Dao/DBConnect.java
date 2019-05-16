package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.prism.Image;

public class DBConnect {

    public enum TableType {
        USER, JOB, LOR, OTHER, CAMPUS
    }


    public static void createTable(final TableType tableType) throws Exception {
    	Connection conn = getConnection();
        try {
            final String tableInfo;
            switch (tableType) {
                case USER:
                    tableInfo = "CREATE TABLE IF NOT EXISTS ragi_users2(A_number varchar(10) NOT NULL, is_Admin Integer, User_name varchar(20), PassCode varchar(10), First_Name varchar(20), Last_Name varchar(20), PRIMARY KEY(A_number), image blob, department varchar(30), email varchar(25))";
                    break;
                case JOB:
                    tableInfo = "CREATE TABLE IF NOT EXISTS ragi_job2(job_req_id INT NOT NULL AUTO_INCREMENT, A_number varchar(10), User_name varchar(20), Email varchar(20), GPA decimal(2,1), linkedin varchar(20), facebook varchar(20),  PRIMARY KEY(job_req_id), FOREIGN KEY(A_number) REFERENCES ragi_users2(A_number))";
                    break;
                case LOR:
                    tableInfo = "CREATE TABLE IF NOT EXISTS ragi_lor1(lor_req_id INT NOT NULL AUTO_INCREMENT, A_number varchar(10), User_name varchar(20), Studying varchar(10),  Course1 varchar(10),  Course2 varchar(10), Course3 varchar(10), Prof1 varchar(10), Prof2 varchar(10), Prof3 varchar(10),  Term1 varchar(10), Term2 varchar(10), Term3 varchar(10),  PRIMARY KEY(lor_req_id), FOREIGN KEY(A_number) REFERENCES ragi_users2(A_number))";
                    break;
//                case OTHER:
//                	tableInfo = "CREATE TABLE IF NOT EXISTS ragi_other(Other_Req_id INT NOT NULL AUTO_INCREMENT, A_number varchar(10), Proff_Email varchar(20), Explain_Req varchar(2000), primary key(other_req_id), FOREIGN KEY(A_number) REFERENCES ragi_users2(A_number))";
//                	break;
//                case CAMPUS:
//                
//                	tableInfo = "CREATE TABLE IF NOT EXISTS ragi_campus(col_req_id INT NOT NULL AUTO_INCREMENT, A_number varchar(10), Proff_Email varchar(20), Position_Name(50), primary key(col_req_id), FOREIGN KEY(A_number) REFERENCES ragi_users2(A_number))";
//                	break;
                default:
                    throw new Exception("Invalid table");
            }
            PreparedStatement create = conn.prepareStatement(tableInfo);
            create.executeUpdate();
            System.out.println("Successfully created table for tableType  " + tableType);
        } catch (Exception e) {
            System.out.println("Failed table creation for " + tableType + " "+ e.getMessage());
        }finally {
        	conn.close();
        }
    }


    public static void insertInTable(final String userTableEntry) throws Exception {
            Connection conn = getConnection();
            PreparedStatement inserted = conn.prepareStatement(userTableEntry);
            inserted.executeUpdate();
            System.out.println("Insert Completed!!!!!");
    }

    public static String getUserQuery(String anumber, int isAdmin, String username, String pass, String f_name, String l_name,Image pic, String dept, String email) {
        return "INSERT INTO ragi_users2(A_number, is_Admin, User_name, PassCode, First_Name,Last_Name,Image, department, email ) VALUES('" + anumber + "', '" + isAdmin + "', '" + username + "', '" + pass + "', '" + f_name + "','" + l_name + "','" + pic +"','" + dept + "', '" + email + "');";
    }
    
    public static TestRecords login(String sql) throws SQLException, Exception {
    	 Connection conn = getConnection();
		try {
			// Execute a query

			System.out.println("Fetching records from the table...");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return processResult(rs);
		}finally {
			conn.close();
		}
			
	}

    public static int count(String sql) throws SQLException, Exception {
   	 Connection conn = getConnection();
		try {
			// Execute a query

			System.out.println("Fetching records from the table...");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		}finally {
			conn.close();
		}
			
	}


    public static String loginQuery(String username, String password) {
		String sql = "Select * from ragi_users2 where user_name = \"" + username + "\" and passcode = \""
				+ password + "\"";
		return sql;
	}
    
    public static String countQuery(String tableName) {
		String sql = "select count(*) from "+ tableName;
		return sql;
	}
    
    public static TestRecords processResult(final ResultSet rs) throws SQLException {

    	String num = (String) rs.getObject(1);
		int isAdmin = (int) rs.getObject(2) ;
		String uname = (String) rs.getObject(3);

		String pass = (String) rs.getObject(4);
		return new TestRecords(num, uname, pass, isAdmin == 0 ? "N": "Y");
	}

    public static String getJobQuery(String anumber, String username, String email, double gpa, String linkedin, String facebook) {
        return "INSERT INTO ragi_job2(A_number, User_name, Email, GPA, linkedin, facebook ) VALUES('" + anumber + "', '" + username + "', '" + email + "', '" + gpa + "', '" + linkedin + "', '" + facebook + "')";
    }

    public static String getLorQuery(String anumber, String username, String studying, String course1, String course2, String course3, String prof1, String prof2, String prof3, String t1, String t2, String t3) {
        return "INSERT INTO ragi_lor1(A_number, User_name, Studying , Course1 ,  Course2 , Course3 , Prof1 , Prof2 , Prof3 ,  Term1 , Term2 , Term3 ) VALUES('" + anumber + "', '" + username + "', '" + studying + "', '" + course1 + "', '" + course2 + "', '" + course3 + "', '" + prof1 + "', '" + prof2 + "', '" + prof3 + "', '" + t1 + "', '" + t2 + "', '" + t3 + "')";
    }
    
    public static String getOtherQuery(String anumber, String email, String explain) {
        return "INSERT INTO ragi_other(A_number, Proff_Email, Explain_Req) VALUES('" + anumber + "', '" + email + "', '" + explain + "')";
    }
    
    public static String getCampusQuery(String anumber, String email, String position) {
        return "INSERT INTO ragi_campus(A_number, Proff_Email, Position_Name) VALUES('" + anumber + "', '" + email + "', '" + position + "')";
    }


    public static Connection getConnection() throws Exception {

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
            String username = "db510";
            String password = "510";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }


}