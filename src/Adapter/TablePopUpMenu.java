// Version simplifiée du TablePopUpMenu
package Adapter;

import javax.swing.*;
import java.awt.event.*;

public class TablePopUpMenu extends JPopupMenu {

    public TablePopUpMenu(JTable table, EtudiantTableModel model, int cin) {
        JMenuItem itemSup = new JMenuItem("Supprimer");
        this.add(itemSup);

        itemSup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(
                        table,
                        "Supprimer l'étudiant CIN: " + cin + " ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmation == JOptionPane.YES_OPTION) {
                    model.supprimerEtudiant(cin);
                    JOptionPane.showMessageDialog(table, "Supprimé avec succès!");
                }
            }
        });
    }
}