package lk.ijse.gdse.service.custom.imple;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.WorkerDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.WorkersDTO;
import lk.ijse.gdse.entity.Workers;
import lk.ijse.gdse.service.custom.WorkersBO;
import lk.ijse.gdse.service.util.Convert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkersBOImple implements WorkersBO {
  WorkerDAO workerDAO=DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.WORKER, DBConnection.getInstance().getConnection());

    @Override
    public WorkersDTO searchByName(String name) {
        return null;
    }

    @Override
    public WorkersDTO searchByNic(String nic) {
        return null;
    }

    @Override
    public WorkersDTO searchByJob(String job) {
        return null;
    }

    @Override
    public boolean addWorkers(WorkersDTO workersDTO) throws SQLException, ClassNotFoundException {
     return   workerDAO.add(Convert.toWorkers(workersDTO));
    }

    @Override
    public boolean deleteWorkers(String workerId) throws SQLException, ClassNotFoundException {
      return workerDAO.delete(workerId);
    }

    @Override
    public boolean updateWorkers(WorkersDTO workersDTO) throws SQLException, ClassNotFoundException {
       return workerDAO.update(Convert.toWorkers(workersDTO));
    }

    @Override
    public boolean searchWorkers(String workerId) throws SQLException, ClassNotFoundException {
        Workers search = workerDAO.search(workerId);
        return search!=null;
    }

    @Override
    public ArrayList<WorkersDTO> getAllWorkersDetailes() throws SQLException, ClassNotFoundException {
        List<Workers> all = workerDAO.getAll();
        ArrayList<WorkersDTO> collect = all.stream().map(Convert::fromWorkers).collect(Collectors.toCollection(ArrayList::new));
        return collect;
    }

    @Override
    public String generateNextWorkerId() throws SQLException, ClassNotFoundException {
        ArrayList<WorkersDTO> allMembers = getAllWorkersDetailes();
        if (allMembers.size()>0) {
            return String.format("W%08d", Integer.parseInt( allMembers.get(allMembers.size() - 1).getWorker_Id().split("[W]")[1])+1 );
        }else {
            return "W00000001";
        }
    }

    @Override
    public WorkersDTO searchByWorkerNo(String workerNo) throws SQLException, ClassNotFoundException {
        return Convert.fromWorkers(workerDAO.search(workerNo));
    }
}
