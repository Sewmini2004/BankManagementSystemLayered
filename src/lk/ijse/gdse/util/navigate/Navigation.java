package lk.ijse.gdse.util.navigate;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {

            case LOGIN:
                window.setTitle("LOGIN Form");
                window.setResizable(false);
                initUI("LoginForm.fxml");
                break;


            case DASHBOARD:
                window.setTitle("Dashboard");
                window.setResizable(false);
                initUI("DashBoardForm.fxml");
                break;



                case SAVING:
                window.setTitle("SAVING ACCOUNT Form");
                window.setResizable(false);
                initUI("CreateAccount.fxml");
                break;

            case CHILD:
                window.setTitle("Child Account Form");
                window.setResizable(false);
                initUI("ChildAccountForm.fxml");
                break;


            case ACCTOACC:
                window.setTitle("Account To Account Form");
                window.setResizable(false);
                initUI("InterAccountTransactionFrom.fxml");
                break;

            case DEPOSIT:
                window.setTitle("Deposit Form");
                window.setResizable(false);
                initUI("DepositToAccountFrom.fxml");
                break;


            case WITHDRAW:
                window.setTitle("Withdraw Form");
                window.setResizable(false);
                initUI("WithdrowFromAccountForm.fxml");
                break;


            case ACCOUNT:
                break;
            case TRANSACTION:
                break;
            case BALANCE:
                break;
            case REPORT:
                break;

                case LOAN:
                window.setTitle("Loan Details Form");
                window.setResizable(false);
                initUI("LoanCreateFrom.fxml");
                break;

            case VIEWACCOUNTDETAILS:
                window.setTitle("View Account Details Form");
                window.setResizable(false);
                initUI("ViewAccountDetailsForm.fxml");
                break;


            case VIEWDEPOSIT:
                window.setTitle("View Deposit Form");
                window.setResizable(false);
                initUI("ViewDepositFrom.fxml");
                break;


            case BALANCECHECK:
                break;

            case VIEWCUSTOMERDETAILS:
                window.setTitle("View Customer Form");
                window.setResizable(false);
                initUI("ViewCustomerDetails.fxml");
                break;

            case WORKERDETAILS:
                window.setTitle("View Worker Form");
                window.setResizable(false);
                initUI("View Worker Details.fxml");
                break;


            case VIEWWITHDRAW:
                window.setTitle("View Withdraw Form");
                window.setResizable(false);
                initUI("ViewWithdrowFrom.fxml");
                break;


            case WORKER:
                window.setTitle("Workers Form");
                window.setResizable(false);
                initUI("WorkersForm.fxml");
                break;


            case LOANCREATE:
                window.setTitle("Loan Create Form");
                window.setResizable(false);
                initUI("LoanCreateFrom.fxml");
                break;

            case VIEWLOAN:
                window.setTitle("Loan Details Form");
                window.setResizable(false);
                initUI("LoanDetailsFrom.fxml");
                break;

            case CHILDDEPOSIT:
                window.setTitle("Child Deposit Form");
                window.setResizable(false);
                initUI("Deposit_Child.fxml");
                break;


            case DEPOSITLOAN:
                window.setTitle(" Loan Deposit Form");
                window.setResizable(false);
                initUI("LoanDeposit.fxml");
                break;

            case GUARDIAN:
                window.setTitle("Child Guardian Details Form");
                window.setResizable(false);
                initUI("ChildGuardianFrom.fxml");
                break;



            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }





    }

    private static void initUI(String s) throws IOException {
    Navigation.pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/lk/ijse/gdse/view/" + s))));


    }
}