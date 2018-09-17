/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HenJul
 */
public class DbConnect {
   private final String user = "root";
    private final String pwd = "root";
    private final String co = "jdbc:mysql://localhost:3306/base_fabian ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private Connection conn = null;

    public void connection() {

        try {
            conn = DriverManager.getConnection(co, user, pwd);
            System.out.println("connected!");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void afficherTable(String table) {
    try {
            Statement stmt = (Statement) conn.createStatement();
            String insert = "SELECT * FROM " + table;
            stmt.executeUpdate(insert);
            stmt.close();
            System.out.println("Afficher " + table + " . ");
        } catch (SQLException ex) {
            System.err.println("SQLEception : " + ex.getMessage());
        }
    }
    
    public void insertCol(String tabl, String colName, String colType) {
        try {
            Statement stmt = (Statement) conn.createStatement();
            String insert = "ALTER TABLE " + tabl + " ADD " + colName + " " + colType;
            stmt.executeUpdate(insert);
            stmt.close();
            System.out.println("Insertion " + colName + " dans la table " + tabl);
        } catch (SQLException ex) {
            System.err.println("SQLEception : " + ex.getMessage());
        }
    }

    public void dropCol(String tabl, String colName) {
        try {
            Statement stmt = (Statement) conn.createStatement();
            String insert = "ALTER TABLE " + tabl + " DROP " + colName;
            stmt.executeUpdate(insert);
            stmt.close();
            System.out.println("Suppression " + colName + " dans la table " + tabl);
        } catch (SQLException ex) {
            System.err.println("SQLEception : " + ex.getMessage());
        }
    }

    public void insertInfo(String tabl, String colName, String value) {
        try {
            Statement stmt = (Statement) conn.createStatement();
            String insert = "INSERT INTO " + tabl + "(" + colName + ") VALUES ('" + value + "')";
            stmt.executeUpdate(insert);
            stmt.close();
            System.out.println("insertionInfo " + value + "dans les columns" + colName + " dans la table " + tabl);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void dInfo(String name) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM test WHERE id = ?");
            st.setString(1, name);
            st.executeUpdate();
            st = conn.prepareStatement("ALTER TABLE test AUTO_INCREMENT = " + name );
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void sendLine(DefaultTableModel tbl) {
        try{
            int rows = tbl.getRowCount();
            for (int row = 0; row < rows; row++) {
              /*  int id = (int) tbl.getValueAt(row, 0);
                String rue = (String) tbl.getValueAt(row, 1);
                int numero = (int) tbl.getValueAt(row, 2);
                int CP = (int) tbl.getValueAt(row, 3);
                int pub = (int) tbl.getValueAt(row, 4);
                int tournee = (int) tbl.getValueAt(row, 5);
*/
               // String id = (String) tbl.getValueAt(row, 0);
                String rue = (String) tbl.getValueAt(row, 1);
                String numero = (String) tbl.getValueAt(row, 2);
                String CP = (String) tbl.getValueAt(row, 3);
                String pub = (String) tbl.getValueAt(row, 4);
                String tournee = (String) tbl.getValueAt(row, 5);
                String command = "INSERT INTO test(rue, numero, CP, pub, tournee) VALUES ('" + rue
                        + "','" + numero + "','" + CP + "','" + pub + "','" + tournee + "')";

                PreparedStatement pst = conn.prepareStatement(command);
                pst.execute();
            }
            //vue.renvoyerOptPane().showMessageDialog(null, "Successfully Save");
        } catch (Exception e) {
//            vue.renvoyerOptPane().showMessageDialog(null, e.getMessage());
            System.err.println(e);
        }
    }
    
}
