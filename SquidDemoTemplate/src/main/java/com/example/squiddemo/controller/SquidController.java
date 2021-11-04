/**
 * 1972046 - Jhonathan Oktavianus
 */
package com.example.squiddemo.controller;

import com.example.squiddemo.DAO.HutangDAO;
import com.example.squiddemo.DAO.PlayerDAO;
import com.example.squiddemo.Entity.Hutang;
import com.example.squiddemo.Entity.Player;
import com.example.squiddemo.SquidApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SquidController implements Initializable {

    public ComboBox<Player> cmbPeserta;
    public TextField txtPemberiUtang;
    public TextField txtJumlah;
    public TableView<Hutang> tableHutang;
    public TableColumn<Hutang, String> colId;
    public TableColumn<Hutang, String> hutangTo;
    public TableColumn<Hutang, String> jumlah;
    public TableView<Player> tablePemain;
    public TableColumn<Player, String> colIdPlayer;
    public TableColumn<Player, String> colNamaPlayer;
    public TableColumn<Player, String> colUmurPlayer;
    public TableColumn<Player, String> colKemampuanPlayer;
    public Button delBtn;

    private ObservableList<Player> players;
    private ObservableList<Hutang> hutangs;
    private Player selectedItem;

    public void addBtnClicked(ActionEvent event) {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SquidApplication.class.getResource("ModalPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StageModalController SMCtrl = loader.getController();
        SMCtrl.setController(this);

        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.show();
    }

    public void editBtnClicked(ActionEvent event) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SquidApplication.class.getResource("ModalPage.fxml"));
        Parent root = loader.load();
        StageModalController SMCtrl = loader.getController();
        SMCtrl.txtId.setDisable(true);
        SMCtrl.setController(this);

        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.show();
    }

    public void delBtnClicked(ActionEvent event) {
        Player selected;
        selected = tablePemain.getSelectionModel().getSelectedItem();

        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.delData(selected);
        players.clear();
        players.addAll(playerDAO.showData());
    }

    public void hutangBtnClicked(ActionEvent event) {
    }

    public void delHutangClicked(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delBtn.setDisable(true);

        // menampilkan data ke table pemain
        PlayerDAO playerDAO = new PlayerDAO();
        players = (ObservableList<Player>) playerDAO.showData();
        tablePemain.setItems(players);
        colIdPlayer.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colNamaPlayer.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNama()));
        colUmurPlayer.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getUmur())));
        colKemampuanPlayer.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getKeahlian()));
        // memasukkan nama para plater ke combo box
        cmbPeserta.setItems(players);

    }

    public void cellSelected(MouseEvent mouseEvent) {
        selectedItem = tablePemain.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            delBtn.setDisable(false);
            // menampilkan data ke table hutang
            HutangDAO hutangDAO = new HutangDAO();
            hutangs = (ObservableList<Hutang>) hutangDAO.showData();
            tableHutang.setItems(hutangs);
            colId.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
            hutangTo.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPemberiUtang()));
            jumlah.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getJumlah())));
        }
    }
}
