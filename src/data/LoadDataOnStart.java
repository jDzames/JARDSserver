package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.json.JSONObject;

@WebListener
public class LoadDataOnStart implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// zavri pripojenie na db
		Data.INSTANCE.closeDB();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//otvor pripojenie a nacitaj data
		Data.INSTANCE.openDB();
		Data.INSTANCE.readDB();
		
	}

}
