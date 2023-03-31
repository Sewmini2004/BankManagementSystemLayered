package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Workers;

public interface WorkerDAO extends CrudDAO<Workers> {
    public Workers searchByName(String name);
    public Workers searchByNic(String nic);
    public Workers searchByJob(String job);
}
