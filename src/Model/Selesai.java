/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dwinanda
 */
public class Selesai {

    private String nama;
    private String nomorKendaraan;
    private String waktuMasuk;
    private String waktuKeluar;
    private Integer durasi;
    private Integer biaya;
    private Integer civitas;

    public Selesai(String nama, String nomorKendaraan, String waktuMasuk, String waktuKeluar, Integer durasi, Integer biaya, Integer civitas) {
        this.nama = nama;
        this.nomorKendaraan = nomorKendaraan;
        this.waktuMasuk = waktuMasuk;
        this.waktuKeluar = waktuKeluar;
        this.durasi = durasi;
        this.biaya = biaya;
        this.civitas = civitas;
    }

    // Getters and setters
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorKendaraan() {
        return nomorKendaraan;
    }

    public void setNomorKendaraan(String nomorKendaraan) {
        this.nomorKendaraan = nomorKendaraan;
    }

    public String getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(String waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public String getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(String waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }

    public Integer getBiaya() {
        return biaya;
    }

    public void setBiaya(Integer biaya) {
        this.biaya = biaya;
    }

    public Integer getCivitas() {
        return civitas;
    }

    public void setCivitas(Integer civitas) {
        this.civitas = civitas;
    }
}
