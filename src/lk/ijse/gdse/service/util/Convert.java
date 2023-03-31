package lk.ijse.gdse.service.util;

import lk.ijse.gdse.dto.*;
import lk.ijse.gdse.entity.*;

public class Convert {

    //mek threnodaq manika oo methnin api entity ekak DTO krnnai DTO ekk entity krnnai method tk  wenna hridaha

    //from kyla ptan gnn ewgene entity DTO wlata convert krno hrida
    //to kyla ptan gnn ewagen entity wlta convert krno thrunda oy 2?ooo

    // from______    entity -> DTO
    //to______       DTO   -> entity
    public static Child_AccountDTO fromChildAccount(Child_Account child_account){
        return new Child_AccountDTO(
                child_account.getChild_Id(),
                child_account.getChild_Account_No(),
                child_account.getName(),
                child_account.getGender(),
                child_account.getAddress(),
                child_account.getDate_Of_Birth(),
                child_account.getNic(),
                child_account.getCreat_Date(),
                child_account.getCreat_Time(),
                child_account.getBalance(),
                child_account.getBranch_No()
        );
    }
    public static Child_Account toChildAccount(Child_AccountDTO child_account){
        return new Child_Account(
                child_account.getChild_Id(),
                child_account.getChild_Account_No(),
                child_account.getName(),
                child_account.getGender(),
                child_account.getAddress(),
                child_account.getDate_Of_Birth(),
                child_account.getNic(),
                child_account.getCreat_Date(),
                child_account.getCreat_Time(),
                child_account.getBalance(),
                child_account.getBranch_No()
        );
    }
    public static AccountDTO fromAccount(Account account){
        return new AccountDTO(
                account.getMember_Id(),
                account.getSaving_Account_No(),
                account.getName(),
                account.getNic(),
                account.getGender(),
                account.getAddress(),
                account.getMobile(),
                account.getEmail(),
                account.getDate_of_birth(),
                account.getCreat_Date(),
                account.getCreat_Time(),
                account.getSaving_Balance(),
                account.getBranch_No()
        );

    }

    public static Account toAccount(AccountDTO account){
      return new Account(
              account.getMember_Id(),
              account.getSaving_Account_No(),
              account.getName(),
              account.getNic(),
              account.getGender(),
              account.getAddress(),
              account.getMobile(),
              account.getEmail(),
              account.getDate_of_birth(),
              account.getCreat_Date(),
              account.getCreat_Time(),
              account.getSaving_Balance(),
              account.getBranch_No()
      );
    }

    public static WorkersDTO fromWorkers(Workers workers){
         return new WorkersDTO(
                workers.getWorker_Id(),
                workers.getName(),
                workers.getNic(),
                workers.getAddress(),
                workers.getJob(),
                workers.getMobile(),
                workers.getEmail(),
                workers.getDate_of_birth(),
                Workers.getBranch_No(),
                workers.getSalary()
         );
    }
  public static Workers toWorkers(WorkersDTO workers){
      return new Workers(
              workers.getWorker_Id(),
              workers.getName(),
              workers.getNic(),
              workers.getAddress(),
              workers.getJob(),
              workers.getMobile(),
              workers.getEmail(),
              workers.getDate_of_birth(),
              Workers.getBranch_No(),
              workers.getSalary()
      );

  }
 public static Withdrow toWithdrow(WithdrowDTO withdrowDTO){
     return new Withdrow(
             withdrowDTO.getWithdrow_Id(),
             withdrowDTO.getAmount(),
             withdrowDTO.getDate(),
             withdrowDTO.getTime(),
             withdrowDTO.getSaving_Account_No()
     );
}



    public static WithdrowDTO fromWithdrow(Withdrow withdrow){
        return new WithdrowDTO(
                withdrow.getWithdrow_Id(),
                withdrow.getAmount(),
                withdrow.getDate(),
                withdrow.getTime(),
                withdrow.getSaving_Account_No()
        );
    }

   public static ChildGuardian toChildGuardian(ChildGuardianDTO childGuardian){
         return new ChildGuardian(
               childGuardian.getNic(),
               childGuardian.getName(),
               childGuardian.getGender(),
               childGuardian.getAddress(),
               childGuardian.getDate_Of_Birth(),
               childGuardian.getMobile(),
               childGuardian.getEmail()
         );
   }



