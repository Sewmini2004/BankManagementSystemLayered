package lk.ijse.gdse.entity;

import java.util.ArrayList;

public class DepositPlace {
    private String deposit_Id;
    private String saving_Account_No;
    private double amount;
    private String date;
    private String time;
    private ArrayList<Account> accounts = new ArrayList<>();

    public DepositPlace(String deposit_Id, String saving_Account_No, double amount, String date, String time, ArrayList<Account> accounts) {
        this.deposit_Id = deposit_Id;
        this.saving_Account_No = saving_Account_No;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.accounts = accounts;
    }

    public DepositPlace() {
    }

    public String getDeposit_Id() {
        return deposit_Id;
    }

    public void setDeposit_Id(String deposit_Id) {
        this.deposit_Id = deposit_Id;
    }

    public String getSaving_Account_No() {
        return saving_Account_No;
    }

    public void setSaving_Account_No(String saving_Account_No) {
        this.saving_Account_No = saving_Account_No;
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

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "DepositPlace{" +
                "deposit_Id='" + deposit_Id + '\'' +
                ", saving_Account_No='" + saving_Account_No + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
