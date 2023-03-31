package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.Loan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanDAO extends CrudDAO<Loan>, SearchByDAO<Loan>{

    public boolean loanStatus(String loanId);
     public int RemainingInstallments(String loanId);
    public boolean updateLoanBalance(String loanId,Double amount) throws SQLException, ClassNotFoundException;
    public ArrayList<Loan> SearchLoanByAccountId(String accountId);
    public ArrayList<Loan> SearchLoanByLoanValue(double loanValue);
    public double getInterestByPk(String loanId);
    public String getInterest() throws SQLException, ClassNotFoundException;


}
