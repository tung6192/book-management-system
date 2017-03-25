/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.Assignment4;

import java.awt.HeadlessException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author dinhtungtp
 */
public class BookManagement extends javax.swing.JFrame {
    static PreparedStatement preparedStmt;
    static ResultSet results;

    /**
     * Creates new form BookManagement
     */
    public BookManagement() {
        initComponents();
        showTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the UpdateForm Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        displayTable = new javax.swing.JTable();
        buyBtn = new javax.swing.JButton();
        sellBtn = new javax.swing.JButton();
        editBook = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        codeLable = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Management System");

        displayTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Title", "Author", "Bought", "Sold", "Remained"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(displayTable);
        if (displayTable.getColumnModel().getColumnCount() > 0) {
            displayTable.getColumnModel().getColumn(1).setMinWidth(250);
        }

        buyBtn.setText("Buy More");
        buyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyBtnActionPerformed(evt);
            }
        });

        sellBtn.setText("Sell Book");
        sellBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellBtnActionPerformed(evt);
            }
        });

        editBook.setText("Edit Book");
        editBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBookActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete Book");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        codeLable.setText("Enter Book Code:");

        addBtn.setText("Add New");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codeLable, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sellBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codeField, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(buyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editBook, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLable)
                    .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyBtn)
                    .addComponent(sellBtn)
                    .addComponent(deleteBtn)
                    .addComponent(addBtn)
                    .addComponent(editBook))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sellBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellBtnActionPerformed
        String code = codeField.getText();
        if (code.equals("")) {
            JOptionPane.showMessageDialog(null, "Need to fill the Book Code field", "Missing book code", JOptionPane.ERROR_MESSAGE);
            codeField.requestFocus();
        } else {
            updateTable("Sell", code);
            codeField.setText("");
        }
    }//GEN-LAST:event_sellBtnActionPerformed

    private void editBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBookActionPerformed
        if (codeField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Need to fill the Book Code field", "Missing book code", JOptionPane.ERROR_MESSAGE);
            codeField.requestFocus();
        } else {
            try {
                String code = codeField.getText();
                preparedStmt = RunBookMS.connection.prepareStatement("SELECT Code, Title, Author FROM book_system WHERE Code = ?");
                preparedStmt.setString(1, code);
                results = preparedStmt.executeQuery();
                
                if (!results.next()){
                    JOptionPane.showMessageDialog(null, "Book code doesn't exist", "Book Code Error", JOptionPane.ERROR_MESSAGE);
                    codeField.setText("");
                    codeField.requestFocus();
                } else {
                    String title = results.getString(2);
                    String author = results.getString(3);
                    new EditForm(code, title, author).setVisible(true);
                }
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error happend. Action is unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBookActionPerformed

    private void buyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyBtnActionPerformed
        String code = codeField.getText();
        if (code.equals("")) {
            JOptionPane.showMessageDialog(null, "Need to fill the Book Code field", "Missing book code", JOptionPane.ERROR_MESSAGE);
            codeField.requestFocus();
        } else {
            updateTable("Buy More", code);
            codeField.setText("");
        }
    }//GEN-LAST:event_buyBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        String code = codeField.getText();
        if (code.equals("")) {
            JOptionPane.showMessageDialog(null, "Need to fill the Book Code field", "Missing book code", JOptionPane.ERROR_MESSAGE);
            codeField.requestFocus();
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete the book " + code, "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION){
                try {
                preparedStmt = RunBookMS.connection.prepareStatement("DELETE FROM book_system WHERE Code = ?");
                preparedStmt.setString(1, code);
                preparedStmt.executeUpdate();
                
                // Update table
                JOptionPane.showMessageDialog(null, "The Book has been successfully deleted");
                showTable();
                
                codeField.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error happend. Action is unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                dispose();
            }
            
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String action = "Add New";
        String code = "", title="", author="";
        int remained = 0;
        new UpdateForm(action, code, title, author, remained).setVisible(true);
        codeField.setText("");
    }//GEN-LAST:event_addBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton buyBtn;
    private javax.swing.JTextField codeField;
    private javax.swing.JLabel codeLable;
    private javax.swing.JButton deleteBtn;
    private static javax.swing.JTable displayTable;
    private javax.swing.JButton editBook;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sellBtn;
    // End of variables declaration//GEN-END:variables

    public static void showTable() {
        try {
            Statement stmt = RunBookMS.connection.createStatement();
            results = stmt.executeQuery("Select Code, Title, Author, Bought, Sold, (Bought - Sold) as Remained FROM book_system");

            // Using external library. Download at https://sourceforge.net/projects/finalangelsanddemons/files/rs2xml.jar/download
            displayTable.setModel(DbUtils.resultSetToTableModel(results));

        } catch (SQLException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void updateTable(String updateType, String code) {
        try {
                preparedStmt = RunBookMS.connection.prepareStatement("SELECT Code, Title, Author, (Bought - Sold) as Remained FROM book_system WHERE Code = ?");
                preparedStmt.setString(1, code);
                results = preparedStmt.executeQuery();
                
                if (!results.next()){
                    JOptionPane.showMessageDialog(null, "Book code doesn't exist", "Book Code Error", JOptionPane.ERROR_MESSAGE);
                    codeField.setText("");
                    codeField.requestFocus();
                } else {
                    String action = updateType;
                    String title = results.getString(2);
                    String author = results.getString(3);
                    int remained = results.getInt(4);
                    new UpdateForm(action, code, title, author, remained).setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}