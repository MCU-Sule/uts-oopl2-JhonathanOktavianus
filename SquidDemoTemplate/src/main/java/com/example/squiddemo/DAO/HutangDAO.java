package com.example.squiddemo.DAO;

import com.example.squiddemo.Entity.Hutang;
import com.example.squiddemo.Entity.Player;
import com.example.squiddemo.Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HutangDAO implements DAOInterface<Hutang>{
    @Override
    public int addData(Hutang data) {
        int result = 0;
        try {
            String query = "INSERT INTO Hutang (id,pemberiutang,jumlah,player_id) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getPemberiUtang());
            ps.setDouble(3,data.getJumlah());
            ps.setInt(4,data.getPlayer_id().getId());
            result = ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Hutang data) {
        int result = 0;

        String query = "delete from Hutang where id = ?";
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
    public int updateData(Hutang data) {
        return 0;
    }

    @Override
    public List<Hutang> showData() {
        ObservableList<Hutang> hutangs = FXCollections.observableArrayList();
        try {
            String query = "SELECT h.id, h.pemberiutang, h.jumlah, h.player_id, p.id as player FROM Hutang h JOIN Player p ON h.player_id = p.id ORDER BY id";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("player_id");
                String nama = rs.getString("nama");
                int umur = rs.getInt("umur");
                String keahlian = rs.getString("keahlian");
                Player player = new Player(id,umur,nama,keahlian);

                Hutang hutang = new Hutang();
                hutang.setId(rs.getInt("id"));
                hutang.setPemberiUtang(rs.getString("PemberiUtang"));
                hutang.setJumlah(rs.getDouble("jumlah"));
                hutang.setPlayer_id(player);
                hutangs.add(hutang);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return hutangs;
    }
}