    public static ChildGuardianDTO fromChildGuardian(ChildGuardian childGuardian){
        return new ChildGuardianDTO(
                childGuardian.getNic(),
                childGuardian.getName(),
                childGuardian.getGender(),
                childGuardian.getAddress(),
                childGuardian.getDate_Of_Birth(),
                childGuardian.getMobile(),
                childGuardian.getEmail()
        );
    }

    public static ChildDepositDTO fromChildDeposit(ChildDeposit childDeposit){
       return new ChildDepositDTO(
                childDeposit.getDeposit_Id(),
                childDeposit.getAmount(),
                childDeposit.getDate(),
                childDeposit.getTime(),
                childDeposit.getChild_Account_No()
       );
    }

    public static ChildDeposit toChildDeposit(ChildDepositDTO childDeposit){
        return new ChildDeposit(
                childDeposit.getDeposit_Id(),
                childDeposit.getAmount(),
                childDeposit.getDate(),
                childDeposit.getTime(),
                childDeposit.getChild_Account_No()
        );
    }
    public static Deposit toDeposit(DepositDTO deposit){
        return new Deposit(
                deposit.getDeposit_Id(),
                deposit.getAmount(),
                deposit.getDate(),
                deposit.getTime(),
                deposit.getSaving_Account_No()
        );
    }

    public static DepositDTO fromDeposit(Deposit deposit){
        return new DepositDTO(
                deposit.getDeposit_Id(),
                deposit.getAmount(),
                deposit.getDate(),
                deposit.getTime(),
                deposit.getSaving_Account_No()
        );
    }

    public static LoanDeposit toLoanDeposit(LoanDepositDTO loanDeposit){
        return new LoanDeposit(
                loanDeposit.getDeposit_loan_Id(),
                loanDeposit.getDeposit_Loan_amount(),
                loanDeposit.getLoan_Id(),
                loanDeposit.getDate(),
                loanDeposit.getTime()
        );

    }

    public static LoanDepositDTO fromLoanDeposit(LoanDeposit loanDeposit){
        return new LoanDepositDTO(
                loanDeposit.getDeposit_loan_Id(),
                loanDeposit.getDeposit_Loan_amount(),
                loanDeposit.getLoan_Id(),
                loanDeposit.getDate(),
                loanDeposit.getTime()
        );

    }


    public static InterAccountTransaction toInterAccountTransaction(InterAccountTransactionDTO interAccountTransaction) {
     return new InterAccountTransaction(
             interAccountTransaction.getTransaction_Id(),
             interAccountTransaction.getAmount(),
             interAccountTransaction.getDate(),
             interAccountTransaction.getTime(),
             interAccountTransaction.getAccount_01(),
             interAccountTransaction.getAccount_02()
     );
    }


    public static InterAccountTransactionDTO fromInterAccountTransaction(InterAccountTransaction interAccountTransaction) {
        return new InterAccountTransactionDTO(
                interAccountTransaction.getTransaction_Id(),
                interAccountTransaction.getAmount(),
                interAccountTransaction.getDate(),
                interAccountTransaction.getTime(),
                interAccountTransaction.getAccount_01(),
                interAccountTransaction.getAccount_02()
        );
    }


    public static LoanDTO fromLoan(Loan loan) {
        return new LoanDTO(
                loan.getLoan_Id(),
                loan.getInstallment_Amount(),
                loan.getNo_Of_Installment(),
                loan.getDate_Of_Loan(),
                loan.getTime(),
                loan.getSaving_Account_No(),
                loan.getInterest(),
                loan.getLoan_Value(),
                loan.getLoan_Balance(),
                loan.getTotal_Amount()
        );
    }

    public static Loan toLoan(LoanDTO loan) {
        return new Loan(
                loan.getLoan_Id(),
                loan.getInstallment_Amount(),
                loan.getNo_Of_Installment(),
                loan.getDate_Of_Loan(),
                loan.getTime(),
                loan.getSaving_Account_No(),
                loan.getInterest(),
                loan.getLoan_Value(),
                loan.getLoan_Balance(),
                loan.getTotal_Amount()
        );
    }


   public static Branch toBranch(BranchDTO branch){
        return new Branch(
             branch.getBranch_No(),
             branch.getName(),
             branch.getAddress(),
                branch.getBank_Email(),
                branch.getTel()
        );


   }

    public static BranchDTO fromBranch(Branch branch){
        return new BranchDTO(
                branch.getBranch_No(),
                branch.getName(),
                branch.getAddress(),
                branch.getBank_Email(),
                branch.getTel()
        );


    }

}
