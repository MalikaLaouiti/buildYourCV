package IHM;

import javax.swing.*;
import java.awt.event.*;

public class MyPopUpMenu extends JPopupMenu {
    JMenuItem itemMod = new JMenuItem("modifier");
    JMenuItem itemSup = new JMenuItem("supprimer");
    JMenuItem itemSuptout = new JMenuItem("supprimer tous");

    JList jl;
    DefaultListModel model;
    JTabbedPane jtp;

    public MyPopUpMenu(JList jl, DefaultListModel model, JTabbedPane jtp) {
        this.jl = jl;
        this.model = model;
        this.jtp = jtp;

        this.add(itemMod);
        this.add(itemSup);
        this.add(itemSuptout);

        itemSuptout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                jtp.removeAll();
            }
        });

        itemSup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jl.getSelectedIndex();
                model.remove(index);

            }
        });
    }
}