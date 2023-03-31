package lk.ijse.gdse.view.tm;

public class WorkersDetails {
    private String worker_Id;
    private String name;
    private  String nic;
    private String address;
    private String job;
    private String mobile;
    private String email;
    private double salary;

    public WorkersDetails(String worker_Id, String name, String nic, String address, String job, String mobile, String email, double salary) {
        this.worker_Id = worker_Id;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.job = job;
        this.mobile = mobile;
        this.email = email;
        this.salary = salary;
    }

    public WorkersDetails() {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "WorkersDetails{" +
                "worker_Id='" + worker_Id + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
