package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.dto.Child_AccountDTO;
import lk.ijse.gdse.entity.ChildDeposit;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.BranchBO;
import lk.ijse.gdse.service.custom.ChildAccountBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChildAccountFormContller {
    @FXML
    private Label lblChildId;

    @FXML
    private Label lblAccNo;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtDob;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblDate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Label lblDate1;
    @FXML
    private Label lblTime;

    @FXML
    private Label lblSavingBalance;

    @FXML
    private JFXTextField txtName1;

    @FXML
    private JFXTextField txtNic1;

    @FXML
    private JFXRadioButton rbtnFemale;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton rbtnMale;

    @FXML
    private JFXTextField txtAddress1;

    @FXML
    private JFXButton btnAdd1;

    @FXML
    private JFXButton btnCancel1;

    @FXML
    private JFXButton btnUpdate1;

    @FXML
    private JFXButton btnDelete1;

    @FXML
    private Label lblMemberNo;

    @FXML
    private Label lblMemberAcc;

    @FXML
    private Label lblBranch;

    @FXML
    private JFXTextField AccountNo;

    @FXML
    private Label lblSavingAcc;

    @FXML
    private JFXTextField txtDateOfBirth;

    @FXML
    private JFXButton btnSearch;

    private final ChildAccountBO childAccountBO=ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.CHILD_ACCOUNT);
    private final BranchBO branchBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.BRANCH);


    public void initialize() {
        try {
            loadMemberId();
            loadAccNo();
            lblBranch.setText(branchBO.getAllBranchDetailes().get(0).getBranch_No());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void loadAccNo() {
        try {
            lblSavingAcc.setText(childAccountBO.generateNextAccNo());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadMemberId() {
        try {
            lblMemberAcc.setText(childAccountBO.generateNextId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void AccountNo(ActionEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String childId = lblMemberAcc.getText();
        String childAcc = lblSavingAcc.getText();
        String name = txtName1.getText();
        String address = txtAddress1.getText();
        String dateOfBirth = txtDateOfBirth.getText();
        String nic = txtNic1.getText();
        String date = lblDate1.getText();
        String time = lblTime.getText();
        String savingBalance = lblSavingBalance.getText();
        String branch = lblBranch.getText();

        String gender = null;
        if (rbtnMale.isSelected()) {
            gender = "MALE";
        } else if (rbtnFemale.isSelected()) {
            gender = "FEMALE";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select Gender !").show();
            return;
        }

        boolean matchesName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$").matcher(txtName1.getText()).matches();
        boolean matchesNic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(txtNic1.getText()).matches();
        boolean matchesAddress = Pattern.compile("^[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+$").matcher(txtAddress1.getText()).matches();
        boolean matchesDob = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(txtDateOfBirth.getText()).matches();

        if (matchesName) {
            if (matchesNic) {
                if (matchesAddress) {
                    if (matchesDob) {
                        try {
                            ArrayList<Child_AccountDTO> collect = childAccountBO.getAllChildAccDetailes().stream().filter(childAccountDTO -> childAccountDTO.getChild_Account_No().equals(name)).collect(Collectors.toCollection(ArrayList::new));
                            if (collect.size() > 0) {
                                new Alert(Alert.AlertType.WARNING, "Already added !").show();
                            } else {
                                DBConnection.getInstance().getConnection().setAutoCommit(false);
                                boolean isAdded = childAccountBO.addChild(new Child_AccountDTO(
                                        childId,
                                        childAcc,
                                        name,
                                        gender,
                                        address,
                                        dateOfBirth,
                                        nic,
                                        LocalDate.now().toString(),
                                        LocalTime.now().toString(),
                                        0.0,
                                        "B002"

                                ));


                                if (isAdded) {
                                    ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType cancel = new ButtonType("Cancel ", ButtonBar.ButtonData.CANCEL_CLOSE);
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? !", ok, cancel);
                                    Optional<ButtonType> buttonType = alert.showAndWait();
                                    if (buttonType.orElse(cancel) == ok) {
                                        DBConnection.getInstance().getConnection().commit();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Added Success !").show();
                                        initialize();
                                        btnClearOnAction(new ActionEvent());
                                    } else {
                                        DBConnection.getInstance().getConnection().rollback();
                                    }
                                } else {
                                    DBConnection.getInstance().getConnection().rollback();
                                }

                            }
                        } catch (SQLException | ClassNotFoundException throwables) {
                            throwables.printStackTrace();
                        } finally {
                            DBConnection.getInstance().getConnection().setAutoCommit(true);
                        }
                    } else {
                        txtDateOfBirth.setFocusColor(Paint.valueOf("red"));
                        txtDateOfBirth.requestFocus();
                    }
                } else {
                    txtAddress1.setFocusColor(Paint.valueOf("red"));
                    txtAddress1.requestFocus();
                }
            } else {
                txtNic1.setFocusColor(Paint.valueOf("red"));
                txtNic1.requestFocus();
            }
        } else {
            txtName1.setFocusColor(Paint.valueOf("RED"));
            txtName1.requestFocus();
        }


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtNic1.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnDeletelOnAction(ActionEvent event) {
        try {
            boolean isDeleted = childAccountBO.deleteChild(AccountNo.getText());
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
        String accNo = AccountNo.getText();
        boolean isAvailables = childAccountBO.searchChild(accNo);
        if (isAvailables) {
            Child_AccountDTO member = childAccountBO.searchChildByAccNo(accNo);
            //mn ar code ek ghuwe kohed?
            lblMemberAcc.setText(member.getChild_Id());
            lblSavingAcc.setText(member.getChild_Account_No());
            txtName1.setText(member.getName());
            txtNic1.setText(member.getNic());

            String gender = member.getGender();
            if (gender.equals("FEMALE")) {
                rbtnFemale.setSelected(true);
            } else {
                rbtnMale.setSelected(true);
            }
            txtAddress1.setText(member.getAddress());
            txtDateOfBirth.setText(member.getDate_Of_Birth());
            lblSavingBalance.setText("Saving_Balance   : " + member.getBalance());
            lblDate1.setText("Create Date          :  " + member.getCreat_Date());
            lblTime.setText("Create Time          :  " + member.getCreat_Time());
        } else {
            new Alert(Alert.AlertType.WARNING, "Account is not found").show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String childId = lblMemberAcc.getText();
        String childAcc = lblSavingAcc.getText();
        String name = txtName1.getText();
        String address = txtAddress1.getText();
        String dateOfBirth = txtDateOfBirth.getText();
        String nic = txtNic1.getText();
        String date = lblDate1.getText();
        String time = lblTime.getText();
        String savingBalance = lblSavingBalance.getText();
        String branch = lblBranch.getText();

        String gender = null;
        if (rbtnMale.isSelected()) {
            gender = "MALE";
        } else if (rbtnFemale.isSelected()) {
            gender = "FEMALE";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select Gender !").show();
            return;
        }

        boolean matchesName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$").matcher(txtName1.getText()).matches();
        boolean matchesNic = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(txtNic1.getText()).matches();
        boolean matchesAddress = Pattern.compile("^[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+[a-zA-Z0-9\\\\s,.'-]+$").matcher(txtAddress1.getText()).matches();
        boolean matchesDob = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(txtDateOfBirth.getText()).matches();

        if (matchesName) {
            if (matchesNic) {
                if (matchesAddress) {
                    if (matchesDob) {
                        try {
                            DBConnection.getInstance().getConnection().setAutoCommit(false);
                            boolean isUpdated = childAccountBO.updateChild(new Child_AccountDTO(
                                    childId,
                                    childAcc,
                                    name,
                                    gender,
                                    address,
                                    dateOfBirth,
                                    nic,
                                    LocalDate.now().toString(),
                                    LocalTime.now().toString(),
                                    0.0,
                                    "B002"
                            ));

                            if (isUpdated) {
                                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                ButtonType cancel = new ButtonType("Cancel ", ButtonBar.ButtonData.CANCEL_CLOSE);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? !", ok, cancel);
                                Optional<ButtonType> buttonType = alert.showAndWait();
                                if (buttonType.orElse(cancel) == ok) {
                                    DBConnection.getInstance().getConnection().commit();
                                    new Alert(Alert.AlertType.CONFIRMATION, "Update Success !").show();
                                    initialize();
                                    btnClearOnAction(new ActionEvent());
                                } else {
                                    DBConnection.getInstance().getConnection().rollback();
                                }
                            } else {
                                DBConnection.getInstance().getConnection().rollback();
                            }

                        } catch (SQLException | ClassNotFoundException throwables) {
                            throwables.printStackTrace();
                        } finally {
                            DBConnection.getInstance().getConnection().setAutoCommit(true);
                        }
                    } else {
                        txtDateOfBirth.setFocusColor(Paint.valueOf("red"));
                        txtDateOfBirth.requestFocus();
                    }
                } else {
                    txtAddress1.setFocusColor(Paint.valueOf("red"));
                    txtAddress1.requestFocus();
                }
            } else {
                txtNic1.setFocusColor(Paint.valueOf("red"));
                txtNic1.requestFocus();
            }
        } else {
            txtName1.setFocusColor(Paint.valueOf("RED"));
            txtName1.requestFocus();
        }

    }

    @FXML
    void btnrfemaleOnAction(ActionEvent event) {

    }

    @FXML
    void rbtnFemaleOnAction(ActionEvent event) {

    }

    @FXML
    void rbtnMaleOnAction(ActionEvent event) {

    }

    @FXML
    void txtName(ActionEvent event) {

    }

    public void btnClearOnAction(ActionEvent event) {
        lblMemberAcc.setText(null);
        lblSavingAcc.setText(null);
        txtName1.clear();
        txtNic1.clear();
        rbtnFemale.setSelected(false);
        rbtnMale.setSelected(false);
        txtAddress1.clear();
        txtDateOfBirth.clear();
        lblBranch.setText(null);
        AccountNo.clear();
        lblSavingBalance.setText("Saving_Balance   :");
        lblDate.setText("Create Date          :");
        lblTime.setText("Create Time          :");
        initialize();

    }

}