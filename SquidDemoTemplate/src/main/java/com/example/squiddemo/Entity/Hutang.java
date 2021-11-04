/**
 * 1972046 - Jhonathan Oktavianus
 */
package com.example.squiddemo.Entity;

public class Hutang {
    private int id;
    private double jumlah;
    private String pemberiUtang;
    private Player player_id;

    public Hutang() {
    }

    public Hutang(int id, double jumlah, String pemberiUtang, Player player_id) {
        this.id = id;
        this.jumlah = jumlah;
        this.pemberiUtang = pemberiUtang;
        this.player_id = player_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getPemberiUtang() {
        return pemberiUtang;
    }

    public void setPemberiUtang(String pemberiUtang) {
        this.pemberiUtang = pemberiUtang;
    }

    public Player getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Player player_id) {
        this.player_id = player_id;
    }
}
