package dk.kea.obopgave.utilities;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager db = new DatabaseConnectionManager();
    private static Connection conn;

    private DatabaseConnectionManager() {

        Properties pro = new Properties();
        try {
            FileInputStream fil = new FileInputStream("src/main/resources/application.properties");
            pro.load(fil);
            String user = pro.getProperty("db.user");
            String password = pro.getProperty("db.password");
            String url = pro.getProperty("db.url");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception ignored){
        }

    }

    public Connection getConn(){
        return conn;
    }

    public static DatabaseConnectionManager getDb(){
        return db;
    }
}
