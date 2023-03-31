package lk.ijse.gdse.service.custom.imple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.DepositTransactionsDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.ChildDepositDTO;
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.service.custom.DepositBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepositBOImple implements DepositBO {

    private final  DepositTransactionsDAO depositTransactionsDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.DEPOSIT_TRANSACTION, DBConnection.getInstance().getConnection());


    @Override
    public ArrayList<DepositDTO> searchBySavingsAccountNb(String savingsAccountNo) throws SQLException, ClassNotFoundException {
        return getAllDepositDetailes().stream().filter(depositDTO -> depositDTO.getSaving_Account_No().equals(savingsAccountNo)).collect(Collectors.toCollection(ArrayList::new));
}

    @Override
    public ArrayList<DepositDTO> searchByDateDeposit(Date date) throws SQLException, ClassNotFoundException {
        return getAllDepositDetailes().stream().filter(depositDTO -> depositDTO.getDeposit_Id().equals(date)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public boolean addDeposit(DepositDTO depositDTO) throws SQLException, ClassNotFoundException {
        return depositTransactionsDAO.add(Convert.toDeposit(depositDTO));
    }

    @Override
    public boolean searchDeposit(String accountNo) throws SQLException, ClassNotFoundException {
        Deposit search = depositTransactionsDAO.search(accountNo);
        return search!=null;
    }

    @Override
    public ArrayList<DepositDTO> getAllDepositDetailes() throws SQLException, ClassNotFoundException {
        List<Deposit> all = depositTransactionsDAO.getAll();
     return  all.stream().map(Convert::fromDeposit).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public String generateNextDepositId() throws SQLException, ClassNotFoundException {
        ArrayList<DepositDTO> allDepositDetailes = getAllDepositDetailes();
        if (allDepositDetailes.size()>0) {
            return String.format("D%08d", Integer.parseInt( allDepositDetailes.get(allDepositDetailes.size() - 1).getDeposit_Id().split("[D]")[1])+1 );
        }else {
            return "D00000001";
        }
    }

    @Override
    public String checkAndGetAccount(String AccNo) throws SQLException, ClassNotFoundException {
        return depositTransactionsDAO.checkAndGetAccount(AccNo);

    }

    @Override
    public ObservableList<DepositDTO> getAllDepositsTM(String accNo) throws SQLException, ClassNotFoundException {
        List<Deposit> allDepositsTM = depositTransactionsDAO.getAllDepositsTM(accNo);
        ObservableList<DepositDTO> ob= FXCollections.observableArrayList();
        allDepositsTM.forEach(deposit -> ob.add(Convert.fromDeposit(deposit)));

        return ob;
    }
}

