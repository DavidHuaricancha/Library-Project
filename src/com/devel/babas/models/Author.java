package com.devel.babas.models;

import com.devel.babas.interfaces.IModels;
import com.devel.babas.connectiondb.Connect;

import java.util.ArrayList;
import java.util.List;

public class Author extends Connect implements IModels{

    private int idAuthor;
    private String code;
    private String firstName;
    private String lastName;
    private String email;
    private boolean sex;

    public Author() {
        idAuthor=0;
        code="";
        firstName="";
        lastName="";
        email="";
        sex=true;
    }

    public Author(String code, String firstName, String lastName, String email) {
        idAuthor = 0;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex=true;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public boolean save() throws Exception {
        try {

            openConnection();

            query = connection.prepareCall("call spManagerAuthor(?,?,?,?,?)");

            query.setString(1,getCode());
            query.setString(2,getFirstName());
            query.setString(3,getLastName());
            query.setString(4,getEmail());
            query.setBoolean(5,isSex());

            query.execute();
            return false;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean delete() throws Exception {

        try {

            openConnection();
            query = connection.prepareStatement("DELETE FROM author_tbl WHERE code=?");
            query.setString(1,getCode());
            query.execute();

            return false;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return true;
    }


    public static Author newAuthor(String firstName, String lastName, String email, boolean sex){
        Author a = new Author();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setEmail(email);
        a.setSex(sex);

        return a;
    }

    public  static Author newAuthor(String firstName, String lastName, boolean sex){
        Author a = new Author();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setSex(sex);

        return a;
    }

    public static Author get(String code){
        Author a = new Author();
        try {
            openConnection();
            query = connection.prepareStatement("select code ,id_author , first_name , last_name, email ,sex from author_tbl where code=?");
            query.setString(1,code);
            result = query.executeQuery();

            if(result.next()){
                a.setCode(result.getString(1));
                a.setIdAuthor(result.getInt(2));
                a.setFirstName(result.getString(3));
                a.setLastName(result.getString(4));
                a.setEmail(result.getString(5));
                a.setSex(result.getBoolean(6));
                return a;
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();

        }
        return null;
    }

    public static List<Author> all(){
        List<Author> la = new ArrayList<>();
        try {
            openConnection();
            query = connection.prepareStatement("select id_author , code, first_name , last_name, email ,sex from author_tbl");
            result = query.executeQuery();

            while (result.next()){
                Author a = new Author();
                a.setIdAuthor(result.getInt(1));
                a.setCode(result.getString(2));
                a.setFirstName(result.getString(3));
                a.setLastName(result.getString(4));
                a.setEmail(result.getString(5));
                a.setSex(result.getBoolean(6));

                la.add(a);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return  la;
    }

    public String toString() {
        return "Author{" +
                "idAuthor='" + idAuthor +
                ", Code='" + code + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" +  email + '\'' +
                ", sex='" + sex + '\'' +
                '}' + "\n";
    }

}
