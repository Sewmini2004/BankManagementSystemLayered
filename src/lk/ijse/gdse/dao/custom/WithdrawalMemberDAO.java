package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dao.SearchByDAO;
import lk.ijse.gdse.entity.Withdrow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WithdrawalMemberDAO extends CrudDAO<Withdrow>, SearchByDAO<Withdrow> {

 public ArrayList<Withdrow> searchBySavingAccNo(String savingAccNo) throws SQLException, ClassNotFoundException;
 public String checkAndGetAccount(String accNo) throws SQLException, ClassNotFoundException;
 public List<Withdrow> getAllWithdrowTM(String accNo) throws SQLException, ClassNotFoundException;
}
