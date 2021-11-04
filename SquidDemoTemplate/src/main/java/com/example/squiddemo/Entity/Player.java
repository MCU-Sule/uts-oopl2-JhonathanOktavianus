/**
 * 1972046 - Jhonathan Oktavianus
 */
package com.example.squiddemo.Entity;

public class Player {
    private int id, umur;
    private String nama, keahlian;

    public Player() {
    }

    public Player(int id, int umur, String nama, String keahlian) {
        this.id = id;
        this.umur = umur;
        this.nama = nama;
        this.keahlian = keahlian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    @Override
    public String toString() {
        return nama;
    }
}
