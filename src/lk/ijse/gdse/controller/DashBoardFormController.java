package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.util.navigate.Navigation;
import lk.ijse.gdse.util.navigate.Routes;

import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private AnchorPane pane2;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu memuAccount;

    @FXML
    private MenuItem MenuItemSavingAcc;

    @FXML
    private MenuItem MenuItemChildAcc;

    @FXML
    private MenuItem menuItemChildGuardian;

    @FXML
    private Menu menuTransaction;

    @FXML
    private MenuItem MenuItemDeposit;

    @FXML
    private MenuItem menuItemWithdraw;

    @FXML
    private MenuItem menuItemChildDeposit;

    @FXML
    private Menu menuTransfer;

    @FXML
    private MenuItem menuItemAcctoAcc;

    @FXML
    private Menu menuReport;

    @FXML
    private MenuItem menuItemViewCustomerDetails;

    @FXML
    private MenuItem menuItemViewAccountDetails;

    @FXML
    private MenuItem menuItemViewDeposit;

    @FXML
    private MenuItem menuItemViewLoan;

    @FXML
    private MenuItem menuItemViewWithdrow;

    @FXML
    private MenuItem menuWorkersDetails;

    @FXML
    private Menu menuLoan;

    @FXML
    private MenuItem menuItemCreateLoan;

    @FXML
    private MenuItem menuItemDepositLoan;

    @FXML
    private MenuItem menuWorkerDetails;

    @FXML
    private AnchorPane dashboardContext;

    @FXML
    private JFXButton btnExit;


    public void memuAccountOnAction(ActionEvent actionEvent) {
    }

    public void MenuItemSavingAccOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SAVING,dashboardContext);

    }

    public void MenuItemChildAccOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CHILD,dashboardContext);


    }

    public void menuTransactionOnAction(ActionEvent actionEvent) throws IOException {


    }

    public void MenuItemDepositOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DEPOSIT,dashboardContext);

    }

    public void menuItemWithdrawOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.WITHDRAW,dashboardContext);

    }

    public void menuTransferOnAction(ActionEvent actionEvent) {


    }

    public void menuItemAcctoAccONAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACCTOACC,dashboardContext);


    }


    public void menuReportOnAction(ActionEvent actionEvent) {

    }



    public void menuLoanOnAction(ActionEvent actionEvent) {


    }

    public void btnExitlOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();


    }


    public void menuItemViewDetailsCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEWCUSTOMERDETAILS,dashboardContext);


    }

    public void menuItemViewAccountDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEWACCOUNTDETAILS,dashboardContext);

    }

    public void menuItemViewDepositOnAction(ActionEvent actionEvent) throws IOException {

        Navigation.navigate(Routes.VIEWDEPOSIT,dashboardContext);
    }

    public void menuItemViewLoanOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEWLOAN,dashboardContext);

    }

    public void menuItemViewWithdrowOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEWWITHDRAW,dashboardContext);


    }

    public void menuItemCreateLoanOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOANCREATE,dashboardContext);
    }

    public void menuWorkerDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.WORKER,dashboardContext);

    }

    public void menuItemChildGuardianOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.GUARDIAN,dashboardContext);
    }

    public void menuItemChildDepositOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CHILDDEPOSIT,dashboardContext);

    }

    public void menuItemDepositLoanOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DEPOSITLOAN,dashboardContext);
    }

    public void menuWorkersDetailsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.WORKERDETAILS,dashboardContext);

    }
}
