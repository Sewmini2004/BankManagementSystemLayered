package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.LoanDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.LoanDTO;
import lk.ijse.gdse.entity.Loan;
import lk.ijse.gdse.service.custom.LoanBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanBOImple implements LoanBO {
 private final   LoanDAO loanDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.LOAN, DBConnection.getInstance().getConnection());


    @Override
    public boolean loanStatus(String loanId) {
        return false;
    }

    @Override
    public int RemainingInstallments(String loanId) {
        return 0;
    }

    @Override
    public boolean addLoanBalance(String loanId,Double amount) throws SQLException, ClassNotFoundException {
       return loanDAO.updateLoanBalance(loanId, amount +loanDAO.search(loanId).getLoan_Balance());
    }


    @Override
    public boolean minusLoanBalance(String loanId,Double amount) throws SQLException, ClassNotFoundException {

      return   loanDAO.updateLoanBalance(loanId,amount- loanDAO.search(loanId).getLoan_Balance());
    }
    @Override
    public ArrayList<LoanDTO> SearchLoanByAccountId(String AccNo) throws SQLException, ClassNotFoundException {
     return  getAllLoanDetailes().stream().filter(loanDTO -> loanDTO.getSaving_Account_No().equals(AccNo)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public ArrayList<LoanDTO> SearchLoanByLoanValue(double loanValue) {
        return null;
    }

    @Override
    public double getInterestByPk(String loanId) throws SQLException, ClassNotFoundException {

      return  loanDAO.search(loanId).getInterest();

    }
    @Override
    public String getInterest() throws SQLException, ClassNotFoundException {

      return loanDAO.getInterest();

    }

    @Override
    public boolean addLoan(LoanDTO loanDTO) throws SQLException, ClassNotFoundException {
        return loanDAO.add(Convert.toLoan(loanDTO));
    }

    @Override
    public boolean searchLoanPk(String loanId) throws SQLException, ClassNotFoundException {
        Loan search = loanDAO.search(loanId);
        return search!=null;
    }

    @Override
    public ArrayList<LoanDTO> getAllLoanDetailes() throws SQLException, ClassNotFoundException {
        List<Loan> all = loanDAO.getAll();
        return all.stream().map(Convert::fromLoan).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public String generateNextLoanId() throws SQLException, ClassNotFoundException {
        ArrayList<LoanDTO> allMembers = getAllLoanDetailes();
        if (allMembers.size()>0) {
            return String.format("L%08d", Integer.parseInt( allMembers.get(allMembers.size() - 1).getLoan_Id().split("[L]")[1])+1 );
        }else {
            return "L00000001";
        }
    }

}
