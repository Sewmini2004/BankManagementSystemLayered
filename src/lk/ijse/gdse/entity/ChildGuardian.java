package lk.ijse.gdse.entity;

public class ChildGuardian implements SuperEntity{
private String nic;
private String name;
private String gender;
private String address;
private String date_Of_Birth;
private String email;
private String mobile;

    public ChildGuardian() {
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "ChildGuardian{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", date_Of_Birth='" + date_Of_Birth + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ChildGuardian(String nic, String name, String gender, String address, String date_Of_Birth, String email, String mobile) {
        this.nic = nic;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.date_Of_Birth = date_Of_Birth;
        this.email = email;
        this.mobile = mobile;
    }


}
