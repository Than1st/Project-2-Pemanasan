package com.example.project2panas.Model;

public class ModelData {
    String NIM, Nama, Jk, Peminatan, Alamat, Agama, Email;



    public ModelData(String NIM, String Nama, String Prodi, String Fakultas){
        this.NIM = NIM;
        this.Nama = Nama;
        this.Jk = Jk;
        this.Peminatan = Peminatan;
        this.Alamat = Agama;
        this.Email = Email;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJk() {
        return Jk;
    }

    public void setJk(String jk) {
        Jk = jk;
    }

    public String getPeminatan() {
        return Peminatan;
    }

    public void setPeminatan(String peminatan) {
        Peminatan = peminatan;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getAgama() {
        return Agama;
    }

    public void setAgama(String agama) {
        Agama = agama;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
