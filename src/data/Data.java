package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;

public enum Data {
	INSTANCE;
	
	protected Map<String, Adresar> docs = new HashMap<>();
	protected Connection c = null;
	

	protected void closeDB() {
		// TODO Auto-generated method stub
		try {
			if (c != null && !c.isClosed()) {
				c.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void readDB() {
		//vsetky tabulky = kolekcie
				try {
					Class.forName("org.postgresql.Driver");
					c = DriverManager.getConnection(DbContract.HOST + DbContract.DB_NAME, DbContract.USERNAME,
							DbContract.PASSWORD);

					System.out.println("DB connected");

					Statement stmt = c.createStatement();
					String sql= "SELECT * FROM MyCollectionsT;";
					ResultSet rsT = stmt.executeQuery(sql);
					while (rsT.next()) {
						String tableN = rsT.getString("root");
						Adresar adr = new Adresar(
								tableN, rsT.getBoolean("invalidated"), 
								rsT.getString("schema"), rsT.getLong("changed"), rsT.getBoolean("readonly"), 
								rsT.getString("idgenerator"));
						
						Statement stmtT = c.createStatement();
						ResultSet rsD = stmtT.executeQuery("SELECT * FROM "+tableN+";");
						while (rsD.next()) {
							Document doc = new Document(
									(UUID)rsD.getObject("id"), rsD.getLong("changed"), 
									rsD.getBoolean("deleted"), rsD.getString("content"), 
									new JSONObject(rsD.getString("json")));
							adr.addDocument(doc);
						}
						Data.INSTANCE.docs.put(tableN, adr);
					}

					stmt.close();
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		
	}

	protected void saveDocumentsIntoDB(List<Document> newDocuments, List<Document> deletedDocuments, List<Document> updatedDocuments) {
		// ulozenie jednotlivych
		
		
		
	}
	
	
	

}
