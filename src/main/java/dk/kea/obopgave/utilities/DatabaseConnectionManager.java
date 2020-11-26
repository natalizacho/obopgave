package dk.kea.obopgave.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager db = new DatabaseConnectionManager();
    private Connection conn;

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
        catch(FileNotFoundException e){
            System.out.println("File could not be found");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("Property could not be loaded");
            e.printStackTrace();
        }
        catch(SQLException e){
            System.out.println("Message to the developer");
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        return conn;
    }

    public static DatabaseConnectionManager getDb(){
        return db;
    }
}
