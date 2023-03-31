package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.entity.LoanDeposit;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.DepositBO;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ViewDepositFromController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Deposit> tblDeposit;

    @FXML
    private TableColumn<?, ?> colDepositId;

    @FXML
    private TableColumn<?, ?> colAccountNo;

    @FXML
    private TableColumn<?, ?> colDepositAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    DepositBO depositBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.DEPOSIT_TRANSACTION);


    public void initialize() throws SQLException, ClassNotFoundException {

        tblDeposit.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("deposit_Id"));
        tblDeposit.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("saving_Account_No"));
        tblDeposit.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblDeposit.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblDeposit.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("time"));

         List<Deposit> depositList= depositBO.getAllDepositDetailes().stream().map(deposit -> new Deposit(
                 deposit.getDeposit_Id(),
                 deposit.getAmount(),
                 deposit.getDate(),
                 deposit.getTime(),
                 deposit.getSaving_Account_No()


         )).collect(Collectors.toList());
        tblDeposit.setItems(FXCollections.observableArrayList(depositList));

    }


}
