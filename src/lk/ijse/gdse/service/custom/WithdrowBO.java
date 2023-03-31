package lk.ijse.gdse.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.dto.WithdrowDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface WithdrowBO extends SuperBO {
    public ArrayList<WithdrowDTO> searchBySavingAccNo(String savingAccNo);
    public ArrayList<WithdrowDTO> searchByDateWithdrow(Date date) throws SQLException, ClassNotFoundException;
    public boolean addWithdrow(WithdrowDTO withdrowDTO) throws SQLException, ClassNotFoundException;
    public WithdrowDTO searchWithdrow(String depositId) throws SQLException, ClassNotFoundException;
    public ArrayList<WithdrowDTO> getAllWithdrowDetailes() throws SQLException, ClassNotFoundException;
    public  String generateNextWithdrwaId() throws SQLException, ClassNotFoundException;
    public  String checkAndGetAccount(String AccNo) throws SQLException, ClassNotFoundException;
    public ObservableList<WithdrowDTO> getAllWithdrowTM(String accNo) throws SQLException, ClassNotFoundException;
}
