/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_toko;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Aplikasi_Transaksi_Pembelian extends javax.swing.JFrame {
 Connection c;
    ResultSet r;
    Statement s;
    private Object[][]datapembelian=null;
    private String[] label={"Kode Pembelian","Tanggal Pembelian","Kode Supplier","Kode Barang","jumlah Pembelian","Harga Beli"};
    
    public Aplikasi_Transaksi_Pembelian() {
        initComponents();
        BukaKoneksi ();
        BacaTablePembelian();
        tkd_beli.setVisible(true);
    }
    
     private void BukaKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_toko","root","");
            System.out.println("Koneksi Sukses");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
     
     private void BacaTablePembelian(){
        try{
            s=(Statement) c.createStatement();
            String sql="Select* From Pembelian";
            r=s.executeQuery(sql);
            ResultSetMetaData m=r.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(r.next()){
                baris=r.getRow();
            }
            datapembelian=new Object[baris][kolom];
            int x=0;
            r.beforeFirst();
            while(r.next()){
                datapembelian[x][0]=r.getString("kd_beli");
                datapembelian[x][1]=r.getString("tgl_beli");
               datapembelian[x][2]=r.getString("kd_sup");
                datapembelian[x][3]=r.getString("kd_brg");
                datapembelian[x][4]=r.getString("jum_beli");
                datapembelian[x][5]=r.getString("hrg_beli");
                x++;
            }
            tbl_beli.setModel(new DefaultTableModel(datapembelian,label));
        } 
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
     
     private void SetTable(){
        int row=tbl_beli.getSelectedRow();
        tkd_beli.setText((String)tbl_beli.getValueAt(row,0));
        ttgl.setText((String)tbl_beli.getValueAt(row,1));
        Tkd_sup.setText((String)tbl_beli.getValueAt(row,2));
        Tkd_brg.setText((String)tbl_beli.getValueAt(row,3));
        tjum.setText((String)tbl_beli.getValueAt(row,4));
        Thrg_beli.setText((String)tbl_beli.getValueAt(row,5));
    }
     
     private void BersihField(){
        tkd_beli.setText("");
        ttgl.setText("");
       Tkd_sup.setText("");
        Tkd_brg.setText("");
        tjum.setText("");
         Thrg_beli.setText("");
    }
    private void SimpanData(){
        try{
            String sql="Insert Into Barang Values('"+tkd_beli.getText()+"','"
                    +ttgl.getText()+"','"+ Tkd_sup.getText()+"','"
                    +Tkd_brg.getText()+"','"+tjum.getText()+"','"
                    + Thrg_beli.getText()+"')";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
            BersihField();
            BacaTablePembelian();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Kode Sudah ada Atau Data Ada Yang Kosong");
        }
    }
    
    private void EditData(){
        try{
            String sql="Update pembelian Set "
                    +"'kd_beli='"+Thrg_beli.getText()
                    +"',tgl_beli='"+ttgl.getText()
                    +"',kd_sup='"+Tkd_sup.getText()
                    +"',kd_brg='"+Tkd_brg.getText()
                    +"',jum_beli='"+tjum.getText()
                    +"',hrg_beli='"+Thrg_beli.getText()
                    +"' Where kd_beli='"+Thrg_beli.getText()+"'";
             s.executeUpdate(sql);
             s.close();
             JOptionPane.showMessageDialog(null,"Data berhasil di edit");
             BersihField();
             BacaTablePembelian();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"kode sudah ada atau data ada yang kosong");
        }
    }
    private void HapusData(){
        try{
            String sql="Delete from Where kd_brg="+tkd_beli.getText()+"";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            BersihField();
            BacaTablePembelian();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_beli = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tkd_beli = new javax.swing.JTextField();
        ttgl = new javax.swing.JTextField();
        Tkd_sup = new javax.swing.JTextField();
        Tkd_brg = new javax.swing.JTextField();
        tjum = new javax.swing.JTextField();
        Thrg_beli = new javax.swing.JTextField();
        bt_tambah = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_hapus = new javax.swing.JButton();
        bt_keluar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI TRANSAKSI PEMBELIAN");

        tbl_beli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Pembelian", "Tanggal Pembelian", "Kode Supplier", "Kode Barang", "Jumlah Pembelian", "Harga Beli"
            }
        ));
        jScrollPane1.setViewportView(tbl_beli);

        jLabel1.setText("TABEL DATA PEMBELIAN");

        jLabel2.setText("INPUT DATA TRANSAKSI PEMBELIAN");

        jLabel3.setText("Kode Pembelian");

        jLabel4.setText("Tanggal Pembelian");

        jLabel5.setText("Kode Supplier");

        jLabel6.setText("Kode Barang");

        jLabel7.setText("Jumlah Pembelian");

        jLabel8.setText("Harga Beli");

        bt_tambah.setText("Tambah");
        bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tambahActionPerformed(evt);
            }
        });

        bt_edit.setText("Edit");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        bt_hapus.setText("Hapus");
        bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_hapusActionPerformed(evt);
            }
        });

        bt_keluar.setText("Keluar");
        bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tjum)
                                            .addComponent(Tkd_brg)
                                            .addComponent(Tkd_sup)
                                            .addComponent(ttgl)
                                            .addComponent(tkd_beli)
                                            .addComponent(Thrg_beli, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(bt_tambah)
                .addGap(80, 80, 80)
                .addComponent(bt_edit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_hapus)
                .addGap(74, 74, 74)
                .addComponent(bt_keluar)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tkd_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Tkd_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(Tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(tjum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Thrg_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_tambah)
                    .addComponent(bt_edit)
                    .addComponent(bt_hapus)
                    .addComponent(bt_keluar))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_hapusActionPerformed
        HapusData();        // TODO add your handling code here:
    }//GEN-LAST:event_bt_hapusActionPerformed

    private void bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_keluarActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_bt_keluarActionPerformed

    private void bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tambahActionPerformed
        SimpanData();        // TODO add your handling code here:
    }//GEN-LAST:event_bt_tambahActionPerformed

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        EditData();        // TODO add your handling code here:
    }//GEN-LAST:event_bt_editActionPerformed

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
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplikasi_Transaksi_Pembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Thrg_beli;
    private javax.swing.JTextField Tkd_brg;
    private javax.swing.JTextField Tkd_sup;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_hapus;
    private javax.swing.JButton bt_keluar;
    private javax.swing.JButton bt_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tbl_beli;
    private javax.swing.JTextField tjum;
    private javax.swing.JTextField tkd_beli;
    private javax.swing.JTextField ttgl;
    // End of variables declaration//GEN-END:variables

   
}
