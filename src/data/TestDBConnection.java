package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

interface DbContract {

	public static final String HOST = "jdbc:postgresql://localhost:5432/";

	public static final String DB_NAME = "JARDS";

	public static final String USERNAME = "jards";

	public static final String PASSWORD = "jards";

}

public class TestDBConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(DbContract.HOST + DbContract.DB_NAME, DbContract.USERNAME,
					DbContract.PASSWORD);

			System.out.println("DB connected");

			Statement stmt = c.createStatement();

			// CREATE TABLE
			/*String csql = "CREATE TABLE public.test ( "
					+ "id uuid NOT NULL, changed bigint, deleted boolean, content text, json json, "
					+ "CONSTRAINT test_pkey PRIMARY KEY (id) ) "
					+ "WITH ( OIDS=FALSE ); "
					+ "ALTER TABLE public.test OWNER TO jards; ";
			
			stmt.executeUpdate(csql);
			stmt.close();*/

			// INSERT
			/*stmt = c.createStatement();
			String sql = "INSERT INTO test (id,changed,deleted,content,json) " + "VALUES(?,?,?,?,?);";
			int nthPlaceholder = 1; // 1-based counting (not an index).
			PreparedStatement preparedStatement = c.prepareStatement(sql);
		
			preparedStatement.setObject(nthPlaceholder++, UUID.randomUUID());
			Date d = new Date();
			preparedStatement.setObject(nthPlaceholder++, d.getTime());
			preparedStatement.setObject(nthPlaceholder++, false);
			preparedStatement.setObject(nthPlaceholder++, "obsah testovaci");
			PGobject jsonObject = new PGobject();
			jsonObject.setType("json");
			jsonObject.setValue(new JSONObject().put("auto", "subaru").toString());
			preparedStatement.setObject(nthPlaceholder++, jsonObject);

			if (!(preparedStatement.executeUpdate() == 1)) {
				// If the SQL reports other than one row inserted…
				System.out.println("asi chyba");
			}*/

			
			// INSERT 2
			/*stmt = c.createStatement();
			String sql = "INSERT INTO MyCollectionsT (root,changed,invalidated,schema,readonly,idgenerator) " + 
			"VALUES('test',1459936458275,true,'json',false,'UUID');";
			stmt.execute(sql);*/

			
			
			// SELECT
			ResultSet rs = stmt.executeQuery("SELECT * FROM test;");
			while (rs.next()) {
				System.out.println("id: " + (UUID) rs.getObject("id"));
			}

			stmt.close();
			c.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}


/*
  
  CREATE TABLE public."mycollectionst"
(
   root text, 
   changed bigint, 
   invalidated boolean, 
   schema text, 
   readonly boolean, 
   idgenerator text, 
   PRIMARY KEY (root)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public."mycollectionst"
  OWNER TO jards;
  
 */
