package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.Child_AccountDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.Child_AccountDTO;
import lk.ijse.gdse.entity.Child_Account;
import lk.ijse.gdse.service.custom.ChildAccountBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChildAccountBOImple implements ChildAccountBO {
    Child_AccountDAO child_accountDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.CHILD_ACCOUNT, DBConnection.getInstance().getConnection());
    @Override
    public boolean addChild(Child_AccountDTO childAccountDTO) throws SQLException, ClassNotFoundException {
        return child_accountDAO.add(Convert.toChildAccount(childAccountDTO));
    }

    @Override
    public boolean deleteChild(String accountNo) throws SQLException, ClassNotFoundException {
       return  child_accountDAO.delete(accountNo);
    }

    @Override
    public boolean updateChild(Child_AccountDTO childAccountDTO) throws SQLException, ClassNotFoundException {
        return child_accountDAO.update(Convert.toChildAccount(childAccountDTO));
    }

    @Override
    public boolean searchChild(String accountNo) throws SQLException, ClassNotFoundException {
        return getAllChildAccDetailes().stream().filter(childAccountDTO -> childAccountDTO.getChild_Account_No().equals(accountNo)).collect(Collectors.toCollection(ArrayList::new)).size()>0;

    }



    @Override
    public Child_AccountDTO searchChildByAccNo(String accountNo) throws SQLException, ClassNotFoundException {
        return getAllChildAccDetailes().stream().filter(childAccountDTO -> childAccountDTO.getChild_Account_No().equals(accountNo)).collect(Collectors.toCollection(ArrayList::new)).get(0);
    }

    @Override
    public ArrayList<Child_AccountDTO> getAllChildAccDetailes() throws SQLException, ClassNotFoundException {
        List<Child_Account> all = child_accountDAO.getAll();
        return all.stream().map(Convert::fromChildAccount).collect(Collectors.toCollection(ArrayList::new));


        /*
        all.stream();//mken krnn all eken eka eka argena deno hrida?hri
        all.stream().map(child_account -> );//all.steam eken ewana ek alla ganne menn mee child_Account kyn eken
        all.stream().map(child_account -> Convert.fromChildAccount(child_account));//mken krnne mkdd? child_acc eke tika hrwnw dto eke widiyt nymai nmanika
        all.stream().map(Convert::fromChildAccount);//mken wenne manika mita uda eken wen ekmai mthn krla thynne ar ewan ek kelinma ape method ekta ywla thynne thrunda blnna oka?
        all.stream().map(child_account -> new Child_AccountDTO(child_account.getChild_Id(),child_account.getChild_Account_No(),child_account......));//hri enm emth pluwm api method ekk hdpu nisn arma krn ek lesi
        //Convert::fromChildAccount   -> mke Convert kynne ape class ek hrida?? thrunda? hriii ::dalalynne eke athule thyen method ek hrida? hri
        //ywnna on naththe nee ek auto ynwa    oy 2ma samanai manika hri mek nkm menna me wge hrida onna blno
        */
        /*
         ArrayList<Child_AccountDTO> arrayList=new ArrayList<>();
        for(Child_Account child:all){//
            Child_AccountDTO child_accountDTO = Convert.fromChildAccount(child);//menna mee wde hrida ??hari
            arrayList.add(child_accountDTO);
        }
        return arrayList;
        */
    }

    @Override
    public boolean updateChildAccBalance(String child_Account_No, double amount) throws SQLException, ClassNotFoundException {
        Child_AccountDTO child_accountDTO = searchChildByAccNo(child_Account_No);
        child_accountDTO.setBalance(amount);
        return updateChild(child_accountDTO);
    }

    @Override
    public List<Child_AccountDTO> searchByNic(String nic) throws SQLException, ClassNotFoundException {
        return getAllChildAccDetailes().stream().filter(childAccountDTO -> childAccountDTO.getNic().equals(nic)).collect(Collectors.toList());
    }

    @Override
    public Child_AccountDTO searchByNameChild(String name) throws SQLException, ClassNotFoundException {
        return getAllChildAccDetailes().stream().filter(childAccountDTO -> childAccountDTO.getName().equals(name)).collect(Collectors.toCollection(ArrayList::new)).get(0);
    }

    @Override
    public ArrayList<Child_AccountDTO> searchByDateChildAcc(Date date) throws SQLException, ClassNotFoundException {
        return getAllChildAccDetailes().stream().filter(childAccountDTO -> childAccountDTO.getCreat_Date().equals(date)).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ArrayList<Child_AccountDTO> allMembers =getAllChildAccDetailes();
        if (allMembers.size()>0) {
            String member_id = allMembers.get(allMembers.size() - 1).getChild_Id();//
            String[] split = member_id.split("[C]");
            int i = Integer.parseInt(split[1]);
            i++;
            return String.format("C%08d", i);
        } else {
            return "C00000001";
        }

    }

    @Override
    public String generateNextAccNo() throws SQLException, ClassNotFoundException {
        ArrayList<Child_AccountDTO> allMembers =getAllChildAccDetailes();
        if (allMembers.size()>0) {
            return String.format("CA%08d", Integer.parseInt(allMembers.get(allMembers.size() - 1).getChild_Account_No().split("[CA]")[2])+1);
        }else {
            return "CA00000001";
        }
    }

    @Override
    public boolean addSavingBalance(String accNo, double amount) throws SQLException, ClassNotFoundException {
        return child_accountDAO.addSavingBalance(accNo, amount);
    }

    @Override
    public Child_AccountDTO getDepositByChildAccountNb(String accNo) throws SQLException, ClassNotFoundException {
      return   Convert.fromChildAccount(child_accountDAO.searchByAccountNumber(accNo));

    }
}
