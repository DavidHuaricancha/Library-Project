package com.devel.babas.models;

import com.devel.babas.connectiondb.Connect;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.devel.babas.interfaces.IModels;
import com.devel.babas.connectiondb.Encrypt;
import com.devel.babas.interfaces.IModelsUser;

public class Worker  extends People implements IModelsUser {
    private double salary;
    private Time entry_time;
    private Time end_time;
    private String occupation;

    public Worker() {
        this.salary = 0.0D;
        this.entry_time = null;
        this.end_time = null;
    }

    public Worker(int id) {
        super.setId_People(id);
        this.salary = 0.0D;
        this.entry_time = null;
        this.end_time = null;
    }

    public Worker(String dni, String firstName, double salary) {
        super(dni, firstName);
        this.salary = salary;
    }

    public Worker(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String address, String email, String password, boolean isStaffs, boolean isActive, boolean isSuperUser, double salary, Time entry_time, Time end_time, Date date_register) {
        super(dni, firstName, lastName, phone, sex, birthDay, address, email, password, isStaffs, isActive, isSuperUser, date_register);
        this.salary = salary;
        this.entry_time = entry_time;
        this.end_time = end_time;
    }


    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Time getEntry_time() {
        return this.entry_time;
    }

    public void setEntry_time(Time entry_time) {
        this.entry_time = entry_time;
    }

    public Time getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public static Worker newWorker(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String adress, String email, String password, boolean isStaffs, boolean isSuperUser, double salary, Time entryTime, Time departureTime) {
        Worker p = new Worker();
        p.setDni(dni);
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setPhone(phone);
        p.setSex(sex);
        p.setBirthDay(birthDay);
        p.setAddress(adress);
        p.setEmail(email);
        p.setPassword(encryp(password));
        p.setIs_staff(isStaffs);
        p.setSuperUser(isSuperUser);
        p.setSalary(salary);
        p.setEntry_time(entryTime);
        p.setEnd_time(departureTime);
        return p;
    }
    public static Worker newWorker(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String adress, String email, String password, boolean isStaffs, boolean isSuperUser, String fromUrl,double salary, Time entryTime, Time departureTime) {
        Worker q = new Worker();
        q.setDni(dni);
        q.setFirstName(firstName);
        q.setLastName(lastName);
        q.setPhone(phone);
        q.setSex(sex);
        q.setBirthDay(birthDay);
        q.setAddress(adress);
        q.setEmail(email);
        q.setPassword(encryp(password));
        q.setIs_staff(isStaffs);
        q.setSuperUser(isSuperUser);
        q.setSalary(salary);
        q.setEntry_time(entryTime);
        q.setEnd_time(departureTime);
        return q;
    }

