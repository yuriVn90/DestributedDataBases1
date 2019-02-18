package DBTranslate.logic;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


@Service
public class CsvService implements ICsvService {
	
	public static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/hr?useSSL=true";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	public static final String MONGO_DB_NAME = "mongo_hr";
	
    private String user;
    private String password;
    private String mongoName;
    
    public CsvService() {
    	
    }
    
    @PostConstruct
    public void init() {
    	this.user = USER;
    	this.password = PASSWORD;
    	this.mongoName = MONGO_DB_NAME;
    }
    
	@Override
	public void export(String tableName, String fileName) throws Exception {
		Connection connection = DriverManager.getConnection(URL_CONNECTION, this.user, this.password);
		try {
	        Statement stmt = connection.createStatement();
	        String sql = "Select * from "+tableName;
	        ResultSet res = stmt.executeQuery(sql);
	        PrintWriter pw = new PrintWriter(new File(fileName + ".csv"));
	        writeColumnsToCsv(pw, res);        
	        writeDataToCsv(pw, res);
	        
	        pw.flush();
	        pw.close();
	        connection.close();
	        System.out.println("exported to CSV successfuly");
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * write data into CSV columns
	 * @param pw
	 * @param res
	 * @throws SQLException
	 */
	private void writeDataToCsv(PrintWriter pw, ResultSet res) throws SQLException {
        int columnCount = res.getMetaData().getColumnCount();
		while(res.next()) {
	        StringBuilder sb = new StringBuilder();
			for(int i = 1; i <= columnCount; i++) {
				 String Data = res.getString(res.getMetaData().getColumnName(i));
				 if ( i == columnCount) {		 
					 sb.append(Data + "\n");
				 }
				 else { 
					 sb.append(Data + ",");
				 }
			}
			pw.write(sb.toString());
		}
	}

	/**
	 * create columns for table in CSV file
	 * @param pw
	 * @param res
	 * @throws SQLException
	 */
	private void writeColumnsToCsv(PrintWriter pw, ResultSet res) throws SQLException {
        StringBuilder sb = new StringBuilder();
        int columnCount = res.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
        	System.out.println(res.getMetaData().getColumnName(i));
        	sb.append(res.getMetaData().getColumnName(i));
        	sb.append(",");
        }
        sb.append('\n');
        pw.write(sb.toString());
	}

	@Override
	public void importFromCsv(String tableName, String fileName) {
		try {
			ProcessBuilder pb2 = new ProcessBuilder("/usr/local/bin/mongoimport","-d",this.mongoName
					,"-c",tableName,"--type","csv","--file","/Users/yuriv/Developer/Yuri/DataBase/DistributedDataBases1/" +fileName+".csv", "--headerline");
			Process process = pb2.start();
			System.out.println("Reading csv file");
			process.waitFor();
			System.out.println("ended succesfuly \n");
		} catch (Exception e) {
			System.out.println("Error executing " + e.toString());
		}
	}


}
