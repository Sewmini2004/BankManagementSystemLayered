package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.SavingAccountDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.entity.Account;
import lk.ijse.gdse.service.custom.SavingAccountBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SavingAccountBOImple implements SavingAccountBO {
    private final SavingAccountDAO savingAccountDAO = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.SAVINGS_ACCOUNT, DBConnection.getInstance().getConnection());

    @Override
    public boolean addMember(AccountDTO accountDTO) throws SQLException, ClassNotFoundException {
        return savingAccountDAO.add(Convert.toAccount(accountDTO));
    }

    @Override
    public boolean deleteMember(String accountId) throws SQLException, ClassNotFoundException {
        return savingAccountDAO.delete(accountId);
    }

    @Override
    public boolean updateMember(AccountDTO accountDTO) throws SQLException, ClassNotFoundException {
        return savingAccountDAO.update(Convert.toAccount(accountDTO));
    }

    @Override
    public boolean searchMember(String accountNo) throws SQLException, ClassNotFoundException {
        Account search = savingAccountDAO.search(accountNo);
        return search != null;
    }

    @Override
    public AccountDTO getMember(String accountNo) throws SQLException, ClassNotFoundException {
        return Convert.fromAccount(savingAccountDAO.searchByAccountNumber(accountNo));
    }

    @Override
    public ArrayList<AccountDTO> getAllMembers() throws SQLException, ClassNotFoundException {
        List<Account> all = savingAccountDAO.getAll();
        return all.stream().map(Convert::fromAccount).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public boolean updateSavingBalance(String saving_Account_No, double amount) throws SQLException, ClassNotFoundException {
        AccountDTO accountDTO = getMember(saving_Account_No);
        accountDTO.setSaving_Balance(amount);
        return updateMember(accountDTO);
    }


    @Override
    public AccountDTO getMemberByNic(String nic) {
        return null;
    }

    @Override
    public AccountDTO getMemberByName(String name) {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ArrayList<AccountDTO> allMembers = getAllMembers();
        if (allMembers.size() > 0) {
            String member_id = allMembers.get(allMembers.size() - 1).getMember_Id();
            String[] split = member_id.split("[M]");
            int i = Integer.parseInt(split[1]);
            i++;
            return String.format("M%08d", i);
        } else {
            return "M00000001";
        }
    }


    @Override
    public String generateNextAccNo() throws SQLException, ClassNotFoundException {
        ArrayList<AccountDTO> allMembers = getAllMembers();
        if (allMembers.size()>0) {
           return String.format("A%08d", Integer.parseInt( allMembers.get(allMembers.size() - 1).getSaving_Account_No().split("[A]")[1])+1 );
        }else {
           return "A00000001";
        }
    }

    @Override
    public boolean addSavingBalance(String saving_Account_No, double amount) throws SQLException, ClassNotFoundException {
        return savingAccountDAO.addSavingBalance(saving_Account_No, amount);
    }

    @Override
    public boolean minusBalanceSavingBalance(String saving_Account_No, double amount) throws SQLException, ClassNotFoundException {
        return savingAccountDAO.minusBalanceSavingBalance(saving_Account_No,amount);
    }

    @Override
    public double getAccountBalance(String AccNo) throws SQLException, ClassNotFoundException {
        return savingAccountDAO.getAccountBalance(AccNo);
    }
}
