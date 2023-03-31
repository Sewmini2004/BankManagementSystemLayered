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
import lk.ijse.gdse.dto.WithdrowDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.SavingAccountBO;
import lk.ijse.gdse.service.custom.WithdrowBO;
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

public class WithdrowFromAccountFormController {

    public Label lblWithdrawId;
    public JFXButton btnClear;
    @FXML

    private JFXButton btnFind;

    @FXML
    private JFXTextField txtAccNo;

    @FXML
    private Label lblBalance;

    @FXML
    private JFXTextField txtWithdraw;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXButton btnWithdrew;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<WithdrowDTO> tblWithdraw;

    @FXML
    private TableColumn<?, ?> tblWithdrawId;

    @FXML
    private TableColumn<?, ?> tblAccountNo;

    @FXML
    private TableColumn<?, ?> tblDepositAmount;

    @FXML
    private TableColumn<?, ?> tblDate;

    @FXML
    private TableColumn<?, ?> tblTime;

    private final WithdrowBO withdrowBO=ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.WITHDRAWAL_MEMBER);
    private final SavingAccountBO savingAccountBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SAVINGS_ACCOUNT);
    public void initialize() throws SQLException, ClassNotFoundException {
        loadWithdrawId();
    }

    private void loadWithdrawId() throws SQLException, ClassNotFoundException {
        lblWithdrawId.setText(withdrowBO.generateNextWithdrwaId());
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtWithdraw.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnFindOnAction(ActionEvent event) {
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
    void btnWithdrowOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String withdrawId = lblWithdrawId.getText();
        double amount = Double.parseDouble(txtWithdraw.getText());
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();
        String accNo = txtAccNo.getText();

        boolean isAmountMatched = Pattern.compile("^(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))$").matcher(txtWithdraw.getText()).matches();
        if (isAmountMatched) {
            double accountBalance = savingAccountBO.getAccountBalance(accNo);
            if(accountBalance<Double.parseDouble(txtWithdraw.getText())){
           new Alert(Alert.AlertType.WARNING, "Account number not found !").show();
                return;
            }
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdded = withdrowBO.addWithdrow(new WithdrowDTO(withdrawId, amount, date, time, accNo));
            if (isAdded) {
                double saving_balance = savingAccountBO.getMember(accNo).getSaving_Balance();
                boolean isUpdated = false;
                try {
                    isUpdated = savingAccountBO.minusBalanceSavingBalance(accNo, amount);

                    if (isUpdated) {
                        lblBalance.setText(String.valueOf(saving_balance - amount));
                        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? ", ok, cancel);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(cancel) == ok) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Withdraw Successfully !").show();
                            DBConnection.getInstance().getConnection().commit();
                            generateReport();

                        } else {
                            DBConnection.getInstance().getConnection().rollback();
                        }
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                    }
                } catch (SQLException | ClassNotFoundException | JRException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                txtWithdraw.setFocusColor(Paint.valueOf("Red"));
                txtWithdraw.requestFocus();
            }

        }
    }
    @FXML
    void txtAccOnKeyReleased(KeyEvent event) throws SQLException, ClassNotFoundException {

        String accNo = withdrowBO.checkAndGetAccount(txtAccNo.getText());
        if (accNo!=null) {
            ObservableList<WithdrowDTO> withdrow= withdrowBO.getAllWithdrowTM(accNo);
            setCellValueFactory();
            tblWithdraw.setItems(withdrow);
        } else {
           tblWithdraw.getItems().clear();
            tblWithdraw.refresh();
        }
    }


    private void setCellValueFactory() {
        tblWithdrawId.setCellValueFactory(new PropertyValueFactory("withdrow_Id"));
        tblDepositAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        tblAccountNo.setCellValueFactory(new PropertyValueFactory("saving_Account_No"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));


    }

    private void generateReport() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign= JRXmlLoader.load("D:\\GDSE\\MyfinalProject1\\BankManagementSystemLayered\\src\\lk\\ijse\\gdse\\report\\Withdraw1.jrxml");
        String query="SELECT bankmanagementsystem.withdrawal_member.`withdraw_Id`,bankmanagementsystem.withdrawal_member.amount, bankmanagementsystem.withdrawal_member.date,bankmanagementsystem.withdrawal_member.time, bankmanagementsystem.saving_account.`saving_Account_No`,bankmanagementsystem.saving_account.`saving_Balance` FROM bankmanagementsystem.withdrawal_member INNER JOIN bankmanagementsystem.saving_account ON bankmanagementsystem.withdrawal_member.`saving_Account_No` = bankmanagementsystem.saving_account.`saving_Account_No`ORDER BY bankmanagementsystem.withdrawal_member.`withdraw_Id` DESC LIMIT 1";
        JRDesignQuery updateQuery1=new JRDesignQuery();
        updateQuery1.setText(query);

        jasperDesign.setQuery(updateQuery1);

        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,DBConnection.getInstance().getConnection());

        JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
        jasperViewer.setVisible(true);


    }


    public void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        txtAccNo.clear();
        txtWithdraw.clear();
        lblWithdrawId.setText(null);
        lblBalance.setText(null);
        lblDate.setText(null);
        lblTime.setText(null);
        initialize();
    }
}
