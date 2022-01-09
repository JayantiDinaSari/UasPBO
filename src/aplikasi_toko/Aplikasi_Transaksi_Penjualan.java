/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_toko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Aplikasi_Transaksi_Penjualan extends javax.swing.JFrame {
    Connection c;
    ResultSet r;
    Statement s;
    private Object[][]datajual=null;
    private String[] label={"Kode Barang","Nama Barang","Harga Jual","Jumlah","Total Harga"};
    
    public Aplikasi_Transaksi_Penjualan() {
       initComponents();
        BukaKoneksi ();
        BacaTablePenjualan();
        tkd_trans.setVisible(true);
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
      private void BacaTablePenjualan(){
        try{
            s=(Statement) c.createStatement();
            String sql="Select jual.kd_brg, barang.nm_brg, barang.hrg_jual,"
                    + "jual.jml, jual.tot_hrg"
                    + "From jual, barang where barang.kd_brg=jual.kd_brg";
            r=s.executeQuery(sql);
            ResultSetMetaData m=r.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(r.next()){
                baris=r.getRow();
            }
            datajual=new Object[baris][kolom];
            int x=0;
            r.beforeFirst();
            while(r.next()){
                datajual[x][0]=r.getString("kd_brg");
                datajual[x][1]=r.getString("nm_brg");
                datajual[x][2]=r.getString("hrg_jual");
                datajual[x][3]=r.getString("jml");
                datajual[x][4]=r.getString("tot_hrg");
                x++;
            }
            tbl_jual.setModel(new DefaultTableModel(datajual,label));
        } 
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
      private void SetTable(){
        int row=tbl_jual.getSelectedRow();
        tkd_brg.setText((String)tbl_jual.getValueAt(row,0));
        tnm_brg.setText((String)tbl_jual.getValueAt(row,1));
        thrg_jual.setText((String)tbl_jual.getValueAt(row,2));
        tjml.setText((String)tbl_jual.getValueAt(row,3));
        thrg_tot.setText((String)tbl_jual.getValueAt(row,4));
    }
       private void BersihField(){
        tkd_brg.setText("");
        tnm_brg.setText("");
        thrg_jual.setText("");
        tjml.setText("");
        thrg_tot.setText("");
        ltot.setText("");
        tbyr.setText("");
        lkembali.setText("");
    }
      private void SimpanData(){
        try{
            String sql="Insert Into Penjualan Values('"+tkd_trans.getText()+"','"
                    +ttgl.getText()+"','"+ tkd_brg.getText()+"','"
                    +tjml.getText()+"','"+tkd_peg.getText()+"','"
                    + thrg_tot.getText()+"')";
            s.executeUpdate(sql);
           
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Kode Sudah ada Atau Data Ada Yang Kosong");
        }
    }
      private void TambahData(){
          try{
              String sql="Insert Into jual Values('"+tkd_brg.getText()+"','"
                      +tjml.getText()+"','"+thrg_tot.getText()+"')";
              s.executeUpdate(sql);
              s.close();
              
              BersihField();
              BacaTablePenjualan();
              tkd_brg.requestFocus();
          }
          catch(SQLException e){
              JOptionPane.showMessageDialog(null,"kode sudah ada atau data ada yang kosong");
          }
      }
      private void HapusData(){
        try{
            String sql="Delete from Where kd_brg="+tkd_brg.getText()+"";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            BersihField();
            BacaTablePenjualan();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
      private void HapusDataPenjualan(){
        try{
            String sql="Delete from penjualan Where kd_trans="+tkd_trans.getText()
                    +"' And kd_brg"+tkd_brg.getText()+"'";
            s.executeUpdate(sql);
 
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
      private void HapusDataJual(){
        try{
            String sql="Delete from jual ";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null,"Mulai Transaksi Baru");
            BersihField();
            BacaTablePenjualan();
            tkd_trans.requestFocus();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
      private void total(){
          int y =0;
          int totrec=tbl_jual.getRowCount();
          for(int z=0;z<totrec;z++){
              y=y+Integer.parseInt(tbl_jual.getValueAt(z,4).toString());
              ltot.setText(String.valueOf(y));
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ltot = new javax.swing.JLabel();
        lkembali = new javax.swing.JLabel();
        tkd_trans = new javax.swing.JTextField();
        ttgl = new javax.swing.JTextField();
        tkd_brg = new javax.swing.JTextField();
        tnm_brg = new javax.swing.JTextField();
        thrg_jual = new javax.swing.JTextField();
        tjml = new javax.swing.JTextField();
        thrg_tot = new javax.swing.JTextField();
        tkd_peg = new javax.swing.JTextField();
        tnm_peg = new javax.swing.JTextField();
        tbyr = new javax.swing.JTextField();
        bt_total = new javax.swing.JButton();
        bt_total_bayar = new javax.swing.JButton();
        bt_kembali = new javax.swing.JButton();
        bt_tambah = new javax.swing.JButton();
        bt_hapus = new javax.swing.JButton();
        bt_trbaru = new javax.swing.JButton();
        bt_keluar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_jual = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI TRANSAKSI PENJUALAN");

        jLabel1.setText("TRANSAKSI PENJUALAN");

        jLabel2.setText("Kode Transaksi");

        jLabel3.setText("Tanggal Transaksi");

        jLabel4.setText("Kode Barang");

        jLabel5.setText("Nama Barang");

        jLabel6.setText("Harga Jual");

        jLabel7.setText("Jumlah Jual");

        jLabel8.setText("Kode Pegawai");

        jLabel9.setText("Nama Pegawai");

        jLabel10.setText("Pembayaran");

        jLabel11.setText("Rp.");

        jLabel12.setText("Rp.");

        jLabel13.setText("Rp.");

        jLabel14.setText("Rp.");

        jLabel15.setText("Rp.");

        ltot.setText("jLabel16");

        lkembali.setText("jLabel17");

        tnm_peg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnm_pegActionPerformed(evt);
            }
        });

        bt_total.setText("Total");

        bt_total_bayar.setText("Total Bayar");

        bt_kembali.setText("Kembali");

        bt_tambah.setText("Tambah");

        bt_hapus.setText("Hapus");

        bt_trbaru.setText("Transaksi Baru");

        bt_keluar.setText("Keluar");

        tbl_jual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Jual", "Jumlah", "Total Harga"
            }
        ));
        jScrollPane1.setViewportView(tbl_jual);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tkd_peg, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(204, 204, 204)
                        .addComponent(jLabel9)
                        .addGap(61, 61, 61)
                        .addComponent(tnm_peg, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(578, 578, 578)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(441, 441, 441)
                                        .addComponent(jLabel7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(263, 263, 263)
                                        .addComponent(bt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(57, 57, 57)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tjml, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(thrg_tot))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel10)
                            .addGap(34, 34, 34)
                            .addComponent(jLabel13)
                            .addGap(18, 18, 18)
                            .addComponent(tbyr, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bt_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel12)
                                    .addGap(18, 18, 18)
                                    .addComponent(lkembali)
                                    .addGap(0, 350, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bt_trbaru)
                                    .addGap(327, 327, 327)))
                            .addComponent(bt_keluar)
                            .addGap(254, 254, 254)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_total_bayar)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(ltot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(246, 246, 246))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(tkd_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(49, 49, 49)
                        .addComponent(ttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tnm_brg, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(tkd_brg))
                        .addGap(199, 199, 199)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tkd_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel15)
                    .addComponent(thrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tnm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tjml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_total)
                    .addComponent(jLabel14)
                    .addComponent(thrg_tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tkd_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(tnm_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_total_bayar)
                    .addComponent(ltot)
                    .addComponent(jLabel11)
                    .addComponent(bt_tambah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(tbyr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_kembali)
                    .addComponent(lkembali)
                    .addComponent(jLabel12)
                    .addComponent(bt_hapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_trbaru)
                    .addComponent(bt_keluar))
                .addGap(103, 103, 103)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tnm_pegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnm_pegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnm_pegActionPerformed

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
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Transaksi_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplikasi_Transaksi_Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_hapus;
    private javax.swing.JButton bt_keluar;
    private javax.swing.JButton bt_kembali;
    private javax.swing.JButton bt_tambah;
    private javax.swing.JButton bt_total;
    private javax.swing.JButton bt_total_bayar;
    private javax.swing.JButton bt_trbaru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lkembali;
    private javax.swing.JLabel ltot;
    private javax.swing.JTable tbl_jual;
    private javax.swing.JTextField tbyr;
    private javax.swing.JTextField thrg_jual;
    private javax.swing.JTextField thrg_tot;
    private javax.swing.JTextField tjml;
    private javax.swing.JTextField tkd_brg;
    private javax.swing.JTextField tkd_peg;
    private javax.swing.JTextField tkd_trans;
    private javax.swing.JTextField tnm_brg;
    private javax.swing.JTextField tnm_peg;
    private javax.swing.JTextField ttgl;
    // End of variables declaration//GEN-END:variables
}
