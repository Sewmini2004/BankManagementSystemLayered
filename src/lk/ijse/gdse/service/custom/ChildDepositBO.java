package lk.ijse.gdse.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse.dto.ChildDepositDTO;
import lk.ijse.gdse.entity.ChildDeposit;
import lk.ijse.gdse.service.SuperBO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface

ChildDepositBO extends SuperBO {
    public ArrayList<ChildDepositDTO> searchChildByAccNo(String childAccNo) throws SQLException, ClassNotFoundException;
    public ArrayList<ChildDepositDTO> searchByDateChildDeposit(Date date) throws SQLException, ClassNotFoundException;
    public boolean addChildDeposit(ChildDepositDTO childDepositDTO) throws SQLException, ClassNotFoundException;
    public boolean searchChildDeposit(String accountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<ChildDepositDTO> getAllChildDepositDetailes() throws SQLException, ClassNotFoundException;
    public String generateNextChildDepositId() throws SQLException, ClassNotFoundException;
    public ObservableList<ChildDepositDTO> getDeposits(String accNo) throws SQLException, ClassNotFoundException;
    public  String checkAndGetAccount(String child_Account_No) throws SQLException, ClassNotFoundException;



}
