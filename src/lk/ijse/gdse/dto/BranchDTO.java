package lk.ijse.gdse.dto;

public class BranchDTO  {
private String branch_No;
private String name;
private String address;
private String bank_Email;
private String tel;

    public BranchDTO() {
    }

    public BranchDTO(String branch_No, String name, String address, String bank_Email, String tel) {
        this.branch_No = branch_No;
        this.name = name;
        this.address = address;
        this.bank_Email = bank_Email;
        this.tel = tel;
    }

    public String getBranch_No() {
        return branch_No;
    }

    public void setBranch_No(String branch_No) {
        this.branch_No = branch_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank_Email() {
        return bank_Email;
    }

    public void setBank_Email(String bank_Email) {
        this.bank_Email = bank_Email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
