package lk.ijse.gdse.dao.custom.imple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.dao.custom.DepositChildDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.ChildDeposit;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositChildDAOImple implements DepositChildDAO {

    @Override
    public List<ChildDeposit> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM deposit_child");
        List<ChildDeposit> ob=new ArrayList<>();
        while (resultSet.next()){
            ob.add(new ChildDeposit(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return ob;
    }

    @Override
    public boolean add(ChildDeposit childDeposit) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "INSERT INTO deposit_child VALUES(?,?,?,?,?)",
                childDeposit.getDeposit_Id(),
                childDeposit.getAmount(),
                childDeposit.getDate(),
                childDeposit.getTime(),
                childDeposit.getChild_Account_No()
        );
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(ChildDeposit obj) {
        return false;
    }

    @Override
    public ChildDeposit search(String id) {
        return null;
    }

    @Override
    public boolean isExists(String deposit_Id) throws SQLException, ClassNotFoundException {
        ResultSet rst =DBUtil.executeQuery("SELECT * FROM deposit_child WHERE deposit_Id=? ", deposit_Id);
       return rst.next();

    }

    @Override
    public ArrayList<ChildDeposit> searchByDate(Date date) {
        return null;
    }

    @Override
    public ArrayList<ChildDeposit> searchByChildAccountNb(String childAccNo) {
        return null;
    }


    @Override
    public String checkAndGetAccount(String child_Account_No) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select child_Account_No from child_account where child_Account_No = ?", child_Account_No);
        return rst.next() ? rst.getString(1) : null;

    }

    @Override
    public List<ChildDeposit> getDepositByChildAccountNb(String child_Account_No) throws SQLException, ClassNotFoundException {

        ResultSet rst = DBUtil.executeQuery("select * from deposit_child where child_Account_No = ?", child_Account_No);
         List<ChildDeposit> ob= new ArrayList<>();
        while (rst.next()){
            ob.add(
                    new ChildDeposit(
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


    public List<ChildDeposit> getDeposits(String accNo) throws SQLException, ClassNotFoundException {
        List<ChildDeposit> ob= new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery(
                "select * from deposit_child where child_Account_No = ?",
                accNo
        );
        while (rst.next()){
            ob.add(
                    new ChildDeposit(
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
  }









