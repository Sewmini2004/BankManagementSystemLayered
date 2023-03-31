package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.ChildGuardianDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ChildGuardianBO extends SuperBO {
    public ArrayList<ChildGuardianDTO> getAllChildGuardian() throws SQLException, ClassNotFoundException;
    public boolean addChildGuardian(ChildGuardianDTO childGuardianDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteChildGuardian(String id) throws SQLException, ClassNotFoundException;
    public boolean updateChildGuardian(ChildGuardianDTO childGuardianDTO) throws SQLException, ClassNotFoundException;
    public ChildGuardianDTO searchChildGuardian(String id) throws SQLException, ClassNotFoundException;
    public boolean isExistsChildGuardian(String id) throws SQLException, ClassNotFoundException;
    public ChildGuardianDTO searchByNicChildGuardian(String nic) throws SQLException, ClassNotFoundException;
    public int countOfChildAccountsByNicChildGuardian(String nic);

}
