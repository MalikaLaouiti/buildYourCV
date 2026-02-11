package IHM;

import javax.swing.*;
import java.awt.*;

public class GestionProfile extends JFrame {
    JLabel nom;
    JTextField nomf;
    JLabel prenom;
    JTextField prenomf;
    JLabel pseudo;
    JTextField pseudof;
    JLabel help;
    JPanel p;
    JButton save;
    JSplitPane jsp;
    JList<String> jl;
    DefaultListModel<String> model;
    JTabbedPane jtp;

    public GestionProfile(){
        this.setLayout(new BorderLayout());
        this.setTitle("Gestion Profile");
        this.setSize(new Dimension(800,600));
        nom=new JLabel("Nom");
        prenom= new JLabel("Prenom");
        pseudo=new JLabel("Pseudo");
        help=new JLabel("HELP");

        nomf=new JTextField(15);
        prenomf=new JTextField(15);
        pseudof=new JTextField(15);

        p=new JPanel();
        save=new JButton("Enregistrer");
        jsp=new JSplitPane();
        jl = new JList<>();
        model = new DefaultListModel<>();
        jtp = new JTabbedPane();

        p.add(nom);
        p.add(nomf);
        p.add(prenom);
        p.add(prenomf);
        p.add(pseudo);
        p.add(pseudof);
        p.add(save);
        this.add(p,BorderLayout.NORTH);
        this.add(jsp,BorderLayout.CENTER);
        this.add(help,BorderLayout.SOUTH);

        jl.setModel(model);
        model.addElement("A");
        model.addElement("B");
        model.addElement("C");

        jtp.addTab("T1", new JPanel());
        jtp.addTab("T2", new JPanel());

        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);

        save.addActionListener(new EcouteurDash(this));


    }
}
