package lk.ijse.gdse.service;

import lk.ijse.gdse.dao.custom.imple.DepositLoanAmountDAOImple;
import lk.ijse.gdse.service.custom.imple.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory() {
    if (serviceFactory==null){
        serviceFactory = new ServiceFactory();
    }
    return serviceFactory;
    }
    public enum ServiceType{
    BRANCH,CHILD_ACCOUNT,CHILD_GUARDIAN,DEPOSIT_CHILD,DEPOSIT_LOAN_AMOUNT,DEPOSIT_TRANSACTION,INTER_ACCOUNT_TRANSACTION,LOAN,SAVINGS_ACCOUNT,WITHDRAWAL_MEMBER,WORKER

    }
    public <T extends SuperBO>T getService(ServiceType serviceType) {
      switch (serviceType){
          case CHILD_ACCOUNT:
              return (T) new ChildAccountBOImple();
          case SAVINGS_ACCOUNT:
              return (T) new SavingAccountBOImple();
          case LOAN:
              return (T) new LoanBOImple();
          case DEPOSIT_TRANSACTION:
              return (T) new DepositBOImple();
          case BRANCH:
              return (T) new BranchBOImple();
          case WORKER:
               return (T) new WorkersBOImple();
          case WITHDRAWAL_MEMBER:
              return (T) new WithdrowBOImple();
          case DEPOSIT_CHILD:
              return (T) new ChildDepositBOImple();
          case CHILD_GUARDIAN:
              return (T) new ChildGuardianBOImple();
          case DEPOSIT_LOAN_AMOUNT:
              return (T) new LoanDepositBOImple();
          case INTER_ACCOUNT_TRANSACTION:
              return (T) new InterAccountTransactionBOImple();
           default:
         return null;

      }
    }
}
