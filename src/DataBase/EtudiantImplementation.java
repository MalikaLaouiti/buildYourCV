package DataBase;

import java.sql.*;

public class EtudiantImplementation implements EtudinatDAO{

    Connection con= null;
    EtudiantImplementation (){
        con= DataBaseConnection.makeConnection();
    }

    @Override
    public int insertEtudiant(int CIN, String nom, String prenom, double moyenne) {
        String requete_insertion="insert into Etudiant values (?,?,?,?)";
        if (con!=null){

            try {
                //on utilise prepared statement pour eviter construit insert en fonction de parametre
                PreparedStatement ps= con.prepareStatement(requete_insertion);

                ps.setInt(1,CIN);
                ps.setString(2,nom);
                ps.setString(3,prenom);
                ps.setDouble(4,moyenne);

                int a= ps.executeUpdate();
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
        if (con!=null) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(requeteSelection);
                return rs;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public void afficheResultset(ResultSet rs) {

    }
}
