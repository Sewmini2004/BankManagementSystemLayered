package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.entity.Deposit;
import lk.ijse.gdse.entity.Withdrow;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.WithdrowBO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewWithdrowFromController {

    @FXML
    private TableView<Withdrow> tblWithdraw;

    @FXML
    private TableColumn<?, ?> colwithdrowId;

    @FXML
    private TableColumn<?, ?> colamount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> coltime;

    @FXML
    private TableColumn<?, ?> colsavingAccountNo;

    WithdrowBO withdrowBO= ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.WITHDRAWAL_MEMBER);

    public void initialize() throws SQLException, ClassNotFoundException {

        tblWithdraw.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("withdrow_Id"));
        tblWithdraw.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblWithdraw.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblWithdraw.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("time"));
        tblWithdraw.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("saving_Account_No"));

        List<Withdrow> withdrowList= withdrowBO.getAllWithdrowDetailes().stream().map(withdrow -> new Withdrow(
                withdrow.getWithdrow_Id(),
                withdrow.getAmount(),
                withdrow.getDate(),
                withdrow.getTime(),
                withdrow.getSaving_Account_No()


        )).collect(Collectors.toList());
        tblWithdraw.setItems(FXCollections.observableArrayList(withdrowList));

    }

}
