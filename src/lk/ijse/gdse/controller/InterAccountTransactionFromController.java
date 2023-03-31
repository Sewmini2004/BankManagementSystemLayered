package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.InterAccountTransactionDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.InterAccountTransactionBO;
import lk.ijse.gdse.service.custom.SavingAccountBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Pattern;

public class InterAccountTransactionFromController {

    public JFXButton btnClear;
    @FXML
    private JFXButton btnTransfer;

    @FXML
    private JFXTextField txtAccount01;

    @FXML
    private JFXTextField txtAccount02;

    @FXML
    private JFXButton btnCancel1;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblTransactionId;

    @FXML
    private JFXButton btnFind;

    @FXML
    private JFXTextField txtAccNo;

    @FXML
    private JFXTextField txtAmount;
    private final InterAccountTransactionBO interAccountTransactionBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.INTER_ACCOUNT_TRANSACTION);
    private final SavingAccountBO savingAccountBO =ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SAVINGS_ACCOUNT);


    public void initialize() throws SQLException, ClassNotFoundException {
        loadTranactionId();
    }

    private void loadTranactionId() throws SQLException, ClassNotFoundException {
        lblTransactionId.setText(interAccountTransactionBO.generateNextTranactionId());
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtAmount.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnFindOnAction(ActionEvent event) throws SQLException {
        try {
            String accNo = txtAccNo.getText();
            double saving_balance = savingAccountBO.getMember(accNo).getSaving_Balance();
            boolean isAvailable = savingAccountBO.searchMember(accNo);

            if (isAvailable) {
                lblBalance.setText(String.valueOf(saving_balance));
                lblDate.setText(LocalDate.now().toString());
                lblTime.setText(LocalTime.now().toString());
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException e){
            new Alert(Alert.AlertType.CONFIRMATION, "Account number not found  !").show();
        }
    }

    @FXML
    void btnTranferOnAction(ActionEvent event) throws SQLException {
        String transaction_Id = lblTransactionId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();
        String account_01=txtAccount01.getText();
        String account_02= txtAccount02.getText();

        boolean isAmountMatched = Pattern.compile("^(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))$").matcher(txtAmount.getText()).matches();

        if (isAmountMatched) {

                    try{
                        double accountBalance = savingAccountBO.getAccountBalance(account_01);
                        if (accountBalance<Double.parseDouble(txtAmount.getText())){
                            new Alert(Alert.AlertType.WARNING,"Account balance is not enough !").show();
                            txtAccount01.clear();
                            lblTime.setText(LocalTime.now().toString());
                            lblDate.setText(LocalDate.now().toString());
                            return;
                        }
                        DBConnection.getInstance().getConnection().setAutoCommit(false);
                        boolean isAdded = interAccountTransactionBO.addInterAccTransaction(new InterAccountTransactionDTO(transaction_Id, amount, date, time, account_01, account_02));
                        if (isAdded) {

                            double saving_balance = savingAccountBO.getMember(account_01).getSaving_Balance();
                            boolean isUpdated = savingAccountBO.minusBalanceSavingBalance(account_01,amount);

                            if (isUpdated) {
                               boolean isDeposited = savingAccountBO.addSavingBalance(account_02,amount);
                                if(isDeposited){
                                    lblBalance.setText(String.valueOf(saving_balance - amount));
                                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.orElse(no) == ok) {
                                        DBConnection.getInstance().getConnection().commit();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Transfer is  Success !").show();
                                        initialize();
                                    } else {
                                        DBConnection.getInstance().getConnection().rollback();
                                    }

                                }else {
                                    DBConnection.getInstance().getConnection().rollback();
                                    new Alert(Alert.AlertType.ERROR, "  Deposit Failed !");
                                }

                            }else {
                                DBConnection.getInstance().getConnection().rollback();
                                new Alert(Alert.AlertType.ERROR, "withdraw Failed !");
                            }
                        } else {
                            DBConnection.getInstance().getConnection().rollback();
                            new Alert(Alert.AlertType.ERROR, "withdraw Failed !");
                        }
                    }catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();

                    } finally {
                        DBConnection.getInstance().getConnection().setAutoCommit(true);
                    }

           } else {
            txtAmount.setFocusColor(Paint.valueOf("Red"));
            txtAmount.requestFocus();
        }

    }

    public void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lblTransactionId.setText(null);
        txtAmount.clear();
        lblDate.setText(null);
        lblBalance.setText(null);
        lblTime.setText(null);
        txtAccount01.clear();
        txtAccount02.clear();
        txtAccNo.clear();
        initialize();
    }
}
