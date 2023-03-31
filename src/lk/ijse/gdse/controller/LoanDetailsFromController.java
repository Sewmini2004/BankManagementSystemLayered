package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.entity.Loan;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.LoanBO;
import lk.ijse.gdse.view.tm.WorkersDetails;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class LoanDetailsFromController {

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
  LoanBO loanBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.LOAN);

    public void initialize() throws SQLException, ClassNotFoundException {



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
}
