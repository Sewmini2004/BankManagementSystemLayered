package lk.ijse.gdse.dao.custom.imple;

import lk.ijse.gdse.dao.custom.WorkerDAO;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Workers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImple implements WorkerDAO {

    @Override
    public Workers searchByName(String name) {
        return null;
    }

    @Override
    public Workers searchByNic(String nic) {
        return null;
    }

    @Override
    public Workers searchByJob(String job) {
        return null;
    }

    @Override
    public List<Workers> getAll() throws SQLException, ClassNotFoundException {
        ResultSet execute = DBUtil.executeQuery("select * from worker");
        List<Workers> workers =new ArrayList<>();
        while (execute.next()){
            workers.add(new Workers(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getString(5),
                    execute.getString(6),
                    execute.getString(7),
                    execute.getString(8),
                    execute.getString(9),
                    execute.getDouble(10)

            ));
        }
       return workers;
    }

    @Override
    public boolean add(Workers workers) throws SQLException, ClassNotFoundException {
       return  DBUtil.executeUpdate("INSERT INTO worker VALUES (?,?,?,?,?,?,?,?,?,?)",
               workers.getWorker_Id(),
               workers.getName(),
               workers.getNic(),
               workers.getAddress(),
               workers.getJob(),
               workers.getMobile(),
               workers.getEmail(),
               workers.getDate_of_birth(),
               Workers.getBranch_No(),
               workers.getSalary()
       );
    }

    @Override
    public boolean delete(String worker_Id) throws SQLException, ClassNotFoundException {
        return  DBUtil.executeUpdate("Delete From  worker where worker_Id=?",worker_Id);

    }

    @Override
    public boolean update(Workers workers) throws SQLException, ClassNotFoundException {
        boolean i= DBUtil.executeUpdate("UPDATE worker SET name=?,nic=?,address=?,job=?,mobile=?,email=?,date_of_birth=?,branch_No=?,salary=? WHERE worker_Id=?",
                workers.getName(),
                workers.getNic(),
                workers.getAddress(),
                workers.getJob(),
                workers.getMobile(),
                workers.getEmail(),
                workers.getDate_of_birth(),
                Workers.getBranch_No(),
                workers.getSalary(),
                workers.getWorker_Id()


        );
        return  i;
    }

    @Override
    public Workers search(String worker_Id) throws SQLException, ClassNotFoundException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM worker  WHERE worker_Id = ?", worker_Id);

        if (result.next()) {
            return new Workers(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8),
                    result.getString(9),
                    result.getDouble(10)

            );
        }
        return null;

    }

    @Override
    public boolean isExists(String worker_Id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.executeQuery("SELECT * FROM worker WHERE worker_Id=?", worker_Id);
        return resultSet.next();
    }
}
