package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.DepositLoanAmountDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.entity.InterAccountTransaction;
import lk.ijse.gdse.entity.LoanDeposit;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositLoanAmountDAOImple implements DepositLoanAmountDAO {



    @Override
    public ArrayList<LoanDeposit> getAllSearchByLoanId(String loanId) throws SQLException, ClassNotFoundException {
        ArrayList<LoanDeposit> ob=new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery(
                "select * from deposit_loan_amount where loan_Id = ?",
                loanId
        );
        while (rst.next()){
            ob.add(
                    new LoanDeposit(
                            rst.getString(1),
                            rst.getDouble(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5)
                    )
            );
        }
        return ob;
    }

    @Override
    public boolean updateLoanBalances(String loan_id, double deposit_loan_amount) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "UPDATE loan SET loan_Balance = loan_Balance - ? WHERE loan_Id= ?",
                deposit_loan_amount,
                loan_id
        );
    }

    @Override
    public double getLoanBalanceSearchByPk(String loan_id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery(
                "select loan_Balance from loan where loan_Id = ?",
                loan_id
        );
        return rst.next() ? rst.getDouble(1): -1;
    }

    @Override
    public double getLoanBalanceSearchBySavingAccNo(String saving_Account_No) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery(
                "select loan_Balance from loan where saving_Account_No = ?",
                saving_Account_No
        );
        return rst.next() ? rst.getDouble(1): -1;
    }

    @Override
    public String checkAndGetLoanId(String loanId) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select loan_Id from deposit_loan_amount where  loan_Id= ?",loanId);
        return rst.next() ? rst.getString(1) : null;
    }


    @Override
    public List<LoanDeposit> getAllDepositLoanTM(String loanId) throws SQLException, ClassNotFoundException {
        List<LoanDeposit> ob= new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery(
                "select * from deposit_loan_amount where loan_Id = ?",loanId

        );
        while (rst.next()){
            ob.add(
                    new LoanDeposit(
                            rst.getString(1),
                            rst.getDouble(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5)
                    )
            );
        }
        return ob;
    }


    @Override
    public ArrayList<LoanDeposit> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select * from deposit_loan_amount");
        ArrayList<LoanDeposit>  loanDeposits=new ArrayList<>();
        while (rst.next()){
            loanDeposits.add(new LoanDeposit(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
                    )
            );
        }
        return loanDeposits;
    }

    @Override
    public boolean add(LoanDeposit deposit) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "INSERT INTO deposit_loan_amount VALUES(?,?,?,?,?)",
                deposit.getDeposit_loan_Id(),
                deposit.getDeposit_Loan_amount(),
                deposit.getLoan_Id(),
                deposit.getDate(),
                deposit.getTime()

        );
    }


    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(LoanDeposit obj) {
        return false;
    }

    @Override
    public LoanDeposit search(String id) {
        return null;
    }

    @Override
    public boolean isExists(String id) {
        return false;
    }

    @Override
    public ArrayList<LoanDeposit> searchByDate(Date date) {
        return null;
    }
}
