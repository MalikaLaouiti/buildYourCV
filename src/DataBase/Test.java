package DataBase;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Star..");
        //Chargement Driver
        try {
            Class.forName(DataBaseConfig.NOM_DRIVER);
            System.out.println("Driver ok");
        } catch (ClassNotFoundException e) {
           System.out.println("erreur driver"+e.getMessage());
        }

        //connection à la BD
        Connection con=null;
        try {
            con= DriverManager.getConnection(DataBaseConfig.URL_DB,DataBaseConfig.USERNAME,DataBaseConfig.PASSWORD);
            System.out.println("Connected ...");
        } catch (SQLException e) {
            System.out.println("erreur driver"+e.getMessage());
        }

        //Execution Requete
        String requete_insertion="insert into Etudiant values (14043140,'L','Malika',13)";
        if (con!=null){
            Statement st= null;
            try {
                st = con.createStatement();
                int a= st.executeUpdate(requete_insertion);
                if (a>0){
                    System.out.println("done, inserted");
                }
            } catch (SQLException e) {
                System.out.println("erreur driver"+e.getMessage());
            }
        }
        String requete_selection="select * from etudiant";
        if (con!=null) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(requete_selection);
                while(rs.next()){
                    System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getDouble(4));
                }

            } catch (SQLException e) {
                System.out.println("erreur driver"+e.getMessage());
            }

        }
    }
}
