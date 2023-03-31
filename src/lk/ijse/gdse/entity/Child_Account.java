package lk.ijse.gdse.entity;

public class Child_Account implements SuperEntity{

private String child_Id;
private  String child_Account_No;
private String name;
private String gender;
private  String address;
private String date_Of_Birth;
private String  nic;
private String creat_Date;
private String creat_Time;
private double balance;
private String  branch_No;


    public Child_Account(String child_Id, String child_Account_No, String name, String gender, String address, String date_Of_Birth,String nic, String creat_Date,String creat_Time, double balance, String branch_No ) {
        this.child_Id = child_Id;
        this.child_Account_No = child_Account_No;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.date_Of_Birth = date_Of_Birth;
        this.creat_Date = creat_Date;
        this.creat_Time=creat_Time;
        this.balance = balance;
        this.branch_No = branch_No;
        this.nic = nic;
    }

    public Child_Account() {
    }


    @Override
    public String toString() {
        return "Child_Account{" +
                "child_Id='" + child_Id + '\'' +
                ", child_Account_No='" + child_Account_No + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", date_Of_Birth='" + date_Of_Birth + '\'' +
                ", nic='" + nic + '\'' +
                ", creat_Date='" + creat_Date + '\'' +
                ", creat_Time='" + creat_Time + '\'' +
                ", balance=" + balance +
                ", branch_No='" + branch_No + '\'' +
                '}';
    }

    public String getChild_Id() {
        return child_Id;
    }

    public void setChild_Id(String child_Id) {
        this.child_Id = child_Id;
    }

    public String getChild_Account_No() {
        return child_Account_No;
    }

    public void setChild_Account_No(String child_Account_No) {
        this.child_Account_No = child_Account_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_Of_Birth() {
        return date_Of_Birth;
    }

    public void setDate_Of_Birth(String date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }

    public String getCreat_Date() {
        return creat_Date;
    }

    public void setCreat_Date(String creat_Date) {
        this.creat_Date = creat_Date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBranch_No() {
        return branch_No;
    }

    public void setBranch_No(String branch_No) {
        this.branch_No = branch_No;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCreat_Time() {
        return creat_Time;
    }

    public void setCreat_Time(String creat_Time) {
        this.creat_Time = creat_Time;
    }

}
