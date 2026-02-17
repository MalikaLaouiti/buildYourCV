package DataBase;

import java.sql.ResultSet;

public interface EtudinatDAO {
    int insertEtudiant(int CIN ,String nom, String prenom,double moyenne);
    int deleteEtudiant(int Cin);
    int modifEtudiant(int CIN ,String nom, String prenom,double moyenne);
    ResultSet selectEtudiant(String requeteSelection);
    void afficheResultset(ResultSet rs);
    //on utilisera JTable pour l'affichage de donnees
}
