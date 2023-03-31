package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.WorkersDTO;
import lk.ijse.gdse.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorkersBO extends SuperBO {
    public WorkersDTO searchByName(String name);
    public WorkersDTO searchByNic(String nic);
    public WorkersDTO searchByJob(String job);
    public boolean addWorkers(WorkersDTO workersDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteWorkers(String workerId) throws SQLException, ClassNotFoundException;
    public boolean updateWorkers(WorkersDTO workersDTO) throws SQLException, ClassNotFoundException;
    public boolean searchWorkers(String workerId) throws SQLException, ClassNotFoundException;
    public ArrayList<WorkersDTO> getAllWorkersDetailes() throws SQLException, ClassNotFoundException;
    public String generateNextWorkerId() throws SQLException, ClassNotFoundException;
    public WorkersDTO searchByWorkerNo(String workerNo) throws SQLException, ClassNotFoundException;

}
