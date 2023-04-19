package lk.ijse.gdse.dao;

import lk.ijse.gdse.dao.custom.imple.*;

import java.sql.Connection;
public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new DaoFactory();
        }
        return daoFactory;
    }



    public enum DaoTypes{
        BRANCH,CHILD_ACCOUNT,CHILD_GUARDIAN,DEPOSIT_CHILD,DEPOSIT_LOAN_AMOUNT,DEPOSIT_TRANSACTION,INTER_ACCOUNT_TRANSACTION,LOAN,SAVINGS_ACCOUNT,WITHDRAWAL_MEMBER,WORKER
    }

    public <T extends SuperDAO> T getDao(DaoTypes types, Connection connection){
        switch (types){
            case BRANCH:
                return (T) new BranchDAOImple();
            case CHILD_ACCOUNT:
                return (T) new Child_AccountDAOImple(connection);
            case WITHDRAWAL_MEMBER:
                return (T) new WithdrawalMemberDAOImple();
            case SAVINGS_ACCOUNT:
                return (T) new SavingAccountDAOImple();
            case DEPOSIT_TRANSACTION:
                return (T) new DepositTransactionsDAOImple();
            case WORKER:
                return (T) new WorkerDAOImple();
            case CHILD_GUARDIAN:
                return (T) new ChildGuardianDAOImple(connection);
            case DEPOSIT_CHILD:
                return (T) new DepositChildDAOImple();
            case DEPOSIT_LOAN_AMOUNT:
                return (T) new DepositLoanAmountDAOImple();
            case INTER_ACCOUNT_TRANSACTION:

                return (T) new InterAccountTransactionDAOImple();
            case LOAN:
                return (T) new LoanDAOImple();
            default:
                return null;
        }

    }
}