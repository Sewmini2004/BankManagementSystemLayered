package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.BranchDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.BranchDTO;
import lk.ijse.gdse.entity.Branch;
import lk.ijse.gdse.service.custom.BranchBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BranchBOImple implements BranchBO {

    BranchDAO branchDAO=DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.BRANCH, DBConnection.getInstance().getConnection());

    @Override
    public boolean addBranch(BranchDTO branchDTO) {
        return false;
    }

    @Override
    public boolean deleteBranch(String branchNo) {
        return false;
    }

    @Override
    public boolean updateBranch(BranchDTO branchDTO) {
        return false;
    }

    @Override
    public boolean searchBranch(String branchNo) {
        return false;
    }

    @Override
    public ArrayList<BranchDTO> getAllBranchDetailes() throws SQLException, ClassNotFoundException {
        List<Branch> all = branchDAO.getAll();
        return all.stream().map(Convert::fromBranch).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean isExistsBranch(String branchNo) {
        return false;
    }

    @Override
    public BranchDTO searchByName(String name) {
        return null;
    }

    @Override
    public BranchDTO searchByAddress(String address) {
        return null;
    }
}
