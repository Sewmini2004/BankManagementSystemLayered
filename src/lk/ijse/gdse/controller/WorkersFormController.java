package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.WorkersDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.BranchBO;
import lk.ijse.gdse.service.custom.WorkersBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WorkersFormController {

    public JFXButton btnClear;
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtDateOfBirth;

    @FXML
    private JFXTextField txtPost;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnbtnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label lblWorkerNo;

    @FXML
    private Label lblBranchNo;

    @FXML
    private JFXTextField txtWorkerNo;

    @FXML
    private JFXButton btnSearch;

    private final WorkersBO workersBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.WORKER);
    private final BranchBO branchBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.BRANCH);

    public void initialize(){
        try {
            loadWorkerId();

            String branch_no = branchBO.getAllBranchDetailes().get(0).getBranch_No();
            lblBranchNo.setText(branch_no);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadWorkerId() throws SQLException, ClassNotFoundException {
        lblWorkerNo.setText(workersBO.generateNextWorkerId());
    }


    @FXML
    void WorkertNo(ActionEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        String worker_Id = lblWorkerNo.getText();
        String name = txtName.getText();
        String nic =txtNic.getText();
        String address = txtAddress.getText();
        String job=txtPost.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String date_of_birth = txtDateOfBirth.getText();
        double salary= Double.parseDouble(txtSalary.getText());



        boolean matchesName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$").matcher(txtName.getText()).matches();
        boolean matchesNic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(txtNic.getText()).matches();
        boolean matchesAddress = Pattern.compile("^[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+$").matcher(txtAddress.getText()).matches();
        boolean matchesMobile = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$").matcher(txtMobile.getText()).matches();
        boolean matchesEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(txtEmail.getText()).matches();
        boolean matchesDob = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(txtDateOfBirth.getText()).matches();
//        boolean matchesSalary = Pattern.compile("").matcher(txtSalary.getText()).matches();

        if (matchesName) {
            if (matchesNic) {
                if (matchesAddress) {
                    if (matchesMobile) {
                        if (matchesEmail) {
                            if (matchesDob) {
//                                if (matchesSalary){
                                    try {
                                        ArrayList<WorkersDTO> collect = workersBO.getAllWorkersDetailes().stream().filter(workersDTO -> workersDTO.getNic().equals(nic)).collect(Collectors.toCollection(ArrayList::new));
                                        if (collect.size() > 0) {
                                            new Alert(Alert.AlertType.WARNING, "Already added !").show();
                                        } else {
                                            DBConnection.getInstance().getConnection().setAutoCommit(false);
                                            boolean isAdded = workersBO.addWorkers(new WorkersDTO(
                                                    worker_Id,
                                                    name,
                                                    nic,
                                                    address,
                                                    job,
                                                    mobile,
                                                    email,
                                                    date_of_birth,
                                                    "B002",
                                                    salary
                                            ));

                                            if (isAdded) {
                                                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                                ButtonType cancel = new ButtonType("Cancel ", ButtonBar.ButtonData.CANCEL_CLOSE);
                                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? !", ok, cancel);
                                                Optional<ButtonType> buttonType = alert.showAndWait();
                                                if (buttonType.orElse(cancel) == ok) {
                                                    DBConnection.getInstance().getConnection().commit();
                                                    new Alert(Alert.AlertType.CONFIRMATION,"Added Success !").show();
                                                    initialize();
                                                    btnClearOnAction(new ActionEvent());
                                                } else {
                                                    DBConnection.getInstance().getConnection().rollback();
                                                }
                                            } else {
                                                DBConnection.getInstance().getConnection().rollback();
                                            }
                                        }
                                    } catch (ClassNotFoundException | SQLException e) {
                                        e.printStackTrace();
                                    } finally {
                                        DBConnection.getInstance().getConnection().setAutoCommit(true);
                                    }
//                                } else {
//                                    txtSalary.setFocusColor(Paint.valueOf("red"));
//                                    txtSalary.requestFocus();
//                                }
                               } else {
                                    txtDateOfBirth.setFocusColor(Paint.valueOf("red"));
                                    txtDateOfBirth.requestFocus();
                                }
                            } else {
                                txtEmail.setFocusColor(Paint.valueOf("red"));
                                txtEmail.requestFocus();
                            }
                        } else {
                            txtMobile.setFocusColor(Paint.valueOf("red"));
                            txtMobile.requestFocus();
                        }
                    } else {
                        txtAddress.setFocusColor(Paint.valueOf("red"));
                        txtAddress.requestFocus();
                    }
                } else {
                    txtNic.setFocusColor(Paint.valueOf("red"));
                    txtNic.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("RED"));
                txtName.requestFocus();
            }


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtMobile.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnDeletelOnAction(ActionEvent event) {
       try {
            boolean isDeleted = workersBO.deleteWorkers(txtWorkerNo.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted !..").show();
                btnClearOnAction(new ActionEvent());
                initialize();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String workerNo = txtWorkerNo.getText();
        boolean isAvailables= workersBO.searchWorkers(workerNo);
        if (isAvailables) {
            WorkersDTO member = workersBO.searchByWorkerNo(workerNo);
            lblWorkerNo.setText(member.getWorker_Id());
            txtName.setText(member.getName());
            txtNic.setText(member.getNic());
            txtAddress.setText(member.getAddress());
            txtPost.setText(member.getJob());
            txtMobile.setText(member.getMobile());
            txtEmail.setText(member.getEmail());
            txtDateOfBirth.setText(member.getDate_of_birth());
            lblBranchNo.setText("B002");
           txtSalary.setText(String.valueOf(member.getSalary()));
        } else {
            new Alert(Alert.AlertType.WARNING,"Worker is not found !").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String worker_Id = lblWorkerNo.getText();
        String name = txtName.getText();
        String nic =txtNic.getText();
        String address = txtAddress.getText();
        String job=txtPost.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String date_of_birth = txtDateOfBirth.getText();
        double salary= Double.parseDouble(txtSalary.getText());


        boolean matchesName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$").matcher(txtName.getText()).matches();
        boolean matchesNic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(txtNic.getText()).matches();
        boolean matchesAddress = Pattern.compile("^[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+$").matcher(txtAddress.getText()).matches();
        boolean matchesMobile = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$").matcher(txtMobile.getText()).matches();
        boolean matchesEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(txtEmail.getText()).matches();
        boolean matchesDob = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(txtDateOfBirth.getText()).matches();


        if (matchesName) {
            if (matchesNic) {
                if (matchesAddress) {
                    if (matchesMobile) {
                        if (matchesEmail) {
                            if (matchesDob) {
                                try {

                                    DBConnection.getInstance().getConnection().setAutoCommit(false);
                                    boolean  isUpdated = workersBO.updateWorkers(new WorkersDTO(
                                            worker_Id,
                                            name,
                                            nic,
                                            address,
                                            job,
                                            mobile,
                                            email,
                                            date_of_birth,
                                            "B002",
                                            salary
                                    ));

                                    if(isUpdated){

                                        ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                                        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? !", ok, cancel);
                                        Optional<ButtonType> buttonType = alert.showAndWait();
                                        if(buttonType.orElse(cancel)==ok){
                                            DBConnection.getInstance().getConnection().commit();

                                            new Alert(Alert.AlertType.CONFIRMATION, "Update Success !").show();
                                            btnClearOnAction(new ActionEvent());
                                        } else {
                                            DBConnection.getInstance().getConnection().rollback();
                                        }
                                    }
                                } catch (SQLException | ClassNotFoundException throwables) {
                                    throwables.printStackTrace();
                                }
                            } else {
                                txtDateOfBirth.setFocusColor(Paint.valueOf("red"));
                                txtDateOfBirth.requestFocus();
                            }
                        } else {
                            txtEmail.setFocusColor(Paint.valueOf("red"));
                            txtEmail.requestFocus();
                        }
                    } else {
                        txtMobile.setFocusColor(Paint.valueOf("red"));
                        txtMobile.requestFocus();
                    }
                } else {
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                    txtAddress.requestFocus();
                }
            } else {
                txtNic.setFocusColor(Paint.valueOf("red"));
                txtNic.requestFocus();
            }
        } else {
            txtName.setFocusColor(Paint.valueOf("RED"));
            txtName.requestFocus();
        }

    }

    @FXML
    void rbtnFemale(MouseEvent event) {

    }

    public void btnClearOnAction(ActionEvent event) {
        lblWorkerNo.setText(null);
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtDateOfBirth.clear();
        txtSalary.clear();
        txtPost.clear();
        lblBranchNo.setText(null);
        txtWorkerNo.clear();
        initialize();

    }
}
