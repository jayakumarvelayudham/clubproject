package customer.mmpl.tcs.com.clubproject.modul;

/**
 * Created by BeAsT on 18-Jul-18.
 */

public class Alluser_Module {

    private String user_id;
    private String f_name;
    private int password;
    private String email_id;
    private int Phone_no;
    private String image_url;
    private String address;
    private String profession;
    private String gender;
    private String user_dob;
    private String blood_group;
    private String role_id;
    private String position;
    private String update_date_time;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(int phone_no) {
        Phone_no = phone_no;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUser_dob() {
        return user_dob;
    }

    public void setUser_dob(String user_dob) {
        this.user_dob = user_dob;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(String update_date_time) {
        this.update_date_time = update_date_time;
    }

    @Override
    public String toString() {
        return "Alluser_Module{" +
                "user_id='" + user_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", password='" + password + '\'' +
                ", email_id='" + email_id + '\'' +
                ", Phone_no='" + Phone_no + '\'' +
                ", image_url='" + image_url + '\'' +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
                ", gender='" + gender + '\'' +
                ", user_dob='" + user_dob + '\'' +
                ", blood_group='" + blood_group + '\'' +
                ", role_id='" + role_id + '\'' +
                ", position='" + position + '\'' +
                ", update_date_time='" + update_date_time + '\'' +
                '}';
    }
}
