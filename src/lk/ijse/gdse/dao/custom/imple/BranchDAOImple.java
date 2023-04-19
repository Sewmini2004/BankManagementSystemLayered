package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.BranchDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.entity.Branch;
import lk.ijse.gdse.service.util.Convert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImple implements BranchDAO {

    @Override
    public boolean add(Branch branch) {
    return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Branch branck) {
        return false;
    }

    @Override
    public Branch search(String id) {
        return null;
    }

    @Override
    public boolean isExists(String id) {
        return false;
    }

    @Override
    public Branch searchByName(String name) {
        return null;
    }

    @Override
    public Branch searchByAddress(String address) {
        return null;
    }

    @Override
    public ArrayList<Branch> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM branch");
         ArrayList<Branch> arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(
                    new Branch(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
    return arrayList;

    }

}