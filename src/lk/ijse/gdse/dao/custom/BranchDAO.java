package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Branch;

public interface BranchDAO extends CrudDAO<Branch> {

    public boolean isExists(String id);
    public Branch searchByName(String name);
    public Branch searchByAddress(String address);
}
