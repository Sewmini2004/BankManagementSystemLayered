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
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.dto.LoanDepositDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.LoanBO;
import lk.ijse.gdse.service.custom.LoanDepositBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Pattern;

public class LoanDepositController {

    @FXML
    private JFXButton btnFind;

    @FXML
    private JFXTextField txtLoanId;

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
    private TableView<LoanDepositDTO> tblDeposit;

    @FXML
    private TableColumn<?, ?> tblDepositId;

    @FXML
    private TableColumn<?, ?> tblDepositAmount;

    @FXML
    private TableColumn<?, ?> tblDate;

    @FXML
    private TableColumn<?, ?> tblTime;

    @FXML
    private TableColumn<?, ?> tblLoanId;

    @FXML
    private Label lblInstallAmount;
   LoanDepositBO loanDepositBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.DEPOSIT_LOAN_AMOUNT);
    LoanBO loanBO=ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.LOAN);

    public void initialize() throws SQLException, ClassNotFoundException {

        loadLoanDepositId();

    }

    private void loadLoanDepositId() throws SQLException, ClassNotFoundException {
        lbldepositId.setText(loanDepositBO.generateNextLoanDepositId());
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtDeposit.getScene().getWindow();
        stage.close();
    }


    @FXML
    void btnDepositlOnAction(ActionEvent event) throws SQLException {
        boolean isAmountMatched = Pattern.compile("^(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))$").matcher(txtDeposit.getText()).matches();
        if (isAmountMatched) {
            try {
            String deposit_loan_Id =lbldepositId.getText();
            double deposit_Loan_amount = Double.parseDouble(txtDeposit.getText());
            String loan_Id=txtLoanId.getText();
            String date = LocalDate.now().toString();
            String time = LocalTime.now().toString();
             DBConnection.getInstance().getConnection().setAutoCommit(false);
                double loanBalance= loanDepositBO.getLoanBalanceSearchByPk(loan_Id);
             if (loanBalance==0){
                 new Alert(Alert.AlertType.WARNING, "Already settled Loan !").show();
                 return;
             }
                boolean isDeposited  = loanDepositBO.addLoanDeposit(new LoanDepositDTO(
                        deposit_loan_Id,
                        deposit_Loan_amount,
                        loan_Id,
                        date,
                        time
                ));
                if(isDeposited){
                    double loan_balance = loanBO.getAllLoanDetailes().get(0).getLoan_Balance();
                    boolean isUpdated = loanBO.minusLoanBalance(loan_Id,deposit_Loan_amount);
                    if (isUpdated) {
                        lblBalance.setText(String.valueOf(loan_balance - deposit_Loan_amount));
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
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                    }
                  } else {
                DBConnection.getInstance().getConnection().rollback();
                new Alert(Alert.AlertType.ERROR, "Deposit Failed !");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }finally {
                DBConnection.getInstance().getConnection().setAutoCommit(true);

            }


        }else {
            txtDeposit.setFocusColor(Paint.valueOf("Red"));
            txtDeposit.requestFocus();
        }
    }

    @FXML
    void btnFindOnAction(ActionEvent event) {

        try {
            String loanId = txtLoanId.getText();
            //methana awlk ekam index eka enne
            double loan_balance = loanBO.getAllLoanDetailes().get(0).getLoan_Balance();
            boolean isAvailable = loanBO.searchLoanPk(loanId);

            if (isAvailable) {
                lblBalance.setText(String.valueOf(loan_balance));
                lblDate.setText(LocalDate.now().toString());
                lblTime.setText(LocalTime.now().toString());
                lblInstallAmount.setText(String.valueOf(loanBO.getAllLoanDetailes().get(0).getInstallment_Amount()));

            }else{
                new Alert(Alert.AlertType.WARNING, "Loan number not found  !").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException e){
            new Alert(Alert.AlertType.WARNING, "Loan number not found  !").show();
        }
    }


    private void setCellValueFactory() {
        tblDepositId.setCellValueFactory(new PropertyValueFactory("deposit_loan_Id"));
        tblDepositAmount.setCellValueFactory(new PropertyValueFactory("deposit_Loan_amount"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
        tblLoanId.setCellValueFactory(new PropertyValueFactory("loan_Id"));
    }

    @FXML
    void txtAccOnKeyReleased(KeyEvent event) throws SQLException, ClassNotFoundException {
        String loanId = loanDepositBO.checkAndGetLoanId(txtLoanId.getText());
        if (loanId!=null) {
            ObservableList<LoanDepositDTO> loanDeposit= loanDepositBO.getAllDepositsLoanTM(loanId);
            setCellValueFactory();
            tblDeposit.setItems(loanDeposit);
        } else {
            tblDeposit.getItems().clear();
            tblDeposit.refresh();
        }
    }

    public void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        txtLoanId.clear();
        txtDeposit.clear();
        lbldepositId.setText(null);
        lblBalance.setText(null);
        lblDate.setText(null);
        lblTime.setText(null);
        lblInstallAmount.setText(null);
        initialize();
    }
}
