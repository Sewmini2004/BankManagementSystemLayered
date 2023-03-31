package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.LoanDeposit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DepositLoanAmountDAO extends CrudDAO<LoanDeposit>, SearchByDAO<LoanDeposit> {
    public ArrayList<LoanDeposit> getAllSearchByLoanId(String loanId) throws SQLException, ClassNotFoundException;
    public  boolean updateLoanBalances(String loan_id, double deposit_loan_amount) throws SQLException, ClassNotFoundException;
    public  double getLoanBalanceSearchByPk(String loan_id) throws SQLException, ClassNotFoundException;
    public  double getLoanBalanceSearchBySavingAccNo(String saving_Account_No)throws SQLException, ClassNotFoundException;
    public String checkAndGetLoanId(String loanId) throws SQLException, ClassNotFoundException;
    public List<LoanDeposit> getAllDepositLoanTM(String loanId) throws SQLException, ClassNotFoundException;
}
