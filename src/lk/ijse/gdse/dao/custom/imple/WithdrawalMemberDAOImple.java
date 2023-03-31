package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.WithdrawalMemberDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.entity.Withdrow;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WithdrawalMemberDAOImple implements WithdrawalMemberDAO {

    @Override
    public ArrayList<Withdrow> getAll() throws SQLException, ClassNotFoundException {
        ResultSet execute = DBUtil.executeQuery("select * from withdrawal_member");
        ArrayList<Withdrow> withdrows =new ArrayList<>();


        while (execute.next()){
            withdrows.add(new Withdrow(
                    execute.getString(1),
                    execute.getDouble(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getString(5)




            ));
        }
        return withdrows;
    }

    @Override
    public boolean add(Withdrow withdrow) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate(
                "INSERT INTO withdrawal_member VALUES(?,?,?,?,?)",
                withdrow.getWithdrow_Id(),
                withdrow.getAmount(),
                withdrow.getDate(),
                withdrow.getTime(),
                withdrow.getSaving_Account_No()
        );

    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Withdrow obj) {
        return false;
    }

    @Override
    public Withdrow search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM withdrawal_member WHERE depositId=?", id);
        if (rst.next()){
            return new Withdrow(
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
    public boolean isExists(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM withdrawal_member WHERE child_Account_No=?", id);
        return resultSet.next();
    }

    @Override
    public ArrayList<Withdrow> searchByDate(Date date) {
        return null;
    }

    @Override
    public ArrayList<Withdrow> searchBySavingAccNo(String savingAccNo) throws SQLException, ClassNotFoundException {
        ResultSet execute = DBUtil.executeQuery("select * from withdrawal_member",savingAccNo);
        ArrayList<Withdrow> withdrows =new ArrayList<>();


        while (execute.next()){
            withdrows.add(new Withdrow(
                    execute.getString(1),
                    execute.getDouble(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getString(5)




            ));
        }
        return withdrows;
    }

    @Override
    public String checkAndGetAccount(String accNo) throws SQLException, ClassNotFoundException {
        ResultSet rst = DBUtil.executeQuery("select saving_Account_No from withdrawal_member where saving_Account_No = ?",accNo);
        return rst.next() ? rst.getString(1) : null;
    }

    @Override
    public List<Withdrow> getAllWithdrowTM(String accNo) throws SQLException, ClassNotFoundException {
        List<Withdrow> ob= new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery(
                "select * from withdrawal_member  where saving_Account_No = ?",
                accNo
        );
        while (rst.next()){
            ob.add(
                    new Withdrow(
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
