package lk.ijse.gdse.dto;

public class WithdrowDTO {
    private String withdrow_Id;
    private double amount;
    private String date;
    private String time;
    private String saving_Account_No;


    public WithdrowDTO(String withdrow_Id, double amount, String date, String time, String saving_Account_No) {
        this.withdrow_Id = withdrow_Id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.saving_Account_No = saving_Account_No;
    }

    public WithdrowDTO() {
    }


    public String getWithdrow_Id() {
        return withdrow_Id;
    }

    public void setWithdrow_Id(String withdrow_Id) {
        this.withdrow_Id = withdrow_Id;
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

    @Override
    public String toString() {
        return "Withdrow{" +
                "withdrow_Id='" + withdrow_Id + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", saving_Account_No='" + saving_Account_No + '\'' +
                '}';
    }
}
