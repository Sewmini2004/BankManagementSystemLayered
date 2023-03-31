package lk.ijse.gdse.service.custom.imple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.WithdrawalMemberDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.DepositDTO;
import lk.ijse.gdse.dto.WithdrowDTO;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.entity.Withdrow;
import lk.ijse.gdse.service.custom.WithdrowBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WithdrowBOImple implements WithdrowBO {
    private final WithdrawalMemberDAO withdrawalMemberDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.WITHDRAWAL_MEMBER, DBConnection.getInstance().getConnection());
    @Override
    public ArrayList<WithdrowDTO> searchBySavingAccNo(String savingAccNo) {
        return null;
    }

    @Override
    public ArrayList<WithdrowDTO> searchByDateWithdrow(Date date) throws SQLException, ClassNotFoundException {
        return getAllWithdrowDetailes().stream().filter(withdrowDTO -> withdrowDTO.getDate().equals(date)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public boolean addWithdrow(WithdrowDTO withdrowDTO) throws SQLException, ClassNotFoundException {
        return withdrawalMemberDAO.add(Convert.toWithdrow(withdrowDTO));
    }

    @Override
    public WithdrowDTO searchWithdrow(String depositId) throws SQLException, ClassNotFoundException {
        Withdrow search = withdrawalMemberDAO.search(depositId);
        return Convert.fromWithdrow(search);

    }

    @Override
    public ArrayList<WithdrowDTO> getAllWithdrowDetailes() throws SQLException, ClassNotFoundException {
        List<Withdrow> all = withdrawalMemberDAO.getAll();
        return all.stream().map(Convert::fromWithdrow).collect(Collectors.toCollection(ArrayList::new));


    }

    @Override
    public String generateNextWithdrwaId() throws SQLException, ClassNotFoundException {
        ArrayList<WithdrowDTO> allMembers = getAllWithdrowDetailes();
        if (allMembers.size()>0) {
            return String.format("W%08d", Integer.parseInt( allMembers.get(allMembers.size() - 1).getWithdrow_Id().split("[W]")[1])+1 );
        }else {
            return "W00000001";
        }
    }

    @Override
    public String checkAndGetAccount(String AccNo) throws SQLException, ClassNotFoundException {
        return withdrawalMemberDAO.checkAndGetAccount(AccNo);
    }

    @Override
    public ObservableList<WithdrowDTO> getAllWithdrowTM(String accNo) throws SQLException, ClassNotFoundException {
        List<Withdrow> allWithdrowTM = withdrawalMemberDAO.getAllWithdrowTM(accNo);
        ObservableList<WithdrowDTO> ob= FXCollections.observableArrayList();
        allWithdrowTM.forEach(withdrow -> ob.add(Convert.fromWithdrow(withdrow)));

        return ob;
    }
}
