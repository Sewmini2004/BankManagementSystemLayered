package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.ChildGuardian;

public interface ChildGuardianDAO extends CrudDAO<ChildGuardian> {

    public ChildGuardian searchByNic(String nic);
    public int countOfChildAccountsByNic(String nic);
}