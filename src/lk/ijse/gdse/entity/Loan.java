package lk.ijse.gdse.entity;

public class Loan implements SuperEntity{
    private String loan_Id;
    private double installment_Amount;
    private double no_Of_Installment;
    private String date_Of_Loan;
    private String time;
    private String saving_Account_No;
    private double interest;
    private double loan_Value;
    private double loan_Balance;
    private double total_Amount;

    public Loan(String loan_Id, double installment_Amount, double no_Of_Installment, String date_Of_Loan, String time, String saving_Account_No, double interest, double loan_Value, double loan_Balance, double total_Amount) {
        this.loan_Id = loan_Id;
        this.installment_Amount = installment_Amount;
        this.no_Of_Installment = no_Of_Installment;
        this.date_Of_Loan = date_Of_Loan;
        this.time = time;
        this.saving_Account_No = saving_Account_No;
        this.interest = interest;
        this.loan_Value = loan_Value;
        this.loan_Balance = loan_Balance;
        this.total_Amount = total_Amount;
    }

    public Loan() {
    }

    public String getLoan_Id() {
        return loan_Id;
    }

    public void setLoan_Id(String loan_Id) {
        this.loan_Id = loan_Id;
    }

    public double getInstallment_Amount() {
        return installment_Amount;
    }

    public void setInstallment_Amount(double installment_Amount) {
        this.installment_Amount = installment_Amount;
    }

    public double getNo_Of_Installment() {
        return no_Of_Installment;
    }

    public void setNo_Of_Installment(double no_Of_Installment) {
        this.no_Of_Installment = no_Of_Installment;
    }

    public String getDate_Of_Loan() {
        return date_Of_Loan;
    }

    public void setDate_Of_Loan(String date_Of_Loan) {
        this.date_Of_Loan = date_Of_Loan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSaving_Account_No() {
        return saving_Account_No;
    }

    public void setSaving_Account_No(String saving_Account_No) {
        this.saving_Account_No = saving_Account_No;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getLoan_Value() {
        return loan_Value;
    }

    public void setLoan_Value(double loan_Value) {
        this.loan_Value = loan_Value;
    }

    public double getLoan_Balance() {
        return loan_Balance;
    }

    public void setLoan_Balance(double loan_Balance) {
        this.loan_Balance = loan_Balance;
    }

    public double getTotal_Amount() {
        return total_Amount;
    }

    public void setTotal_Amount(double total_Amount) {
        this.total_Amount = total_Amount;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loan_Id='" + loan_Id + '\'' +
                ", installment_Amount=" + installment_Amount +
                ", no_Of_Installment=" + no_Of_Installment +
                ", date_Of_Loan='" + date_Of_Loan + '\'' +
                ", time='" + time + '\'' +
                ", saving_Account_No='" + saving_Account_No + '\'' +
                ", interest=" + interest +
                ", loan_Value=" + loan_Value +
                ", loan_Balance=" + loan_Balance +
                ", total_Amount=" + total_Amount +
                '}';
    }
}
