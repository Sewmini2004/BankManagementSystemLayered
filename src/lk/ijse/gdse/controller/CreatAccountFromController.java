package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.AccountDTO;
import lk.ijse.gdse.entity.Account;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.BranchBO;
import lk.ijse.gdse.service.custom.SavingAccountBO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatAccountFromController {

    public Label lblCreateDate;
    public Label lblCreateTime;
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXRadioButton rbtnFemale;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton rbtnMale;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Label lblMemberAcc;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblSavingBalance;

    @FXML
    private Label lblBranch;

    @FXML
    private JFXTextField txtAccountNo;

    @FXML
    private Label lblSavingAcc;

    @FXML
    private JFXTextField txtDateOfBirth;

    @FXML
    private JFXButton btnSearch;

    private final SavingAccountBO savingAccountBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SAVINGS_ACCOUNT);
    private final BranchBO branchBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.BRANCH);

    public void initialize(){
        try {
            loadMemberId();
            loadAccNo();
            String branch_no = branchBO.getAllBranchDetailes().get(0).getBranch_No();
            lblBranch.setText(branch_no);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAccNo() throws SQLException, ClassNotFoundException {
        lblSavingAcc.setText(savingAccountBO.generateNextAccNo());
    }

    private void loadMemberId() throws SQLException, ClassNotFoundException {

        lblMemberAcc.setText(savingAccountBO.generateNextId());
    }


    @FXML
    void AccountNo(ActionEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        String memberId = lblMemberAcc.getText();
        String savingsAccNo = lblSavingAcc.getText();

        String name = txtName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String date = txtDateOfBirth.getText();

        String gender=null;
        if (rbtnMale.isSelected()) {
            gender="MALE";
        } else if (rbtnFemale.isSelected()) {
            gender = "FEMALE";
        } else {
            new Alert(Alert.AlertType.WARNING,"Please select Gender !").show();
            return;
        }


        //validation
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
                                    //me yaka meke klin register welda kyla blan ek tmai mee
                                    ArrayList<AccountDTO> collect = savingAccountBO.getAllMembers().stream().filter(accountDTO -> accountDTO.getNic().equals(nic)).collect(Collectors.toCollection(ArrayList::new));
                                    if (collect.size() > 0) {
                                        //klin register wela atha ekne meke e acc ek gana wisthra thynne
                                        new Alert(Alert.AlertType.WARNING, "Already added !").show();
                                    } else {
                                        //ehem nthnm aluth ekek thmai uwa add krnn ona
                                        DBConnection.getInstance().getConnection().setAutoCommit(false);
                                        boolean isAdded = savingAccountBO.addMember(new AccountDTO(
                                                memberId,
                                                savingsAccNo,
                                                name,
                                                nic,
                                                gender,
                                                address,
                                                mobile,
                                                email,
                                                date,
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

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) txtMobile.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnDeletelOnAction(ActionEvent event) {
        try {
            boolean isDeleted = savingAccountBO.deleteMember(txtAccountNo.getText());
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
        String accNo = txtAccountNo.getText();
        boolean isAvailables= savingAccountBO.searchMember(accNo);
        if (isAvailables) {
            AccountDTO member = savingAccountBO.getMember(accNo);
            lblMemberAcc.setText(member.getMember_Id());
            lblSavingAcc.setText(member.getSaving_Account_No());
            txtName.setText(member.getName());
            txtNic.setText(member.getNic());
            //
            String gender = member.getGender();
            if (gender.equals("FEMALE")) {
                rbtnFemale.setSelected(true);
            } else {
                rbtnMale.setSelected(true);
            }
            //
            txtAddress.setText(member.getAddress());
            txtMobile.setText(member.getMobile());
            txtEmail.setText(member.getEmail());
            txtDateOfBirth.setText(member.getDate_of_birth());
            lblSavingBalance.setText("Saving_Balance   : "+member.getSaving_Balance());
            lblDate.setText("Create Date          :  "+member.getCreat_Date());
            lblTime.setText("Create Time          :  "+member.getCreat_Time());
        } else {
            new Alert(Alert.AlertType.WARNING,"Account is not found").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String memberId = lblMemberAcc.getText();
        String savingsAccNo = lblSavingAcc.getText();

        String name = txtName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String date = txtDateOfBirth.getText();

        String gender=null;
        if (rbtnMale.isSelected()) {
            gender="MALE";
        } else if (rbtnFemale.isSelected()) {
            gender = "FEMALE";
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

                                  DBConnection.getInstance().getConnection().setAutoCommit(false);
                                  boolean  isUpdated = savingAccountBO.updateMember(new AccountDTO(
                                            memberId,
                                            savingsAccNo,
                                            name,
                                            nic,
                                            gender,
                                            address,
                                            mobile,
                                            email,
                                            date,
                                            LocalDate.now().toString(),
                                            LocalTime.now().toString(),
                                            0.0,
                                            "B002"
                                    ));

                                  if (isUpdated) {

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
    void btnrfemaleOnAction(ActionEvent event) {

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
        txtName.clear();
        txtNic.clear();
        rbtnFemale.setSelected(false);
        rbtnMale.setSelected(false);
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtDateOfBirth.clear();
        lblBranch.setText(null);
        txtAccountNo.clear();
        lblSavingBalance.setText("Saving_Balance   :");
        lblDate.setText("Create Date          :");
        lblTime.setText("Create Time          :");
        initialize();
    }

    public void AccoutntNoOnAction(ActionEvent event) {

    }
}
