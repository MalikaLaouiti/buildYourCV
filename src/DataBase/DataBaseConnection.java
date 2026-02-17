package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection makeConnection() {
        //Chargement Driver
        try {
            Class.forName(DataBaseConfig.NOM_DRIVER);
            System.out.println("Driver ok");
        } catch (ClassNotFoundException e) {
            System.out.println("erreur driver" + e.getMessage());
        }

        //connection à la BD
        Connection con = null;
        try {
            con = DriverManager.getConnection(DataBaseConfig.URL_DB, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            System.out.println("Connected ...");
        } catch (SQLException e) {
            System.out.println("erreur driver" + e.getMessage());
        }
        return con;
    }
}
