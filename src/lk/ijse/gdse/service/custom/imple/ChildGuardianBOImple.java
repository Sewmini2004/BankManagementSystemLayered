package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.ChildGuardianDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.ChildGuardianDTO;
import lk.ijse.gdse.entity.ChildGuardian;
import lk.ijse.gdse.service.custom.ChildGuardianBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChildGuardianBOImple implements ChildGuardianBO {

    ChildGuardianDAO childGuardianDAO= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.CHILD_GUARDIAN, DBConnection.getInstance().getConnection());


    @Override
    public ArrayList<ChildGuardianDTO> getAllChildGuardian() throws SQLException, ClassNotFoundException {
        List<ChildGuardian> all = childGuardianDAO.getAll();
    return all.stream().map(Convert::fromChildGuardian).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public boolean addChildGuardian(ChildGuardianDTO childGuardianDTO) throws SQLException, ClassNotFoundException {
        return  childGuardianDAO.add(Convert.toChildGuardian(childGuardianDTO));

    }

    @Override
    public boolean deleteChildGuardian(String id) throws SQLException, ClassNotFoundException {
      return  childGuardianDAO.delete(id);
    }

    @Override
    public boolean updateChildGuardian(ChildGuardianDTO childGuardianDTO) throws SQLException, ClassNotFoundException {
        return childGuardianDAO.update(Convert.toChildGuardian(childGuardianDTO));
    }

    @Override
    public ChildGuardianDTO searchChildGuardian(String id) throws SQLException, ClassNotFoundException {
       return  Convert.fromChildGuardian(childGuardianDAO.search(id));
    }

    @Override
    public boolean isExistsChildGuardian(String id) throws SQLException, ClassNotFoundException {
        return childGuardianDAO.isExists(id);

    }

    @Override
    public ChildGuardianDTO searchByNicChildGuardian(String nic) throws SQLException, ClassNotFoundException {

        return Convert.fromChildGuardian(childGuardianDAO.search(nic));


    }

    @Override
    public int countOfChildAccountsByNicChildGuardian(String nic) {
        return 0;
    }
}
