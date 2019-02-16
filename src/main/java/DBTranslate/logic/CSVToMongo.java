package DBTranslate.logic;

public class CSVToMongo {
	
private String name;
	
	public CSVToMongo() {
		this.name = "example";
		
	}
	
	
	
	public void putInMongo(String tableName,String fileName) {

		try {
			
			ProcessBuilder processBuilder = new ProcessBuilder("D:\\Databases\\mongodb-win32-x86_64-2008plus-ssl-4.0.4\\bin\\mongoimport.exe","-d",name
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
