package projekat.FitnessCentar.entity;

import java.util.Date;

public class TrenerDTO {

    String name,surname,username,password,email,phone;
    Date birthday;
    int vrati;

    public TrenerDTO(){}
    public TrenerDTO(String name, String surname, String username, String password, String email, String phone, Date birthday,int vrati) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.vrati=vrati;
    }

    public int getVrati() {
        return vrati;
    }

    public void setVrati(int vrati) {
        this.vrati = vrati;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
