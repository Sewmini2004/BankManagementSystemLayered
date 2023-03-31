package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.InterAccountTransactionDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.InterAccountTransactionDTO;
import lk.ijse.gdse.entity.InterAccountTransaction;
import lk.ijse.gdse.service.custom.InterAccountTransactionBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterAccountTransactionBOImple implements InterAccountTransactionBO {
    private final InterAccountTransactionDAO interAccountTransactionDAO = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.INTER_ACCOUNT_TRANSACTION, DBConnection.getInstance().getConnection());

    @Override
    public ArrayList<InterAccountTransactionDTO> searchBySenderAccount(String savingsAccountNo) throws SQLException, ClassNotFoundException {
     return getAllInterAccTransactionDetailes().stream().filter(interAccountTransactionDTO -> interAccountTransactionDTO.getAccount_01().equals(savingsAccountNo)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public ArrayList<InterAccountTransactionDTO> searchByReceiverAccount(String savingsAccountNo) {
        return null;
    }

    @Override
    public ArrayList<InterAccountTransactionDTO> searchByDateInterAccTransaction(Date date) {
        return null;
    }

    @Override
    public boolean addInterAccTransaction(InterAccountTransactionDTO interAccountTransactionDTO) throws SQLException, ClassNotFoundException {
        return interAccountTransactionDAO.add(Convert.toInterAccountTransaction(interAccountTransactionDTO));
    }

    @Override
    public boolean searchInterAccTransaction(String savingsAccountNo) {
     return false;
    }

    @Override
    public ArrayList<InterAccountTransactionDTO> getAllInterAccTransactionDetailes() throws SQLException, ClassNotFoundException {
        List<InterAccountTransaction> all = interAccountTransactionDAO.getAll();
        return all.stream().map(Convert::fromInterAccountTransaction).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public String generateNextTranactionId() throws SQLException, ClassNotFoundException {
        ArrayList<InterAccountTransactionDTO> allMembers = getAllInterAccTransactionDetailes();
        if (allMembers.size()>0) {
            return String.format("T%08d", Integer.parseInt( allMembers.get(allMembers.size() - 1).getTransaction_Id().split("[T]")[1])+1 );
        }else {
            return "T00000001";
        }
    }
}
