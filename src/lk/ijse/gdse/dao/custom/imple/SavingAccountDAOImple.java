package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.SavingAccountDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Account;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SavingAccountDAOImple implements SavingAccountDAO {

    @Override
    public ArrayList<Account> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM saving_account");
       ArrayList<Account> accountArrayList=new ArrayList<>();
       while (result.next()){
           accountArrayList.add(new Account(
                   result.getString(1),
                   result.getString(2),
                   result.getString(3),
                   result.getString(4),
                   result.getString(5),
                   result.getString(6),
                   result.getString(7),
                   result.getString(8),
                   result.getString(9),
                   result.getString(10),
                   result.getString(11),
                   result.getDouble(12),
                   result.getString(13)
                   )

           );
       }
return accountArrayList;
    }

    @Override
    public boolean add(Account member) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("INSERT INTO saving_account VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                member.getMember_Id(),
                member.getSaving_Account_No(),
                member.getName(),
                member.getNic(),
                member.getGender(),
                member.getAddress(),
                member.getMobile(),
                member.getEmail(),
                member.getDate_of_birth(),
                member.getCreat_Date(),
                member.getCreat_Time(),
                member.getSaving_Balance(),
                member.getBranch_No()
        );

    }

    @Override
    public boolean delete(String Account_No) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("DELETE FROM saving_account WHERE saving_Account_No=?",Account_No);



    }

    @Override
    public boolean update(Account member) throws SQLException, ClassNotFoundException {
        boolean i = DBUtil.executeUpdate("UPDATE saving_account SET member_Id=?,name=?, nic=?, gender=?, address=?, mobile=?, email=?, date_of_birth=?,creat_Date=?,creat_Time=?,saving_Balance=?,branch_No=?   WHERE  saving_Account_No=?",
                member.getMember_Id(),
                member.getName(),
                member.getNic(),
                member.getGender(),
                member.getAddress(),
                member.getMobile(),
                member.getEmail(),
                member.getDate_of_birth(),
                member.getCreat_Date(),
                member.getCreat_Time(),
                member.getSaving_Balance(),
                member.getBranch_No(),
                member.getSaving_Account_No()
        );
        return i;

    }


    @Override
    public Account search(String saving_Account_No) throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM saving_account  WHERE saving_Account_No = ?", saving_Account_No);

        if (result.next()) {
            return new Account(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8),
                    result.getString(9),
                    result.getString(10),
                    result.getString(11),
                    result.getDouble(12),
                    result.getString(13)
            );
        }
        return null;

    }

    @Override
    public boolean isExists(String savingAccNb) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM saving_account WHERE   saving_Account_No=?", savingAccNb);
        return resultSet.next();

    }

    @Override
    public ArrayList<Account> searchByDate(Date date) {
        return null;
    }

    @Override
    public boolean addSavingBalance(String saving_Account_No, double amount) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "UPDATE saving_account SET saving_Balance = saving_Balance + ? WHERE saving_Account_No = ?",
                amount,
                saving_Account_No
        );
    }

    @Override
    public boolean minusBalanceSavingBalance(String saving_Account_No, double amount) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "UPDATE saving_account SET saving_Balance = saving_Balance - ? WHERE saving_Account_No = ?",
                amount,
                saving_Account_No
        );
    }

    @Override
    public Account searchByAccountNumber(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM saving_account WHERE saving_Account_No=?", id);
        if (result.next()) {
            return new Account(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8),
                    result.getString(9),
                    result.getString(10),
                    result.getString(11),
                    result.getDouble(12),
                    result.getString(13)
            );
        }
        return null;
    }

    @Override
    public Account searchByNic(String nic) {
        return null;
    }

    @Override
    public Account searchByName(String name) {
        return null;
    }

    @Override
    public double getAccountBalance(String saving_account_no) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery(
                "select saving_Balance from saving_account where saving_Account_No = ?",
                saving_account_no
        );
        return rst.next() ? rst.getDouble(1): -1;
    }






}
