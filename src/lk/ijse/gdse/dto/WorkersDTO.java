package lk.ijse.gdse.dto;

public class WorkersDTO {
    private String worker_Id;
    private String name;
    private  String nic;
    private String address;
    private String job;
    private String mobile;
    private String email;
    private String date_of_birth;
//    private String creat_Date;
//    private String creat_Time;
    private static String branch_No="B001";
    private double salary;


    public WorkersDTO(String worker_Id, String name, String nic, String address, String job, String mobile, String email, String date_of_birth, String branch_No, double salary) {
        this.worker_Id = worker_Id;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.job = job;
        this.mobile = mobile;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.salary = salary;
        WorkersDTO.branch_No =branch_No;
    }

    public String getWorker_Id() {
        return worker_Id;
    }

    public void setWorker_Id(String worker_Id) {
        this.worker_Id = worker_Id;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public static String getBranch_No() {
        return branch_No;
    }

    public static void setBranch_No(String branch_No) {
        WorkersDTO.branch_No = branch_No;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Workers{" +
                "worker_Id='" + worker_Id + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", salary=" + salary +
                '}';
    }
}
