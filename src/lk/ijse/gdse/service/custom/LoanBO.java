package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.LoanDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBO extends SuperBO {
    public boolean loanStatus(String loanId);
    public int RemainingInstallments(String loanId);
    public boolean addLoanBalance(String loanId,Double amount) throws SQLException, ClassNotFoundException;
    public boolean minusLoanBalance(String loanId,Double amount) throws SQLException, ClassNotFoundException;
    public ArrayList<LoanDTO> SearchLoanByAccountId(String AccNo) throws SQLException, ClassNotFoundException;
    public ArrayList<LoanDTO> SearchLoanByLoanValue(double loanValue);
    public double getInterestByPk(String loanId) throws SQLException, ClassNotFoundException;
    public boolean addLoan(LoanDTO loanDTO) throws SQLException, ClassNotFoundException;
    public boolean searchLoanPk(String loanId) throws SQLException, ClassNotFoundException;
    public ArrayList<LoanDTO> getAllLoanDetailes() throws SQLException, ClassNotFoundException;
    public String generateNextLoanId() throws SQLException, ClassNotFoundException;
    public String getInterest() throws SQLException, ClassNotFoundException;


    }
