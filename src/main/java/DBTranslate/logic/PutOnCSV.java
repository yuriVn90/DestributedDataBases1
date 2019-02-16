package DBTranslate.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBTranslate.DTO.ISqlTableDTO;


public class PutOnCSV {
	
	public static final String URL= "jdbc:sqlserver://localhost:3306;";
	public static final String DB = "databaseName=hr";
	public static final String URLCon = "jdbc:sqlserver://localhost:3306/hr";

    private String user;
    private String password;
    private Connection connection;
	
	public PutOnCSV() throws SQLException {
		this.user = "root";
		this.password = "root";
		this.connection = DriverManager.getConnection(URLCon, user, password);
	}
	
	public void export(String tablename,String fileName){
	
    try {
        Statement stmt = connection.createStatement();
        String sql = "Select * from "+tablename ;
        ResultSet res = stmt.executeQuery(sql);
        PrintWriter pw = new PrintWriter(new File(fileName));
        writeColumns(pw, res);        
        writeData(pw, res);
        
        pw.flush();
        pw.close();
        connection.close();
        System.out.println("bye");
        
        
    }catch(Exception e)
    {
    	
    	e.printStackTrace();
    }
    
     
}
	
	public void writeColumns(String fileName,String tablename){
		PrintWriter pw = null;
	    try {
			int i;
	        Statement stmt = connection.createStatement();
	        String sql = "Select * from "+tablename;
	        ResultSet res = stmt.executeQuery(sql);
	        
	        pw = new PrintWriter(new File(fileName));  
	        StringBuilder sb = new StringBuilder();
	        int columnCount = res.getMetaData().getColumnCount();
	        
	        for(i=1 ; i<=columnCount; i++)
	        {
	        	sb.append(res.getMetaData().getColumnName(i));
	        	sb.append(",");
	        }
	        

	        sb.append('\n');
	        
	        pw.write(sb.toString());
	        
	        pw.flush();
	        pw.close();
	    
	    	
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

	}
	
	public void writeColumns(PrintWriter pw,  ResultSet res) throws SQLException{
		int i;
        StringBuilder sb = new StringBuilder();
        
        int columnCount = res.getMetaData().getColumnCount();
        
        for(i=1 ; i<=columnCount; i++){
        	System.out.println(res.getMetaData().getColumnName(i));
        	sb.append(res.getMetaData().getColumnName(i));
        	sb.append(",");
        }
        sb.append('\n');
        
        pw.write(sb.toString());
        
	}
	
	public void writeData(PrintWriter pw,  ResultSet res) throws SQLException{
		int i;
        int columnCount = res.getMetaData().getColumnCount();

		while(res.next())
		{
	        StringBuilder sb = new StringBuilder();
			for(i=1 ; i <=columnCount; i++)
			{
				 String Data = res.getString(res.getMetaData().getColumnName(i));
				 
				 if( i == columnCount){		 
					 sb.append(Data + "\n");
				 }
				 else{
					 sb.append(Data + ",");
				 }
				 
			}
			pw.write(sb.toString());
		}
		
		
	}
	
	
	public void writeData(String fileName,  ISqlTableDTO[] data){
		int i;
		PrintWriter pw = null;
		
	    try {
	    pw = new PrintWriter(new FileOutputStream(new File(fileName),true));
        
        for(i=0;i< data.length;i++)
        {
        	String StringData = data[i].getStringToCSV() + "\n";
        	pw.write(StringData);
        }
       
        pw.flush();
        pw.close();
		
	}catch(Exception e)
    {

    	e.printStackTrace();
    }
	
	}

}
