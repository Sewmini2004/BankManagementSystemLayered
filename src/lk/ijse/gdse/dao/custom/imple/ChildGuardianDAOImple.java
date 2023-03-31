package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.ChildGuardianDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.ChildGuardian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChildGuardianDAOImple implements ChildGuardianDAO {

    private final Connection connection;

   public ChildGuardianDAOImple(Connection connection){
       this.connection=connection;
   }

    @Override
    public boolean add(ChildGuardian childGuardian) throws SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("INSERT INTO child_guardian VALUES (?,?,?,?,?,?,?)",
                childGuardian.getNic(),
                childGuardian.getName(),
                childGuardian.getGender(),
                childGuardian.getAddress(),
                childGuardian.getDate_Of_Birth(),
                childGuardian.getMobile(),
                childGuardian.getEmail()


        );

    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
       return  DBUtil.executeUpdate("Delete From child_guardian where nic=",nic);

    }

    @Override
    public boolean update(ChildGuardian childGuardian) throws SQLException, ClassNotFoundException {
     return   DBUtil.executeUpdate("UPDATE child_guardian SET name=?,gender=?, address=?, date_Of_Birth=?,mobile=?, email=?  WHERE  nic=?",

                childGuardian.getName(),
                childGuardian.getGender(),
                childGuardian.getAddress(),
                childGuardian.getDate_Of_Birth(),
                childGuardian.getMobile(),
                childGuardian.getEmail(),
                childGuardian.getNic()

        );



    }

    @Override
    public ChildGuardian search(String nic) throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM child_guardian  WHERE nic= ?", nic);
        if (result.next()) {
            return new ChildGuardian(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7)

            );
        }
        return null;

    }

    @Override
    public boolean isExists(String nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM child_account WHERE nic=?", nic);
        return resultSet.next();
    }

    @Override
    public ChildGuardian searchByNic(String nic) {
        return null;
    }

    @Override
    public int countOfChildAccountsByNic(String nic) {
       return 0;
    }

    @Override
    public List<ChildGuardian> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM child_account");
       List<ChildGuardian> childGuardianList =new ArrayList<>();
      while (result.next()){
          childGuardianList.add(new ChildGuardian(
                  result.getString(1),
                  result.getString(2),
                  result.getString(3),
                  result.getString(4),
                  result.getString(5),
                  result.getString(6),
                  result.getString(7)
          ));


      }
      return childGuardianList;
    }



}
