/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Dwinanda
 */
import Model.Parkir;
import Model.Selesai;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IDatabase {

    Connection getConnection() throws SQLException;

    void insertAnggota(String nomorPengenal, String nama, String status, String password) throws SQLException;

    int loginAnggota(String nomorPengenal, String password) throws SQLException;

    int cekDuplikatRegister(String nomorPengenal, String nama) throws SQLException;

    List<Parkir> dataTable() throws SQLException;

    int cekDuplikatUmum(String nama, String nomorKendaraan) throws SQLException;

    void adminHapus(String nomorKendaraan) throws SQLException;

    int keluar(String nomorKendaraan) throws SQLException, ParseException;

    void entriMasukParkirAnggota(String nomorPengenal, String nomorKendaraan, String jenisKendaraan) throws SQLException;

    void entriMasukParkirUmum(String nama, String nomorKendaraan, String jenisKendaraan) throws SQLException;

    List<Selesai> loadTableDataSelesai() throws SQLException;

    void clearSelesaiData() throws SQLException;
}
