package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    JPanel ps;
    EcouteurLabel ecouteurlabel;
    EcouteurTextField ecouteurtextfild;

    public GestionProfile(){
        this.setLayout(new BorderLayout());
        this.setTitle("Gestion Profile");
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        ps = new JPanel();

        save.setBackground(Color.GRAY);
        save.setForeground(Color.white);
        nomf.setToolTipText("Votre nom");
        prenomf.setToolTipText("Votre prenom");
        pseudof.setToolTipText("Votre pseudo");

        p.add(nom);
        p.add(nomf);
        p.add(prenom);
        p.add(prenomf);
        p.add(pseudo);
        p.add(pseudof);
        p.add(save);

        jl.setModel(model);
        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);
        jsp.setDividerLocation(150);

        save.addActionListener(new EcouteurDash(this));
        save.addMouseListener(new EcouteurDash(this));

        ecouteurlabel = new EcouteurLabel(this);
        ecouteurtextfild = new EcouteurTextField(this);

        ps.setLayout(new FlowLayout(FlowLayout.LEFT));
        ps.add(help);
        jl.addMouseListener(new EcouteurDash(this));

        this.add(p, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);

        nom.addMouseListener(ecouteurlabel);
        prenom.addMouseListener(ecouteurlabel);
        pseudo.addMouseListener(ecouteurlabel);

        nomf.addMouseListener(ecouteurtextfild);
        prenomf.addMouseListener(ecouteurtextfild);
        pseudof.addMouseListener(ecouteurtextfild);

    }

    private JButton getCloseBtn(FormPanel form) {
        JButton closeBtn = new JButton("X");
        closeBtn.setFont(new Font("Arial", Font.BOLD, 10));
        closeBtn.setPreferredSize(new Dimension(18, 18));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(ev -> {
            int idx = jtp.indexOfComponent(form);
            if (idx != -1) jtp.remove(idx);
        });
        return closeBtn;
    }
}
