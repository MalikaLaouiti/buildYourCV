package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EtudiantImplementation implements EtudinatDAO{

    Connection con= null;
    EtudiantImplementation (){
        con= DataBaseConnection.makeConnection();
    }

    @Override
    public int insertEtudiant(int CIN, String nom, String prenom, double moyenne) {
        String requete_insertion="insert into Etudiant values ("+CIN+","+nom+","+prenom+","+moyenne+")";
        if (con!=null){
            Statement st= null;
            try {
                st = con.createStatement();
                int a= st.executeUpdate(requete_insertion);
                if (a>0){
                    System.out.println("done, inserted");
                    return a;
                }
            } catch (SQLException e) {
                System.out.println("erreur d'insertion"+e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int deleteEtudiant(int Cin) {
        return 0;
    }

    @Override
    public int modifEtudiant(int CIN, String nom, String prenom, double moyenne) {
        return 0;
    }

    @Override
    public ResultSet selectEtudiant(String requeteSelection) {
        return null;
    }

    @Override
    public void afficheResultset(ResultSet rs) {

    }
}
