package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.entity.LoanDeposit;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.LoanDepositBO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewCustomerDetailsController {

    @FXML
    private TableView<LoanDeposit> tblLoanDeposit;

    @FXML
    private TableColumn<?, ?> coldepositloanId;

    @FXML
    private TableColumn<?, ?> coldepositLoanamount;

    @FXML
    private TableColumn<?, ?> colloanId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    LoanDepositBO loanDepositBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.DEPOSIT_LOAN_AMOUNT);
    public void initialize() throws SQLException, ClassNotFoundException {
        tblLoanDeposit.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("deposit_loan_Id"));
       tblLoanDeposit.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("deposit_Loan_amount"));
       tblLoanDeposit.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("loan_Id"));
        tblLoanDeposit.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblLoanDeposit.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("time"));

        List<LoanDeposit> loanDepositList= loanDepositBO.getAllLoanDepositDetailes().stream().map(loanDeposit -> new LoanDeposit(
                loanDeposit.getDeposit_loan_Id(),
                loanDeposit.getDeposit_Loan_amount(),
                loanDeposit.getLoan_Id(),
                loanDeposit.getDate(),
                loanDeposit.getTime()

        )).collect(Collectors.toList());
           tblLoanDeposit.setItems(FXCollections.observableArrayList(loanDepositList));

    }


}
