/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.Akun;
import Model.Parkir;
import Model.Selesai;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Dwinanda
 */
public class Database implements IDatabase, Serializable {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    public static Database instance;
    private static final int TIMEOUT = 30; // Timeout in seconds
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Database() {
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\Users\\Dwinanda\\OneDrive\\Documents\\STIS\\Praktikum dan Coding\\2KS2\\Pemrograman Berorientasi Objek\\UAS\\ProjekAkhir\\uas.sqlite";
            Connection conn = DriverManager.getConnection(url);
            conn.createStatement().execute("PRAGMA busy_timeout = " + TIMEOUT * 1000);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    @Override
    public void insertAnggota(String nomorPengenal, String nama, String status, String password) throws SQLException {
        try (Connection conn = getConnection()) {
            String sqlStmt = "INSERT INTO anggota (nomorPengenal, nama, status, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlStmt)) {
                stmt.setString(1, nomorPengenal);
                stmt.setString(2, nama);
                stmt.setString(3, status);
                stmt.setString(4, password);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    //    private JScrollPane contentScrollPane;
    @Override
    public int loginAnggota(String nomorPengenal, String password) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM anggota";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int itungan = 0;
            while (rs.next()) {
                if ((nomorPengenal).equals(rs.getString(1))) {
                    if ((password).equals(rs.getString(4))) {
                        itungan = 1;
                        Akun akun = new Akun();
                        akun.setNama(rs.getString("nama"));
                        akun.setNomorPengenal(rs.getString("nomorPengenal"));
                        akun.setStatus(rs.getString("status"));
                        break;
                    } else {
                        itungan = 0;
                    }
                } else {
                    itungan = 0;
                }
            }
            return itungan;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public int cekDuplikatRegister(String nomorPengenal, String nama) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM anggota";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int duplikat = 0;
            while (rs.next()) {
                if ((nomorPengenal).equals(rs.getString(1)) || (nama).equals(rs.getString("nama"))) {
                    duplikat = 1;
                    break;
                } else {
                    duplikat = 0;
                }
            }
            return duplikat;
        }
    }

    @Override
    public List<Parkir> dataTable() throws SQLException {
        List<Parkir> parkirList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM parkir";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Parkir parkir = new Parkir(
                            rs.getString("nama"),
                            rs.getString("nomorKendaraan"),
                            rs.getString("waktuMasuk"),
                            rs.getString("waktuKeluar"),
                            rs.getInt("durasi"),
                            rs.getInt("biaya"),
                            rs.getInt("civitas"),
                            rs.getString("jenisKendaraan")
                    );
                    parkirList.add(parkir);
                }
            }
        }
        return parkirList;
    }

    @Override
    public int cekDuplikatUmum(String nama, String nomorKendaraan) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM parkir";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int duplikat = 0;
            while (rs.next()) {
                if ((nama).equals(rs.getString("nama")) || (nomorKendaraan).equals(rs.getString("nomorKendaraan"))) {
                    duplikat = 1;
                    break;
                } else {
                    duplikat = 0;
                }
            }
            return duplikat;
        }
    }

    @Override
    public void adminHapus(String nomorKendaraan) throws SQLException {
        try (Connection conn = getConnection()) {
            String sqlStmt = "delete from parkir where nomorKendaraan='" + nomorKendaraan + "'";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
    }

    @Override
    public int keluar(String nomorKendaraan) throws SQLException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM parkir WHERE nomorKendaraan='" + nomorKendaraan + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int itungan = 0;
            String pintu = "";
            while (rs.next()) {
                if (nomorKendaraan.equals(rs.getString(2))) {
                    Date waktuKeluar = new Date(); // Set waktuKeluar as the current date-time object
                    long durasi;
                    Date waktuMasuk = dateFormat.parse(rs.getString(3));
                    durasi = (waktuKeluar.getTime() - waktuMasuk.getTime()) / 1000; // detik
                    durasi /= 60; // detik to menit
                    int biaya = 0;
                    if (durasi < 60) {
                        durasi = 60;
                    }
                    if (("Roda 4").equals(rs.getString("jenisKendaraan"))) {
                        biaya = (int) durasi / 60 * 4000;
                        pintu = "Pintu A";
                    }
                    if (("Roda 2").equals(rs.getString("jenisKendaraan"))) {
                        biaya = (int) durasi / 60 * 2000;
                        pintu = "Pintu B";
                    }
                    if (rs.getInt("civitas") == 1) {
                        biaya = 0;
                    }

                    //Update waktu keluar, durasi, dan biaya
                    Connection conn2 = getConnection();
                    try {
                        String sqlStmt2 = "update parkir set waktuKeluar='" + dateFormat.format(waktuKeluar)
                                + "',durasi='" + durasi
                                + "',biaya='" + biaya
                                + "' where nomorKendaraan='" + nomorKendaraan + "'";
                        Statement stmt2 = conn.createStatement();
                        stmt2.executeUpdate(sqlStmt2);

                        String msg
                                = "Nama : " + rs.getString("nama")
                                + "\nNomor Kendaraan : " + rs.getString("nomorKendaraan")
                                + "\nJenis Kendaraan : " + rs.getString("jenisKendaraan")
                                + "\nBiaya yang dikenakan adalah : Rp" + biaya
                                + "\nSilakan keluar lewat " + pintu
                                + "\nTerimakasih telah parkir di Politeknik Statistika STIS";
                        Image test = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/stiskecil.png"));
                        ImageIcon icon = new ImageIcon(test);
                        JOptionPane.showMessageDialog(null, msg, "Pemberitahuan", 1, (Icon) icon);

                        itungan = 1;

                        String sql4 = "SELECT * FROM parkir WHERE nomorKendaraan='" + nomorKendaraan + "'";
                        Statement stmt4 = conn.createStatement();
                        ResultSet rs4 = stmt4.executeQuery(sql4);

                        while (rs4.next()) {
                            //salin orang yang udah keluar ke table selesai
                            Selesai selesai = new Selesai(
                                    rs4.getString("nama"),
                                    rs4.getString("nomorKendaraan"),
                                    rs4.getString("waktuMasuk"),
                                    rs4.getString("waktuKeluar"),
                                    rs4.getInt("durasi"),
                                    rs4.getInt("biaya"),
                                    rs4.getInt("civitas")
                            );

                            String sql3 = "INSERT INTO selesai (nama, nomorKendaraan, waktuMasuk, waktuKeluar, durasi, biaya, civitas) VALUES(?, ?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
                                stmt3.setString(1, selesai.getNama());
                                stmt3.setString(2, selesai.getNomorKendaraan());
                                stmt3.setString(3, selesai.getWaktuMasuk());
                                stmt3.setString(4, selesai.getWaktuKeluar());
                                stmt3.setInt(5, selesai.getDurasi());
                                stmt3.setInt(6, selesai.getBiaya());
                                stmt3.setInt(7, selesai.getCivitas());
                                stmt3.executeUpdate();
                            }

                            //hapus orang yang udah keluar di table parkir
                            String sql5 = "delete from parkir where nomorKendaraan='" + nomorKendaraan + "'";
                            Statement stmt5 = conn.createStatement();
                            stmt5.executeUpdate(sql5);
                        }

                        break;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {
                    itungan = 0;
                }
            }
            return itungan;
        }
    }

    @Override
    public void entriMasukParkirAnggota(String nomorPengenal, String nomorKendaraan, String jenisKendaraan) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM anggota WHERE nomorPengenal = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nomorPengenal);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String sql3 = "SELECT * FROM parkir WHERE nomorKendaraan = ?";
                        try (PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
                            stmt3.setString(1, nomorKendaraan);
                            try (ResultSet rs3 = stmt3.executeQuery()) {
                                if (!rs3.next()) {
                                    String nama = rs.getString("nama");
                                    String waktuMasuk = dateFormat.format(new Date());
                                    int civitas = 1;
                                    String sql4 = "INSERT INTO parkir (nama, nomorKendaraan, waktuMasuk, civitas, jenisKendaraan) VALUES (?, ?, ?, ?, ?)";
                                    try (PreparedStatement stmt4 = conn.prepareStatement(sql4)) {
                                        stmt4.setString(1, nama);
                                        stmt4.setString(2, nomorKendaraan);
                                        stmt4.setString(3, waktuMasuk);
                                        stmt4.setInt(4, civitas);
                                        stmt4.setString(5, jenisKendaraan);
                                        stmt4.executeUpdate();
                                        Image test = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/stiskecil.png"));
                                        ImageIcon icon = new ImageIcon(test);
                                        JOptionPane.showMessageDialog(null, "Login berhasil", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE, icon);
//                                    }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Duplikat data, ulang!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void entriMasukParkirUmum(String nama, String nomorKendaraan, String jenisKendaraan) throws SQLException {
        try (Connection conn = getConnection()) {
            try {
                Connection conn2 = getConnection();
                String waktuMasuk = dateFormat.format(new Date());
                int civitas = 0;
                String sql2 = "INSERT INTO parkir (nama, nomorKendaraan,waktuMasuk,civitas,jenisKendaraan) VALUES('" + nama + "','" + nomorKendaraan + "','" + waktuMasuk + "','" + civitas + "','" + jenisKendaraan + "')";
                Statement stmt2 = conn2.createStatement();
                stmt2.executeUpdate(sql2);
                Image test = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/stiskecil.png"));
                ImageIcon icon = new ImageIcon(test);
                JOptionPane.showMessageDialog(null, "Data parkir Anda telah disimpan", "Pemberitahuan", 1, (Icon) icon);
            } catch (SQLException ex) {
                throw ex;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    @Override
    public List<Selesai> loadTableDataSelesai() throws SQLException {
        List<Selesai> selesaiList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM selesai";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Selesai selesai = new Selesai(
                            rs.getString("nama"),
                            rs.getString("nomorKendaraan"),
                            rs.getString("waktuMasuk"),
                            rs.getString("waktuKeluar"),
                            rs.getInt("durasi"),
                            rs.getInt("biaya"),
                            rs.getInt("civitas")
                    );
                    selesaiList.add(selesai);
                }
            }
        }
        return selesaiList;
    }

    @Override
    public void clearSelesaiData() throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM selesai";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data parkir", "Error", JOptionPane.ERROR_MESSAGE);
            throw ex; // Propagate the exception to handle it at a higher level if needed
        }
    }
}
