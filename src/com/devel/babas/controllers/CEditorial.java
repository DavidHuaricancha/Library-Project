package com.devel.babas.controllers;


import com.devel.babas.models.Editorial;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class CEditorial {
    private static Matcher matcher;
    private static Pattern pattern;

    public static Editorial newEditorial(String name, String adress, String phone){
        if(isValidEditorial(name, adress, phone)){
            return Editorial.newEditorial(name,adress,phone);
        }

        return null;
    }

    public static Editorial get(String name){
        if(isValidName((name.trim()))) {
            Editorial e = Editorial.get(name.trim());
            if (e != null ) {
                if(e.getName().equals(""))
                return e;
            }
        }

        return null;
    }

    public static boolean isExistEditorial(String name){
        return get(name) != null;
    }

    public static Editorial delete(String name) throws Exception {
        Editorial e = Editorial.get(name.trim());
        if (e != null){
            if(!e.delete()){
                return e;
            }
        }

        return null;
    }

    public List<Editorial> all(){
        return Editorial.all();
    }

    public static boolean isValidName(String name){
        pattern = Pattern.compile("^(?=.{2,50}$)[#.a-zA-Z\\s]+$");
        matcher = pattern.matcher(name);

        return matcher.matches();
    }

    public static boolean isValidAdress(String adress){
        pattern = Pattern.compile("^(?=.{2,50}$)[#.0-9a-zA-Z\\s,-]+$");
        matcher = pattern.matcher(adress);

        return matcher.matches();
    }

    public static boolean isValidPhone(String phone){
        pattern = Pattern.compile("[0-9\\-\\+]{9,15}");
        matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public static boolean isValidEditorial(String name, String adress, String phone){

        //if()

        if (!isValidName(name.trim())) {
            return false;
        }

        if (!isValidAdress(adress.trim())) {
            return false;
        }

        if(!isValidPhone(phone.trim())) {
            return false;
        }

        return false;
    }
}
