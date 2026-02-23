package DataBase;

import java.awt.*;
import java.sql.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Star..");
        Connection con= DataBaseConnection.makeConnection();
        //Execution Requete
        EtudiantImplementation implementation= new EtudiantImplementation();
        int a = implementation.insertEtudiant(14045156,"LAOUI","Malika",15);
        String requete_selection="select * from etudiant";
        if (con!=null) {
            try {
                ResultSet rs = implementation.selectEtudiant(requete_selection);
                ResultSetMetaData rsmd=rs.getMetaData();
                int nbCol= rsmd.getColumnCount();
                for (int i=0;i<nbCol;i++){
                    System.out.print(rsmd.getColumnName(i+1)+"\t\t\t");
                }
                System.out.println("\n-------------------------------------------------------------");
                while(rs.next()){
                   for (int i=0;i<nbCol;i++){
                       System.out.print(rs.getObject(i+1)+"\t\t");
                   }
                   System.out.println("\n");
                }

            } catch (SQLException e) {
                System.out.println("erreur driver"+e.getMessage());
            }

        }
    }
}
//fonction delete et modifier