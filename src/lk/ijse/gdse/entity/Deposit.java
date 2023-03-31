package lk.ijse.gdse.entity;

public class Deposit implements SuperEntity {
    private String deposit_Id;
    private double amount;
    private String date;
    private String time;
    private String saving_Account_No;

    public String getDeposit_Id() {
        return deposit_Id;
    }

    public void setDeposit_Id(String deposit_Id) {
        this.deposit_Id = deposit_Id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public String getSaving_Account_No() {
        return saving_Account_No;
    }

    public void setSaving_Account_No(String saving_Account_No) {
        this.saving_Account_No = saving_Account_No;
    }

    public Deposit(String deposit_Id, double amount, String date, String time, String saving_Account_No) {
        this.deposit_Id = deposit_Id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.saving_Account_No = saving_Account_No;
    }

    public Deposit() {
    }
}
