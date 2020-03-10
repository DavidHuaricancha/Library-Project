package com.devel.babas.models;
import com.devel.babas.connectiondb.Connect;
import java.sql.Date;

public class People  extends Connect{
    private int id_people = this.getId();
    private String dni;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean sex;
    private Date birthDay;
    private String address;
    private String email;
    private String password;
    private boolean is_staff;
    private boolean is_active;
    private boolean isSuperUser;
    private Date date_register;

    public People() {
        this.dni = "none";
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.sex = true;
        this.birthDay = null;
        this.address = "";
        this.email = "";
        this.password = "";
        this.is_staff = false;
        this.isSuperUser = false;
        this.date_register = null;
    }

    public People(String firstName, String dni) {
        this.dni = dni;
        this.firstName = firstName;
    }

    public People(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String address, String email, String password, boolean is_staff, boolean isActive, boolean isSuperUser, Date date_register) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.sex = sex;
        this.birthDay = birthDay;
        this.address = address;
        this.email = email;
        this.password = password;
        this.is_staff = is_staff;
        this.is_active = isActive;
        this.isSuperUser = isSuperUser;
        this.date_register = date_register;
    }


    public Date getDate_register() {
        return this.date_register;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
    }

    public int getId_people() {
        return this.id_people;
    }

    public void setId_People(int id_people) {
        this.id_people = id_people;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return toTitle(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return toTitle(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserDiscontinued() {
        return this.is_staff;
    }

    public void setIs_staff(boolean isStaff) {
        this.is_staff = isStaff;
    }

    public boolean isActive() {
        return this.is_active;
    }

    public void setIs_active(boolean is_active) {
    }

    public boolean isSuperUser() {
        return this.isSuperUser;
    }

    public void setSuperUser(boolean superUser) {
        this.isSuperUser = superUser;
    }

    private int getId() {
        int var1;
        try {
            openConnection();
            query = connection.prepareStatement("select  max(id_people)+1 from people_tbl");
            result = query.executeQuery();
            if (!result.next()) {
                return 1;
            }

            var1 = result.getInt(1);
        } catch (Exception var5) {
            var5.printStackTrace();
            return 1;
        } finally {
            closeConnection();
        }

        return var1;
    }
}
