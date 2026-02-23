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
        String requete_suppression = "delete from Etudiant where CIN = ?";
        if (con != null){
            try {
                PreparedStatement ps = con.prepareStatement(requete_suppression);
                ps.setInt(1, Cin);

                int a = ps.executeUpdate();
                if (a > 0){
                    System.out.println("Suppression réussie - Étudiant avec CIN " + Cin + " supprimé");
                    return a;
                } else {
                    System.out.println("Aucun étudiant trouvé avec le CIN: " + Cin);
                }
            } catch (SQLException e) {
                System.out.println("Erreur de suppression: " + e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int modifEtudiant(int CIN, String nom, String prenom, double moyenne) {
        String requete_modification = "update Etudiant set nom = ?, prenom = ?, moyenne = ? where CIN = ?";
        if (con != null){
            try {
                PreparedStatement ps = con.prepareStatement(requete_modification);
                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setDouble(3, moyenne);
                ps.setInt(4, CIN);

                int a = ps.executeUpdate();
                if (a > 0){
                    System.out.println("Modification réussie - Étudiant avec CIN " + CIN + " mis à jour");
                    return a;
                } else {
                    System.out.println("Aucun étudiant trouvé avec le CIN: " + CIN);
                }
            } catch (SQLException e) {
                System.out.println("Erreur de modification: " + e.getMessage());
            }
        }
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
        if (rs != null) {
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();
                System.out.println("----------------------------------------");

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                System.out.println("Erreur d'affichage du ResultSet: " + e.getMessage());
            }
        } else {
            System.out.println("ResultSet est null");
        }
    }
}
