package Adapter;

import DataBase.EtudiantImplementation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantTableModel extends AbstractTableModel {
    ResultSetMetaData rsmd;
    ArrayList<Object []> data= new ArrayList<>();
    EtudiantImplementation imp;
    int nbColumn;
    public EtudiantTableModel(ResultSet rs, EtudiantImplementation imp)  {
        this.imp=imp;
        try {
            rsmd=rs.getMetaData();
            nbColumn=rsmd.getColumnCount();
            while ( rs.next()){
                Object[] ligne= new Object[nbColumn];
                for (int i=0;i<nbColumn;i++){
                    ligne[i]=rs.getObject(i+1);
                }
                data.add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount() ;
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (getColumnName(columnIndex).equalsIgnoreCase("Moyenne"));

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        int cin= Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("CIN")).toString());
        String nom= getValueAt(rowIndex,columnNameToIndex("Nom")).toString();
        String prenom=getValueAt(rowIndex,columnNameToIndex("Prenom")).toString();
        double newMoyenne=Double.parseDouble(aValue+"");

        int a =imp.modifEtudiant(cin,nom,prenom,newMoyenne);
        if(a>0){
            data.get(rowIndex)[columnIndex]=aValue;
        }
        else JOptionPane.showMessageDialog(null,"erreur lors de la modification");
    }
    public void ajoutEtudiant(int cin, String nom, String prenom, double newMoyenne){
        int a =imp.insertEtudiant(cin,nom,prenom,newMoyenne);
        if(a>0){
            data.add(new Object[]{cin,nom,prenom,newMoyenne});
            fireTableDataChanged();
        }
        else JOptionPane.showMessageDialog(null,"erreur lors de l'ajout");
    }
    public void supprimerEtudiant(int cin){
        int a =imp.deleteEtudiant(cin);
        if(a>0){
            int index=data.indexOf(cin);
            System.out.println(index);
            data.remove(index);
            fireTableDataChanged();
        }
        else JOptionPane.showMessageDialog(null,"erreur lors de la suppression");
    }

    int columnNameToIndex(String columnName){
        for (int i=0;i<nbColumn;i++){
            if (getColumnName(i).equalsIgnoreCase(columnName))
                return i;

        }
        return -1;
    }

}
