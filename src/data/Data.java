package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	protected void openDB(){
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DbContract.HOST + DbContract.DB_NAME, 
					DbContract.USERNAME, DbContract.PASSWORD);

			System.out.println("DB connected");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void readDB() {
		//vsetky tabulky = kolekcie
				try {
					if (c==null || c.isClosed()) {
						openDB();
					}
					
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
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
	}

	protected void saveDocumentsIntoDB(Adresar adresar, List<Document> newDocuments, List<Document> deletedDocuments, List<Document> updatedDocuments) {
		// ulozenie jednotlivych
		try {
			if (c==null || c.isClosed()) {
				openDB();
			}
			
			c.setAutoCommit(false);
			List<PreparedStatement> stmts = new ArrayList<>();
			
			//vymazane - oznacit tak
			for (int i = 0; i < deletedDocuments.size(); i++) {
				String sqlD = "delete from "+adresar.getRoot()+" where id = "+deletedDocuments.get(i).getId()+";";
				PreparedStatement ps = c.prepareStatement(sqlD);
				ps.executeUpdate();
				stmts.add(ps);
			}
			
			
			//update - zmenit, vymazat a vlozit
			for (int i = 0; i < updatedDocuments.size(); i++) {
				Document d = updatedDocuments.get(i);
				
				String sql = "update "+adresar.getRoot()+" set changed = ?, deleted = ?,content = ?, json = ? where id = "+d.getId()+";";
				int nthPlaceholder = 1; // 1-based counting (not an index).
				PreparedStatement preparedStatement = c.prepareStatement(sql);
				
				preparedStatement.setObject(nthPlaceholder++, d.getDate().getTime());
				preparedStatement.setObject(nthPlaceholder++, d.isDeleted());
				preparedStatement.setObject(nthPlaceholder++, d.getData());
				preparedStatement.setObject(nthPlaceholder++, d.getJson());
				preparedStatement.executeUpdate();
				stmts.add(preparedStatement);
			}
			
			//nove dokumenty - pridat
			for (int i = 0; i < newDocuments.size(); i++) {
				Document d = updatedDocuments.get(i);
				
				String sql = "INSERT INTO test (id,changed,deleted,content,json) " + "VALUES(?,?,?,?,?);";
				int nthPlaceholder = 1; // 1-based counting (not an index).
				PreparedStatement preparedStatement = c.prepareStatement(sql);
			
				preparedStatement.setObject(nthPlaceholder++, d.getId());
				preparedStatement.setObject(nthPlaceholder++, d.getDate().getTime());
				preparedStatement.setObject(nthPlaceholder++, d.isDeleted());
				preparedStatement.setObject(nthPlaceholder++, d.getData());
				preparedStatement.setObject(nthPlaceholder++, d.getJson());
				preparedStatement.executeUpdate();
				stmts.add(preparedStatement);
			}
			
			//commit vsetky delete, update...
			c.commit();
			
		} catch (SQLException e) {
			System.err.println("CHYBA PRI UKLADANI DOKUMENTOV!");
			e.printStackTrace();
		} finally {
	        try {
				c.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		
		
	}
	
	
	

}
