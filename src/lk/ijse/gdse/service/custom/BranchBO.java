package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.BranchDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchBO extends SuperBO {
    public boolean addBranch(BranchDTO branchDTO);
    public boolean deleteBranch(String branchNo);
    public boolean updateBranch(BranchDTO branchDTO);
    public boolean searchBranch(String branchNo);
    public ArrayList<BranchDTO> getAllBranchDetailes() throws SQLException, ClassNotFoundException;
    public boolean isExistsBranch(String branchNo);
    public BranchDTO searchByName(String name);
    public BranchDTO searchByAddress(String address);

}
