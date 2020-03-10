package com.devel.babas.models;

import com.devel.babas.connectiondb.Connect;
import com.devel.babas.interfaces.IModels;

import java.util.List;
import java.util.ArrayList;


public class Editorial extends Connect implements IModels {

    private int idEditorial;
    private String name;
    private String adress;
    private String phone;

    private boolean isNew;

    public Editorial() {
        idEditorial=0;
        name="";
        adress="";
        phone="";
        isNew=true;
    }

    public Editorial(int idEditorial, String name, String adress, String phone) {
        this.idEditorial = idEditorial;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        isNew=true;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getName() {
        return toTitle(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return toTitle(adress);
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }


    @Override
    public boolean save() throws Exception {
        try {
            openConnection();

            query = connection.prepareCall("CALL spManagerEditorial(?,?,?,?)");
            query.setInt(1,getIdEditorial());
            query.setString(2,getName());
            query.setString(3,getAdress());
            query.setString(4,getPhone());

            query.execute();

            return false;


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //closeConnection();
        }

        return true;
    }

    @Override
    public boolean delete() throws Exception {
        try {
            openConnection();
            query = connection.prepareStatement("DELETE FROM editorial_tbl WHERE name=?");
            query.setString(1,getName());

            query.execute();

            return false;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return true;
    }

    public static  Editorial newEditorial(String name, String adress, String phone){
        Editorial a = new Editorial();
        a.setIdEditorial(0);
        a.setName(name);
        a.setAdress(adress);
        a.setPhone(phone);

        return a;
    }

    public static Editorial get(String name){
        Editorial e = new Editorial();
        try {
            openConnection();
            query = connection.prepareStatement("SELECT id_editorial, name, adress, phone from editorial_tbl WHERE name=?");
            query.setString(1,name);

            result = query.executeQuery();

            if(result.next()){
                e.setIdEditorial(result.getInt(1));
                e.setName(result.getString(2));
                e.setAdress(result.getString(3));
                e.setPhone(result.getString(4));
                return e;
            }


        }catch (Exception E){
            E.printStackTrace();
        }finally {
            closeConnection();
        }
        return  null;
    }

    public static List<Editorial> all(){
        List<Editorial> le = new ArrayList<>();
        try{
            openConnection();
            query = connection.prepareStatement("SELECT id_editorial, name, adress, phone from editorial_tbl");
            result = query.executeQuery();

            while (result.next()){
                Editorial e = new Editorial();
                e.setIdEditorial(result.getInt(1));
                e.setName(result.getString(2));
                e.setAdress(result.getString(3));
                e.setPhone(result.getString(4));

                le.add(e);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return  le;
    }

    public String toString() {
        return "Editorial{" +
                "idEditorial='" + idEditorial+'\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress +'\'' +
                ", phone='"+ phone +'\''+
                '}' + "\n";
    }
}
