package lk.ijse.gdse.entity;

public class LoanDeposit implements SuperEntity{
private String deposit_loan_Id;
private double deposit_Loan_amount;
private String loan_Id;
private String date;
private String time;


    public LoanDeposit(String deposit_loan_Id, double deposit_Loan_amount, String loan_Id, String date, String time ) {
        this.deposit_loan_Id = deposit_loan_Id;
        this.deposit_Loan_amount = deposit_Loan_amount;
        this.loan_Id = loan_Id;
        this.date = date;
        this.time = time;

    }

    public LoanDeposit() {
    }

    public String getDeposit_loan_Id() {
        return deposit_loan_Id;
    }

    public void setDeposit_loan_Id(String deposit_loan_Id) {
        this.deposit_loan_Id = deposit_loan_Id;
    }

    public double getDeposit_Loan_amount() {
        return deposit_Loan_amount;
    }

    public void setDeposit_Loan_amount(double deposit_Loan_amount) {
        this.deposit_Loan_amount = deposit_Loan_amount;
    }

    public String getLoan_Id() {
        return loan_Id;
    }

    public void setLoan_Id(String loan_Id) {
        this.loan_Id = loan_Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return "LoanDeposit{" +
                "deposit_loan_Id='" + deposit_loan_Id + '\'' +
                ", deposit_Loan_amount='" + deposit_Loan_amount + '\'' +
                ", loan_Id='" + loan_Id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +

                '}';
    }
}
