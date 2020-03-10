package com.devel.babas.controllers;

import com.devel.babas.models.Author;
import com.devel.babas.models.Category;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class CCategory {

    private static Pattern pattern;
    private static Matcher matcher;

    public static Category newCategory(String name){
        if(isValidCategory(name.trim())) {
            return Category.newCategory(name.trim());
        }

        return null;
    }

    public static Category  get(String name){
        if(existsCategory(name.trim())){
            Category c = Category.get(name);
            if(c != null && !c.getName().equals("" )){
                return c;
            }
        }

        return null;
    }

    public static Category delete(String name) throws Exception{
        Category c = Category.get(name);
        if(c != null){
            if(!c.delete()){
                return c;
            }
        }
        return null;
    }

    public static List<Category> all(){
        return Category.all();
    }

    private static boolean existsCategory(String name){
        Author a = new Author();
        if(a.get(name) != null){
            return true;
        }
        return false;
    }

    private static boolean isValidCategory(String name){
        pattern = Pattern.compile("^[#.a-zA-Z\\s]+$");
        matcher = pattern.matcher(name);

        return matcher.matches();
    }



}
