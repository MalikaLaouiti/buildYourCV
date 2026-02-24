package IHM;

import DataBase.EtudiantImplementation;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class GestionEtudiant extends JFrame {
    JTable table;

    public GestionEtudiant()  {
        setTitle("Gestion Etudiant");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String requete_selection="select * from etudiant";
        EtudiantImplementation imp= new EtudiantImplementation();
        ResultSet rs = imp.selectEtudiant(requete_selection);

        table =new JTable();
        this.add(table);
    }

    public static void main(String[] args) {
        new GestionEtudiant().setVisible(true);
    }
}
