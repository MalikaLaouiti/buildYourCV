package IHM;

import Adapter.EtudiantTableModel;
import Adapter.TablePopUpMenu;
import DataBase.EtudiantImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

public class GestionEtudiant extends JFrame {
    JTable table;
    EtudiantTableModel model;

    JLabel cin;
    JTextField cinf;
    JLabel nom;
    JTextField nomf;
    JLabel prenom;
    JTextField prenomf;
    JLabel moyenne;
    JTextField moyennef;
    JPanel p;

    JButton save;
    public GestionEtudiant()  {
        setTitle("Gestion Etudiant");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String requete_selection="select * from etudiant";
        EtudiantImplementation imp= new EtudiantImplementation();
        ResultSet rs = imp.selectEtudiant(requete_selection);

        cin=new JLabel("CIN");
        nom=new JLabel("Nom");
        prenom= new JLabel("Prenom");
        moyenne=new JLabel("Moyenne");


        cinf=new JTextField(15);
        nomf=new JTextField(15);
        prenomf=new JTextField(15);
        moyennef=new JTextField(15);

        p=new JPanel();
        save=new JButton("Ajouter");
        p.add(cin);
        p.add(cinf);
        p.add(nom);
        p.add(nomf);
        p.add(prenom);
        p.add(prenomf);
        p.add(moyenne);
        p.add(moyennef);
        p.add(save);

        model =new EtudiantTableModel(rs,imp);
        table =new JTable();
        table.setModel(model);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cin=Integer.parseInt(cinf.getText());
                String nom=nomf.getText();
                String prenom=prenomf.getText();
                double moyenne=Double.parseDouble(moyennef.getText());
                model.ajoutEtudiant(cin,nom,prenom,moyenne);
            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                        int cin = Integer.parseInt(table.getValueAt(row, 0).toString());

                        TablePopUpMenu popup = new TablePopUpMenu(table, model, cin);
                        popup.show(table, e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });;
        this.add(p, BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
        this.add(new JScrollPane(table));
    }

    public static void main(String[] args) {
        new GestionEtudiant().setVisible(true);
    }
}
