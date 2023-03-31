package lk.ijse.gdse.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.service.SuperBO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DepositBO extends SuperBO {
    public ArrayList<DepositDTO> searchBySavingsAccountNb(String savingsAccountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<DepositDTO> searchByDateDeposit(Date date) throws SQLException, ClassNotFoundException;
    public boolean addDeposit(DepositDTO depositDTO) throws SQLException, ClassNotFoundException;
    public boolean searchDeposit(String accountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<DepositDTO> getAllDepositDetailes() throws SQLException, ClassNotFoundException;
    public String generateNextDepositId() throws SQLException, ClassNotFoundException;
    public String checkAndGetAccount(String AccNo) throws SQLException, ClassNotFoundException;
    public ObservableList<DepositDTO> getAllDepositsTM(String accNo) throws SQLException, ClassNotFoundException;




}
