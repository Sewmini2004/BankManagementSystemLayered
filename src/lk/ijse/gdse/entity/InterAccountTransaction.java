package lk.ijse.gdse.entity;

public class InterAccountTransaction implements SuperEntity {
private String transaction_Id;
private double amount;
private String date;
private String time;
private String account_01;
private String account_02;

    public InterAccountTransaction(String transaction_Id, double amount, String date, String time, String account_01, String account_02) {
        this.transaction_Id = transaction_Id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.account_01 = account_01;
        this.account_02 = account_02;
    }

    public InterAccountTransaction() {
    }


    public String getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(String transaction_Id) {
        this.transaction_Id = transaction_Id;
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

    public String getAccount_01() {
        return account_01;
    }

    public void setAccount_01(String account_01) {
        this.account_01 = account_01;
    }

    public String getAccount_02() {
        return account_02;
    }

    public void setAccount_02(String account_02) {
        this.account_02 = account_02;
    }

    @Override
    public String toString() {
        return "InterAccountTransaction{" +
                "transaction_Id='" + transaction_Id + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", account_01='" + account_01 + '\'' +
                ", account_02='" + account_02 + '\'' +
                '}';
    }

}
