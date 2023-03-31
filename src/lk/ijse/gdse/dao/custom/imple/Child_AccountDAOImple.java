package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.Child_AccountDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Child_Account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Child_AccountDAOImple implements Child_AccountDAO {

     private final Connection connection;

    public Child_AccountDAOImple(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Child_Account> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM child_account");
      List<Child_Account> child_account=new ArrayList<>();
      while (result.next()){
                 child_account.add(new Child_Account(

                  result.getString(1),
                  result.getString(2),
                  result.getString(3),
                  result.getString(4),
                  result.getString(5),
                  result.getString(6),
                  result.getString(7),
                  result.getString(8),
                  result.getString(9),
                  result.getDouble(10),
                  result.getString(11)
                 )
                 );
      }
return child_account;
    }

    @Override
    public boolean add(Child_Account childAccount) throws SQLException, ClassNotFoundException {

         return DBUtil.executeUpdate("INSERT INTO child_account VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                   childAccount.getChild_Id(),
                   childAccount.getChild_Account_No(),
                   childAccount.getName(),
                   childAccount.getGender(),
                   childAccount.getAddress(),
                   childAccount.getDate_Of_Birth(),
                   childAccount.getNic(),
                   childAccount.getCreat_Date(),
                   childAccount.getCreat_Time(),
                   childAccount.getBalance(),
                   childAccount.getBranch_No());

    }

    @Override
    public boolean delete(String child_Account_No) throws SQLException, ClassNotFoundException {
       return DBUtil.executeUpdate("DELETE FROM child_account WHERE child_Account_No=?",child_Account_No);


    }

    @Override
    public boolean update(Child_Account childAccount) throws SQLException, ClassNotFoundException {
     return DBUtil.executeUpdate("UPDATE child_account SET child_Id=? ,name=?,gender=?,address=?,date_Of_Birth=?,nic=?,creat_Date=?,creat_Time=?,balance=?,branch_No=?  WHERE  child_Account_No=?",
                childAccount.getChild_Id(),
                childAccount.getName(),
                childAccount.getGender(),
                childAccount.getAddress(),
                childAccount.getDate_Of_Birth(),
                childAccount.getNic(),
                childAccount.getCreat_Date(),
                childAccount.getCreat_Time(),
                childAccount.getBalance(),
                childAccount.getBranch_No(),
                childAccount.getChild_Account_No()
        );


    }

    @Override
    public Child_Account search(String child_Account_No) throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM child_account  WHERE child_Account_No = ?", child_Account_No);

        if (result.next()) {
            return new Child_Account(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8),
                    result.getString(9),
                    result.getDouble(10),
                    result.getString(11)

            );
        }
        return null;
    }

    @Override
    public boolean isExists(String child_Account_No) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM child_account WHERE child_Account_No=?", child_Account_No);
        return resultSet.next();
    }





    @Override
    public ArrayList<Child_Account> searchByDate(Date date) {

        return null;
    }

    @Override
    public Child_Account searchByAccountNumber(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("Select * from child_account where child_Account_No=?", id);
     if(result.next()){
         return new Child_Account(
                 result.getString(1),
                 result.getString(2),
                 result.getString(3),
                 result.getString(4),
                 result.getString(5),
                 result.getString(6),
                 result.getString(7),
                 result.getString(8),
                 result.getString(9),
                 result.getDouble(10),
                 result.getString(11)
         );
     }

   return null;
    }

    @Override
    public ArrayList<Child_Account> searchByGuardianNic(String nic) {
        return null;
    }

    @Override
    public ArrayList<Child_Account> searchByBranchNo(String branchNo) {
        return null;
    }

    @Override
    public double getBalanceByChildId(String childID) {
        return 0;
    }



    @Override
    public boolean checkAge(Date dob) {
        return false;
    }

    @Override
    public boolean addChildDepositBalance(String child_Account_No, double amount) throws SQLException, ClassNotFoundException {


        return DBUtil.executeUpdate(
                "UPDATE child_account SET balance = balance + ? WHERE child_Account_No = ?",
                amount,
                child_Account_No
        );
    }

    @Override
    public boolean addSavingBalance(String accNo, double amount) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "UPDATE child_account SET balance = balance + ? WHERE child_Account_No = ?",
                amount,
                accNo
        );
    }
}
