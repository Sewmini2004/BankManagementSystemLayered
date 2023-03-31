package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SavingAccountBO extends SuperBO {
    public boolean addMember(AccountDTO accountDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteMember(String accountNo) throws SQLException, ClassNotFoundException;
    public boolean updateMember(AccountDTO accountDTO) throws SQLException, ClassNotFoundException;
    public boolean searchMember(String accountNo) throws SQLException, ClassNotFoundException;
    public AccountDTO getMember(String accountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<AccountDTO> getAllMembers() throws SQLException, ClassNotFoundException;
    public boolean updateSavingBalance(String saving_Account_No,double amount) throws SQLException, ClassNotFoundException;
    public AccountDTO getMemberByNic(String nic);
    public AccountDTO getMemberByName(String name);
    public String generateNextId() throws SQLException, ClassNotFoundException;
    public String generateNextAccNo() throws SQLException, ClassNotFoundException;
    public boolean addSavingBalance(String saving_Account_No,double amount) throws SQLException, ClassNotFoundException;
    public boolean minusBalanceSavingBalance(String saving_Account_No,double amount) throws SQLException, ClassNotFoundException;
    public double getAccountBalance(String AccNo) throws SQLException, ClassNotFoundException;
}
