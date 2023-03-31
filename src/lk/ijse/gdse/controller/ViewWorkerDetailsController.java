package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.entity.Withdrow;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.WorkersBO;
import lk.ijse.gdse.view.tm.WorkersDetails;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewWorkerDetailsController {

    @FXML
    private TableView<WorkersDetails> tblWokers;

    @FXML
    private TableColumn<?, ?> colWorker_Id;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colpost;

    @FXML
    private TableColumn<?, ?> colsalary;

    WorkersBO workersBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.WORKER);

    public void initialize() throws SQLException, ClassNotFoundException {

       colWorker_Id.setCellValueFactory(new PropertyValueFactory<>("worker_Id"));
        tblWokers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblWokers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblWokers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblWokers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblWokers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblWokers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("job"));
        tblWokers.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("salary"));

        List<WorkersDetails> workersDetails= workersBO.getAllWorkersDetailes().stream().map(worker -> new WorkersDetails(
                worker.getWorker_Id(),
                worker.getName(),
                worker.getNic(),
                worker.getAddress(),
                worker.getJob(),
                worker.getMobile(),
                worker.getEmail(),
                worker.getSalary()

 )).collect(Collectors.toList());
        tblWokers.setItems(FXCollections.observableArrayList(workersDetails));

    }

}
