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
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.DepositBO;
import lk.ijse.gdse.service.custom.SavingAccountBO;
import lk.ijse.gdse.service.custom.exception.NotFoundException;
import lk.ijse.gdse.service.custom.imple.DepositBOImple;
import lk.ijse.gdse.service.custom.imple.SavingAccountBOImple;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Pattern;

public class DepositToAccountFromController {

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
    private TableView<DepositDTO> tblDeposit;

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



    private final DepositBO depositBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.DEPOSIT_TRANSACTION);
    private final SavingAccountBO savingAccountBO =ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SAVINGS_ACCOUNT);

    public void initialize() throws SQLException, ClassNotFoundException {

            loadDepositId();

    }

    private void loadDepositId() throws SQLException, ClassNotFoundException {
        lbldepositId.setText(depositBO.generateNextDepositId());
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
                if (!savingAccountBO.searchMember(accNo)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Account number not found  !").show();
                    return;
                }
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean isAdded = depositBO.addDeposit(new DepositDTO(deposit_Id, amount, date, time, accNo));
                if (isAdded) {
                    double saving_balance = savingAccountBO.getMember(accNo).getSaving_Balance();
                    boolean isUpdated = savingAccountBO.addSavingBalance(accNo, amount);
                    if (isUpdated) {
                        lblBalance.setText(String.valueOf(saving_balance + amount));
                        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? ", ok, cancel);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(cancel) == ok) {
                            DBConnection.getInstance().getConnection().commit();
                            new Alert(Alert.AlertType.CONFIRMATION, "Deposit Successfully !").show();
                            generateReport();

                        } else {
                            DBConnection.getInstance().getConnection().rollback();
                        }
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                    }
                }
            } else {
                txtDeposit.setFocusColor(Paint.valueOf("Red"));
                txtDeposit.requestFocus();
            }
        } catch (SQLException | ClassNotFoundException | JRException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    @FXML
    void btnFindOnAction(ActionEvent event){

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
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAccOnKeyReleased(KeyEvent event) throws SQLException, ClassNotFoundException {

        String accNo = depositBO.checkAndGetAccount(txtAccNo.getText());
        if (accNo!=null) {
            ObservableList<DepositDTO> deposits = depositBO.getAllDepositsTM(accNo);
            setCellValueFactory();
            tblDeposit.setItems(deposits);
        } else {
            tblDeposit.getItems().clear();
            tblDeposit.refresh();
        }
  }


    private void setCellValueFactory() {
        tblDepositId.setCellValueFactory(new PropertyValueFactory("deposit_Id"));
        tblDepositAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
        tblAccountNo.setCellValueFactory(new PropertyValueFactory("saving_Account_No"));
    }


    private void generateReport() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign= JRXmlLoader.load("D:\\GDSE\\MyfinalProject1\\BankManagementSystemLayered\\src\\lk\\ijse\\gdse\\report\\DepositReport1.jrxml");
        String query="SELECT bankmanagementsystem.saving_account.`saving_Account_No`, bankmanagementsystem.deposit_transactions.`deposit_Id`, bankmanagementsystem.deposit_transactions.amount, bankmanagementsystem.deposit_transactions.date, bankmanagementsystem.deposit_transactions.time, bankmanagementsystem.saving_account.`saving_Balance` FROM bankmanagementsystem.deposit_transactions INNER JOIN bankmanagementsystem.saving_account ON bankmanagementsystem.deposit_transactions.`saving_Account_No` = bankmanagementsystem.saving_account.`saving_Account_No` ORDER BY bankmanagementsystem.deposit_transactions.`deposit_Id` DESC LIMIT 1";

        JRDesignQuery updateQuery=new JRDesignQuery();
        updateQuery.setText(query);

        jasperDesign.setQuery(updateQuery);

        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,DBConnection.getInstance().getConnection());


        JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
        jasperViewer.setVisible(true);

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
