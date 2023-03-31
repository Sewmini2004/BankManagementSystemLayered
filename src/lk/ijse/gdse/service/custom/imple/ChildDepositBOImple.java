package lk.ijse.gdse.service.custom.imple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.DepositChildDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.ChildDepositDTO;
import lk.ijse.gdse.dto.Child_AccountDTO;
import lk.ijse.gdse.entity.ChildDeposit;
import lk.ijse.gdse.entity.Child_Account;
import lk.ijse.gdse.service.custom.ChildDepositBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChildDepositBOImple implements ChildDepositBO {
    DepositChildDAO depositChildDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.DEPOSIT_CHILD, DBConnection.getInstance().getConnection());
    @Override
    public ArrayList<ChildDepositDTO> searchChildByAccNo(String childAccNo) throws SQLException, ClassNotFoundException {
    return getAllChildDepositDetailes().stream().filter(childDepositDTO -> childDepositDTO.getChild_Account_No().equals(childAccNo)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<ChildDepositDTO> searchByDateChildDeposit(Date date) throws SQLException, ClassNotFoundException {
        return getAllChildDepositDetailes().stream().filter(childDepositDTO -> childDepositDTO.getDeposit_Id().equals(date)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public boolean addChildDeposit(ChildDepositDTO childDepositDTO) throws SQLException, ClassNotFoundException {
     return depositChildDAO.add(Convert.toChildDeposit(childDepositDTO));
    }


    @Override
    public boolean searchChildDeposit(String accountNo) throws SQLException, ClassNotFoundException {
        ChildDeposit search = depositChildDAO.search(accountNo);
        return search!=null;
    }

    @Override
    public ArrayList<ChildDepositDTO> getAllChildDepositDetailes() throws SQLException, ClassNotFoundException {
        List<ChildDeposit> all = depositChildDAO.getAll();
        return all.stream().map(Convert::fromChildDeposit).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public String generateNextChildDepositId() throws SQLException, ClassNotFoundException {
        ArrayList<ChildDepositDTO> allMembers = getAllChildDepositDetailes();
        if (allMembers.size()>0) {
            return String.format("CD%08d", Integer.parseInt( allMembers.get(allMembers.size() - 1).getDeposit_Id().split("[CD]")[2])+1 );
        }else {
            return "CD00000001";
        }
    }

    @Override
    public ObservableList<ChildDepositDTO> getDeposits(String accNo) throws SQLException, ClassNotFoundException {
        List<ChildDeposit> all = depositChildDAO.getDeposits(accNo);
        ObservableList<ChildDepositDTO> ob= FXCollections.observableArrayList();
//        ArrayList<ChildDepositDTO> collect = all.stream().map(childDeposit -> Convert.fromChildDeposit(childDeposit)).collect(Collectors.toCollection(ArrayList::new));
//         ob.addAll(collect);
        all.forEach(childDeposit -> ob.add(Convert.fromChildDeposit(childDeposit)));
//        for (ChildDeposit child : all) {
//            ChildDepositDTO child_Deposit = Convert.fromChildDeposit(child);
//            ob.add(child_Deposit);
//        }
        return ob;

    }

    @Override
    public String checkAndGetAccount(String child_Account_No) throws SQLException, ClassNotFoundException {
       return depositChildDAO.checkAndGetAccount(child_Account_No);
    }
}
