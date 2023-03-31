package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.ChildDepositDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.ChildAccountBO;
import lk.ijse.gdse.service.custom.ChildDepositBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Pattern;

public class DepositChildController {

    public JFXButton btnClear;
    @FXML
    private JFXButton btnFind;

    @FXML
    private JFXTextField txtAccNo;

    @FXML
    private JFXTextField txtDeposit;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lbldepositId;

    @FXML
    private JFXButton btnDeposit;

    @FXML
    private JFXButton btnCancel1;

    @FXML
    private JFXButton btnUpdate1;

    @FXML
    private JFXButton btnDelete1;

    @FXML
    private TableView<ChildDepositDTO> tblDeposit;

    @FXML
    private TableColumn<?, ?> tblDepositId;

    @FXML
    private TableColumn<?, ?> tblAccountNo;

    @FXML
    private TableColumn<?, ?> tblDepositAmount;

    @FXML
    private TableColumn<?, ?> tblDate;

    @FXML
    private TableColumn<?, ?> tblTime;

    ChildAccountBO childAccountBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.CHILD_ACCOUNT);
    ChildDepositBO childDepositBO=ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.DEPOSIT_CHILD);

    public void initialize() throws SQLException, ClassNotFoundException {

        loadDepositId();

    }

    private void loadDepositId() throws SQLException, ClassNotFoundException {
        lbldepositId.setText(childDepositBO.generateNextChildDepositId());
    }




    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtDeposit.getScene().getWindow();
        stage.close();
    }



    @FXML
    void btnDepositlOnAction(ActionEvent event) throws SQLException {
        String deposit_Id = lbldepositId.getText();
        double amount = Double.parseDouble(txtDeposit.getText());
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();
        String accNo = txtAccNo.getText();

        try {
            boolean isAmountMatched = Pattern.compile("^(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))$").matcher(txtDeposit.getText()).matches();
            if (isAmountMatched) {
                if (!childAccountBO.searchChild(accNo)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Account number not found  !").show();
                    return;
                }
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean isAdded = childDepositBO.addChildDeposit(new ChildDepositDTO(deposit_Id, amount, date, time, accNo));
                if (isAdded) {
                    double saving_balance = childAccountBO.getDepositByChildAccountNb(accNo).getBalance();
                    //
                    boolean isUpdated = childAccountBO.addSavingBalance(accNo, amount);
                    if (isUpdated) {
                        lblBalance.setText(String.valueOf(saving_balance + amount));
                        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? ", ok, cancel);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(cancel) == ok) {
                            DBConnection.getInstance().getConnection().commit();
                            new Alert(Alert.AlertType.CONFIRMATION, "Deposit Successfully !").show();
                        } else {
                            DBConnection.getInstance().getConnection().rollback();
                        }
                        //ko manika dn kyla dunnu ewa hri hri inno mn baluwe eek krl iwr krd kiyl hri ek exeprion eken blnna
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                    }
                }
            } else {
                txtDeposit.setFocusColor(Paint.valueOf("Red"));
                txtDeposit.requestFocus();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    @FXML
    void btnFindOnAction(ActionEvent event) {
        try {
            String accNo = txtAccNo.getText();
            double balance = childAccountBO.searchChildByAccNo(accNo).getBalance();
            boolean isAvailable = childAccountBO.searchChild(accNo);

            if (isAvailable) {
                lblBalance.setText(String.valueOf(balance));
                lblDate.setText(LocalDate.now().toString());
                lblTime.setText(LocalTime.now().toString());
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException | IndexOutOfBoundsException e){
            new Alert(Alert.AlertType.CONFIRMATION, "Account number not found  !").show();
        }
    }


    private void setCellValueFactory() {
        tblDepositId.setCellValueFactory(new PropertyValueFactory("deposit_Id"));
        tblDepositAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        tblAccountNo.setCellValueFactory(new PropertyValueFactory("child_Account_No"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
    }
    @FXML
    void txtAccOnKeyReleased(KeyEvent event) throws SQLException, ClassNotFoundException {

        String accNo = childDepositBO.checkAndGetAccount(txtAccNo.getText());
        if (accNo!=null) {
            ObservableList<ChildDepositDTO> deposits = childDepositBO.getDeposits(accNo);
            setCellValueFactory();
            tblDeposit.setItems(deposits);
        } else {
            tblDeposit.getItems().clear();
            tblDeposit.refresh();
        }



    }
    
    public void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        txtAccNo.clear();
        txtDeposit.clear();
        lbldepositId.setText(null);
         lblBalance.setText(null);
        lblDate.setText(null);
        lblTime.setText(null);
        initialize();
    }
}
