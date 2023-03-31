package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.LoanDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Loan;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanDAOImple implements LoanDAO {

    @Override
    public List<Loan> getAll() throws SQLException, ClassNotFoundException {
        ResultSet execute = DBUtil.executeQuery("select * from loan");
        List<Loan> loanList=new ArrayList<>();
        while (execute.next()){
             loanList.add(new Loan(
                             execute.getString(1),
                             execute.getDouble(2),
                             execute.getDouble(3),
                             execute.getString(4),
                             execute.getString(5),
                             execute.getString(6),
                             execute.getDouble(7),
                             execute.getDouble(8),
                             execute.getDouble(9),
                             execute.getDouble(10)




                     )

             );
        }
  return loanList;
    }

    @Override
    public boolean add(Loan loan) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("INSERT INTO loan VALUES (?,?,?,?,?,?,?,?,?,?)",
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

        );
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Loan loan) throws SQLException, ClassNotFoundException {
       boolean b = DBUtil.executeUpdate("UPDATE loan SET loan_Id=?,installment_Amount=?,no_Of_Installment=?," +
                                            " date_Of_Loan=?,time=?,interest=?,loan_Value=?," +
                                            "loan_Balance=?,total_Amount=? WHERE saving_Account_No",
        loan.getLoan_Id(),
                loan.getInstallment_Amount(),
                loan.getNo_Of_Installment(),
                loan.getDate_Of_Loan(),
                loan.getTime(),
                loan.getInterest(),
                loan.getLoan_Value(),
                loan.getLoan_Balance(),
                loan.getTotal_Amount(),
                loan.getSaving_Account_No()

        );

   return  b;

    }

    @Override
    public Loan search(String loan_Id) throws SQLException, ClassNotFoundException {

        ResultSet result = DBUtil.executeQuery("SELECT * FROM  loan WHERE loan_Id = ?",loan_Id);

        if (result.next()) {
            return new Loan(
                    result.getString(1),
                    result.getDouble(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getDouble(7),
                    result.getDouble(8),
                    result.getDouble(9),
                    result.getDouble(10)


            );
        }
        return null;
    }

    @Override
    public boolean isExists(String loan_Id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM loan WHERE loan =?",loan_Id);
        return resultSet.next();
    }

    @Override
    public boolean loanStatus(String loanId) {
        return false;
    }

    @Override
    public int RemainingInstallments(String loanId) {
        return 0;
    }

    @Override
    public boolean updateLoanBalance(String loanId,Double amount) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "UPDATE loan SET loan_Balance = ? WHERE loan_Id= ?",
                amount,
                loanId
        );
    }


    @Override
    public ArrayList<Loan> SearchLoanByAccountId(String accountId) {
        return null;
    }

    @Override
    public ArrayList<Loan> SearchLoanByLoanValue(double loanValue) {
        return null;
    }

    @Override
    public double getInterestByPk(String loanId) {
        return 0;
    }

    @Override
    public String getInterest() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select interest from loan order by loan_Id desc limit 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Loan> searchByDate(Date date) {
        return null;
    }
}
