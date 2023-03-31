package lk.ijse.gdse.service.custom.imple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.DepositLoanAmountDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.dto.LoanDepositDTO;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.entity.LoanDeposit;
import lk.ijse.gdse.service.custom.LoanDepositBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanDepositBOImple implements LoanDepositBO {

    DepositLoanAmountDAO depositLoanAmountDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.DEPOSIT_LOAN_AMOUNT, DBConnection.getInstance().getConnection());

    @Override
    public ArrayList<LoanDepositDTO> searchByLoanId(String loanId) throws SQLException, ClassNotFoundException {
        return getAllLoanDepositDetailes().stream().filter(loanDepositDTO -> loanDepositDTO.getDeposit_loan_Id().equals(loanId)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public ArrayList<LoanDepositDTO> searchByDateLoanDeposit(Date date) throws SQLException, ClassNotFoundException {
        return getAllLoanDepositDetailes().stream().filter(loanDepositDTO-> loanDepositDTO.getDate().equals(date)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public boolean addLoanDeposit(LoanDepositDTO loanDepositDTO) throws SQLException, ClassNotFoundException {
       return depositLoanAmountDAO.add(Convert.toLoanDeposit(loanDepositDTO));
    }

    @Override
    public boolean searchLoanDeposit(String depositLoanId) throws SQLException, ClassNotFoundException {
       LoanDeposit loanDeposit =depositLoanAmountDAO.search(depositLoanId);
        return loanDeposit!=null;
    }

    @Override
    public ArrayList<LoanDepositDTO> getAllLoanDepositDetailes() throws SQLException, ClassNotFoundException {
        List<LoanDeposit> all = depositLoanAmountDAO.getAll();
        return all.stream().map(Convert::fromLoanDeposit).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public double getLoanBalanceSearchByPk(String loan_id) throws SQLException, ClassNotFoundException {
     return   depositLoanAmountDAO.getLoanBalanceSearchByPk(loan_id);
    }

    @Override
    public String generateNextLoanDepositId() throws SQLException, ClassNotFoundException {
        ArrayList<LoanDepositDTO> allDepositDetailes =getAllLoanDepositDetailes();
        if (allDepositDetailes.size()>0) {
            return String.format("LD%08d", Integer.parseInt( allDepositDetailes.get(allDepositDetailes.size() - 1).getDeposit_loan_Id().split("[LD]")[2])+1 );
        }else {
            return "LD00000001";
        }
    }

    @Override
    public String checkAndGetLoanId(String loanId) throws SQLException, ClassNotFoundException {
        return depositLoanAmountDAO.checkAndGetLoanId(loanId);
    }

    @Override
    public ObservableList<LoanDepositDTO> getAllDepositsLoanTM(String loanId) throws SQLException, ClassNotFoundException {
        List<LoanDeposit> allDepositsLoanTM = depositLoanAmountDAO.getAllDepositLoanTM(loanId);
        ObservableList<LoanDepositDTO> ob= FXCollections.observableArrayList();
        allDepositsLoanTM.forEach(loanDeposit-> ob.add(Convert.fromLoanDeposit(loanDeposit)));

        return ob;
    }
}
