/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Main;

import Model.Parkir;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dwinanda
 */
public class HapusParkir extends javax.swing.JPanel {

    /**
     * Creates new form keluar
     */
    private JScrollPane contentScrollPane;

    /**
     * Creates new form LoginCivitas
     */
    public HapusParkir(JScrollPane contentScrollPane) throws SQLException {

        this.contentScrollPane = contentScrollPane;
        initComponents();
        loadTableData();
    }

    private void loadTableData() throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) hapusTable.getModel();
        dtm.setRowCount(0); // Clear existing rows

        try {
            List<Parkir> parkirList = Database.getInstance().dataTable();
            // Populate the table with data from the List<parkir>
            for (Parkir p : parkirList) {
                dtm.addRow(new Object[]{
                    p.getNama(),
                    p.getNomorKendaraan(),
                    p.getWaktuMasuk(),
                    p.getCivitas(),
                    p.getJenisKendaraan()
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(HapusParkir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Gagal mengambil data", "Gagal", JOptionPane.ERROR_MESSAGE);
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

        selesaiParkirButton = new javax.swing.JButton();
        berandaAdminButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hapusTable = new javax.swing.JTable();
        keluarParkirButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(243, 255, 243));

        selesaiParkirButton.setBackground(new java.awt.Color(0, 0, 0));
        selesaiParkirButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selesaiParkirButton.setForeground(new java.awt.Color(255, 255, 255));
        selesaiParkirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/g11.png"))); // NOI18N
        selesaiParkirButton.setText("Selesai Parkir");
        selesaiParkirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiParkirButtonActionPerformed(evt);
            }
        });

        berandaAdminButton.setBackground(new java.awt.Color(0, 0, 0));
        berandaAdminButton.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        berandaAdminButton.setForeground(new java.awt.Color(255, 255, 255));
        berandaAdminButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bullet.gif"))); // NOI18N
        berandaAdminButton.setText("Beranda");
        berandaAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                berandaAdminButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Akses Parkir Admin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 24), new java.awt.Color(0, 0, 51))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Silakan klik sesuai di tabel kolom nomor kendaraan yang ingin dihapus dari parkir");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel1.setText("Hapus Data Parkir");

        hapusTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama", "Nomor Kendaraan", "Jam Masuk", "Civitas", "Jenis Kendaraan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hapusTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(hapusTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 364, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(367, 367, 367))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(164, 164, 164))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        keluarParkirButton.setBackground(new java.awt.Color(0, 0, 0));
        keluarParkirButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        keluarParkirButton.setForeground(new java.awt.Color(255, 255, 255));
        keluarParkirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mail-icon-1.png"))); // NOI18N
        keluarParkirButton.setText("Keluar Parkir");
        keluarParkirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarParkirButtonActionPerformed(evt);
            }
        });

        adminButton.setBackground(new java.awt.Color(0, 0, 0));
        adminButton.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        adminButton.setForeground(new java.awt.Color(255, 255, 255));
        adminButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/r11.png"))); // NOI18N
        adminButton.setText("Admin");

        logoutButton.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User-Interface-Logout-icon-1.png"))); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(keluarParkirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selesaiParkirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adminButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(berandaAdminButton, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(logoutButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(berandaAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keluarParkirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selesaiParkirButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(adminButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hapusTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusTableMouseClicked
        String nomorKendaraan = hapusTable.getValueAt(hapusTable.getSelectedRow(), hapusTable.getSelectedColumn()).toString();
        try {
            Database.getInstance().adminHapus(nomorKendaraan);
            Image test = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/stiskecil.png"));
            loadTableData();
            ImageIcon icon = new ImageIcon(test);
            JOptionPane.showMessageDialog(null, "Berhasil hapus", "Pemberitahuan", 1, (Icon) icon);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data parkir", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HapusParkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hapusTableMouseClicked

    private void selesaiParkirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiParkirButtonActionPerformed
        try {
            // TODO add your handling code here:
            contentScrollPane.setViewportView(new SelesaiParkir(contentScrollPane));
        } catch (SQLException ex) {
            Logger.getLogger(HapusParkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selesaiParkirButtonActionPerformed

    private void berandaAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_berandaAdminButtonActionPerformed
        try {
            // TODO add your handling code here:
            contentScrollPane.setViewportView(new MenuAdmin(contentScrollPane));
        } catch (SQLException ex) {
            Logger.getLogger(HapusParkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_berandaAdminButtonActionPerformed

    private void keluarParkirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarParkirButtonActionPerformed
        try {
            // TODO add your handling code here:
            contentScrollPane.setViewportView(new KeluarParkir(contentScrollPane));
        } catch (SQLException ex) {
            Logger.getLogger(HapusParkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_keluarParkirButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        contentScrollPane.setViewportView(new LoginAdmin(contentScrollPane));
    }//GEN-LAST:event_logoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminButton;
    private javax.swing.JButton berandaAdminButton;
    private javax.swing.JTable hapusTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton keluarParkirButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton selesaiParkirButton;
    // End of variables declaration//GEN-END:variables
}