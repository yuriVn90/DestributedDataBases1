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

import DBTranslate.DTO.ISqlTableDTO;

@Service
public class CsvService implements ICsvService {
	
	public static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/hr?useSSL=true";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
    private String user;
    private String password;
    private String mongoName;
    private Connection connection;
    
    public CsvService() {
    	
    }
    
    @PostConstruct
    public void init() {
    	this.user = USER;
    	this.password = PASSWORD;
    	this.mongoName = "mongo_hr";
    	try {
			this.connection = DriverManager.getConnection(URL_CONNECTION, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	@Override
	public void export(String tableName, String fileName, ISqlTableDTO[] tableData) throws Exception {
		try {
	        Statement stmt = connection.createStatement();
	        String sql = "Select * from "+tableName;
	        ResultSet res = stmt.executeQuery(sql);
	        PrintWriter pw = new PrintWriter(new File(fileName));
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
			//TODO install mongoDB and change the path
			ProcessBuilder processBuilder = new ProcessBuilder("D:\\Databases\\mongodb-win32-x86_64-2008plus-ssl-4.0.4\\bin\\mongoimport.exe","-d",this.mongoName
					,"-c",tableName,"--type","csv","--file","D:\\integrationcourseenv\\workspace\\distributedDatabasesFinalSeverSide\\"+fileName, "--headerline");
			Process process = processBuilder.start();
			System.out.println("Reading the csv to  mongoDB");
			process.waitFor();
			System.out.println("end \n");
		} catch (Exception e) {
			System.out.println("Error executing " + e.toString());
		}
	}

}
