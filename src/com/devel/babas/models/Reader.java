package com.devel.babas.models;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.devel.babas.interfaces.IModels;
import com.devel.babas.connectiondb.Encrypt;
import com.devel.babas.interfaces.IModelsUser;

public class Reader extends People implements IModelsUser {
    private String codeReader = this.getCode();
    private String occupation;

    public Reader() {
        this.occupation = "";
    }

    public Reader(String dni, String firstName, String occupation) {
        this.occupation = occupation;
    }

    public Reader(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String adress, String email, String password, boolean isStaff, boolean isActive, boolean isSuperUser, String occupation, Date date_register) {
        super(dni, firstName, lastName, phone, sex, birthDay, adress, email, encryp(password), isStaff, isActive, isSuperUser, date_register);
        this.occupation = occupation;
    }

    public String getCodeReader() {
        return this.codeReader;
    }

    public void setCodeReader(String codeReader) {
        this.codeReader = codeReader;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public static Reader newReader(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String adress, String email, String password, boolean isUserDiscontinued, boolean isSuperUser, String occupation) {
        Reader p = new Reader();
        p.setDni(dni);
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setPhone(phone);
        p.setSex(sex);
        p.setBirthDay(birthDay);
        p.setAddress(adress);
        p.setEmail(email);
        p.setPassword(encryp(password));
        p.setIs_staff(isUserDiscontinued);
        p.setSuperUser(isSuperUser);
        p.setOccupation(occupation);
        return p;
    }

    public static Reader newReader(String dni, String firsName, String occputaion) {
        Reader p = new Reader();
        p.setDni(dni);
        p.setFirstName(firsName);
        p.setOccupation(occputaion);
        return p;
    }

//    public static Reader newPhoto(String photo, String dni) {
//        Reader p = new Reader();
//        p.setDni(dni);
//        p.setFrom_url(photo);
//        return p;
//    }

    public static String encryp(String password) {
        try {
            String clave = "123Q@*_-[}3";
            return Encrypt.encrypPassword(password, clave);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
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
            query.setDouble(12, 0.0D);
            query.setTime(13, (Time)null);
            query.setTime(14, (Time)null);
            query.setString(15, this.getOccupation());
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

    public static Reader get(String dni) {
        Reader o = new Reader();

        try {
            openConnection();
            query = connection.prepareStatement("select a.id_people,a.dni,a.first_name,a.last_name,a.number_phone,a.sex,a.birth,a.address,a.email,a.password,a.is_staff,a.is_active,a.is_superuser,b.code,b.occupation from people_tbl as a inner join reader_tbl as b on a.id_people =b.id_reader where a.dni=?");
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
                o.setCodeReader(result.getString(14));
                o.setOccupation(result.getString(15));
            }

            Reader var2 = o;

            return var2;
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    public static List<Reader> all() {

        ArrayList lp = new ArrayList();

        try {
            openConnection();
            query = connection.prepareStatement("select a.id_people,a.dni,a.first_name,a.last_name,a.number_phone,a.sex,a.birth,a.address,a.email,a.password,a.is_staff,a.is_active,a.is_superuser,b.code_reader,b.occupation from people_tbl as a inner join reader_tbl as b on a.id_people =b.id_reader");
            result = query.executeQuery();

            while(result.next()) {
                Reader p = new Reader();
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
                p.setCodeReader(result.getString(14));
                p.setOccupation(result.getString(15));
                lp.add(p);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        } finally {
            closeConnection();
        }

        return lp;
    }

    private String getCode() {
        String code = "";

        try {
            openConnection();
            query = connection.prepareStatement("Select fnGenerateCode('1')");
            result = query.executeQuery();
            if (result.next()) {
                code = result.getString(1);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            closeConnection();
        }

        return code;
    }

/*    public String toString() {
        return "Worker{id_people=" + this.getId_people() + ", First_name=" + this.getFirstName() + ",from_url =" + this.getFrom_url().substring(this.getFrom_url().lastIndexOf(92) + 1) + ", codeReader=" + this.getCodeReader() + ", occupation=" + this.getOccupation() + '}';
    }*/

    public static Reader login(String email, String password) {
        try {
            openConnection();
            query = connection.prepareStatement("select dni from people_tbl where email=? and password =?");
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

/*    public boolean savePhoto() throws Exception {
        boolean var1;
        try {
            openConnection();
            query = connection.prepareStatement("update people_tbl set from_url=? where dni=?");
            query.setString(1, this.getDni() + "." + this.getFrom_url().substring(1 + this.getFrom_url().lastIndexOf(46)));
            query.setString(2, this.getDni());
            if (query.execute()) {
                return true;
            }

//            this.copyPhoto(this.getFrom_url());
            var1 = false;
        } catch (Exception var5) {
            var5.printStackTrace();
            return true;
        } finally {
            closeConnection();
        }

        return var1;
    }*/

/*    public boolean copyPhoto(String from_url) throws Exception {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            File archivoOriginal = new File(from_url);
            String r = String.format(archivoOriginal.toString());
            r = r.substring(r.lastIndexOf(46) + 1);
            String d = System.getProperty("user.dir") + "\\src\\com\\babas\\devel\\assets\\users\\";
            File archivoCopia = new File(d + this.getDni() + "." + r);
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
            return true;
        } catch (Exception var10) {
            var10.printStackTrace();
            return false;
        }
    }*/
}
