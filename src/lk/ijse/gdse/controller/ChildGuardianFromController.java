package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.gdse.dao.custom.ChildGuardianDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.ChildGuardianDTO;
import lk.ijse.gdse.dto.Child_AccountDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.ChildGuardianBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChildGuardianFromController {
    public Rectangle rdioBtnFemale;
    public ToggleGroup Gender;
    public JFXRadioButton rbtnFemale;
    public JFXRadioButton rbtnMale;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public JFXButton btnUpdate;
    public JFXTextField txtDateOfBirth;
    public JFXTextField txtMobile;
    public JFXTextField txtEmail;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXButton btnSearch;
    public JFXTextField txtNicSearch;
    public JFXButton btnDelete;


    private final ChildGuardianBO childGuardianBO=ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.CHILD_GUARDIAN);
    public JFXButton btnClear;

    public void rbtnFemale(MouseEvent mouseEvent) {
    }

    public void btnrfemaleOnAction(ActionEvent event) {
    }

    public void rbtnMaleOnAction(ActionEvent event) {
    }

    public void btnAddOnAction(ActionEvent event) throws SQLException {

        String nic = txtNic.getText();
        String name = txtName.getText();
        String address= txtAddress.getText();
        String dateOfBirth= txtDateOfBirth.getText();
        String email = txtEmail.getText();
        String mobile= txtMobile.getText();

        String gender=null;
        if (rbtnMale.isSelected()) {
            gender="MALE";
        } else if (rbtnFemale.isSelected()) {
            gender = "FEMALE";
        }else {
            new Alert(Alert.AlertType.WARNING, "Please select Gender !").show();
            return;
        }
            boolean matchesName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$").matcher(txtName.getText()).matches();
            boolean matchesNic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(txtNic.getText()).matches();
            boolean matchesAddress = Pattern.compile("^[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+$").matcher(txtAddress.getText()).matches();
            boolean matchesMobile = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$").matcher(txtMobile.getText()).matches();
            boolean matchesEmail = Pattern.compile("").matcher(txtEmail.getText()).matches();
            boolean matchesDob = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(txtDateOfBirth.getText()).matches();



            if (matchesName) {
                if (matchesNic) {
                    if (matchesAddress) {
                        if (matchesMobile) {
                            if (matchesEmail) {
                                if (matchesDob) {
                                    try {

                                        ArrayList<ChildGuardianDTO> collect = childGuardianBO.getAllChildGuardian().stream().filter(accountDTO -> accountDTO.getNic().equals(nic)).collect(Collectors.toCollection(ArrayList::new));
                                        if (collect.size() > 0) {

                                            new Alert(Alert.AlertType.WARNING, "Already added !").show();
                                        } else {

                                            DBConnection.getInstance().getConnection().setAutoCommit(false);
                                            boolean isAdded = childGuardianBO.addChildGuardian(new ChildGuardianDTO(
                                                    nic,
                                                    name,
                                                    gender,
                                                    address,
                                                    dateOfBirth,
                                                    mobile,
                                                    email
                                            ));

                                            if (isAdded) {
                                                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                                ButtonType cancel = new ButtonType("Cancel ", ButtonBar.ButtonData.CANCEL_CLOSE);
                                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? !", ok, cancel);
                                                Optional<ButtonType> buttonType = alert.showAndWait();
                                                if (buttonType.orElse(cancel) == ok) {
                                                    DBConnection.getInstance().getConnection().commit();
                                                    new Alert(Alert.AlertType.CONFIRMATION,"Added Success !").show();

                                                } else {
                                                    DBConnection.getInstance().getConnection().rollback();
                                                }
                                            } else {
                                                DBConnection.getInstance().getConnection().rollback();
                                            }
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } finally {
                                        DBConnection.getInstance().getConnection().setAutoCommit(true);
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


    public void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtNic.getScene().getWindow();
        stage.close();

    }

    public void btnUpdateOnAction(ActionEvent event) throws SQLException {


        String nic = txtNic.getText();
        String name = txtName.getText();
        String address= txtAddress.getText();
        String dateOfBirth= txtDateOfBirth.getText();
        String email = txtEmail.getText();
        String mobile= txtMobile.getText();

        String gender=null;
        if (rbtnMale.isSelected()) {
            gender="MALE";
        } else if (rbtnFemale.isSelected()) {
            gender = "FEMALE";
        }else {
            new Alert(Alert.AlertType.WARNING, "Please select Gender !").show();
            return;
        }
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
                                    ArrayList<ChildGuardianDTO> collect = childGuardianBO.getAllChildGuardian().stream().filter(childAccountDTO -> childAccountDTO.getNic().equals(nic)).collect(Collectors.toCollection(ArrayList::new));
                                    if(collect.size()>0) {
                                        new Alert(Alert.AlertType.WARNING, "Already added !").show();
                                    }else{
                                        DBConnection.getInstance().getConnection().setAutoCommit(false);
                                        boolean isAdded=childGuardianBO.addChildGuardian(new ChildGuardianDTO(
                                                nic,
                                                name,
                                                gender,
                                                address,
                                                dateOfBirth,
                                                mobile,
                                                email
                                         ));
                                      if (isAdded) {
                                            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                            ButtonType cancel = new ButtonType("Cancel ", ButtonBar.ButtonData.CANCEL_CLOSE);
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? !", ok, cancel);
                                            Optional<ButtonType> buttonType = alert.showAndWait();
                                            if (buttonType.orElse(cancel) == ok) {
                                                DBConnection.getInstance().getConnection().commit();
                                                new Alert(Alert.AlertType.CONFIRMATION,"Updated Success !").show();

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

    public void btnDeletelOnAction(ActionEvent event)  {


        try {
          boolean  isDeleted = childGuardianBO.deleteChildGuardian(txtNic.getText());

        if (isDeleted){
        new Alert(Alert.AlertType.CONFIRMATION, " Deleted !..").show();
    } } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void txtNicSearchOnAction(ActionEvent event) {
    }

    public void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String nic= txtNicSearch.getText();
        boolean isAvailables = childGuardianBO.isExistsChildGuardian(nic);
        if (isAvailables) {
            ChildGuardianDTO member= childGuardianBO.searchByNicChildGuardian(nic);

            txtNic.setText(member.getNic());
            txtName.setText(member.getName());
            txtAddress.setText(member.getAddress());
            txtDateOfBirth.setText(member.getDate_Of_Birth());
            txtMobile.setText(member.getMobile());
            txtEmail.setText(member.getEmail());


            String gender = member.getGender();
            if (gender.equals("FEMALE")) {
                rbtnFemale.setSelected(true);
            } else {
                rbtnMale.setSelected(true);
            }

        } else {
            new Alert(Alert.AlertType.WARNING,"Account is not found").show();
        }

    }

    public void btnClearOnAction(ActionEvent event) {
        txtNic.clear();
        txtName.clear();
        rbtnFemale.setSelected(false);
        rbtnMale.setSelected(false);
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtDateOfBirth.clear();


    }
}
