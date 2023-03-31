package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.Child_AccountDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//methn namee ctrl ekth ekk click krla ynn pluwm manika em ynna e class ekta
public interface ChildAccountBO extends SuperBO {
    public boolean addChild(Child_AccountDTO childAccountDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteChild(String accountNo) throws SQLException, ClassNotFoundException;
    public boolean updateChild(Child_AccountDTO childAccountDTO) throws SQLException, ClassNotFoundException;
    public boolean searchChild(String accountNo) throws SQLException, ClassNotFoundException;
    public Child_AccountDTO searchChildByAccNo(String accountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<Child_AccountDTO> getAllChildAccDetailes() throws SQLException, ClassNotFoundException;
    public boolean updateChildAccBalance(String child_Account_No,double amount) throws SQLException, ClassNotFoundException;
    public List<Child_AccountDTO> searchByNic(String nic) throws SQLException, ClassNotFoundException;
    public Child_AccountDTO searchByNameChild(String name) throws SQLException, ClassNotFoundException;
    public ArrayList<Child_AccountDTO> searchByDateChildAcc(Date date) throws SQLException, ClassNotFoundException;
    public String generateNextId() throws SQLException, ClassNotFoundException;
    public String generateNextAccNo() throws SQLException, ClassNotFoundException;
    public boolean addSavingBalance(String accNo, double amount) throws SQLException, ClassNotFoundException;
    public Child_AccountDTO getDepositByChildAccountNb(String accNo) throws SQLException, ClassNotFoundException;
}
