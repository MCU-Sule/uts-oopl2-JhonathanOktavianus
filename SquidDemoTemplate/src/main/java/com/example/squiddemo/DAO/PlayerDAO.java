package com.example.squiddemo.DAO;

import com.example.squiddemo.Entity.Player;
import com.example.squiddemo.Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlayerDAO implements DAOInterface<Player>{
    @Override
    public int addData(Player data) {
        int result = 0;
        try {
            String query = "INSERT INTO Player (id,nama,umur,keahlian) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getNama());
            ps.setInt(3,data.getUmur());
            ps.setString(4,data.getKeahlian());
            result = ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Player data) {
        int result = 0;

        String query = "delete from Player where id = ?";
        try {
            Connection connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, data.getId());
            result = ps.executeUpdate();
            if (result != 0){
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Player data) {
        int result = 0;
        String query = "update Player set nama = ?, umur = ?, keahlian = ? WHERE id = ?";
        try {
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setInt(2, data.getUmur());
            ps.setString(3, data.getKeahlian());
            ps.setInt(4,data.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Player> showData() {
        ObservableList<Player> players = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM player ORDER BY id";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setNama(rs.getString("nama"));
                player.setUmur(rs.getInt("umur"));
                player.setKeahlian(rs.getString("keahlian"));
                players.add(player);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return players;
    }
}
