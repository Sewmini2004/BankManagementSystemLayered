package lk.ijse.gdse.dto;


public class AccountDTO {

    private String member_Id;
    private String saving_Account_No;
    private String name;
    private  String nic;
    private String gender;
    private String address;
    private String mobile;
    private String email;
    private String date_of_birth;
    private String creat_Date;
    private String creat_Time;
    private double saving_Balance;
    private static String branch_No="B002";


    public AccountDTO(String member_Id, String saving_Account_No, String name, String nic, String gender, String address, String mobile, String email, String date_of_birth, String creat_Date, String creat_Time, double saving_Balance, String branch_No) {
        this.member_Id = member_Id;
        this.saving_Account_No = saving_Account_No;
        this.name = name;
        this.nic = nic;
        this.gender = gender;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.creat_Date = creat_Date;
        this.creat_Time = creat_Time;
        this.saving_Balance = saving_Balance;
        AccountDTO.branch_No =branch_No;
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

    public String getBranch_No() {
        return branch_No;
    }

    public void setBranch_No(String branch_No) {
        AccountDTO.branch_No = branch_No;
    }


    public String getSaving_Account_No() {
        return saving_Account_No;
    }

    public void setSaving_Account_No(String saving_Account_No) {
        this.saving_Account_No = saving_Account_No;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "member_No='" + member_Id + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
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



    public void setGender(String gender) {
        this.gender = gender;
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

    public String getGender() {
  return gender;

    }
}
