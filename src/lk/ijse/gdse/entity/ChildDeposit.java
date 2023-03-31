package lk.ijse.gdse.entity;

public class ChildDeposit implements SuperEntity{
    private String deposit_Id;
    private double amount;
    private String date;
    private String time;
    private String child_Account_No;

    public ChildDeposit(String deposit_Id, double amount, String date, String time, String child_Account_No) {
        this.deposit_Id = deposit_Id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.child_Account_No = child_Account_No;
    }

    public ChildDeposit() {
    }

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

    public String getChild_Account_No() {
        return child_Account_No;
    }

    public void setChild_Account_No(String child_Account_No) {
        this.child_Account_No = child_Account_No;
    }

    @Override
    public String toString() {
        return "ChildDeposit{" +
                "deposit_Id='" + deposit_Id + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", child_Account_No='" + child_Account_No + '\'' +
                '}';
    }
}
