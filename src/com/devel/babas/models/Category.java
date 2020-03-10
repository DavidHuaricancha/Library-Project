package com.devel.babas.models;

import com.devel.babas.connectiondb.Connect;
import com.devel.babas.interfaces.IModels;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;


public class Category extends Connect implements IModels {

    private int idCategory;
    private String name;

    private boolean isNew;

    public Category() {
        idCategory=0;
        name="";
        isNew=true;
    }

    public Category(int idCategory, String name, boolean isNew) {
        this.idCategory = idCategory;
        this.name = name;
        this.isNew = true;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return toTitle(name);
    }

    public void setName(String name) {
        this.name = name;
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
            if(isNew()){
                query = connection.prepareStatement("INSERT INTO category_tbl values(null,?)");
                query.setString(1,getName());

            }else {
                query = connection.prepareStatement("UPDATE category_tbl SET name=? WHERE id_category=?");
                query.setString(1,getName());
                query.setInt(2,getIdCategory());
            }

            if(!query.execute()) {
                setNew(false);
                return false;
            }

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
            query = connection.prepareStatement("DELETE FROM category_tbl WHERE  name=?");
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

    public static Category newCategory(String name){
        Category c = new Category();
        c.setName(name);

        return  c;
    }

    public static Category get(String name){
        Category c = new Category();
        try {
            openConnection();
            query = connection.prepareStatement("SELECT id_category, name FROM category_tbl WHERE name=?");
            query.setString(1,name);
            result = query.executeQuery();

            if(result.next()){
                c.setIdCategory(result.getInt(1));
                c.setName(result.getString(2));
                c.setNew(false);

                return c;
            }



        }catch (Exception e){
            e.printStackTrace();;
        }finally {
            closeConnection();
        }
        return  null;
    }

    public static  List<Category> all(){
        List<Category> lc = new ArrayList<>();
        try{
            openConnection();
            query = connection.prepareStatement("SELECT id_category, name FROM category_tbl");
            result = query.executeQuery();

            while (result.next()){
                Category a = new Category();
                a.setIdCategory(result.getInt(1));
                a.setName(result.getString(2));

                lc.add(a);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return  lc;
    }

    public String toString() {
        return "Category{" +
                "idCategory='" + idCategory+ '\'' +
                ", Name='" + name + '\'' +
                ", isNew='" + isNew + '\''+
                '}' + "\n";
    }

}
