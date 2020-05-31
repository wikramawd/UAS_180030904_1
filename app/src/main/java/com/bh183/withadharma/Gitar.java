package com.bh183.withadharma;

import java.util.Date;

public class Gitar {

    private int idGitar;
    private String nama;
    private Date tanggal;
    private String gambar;
    private String caption;
    private String keterangan;
    private String link;

    public Gitar(int idGitar, String nama, Date tanggal, String gambar, String caption, String keterangan, String link) {
        this.idGitar = idGitar;
        this.nama = nama;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.caption = caption;
        this.keterangan = keterangan;
        this.link = link;
    }

    public int getIdGitar() {
        return idGitar;
    }

    public void setIdGitar(int idGitar) {
        this.idGitar = idGitar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

