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
        //model.addElement("A");
        //model.addElement("B");
        //model.addElement("C");


        //jtp.addTab("T1", new JPanel());
        //jtp.addTab("T2", new JPanel());

        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);
        jsp.setDividerLocation(150);

        save.addActionListener(new EcouteurDash(this));
        save.addMouseListener(new EcouteurDash(this));

        ecouteurlabel = new EcouteurLabel(this);
        ecouteurtextfild = new EcouteurTextField(this);

        ps.setLayout(new FlowLayout(FlowLayout.LEFT));
        ps.add(help);
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                {
                    if (e.getClickCount()==2) {
                        String ps = jl.getSelectedValue();
                        FormPanel form = new FormPanel(GestionProfile.this,ps);
                        jtp.addTab(ps, form);

                    }
                    if(e.getButton()==MouseEvent.BUTTON3){
                        MyPopUpMenu popup=new MyPopUpMenu(jl,model,jtp);
                        popup.show(jl,e.getX(),e.getY());
                    }
                }
            }
        });

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
}
