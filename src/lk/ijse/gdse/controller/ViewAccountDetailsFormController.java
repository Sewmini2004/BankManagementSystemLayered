package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.SavingAccountBO;
import lk.ijse.gdse.view.tm.SavingAccount;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViewAccountDetailsFormController {

    @FXML
    private TableView<SavingAccount> tblAccount;

    @FXML
    private TableColumn<?, ?> colMember_Id;

    @FXML
    private TableColumn<?, ?> colSavingAcc;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colBday;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colTime;


     SavingAccountBO savingAccountBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SAVINGS_ACCOUNT);

    public void initialize() throws SQLException, ClassNotFoundException {
        tblAccount.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("member_Id"));
        tblAccount.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("saving_Account_No"));
        tblAccount.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAccount.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblAccount.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("saving_Balance"));
        tblAccount.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblAccount.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblAccount.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblAccount.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        tblAccount.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("creat_Date"));
        tblAccount.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("creat_Time"));

        List<SavingAccount> savingAccountList= savingAccountBO.getAllMembers().stream().map(account -> new SavingAccount(
                account.getMember_Id(),
                account.getSaving_Account_No(),
                account.getName(),
                account.getNic(),
                account.getAddress(),
                account.getMobile(),
                account.getEmail(),
                account.getDate_of_birth(),
                account.getCreat_Date(),
                account.getCreat_Time(),
                account.getSaving_Balance()
       )).collect(Collectors.toList());

        tblAccount.setItems(FXCollections.observableArrayList(savingAccountList));


    }




}
