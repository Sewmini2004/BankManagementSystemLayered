package lk.ijse.gdse.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse.dto.LoanDepositDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanDepositBO extends SuperBO {
    public ArrayList<LoanDepositDTO> searchByLoanId(String loanId) throws SQLException, ClassNotFoundException;
    public ArrayList<LoanDepositDTO> searchByDateLoanDeposit(Date date) throws SQLException, ClassNotFoundException;
    public boolean addLoanDeposit(LoanDepositDTO loanDepositDTO) throws SQLException, ClassNotFoundException;
    public boolean searchLoanDeposit(String depositLoanId) throws SQLException, ClassNotFoundException;
    public ArrayList<LoanDepositDTO> getAllLoanDepositDetailes() throws SQLException, ClassNotFoundException;
    public double getLoanBalanceSearchByPk(String loan_id) throws SQLException, ClassNotFoundException;
    public String generateNextLoanDepositId() throws SQLException, ClassNotFoundException;
    public String checkAndGetLoanId(String loanId) throws SQLException, ClassNotFoundException;
    public ObservableList<LoanDepositDTO> getAllDepositsLoanTM(String loanId) throws SQLException, ClassNotFoundException;
}
