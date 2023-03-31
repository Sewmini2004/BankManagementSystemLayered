package lk.ijse.gdse.view.tm;

public class SavingAccount {

    private String member_Id;
    private String saving_Account_No;
    private String name;
    private  String nic;
    private String address;
    private String mobile;
    private String email;
    private String date_of_birth;
    private String creat_Date;
    private String creat_Time;
    private double saving_Balance;

    public SavingAccount(String member_Id, String saving_Account_No, String name, String nic, String address, String mobile, String email, String date_of_birth, String creat_Date, String creat_Time, double saving_Balance) {
        this.member_Id = member_Id;
        this.saving_Account_No = saving_Account_No;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.creat_Date = creat_Date;
        this.creat_Time = creat_Time;
        this.saving_Balance = saving_Balance;
    }

    public SavingAccount() {
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
    }

    public String getSaving_Account_No() {
        return saving_Account_No;
    }

    public void setSaving_Account_No(String saving_Account_No) {
        this.saving_Account_No = saving_Account_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCreat_Date() {
        return creat_Date;
    }

    public void setCreat_Date(String creat_Date) {
        this.creat_Date = creat_Date;
    }

    public String getCreat_Time() {
        return creat_Time;
    }

    public void setCreat_Time(String creat_Time) {
        this.creat_Time = creat_Time;
    }

    public double getSaving_Balance() {
        return saving_Balance;
    }

    public void setSaving_Balance(double saving_Balance) {
        this.saving_Balance = saving_Balance;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "member_Id='" + member_Id + '\'' +
                ", saving_Account_No='" + saving_Account_No + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", creat_Date='" + creat_Date + '\'' +
                ", creat_Time='" + creat_Time + '\'' +
                ", saving_Balance=" + saving_Balance +
                '}';
    }
}
