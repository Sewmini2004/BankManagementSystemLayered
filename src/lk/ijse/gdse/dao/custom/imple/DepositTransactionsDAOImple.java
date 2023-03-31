package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.DepositTransactionsDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.ChildDeposit;
import lk.ijse.gdse.entity.Deposit;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositTransactionsDAOImple implements DepositTransactionsDAO {

    @Override
    public ArrayList<Deposit> getSearchBySavingsAccountNb(String savingsAccountNo) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select * from deposit_transactions",savingsAccountNo);
        ArrayList<Deposit>  deposits=new ArrayList<>();

        while (rst.next()){
            deposits.add(new Deposit(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return deposits;
    }



    @Override
    public ArrayList<Deposit> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select * from deposit_transactions");
        ArrayList<Deposit>  deposits=new ArrayList<>();

        while (rst.next()){
            deposits.add(new Deposit(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return deposits;
    }

    @Override
    public boolean add(Deposit deposit) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "INSERT INTO deposit_transactions VALUES(?,?,?,?,?)",
                deposit.getDeposit_Id(),
                deposit.getAmount(),
                deposit.getDate(),
                deposit.getTime(),
                deposit.getSaving_Account_No()
        );
    }
    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Deposit obj) {
        return false;
    }

    @Override
    public Deposit search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM deposit_transactions WHERE deposit_Id=?", id);
    if (rst.next()){
        return new Deposit(
                rst.getString(1),
                rst.getDouble(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5)
        );

    }

    return null;

    }

    @Override
    public boolean isExists(String depositId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM deposit_transactions WHERE depositId=?", depositId);
        return resultSet.next();
    }

    @Override
    public ArrayList<Deposit> searchByDate(Date date) {
        return null;
    }


    public List<Deposit> getAllDepositsTM(String accNo) throws SQLException, ClassNotFoundException {
        List<Deposit> ob= new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery(
                "select * from  deposit_transactions where saving_Account_No = ?",
                accNo
        );
        while (rst.next()){
            ob.add(
                    new Deposit(
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
    public String checkAndGetAccount(String AccNo) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select saving_Account_No from deposit_transactions where saving_Account_No = ?",AccNo);
        return rst.next() ? rst.getString(1) : null;
    }


}
