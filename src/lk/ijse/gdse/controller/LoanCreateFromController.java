package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.LoanDTO;
import lk.ijse.gdse.entity.Loan;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.LoanBO;
import lk.ijse.gdse.service.custom.SavingAccountBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LoanCreateFromController {

    public JFXButton btnClear;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel1;

    @FXML
    private Label lblLoanId;

    @FXML
    private Label lblInstallmentAmount;


    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXTextField txtAccNo;

    @FXML
    private JFXTextField txtInterest;

    @FXML
    private JFXComboBox<String> cmbLoanValue;

    @FXML
    private Label lblLoanBalance;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<Loan> tblLoan;

    @FXML
    private TableColumn<?, ?> colLoanId;

    @FXML
    private TableColumn<?, ?> colInstAmount;

    @FXML
    private TableColumn<?, ?> colNoInst;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colSavingNo;

    @FXML
    private TableColumn<?, ?> colValue;

    @FXML
    private TableColumn<?, ?> colInterest;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colTotal;

    ObservableList<String> observableList2 = FXCollections.observableArrayList("50000","100000","150000","200000","250000","300000","350000","400000","500000");

    public JFXComboBox <String> CmbNoOfInstallment;
    ObservableList<String> observableList = FXCollections.observableArrayList("12","24","36","48","60");
    private final SavingAccountBO savingAccountBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SAVINGS_ACCOUNT);
    public final LoanBO loanBO=ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.LOAN);

    public void initialize() throws SQLException, ClassNotFoundException {
        loadLoanId();
        lblLoanBalance.setText("0.00");
        CmbNoOfInstallment.setItems(observableList);
        cmbLoanValue.setItems(observableList2);
        lblDate.setText(LocalDate.now().toString());
        setInterest();


        colLoanId.setCellValueFactory(new PropertyValueFactory<>("loan_Id"));
        colInstAmount.setCellValueFactory(new PropertyValueFactory<>("installment_Amount"));
        colNoInst.setCellValueFactory(new PropertyValueFactory<>("no_Of_Installment"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_Of_Loan"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSavingNo.setCellValueFactory(new PropertyValueFactory<>("saving_Account_No"));
        colInterest.setCellValueFactory(new PropertyValueFactory<>("interest"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("loan_Value"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("loan_Balance"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total_Amount"));


        List<Loan> loanList= loanBO.getAllLoanDetailes().stream().map(loan -> new Loan(
                loan.getLoan_Id(),
                loan.getInstallment_Amount(),
                loan.getNo_Of_Installment(),
                loan.getDate_Of_Loan(),
                loan.getTime(),
                loan.getSaving_Account_No(),
                loan.getInterest(),
                loan.getLoan_Value(),
                loan.getLoan_Balance(),
                loan.getTotal_Amount()

        )).collect(Collectors.toList());
        tblLoan.setItems(FXCollections.observableArrayList(loanList));


    }

    private void loadLoanId() throws SQLException, ClassNotFoundException {
        lblLoanId.setText(loanBO.generateNextLoanId());

    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        String loan_Id = lblLoanId.getText();
        int no_Of_Installment = Integer.parseInt(CmbNoOfInstallment.getValue());
        String date_Of_Loan = LocalDate.now().toString();
        String time = LocalTime.now().toString();
        String saving_Account_No = txtAccNo.getText();
        double interest = Double.parseDouble(txtInterest.getText());
        double loan_Value = Double.parseDouble(cmbLoanValue.getValue());
        double total_Amount = (((loan_Value * (interest+100.00)) / 100.00));
        double loan_Balance = total_Amount;
        lblTotal.setText(String.valueOf(total_Amount));
        double installment_Amount = total_Amount / no_Of_Installment;
        String PayMonth = String.format("%.2f", installment_Amount);
        lblInstallmentAmount.setText(PayMonth);
        lblLoanBalance.setText(String.valueOf(loan_Balance));

        boolean isAccountIdMatched = Pattern.compile("^(A)([0-9]){1,8}$").matcher(txtAccNo.getText()).matches();
        if(isAccountIdMatched){

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);


            boolean isAvailable = savingAccountBO.searchMember(saving_Account_No);
           if(isAvailable){
               boolean isAdded = loanBO.addLoan(new LoanDTO(
                       loan_Id,
                       installment_Amount,
                       no_Of_Installment,
                       date_Of_Loan,
                       time,
                       saving_Account_No,
                       interest,
                       loan_Value,
                       loan_Balance,
                       total_Amount
               ));
          if (isAdded){
              ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
              ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
              Optional<ButtonType> result = alert.showAndWait();
              if (result.orElse(no) == ok) {
                  DBConnection.getInstance().getConnection().commit();
                  new Alert(Alert.AlertType.CONFIRMATION, "Loan Added Success !").show();
              } else {
                  DBConnection.getInstance().getConnection().rollback();
              }
          } else {
              DBConnection.getInstance().getConnection().rollback();
          }
          }else{
               new Alert(Alert.AlertType.CONFIRMATION, "Account number is not found !").show();
           }

           } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
        }else{
            txtAccNo.setFocusColor(Paint.valueOf("Red"));
            txtAccNo.requestFocus();
        }


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage)  btnCancel1.getScene().getWindow();
        stage.close();

    }

    @FXML
    void btnChangeOnAction(ActionEvent event) {
        txtInterest.setDisable(false);
        txtInterest.requestFocus();
    }

    @FXML
    void cmbLoanValueOnAction(ActionEvent event) {

    }



    private void setInterest() {
         try {
            if (loanBO.getInterest()!=null) {
                txtInterest.setDisable(true);
                txtInterest.setText(String.valueOf(loanBO.getInterest()));
            } else {
                txtInterest.setDisable(false);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    void txtAccNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtInterestOnAction(ActionEvent event) {
        txtInterest.setDisable(true);
    }

    public void btnClearOnAction(ActionEvent event) {

    }
}