    public static String encryp(String password) {
        try {
            String clave = "123Q@*_-[}3";
            return Encrypt.encrypPassword(password, clave);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String desencryp(String password) {
        try {
            String clave = "123Q@*_-[}3";
            return Encrypt.desEncryp(password, clave);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Worker newWorker(String dni, String firsName, Double salary) {
        Worker p = new Worker();
        p.setDni(dni);
        p.setFirstName(firsName);
        p.setSalary(salary);
        return p;
    }

    public boolean save() throws Exception {
        boolean var1;
        try {
            openConnection();
            query = connection.prepareStatement("call spManagerUser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            query.setString(1, this.getDni());
            query.setString(2, this.getFirstName());
            query.setString(3, this.getLastName());
            query.setString(4, this.getPhone());
            query.setBoolean(5, this.isSex());
            query.setDate(6, this.getBirthDay());
            query.setString(7, this.getAddress());
            query.setString(8, this.getEmail());
            query.setString(9, encryp(this.getPassword()));
            query.setBoolean(10, this.isUserDiscontinued());
            query.setBoolean(11, this.isSuperUser());
            query.setDouble(12, this.getSalary());
            query.setTime(13, this.getEntry_time());
            query.setTime(14, this.getEnd_time());
            query.setString(15, (String) null);
            if (query.execute()) {
                return true;
            }

            var1 = false;
        } catch (Exception var5) {
            var5.printStackTrace();
            return true;
        } finally {
            closeConnection();
        }

        return var1;
    }

    public static Worker get(String dni) {
        Worker o = new Worker(0);

        try {
            openConnection();
            query = connection.prepareStatement("select a.id_people,a.dni,a.first_name,a.last_name,a.number_phone,a.sex,a.birth,a.address,a.email,a.password,a.is_staff,a.is_active,a.is_superuser,a.date_register,b.salary,b.entry_time,b.end_time from people_tbl as a inner join worker_tbl as b on a.id_people =b.id_worker where dni=?");
            query.setString(1, dni);
            result = query.executeQuery();
            if (result.next()) {
                o.setId_People(result.getInt(1));
                o.setDni(result.getString(2));
                o.setFirstName(result.getString(3));
                o.setLastName(result.getString(4));
                o.setPhone(result.getString(5));
                o.setSex(result.getBoolean(6));
                o.setBirthDay(result.getDate(7));
                o.setAddress(result.getString(8));
                o.setEmail(result.getString(9));
                o.setPassword(result.getString(10));
                o.setIs_staff(result.getBoolean(11));
                o.setIs_active(result.getBoolean(12));
                o.setSuperUser(result.getBoolean(13));
                o.setDate_register(result.getDate(14));
                o.setSalary(result.getDouble(15));
                o.setEntry_time(result.getTime(16));
                o.setEnd_time(result.getTime(17));
                return o;
            }


        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    public static List<Worker> all() {
        ArrayList lp = new ArrayList();

        try {
            openConnection();
            query = connection.prepareStatement("select a.id_people,a.dni,a.first_name,a.last_name,a.number_phone,a.sex,a.birth,a.address,a.email,a.password,a.is_staff,a.is_active,a.is_superuser,b.salary,b.entry_time,b.end_time from people_tbl as a inner join worker_tbl as b on a.id_people =b.id_worker");
            result = query.executeQuery();

            while (result.next()) {
                Worker p = new Worker();
                p.setId_People(result.getInt(1));
                p.setDni(result.getString(2));
                p.setFirstName(result.getString(3));
                p.setLastName(result.getString(4));
                p.setPhone(result.getString(5));
                p.setSex(result.getBoolean(6));
                p.setBirthDay(result.getDate(7));
                p.setAddress(result.getString(8));
                p.setEmail(result.getString(9));
                p.setPassword(result.getString(10));
                p.setIs_staff(result.getBoolean(11));
                p.setIs_active(result.getBoolean(12));
                p.setSuperUser(result.getBoolean(13));
                p.setSalary(result.getDouble(14));
                p.setEntry_time(result.getTime(15));
                p.setEnd_time(result.getTime(16));
                lp.add(p);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        } finally {
            closeConnection();
        }

        return lp;
    }

/*    public String toString() {
        return "Worker{id_people=" + this.getId_people() + ",password=" + this.getPassword() + ", First_name=" + this.getFirstName() + , salary=" + this.salary + ", entryTime=" + this.entry_time + ", departureTime=" + this.end_time + '}';
    }*/

    public static Worker login(String email, String password) {
        try {
            openConnection();
            query = connection.prepareStatement("select a.dni from people_tbl as a inner join worker_tbl as b ON a.id_people =b.id_worker where a.email=? and a.password =? and Time(now()) BETWEEN time(entry_time) and time(end_time)");
            query.setString(1, email);
            query.setString(2, encryp(password));
            result = query.executeQuery();
            if (result.next()) {
                return get(result.getString(1));
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }

/*    public  void copyPhoto(String fromUrl)  {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            File archivoOriginal = new File(fromUrl);
            String r = String.format(archivoOriginal.toString());
            r = r.substring(r.lastIndexOf(46) + 1);
            System.out.println(r);
            String d = System.getProperty("user.dir") + "/src/com/babas/devel/assets/users/";
            File archivoCopia = new File(d + this.getDni() + "." + r);
            System.out.println(archivoCopia);
            inputStream = new FileInputStream(archivoOriginal);
            outputStream = new FileOutputStream(archivoCopia);
            byte[] buffer = new byte[1024];

            int length;
            while((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();
            System.out.println("Archivo copiado.");
            //return true;
        } catch (Exception var10) {
            var10.printStackTrace();
            //return false;
        }
    }*/

    /*public static Worker newPhoto(String photo, String dni) {
        Worker p = new Worker();
        p.setFrom_url(dni+"."+photo.substring(photo.lastIndexOf(".")+1));
        p.setDni(dni);
        return p;
    }*/

}
