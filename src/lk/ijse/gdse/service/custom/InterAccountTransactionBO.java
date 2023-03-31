package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.InterAccountTransactionDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InterAccountTransactionBO extends SuperBO {
    public ArrayList<InterAccountTransactionDTO> searchBySenderAccount(String savingsAccountNo) throws SQLException, ClassNotFoundException;
    public ArrayList<InterAccountTransactionDTO> searchByReceiverAccount(String savingsAccountNo);
    public ArrayList<InterAccountTransactionDTO> searchByDateInterAccTransaction(Date date);
    public boolean addInterAccTransaction(InterAccountTransactionDTO interAccountTransactionDTO) throws SQLException, ClassNotFoundException;
    public boolean searchInterAccTransaction(String savingsAccountNo);
    public ArrayList<InterAccountTransactionDTO> getAllInterAccTransactionDetailes() throws SQLException, ClassNotFoundException;
    public String generateNextTranactionId() throws SQLException, ClassNotFoundException;
}
