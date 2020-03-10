package com.devel.babas.controllers;

import com.devel.babas.models.Author;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;

public class CAuthor {

    private static Pattern pattern;
    private static Matcher matcher;

    public static Author newAuthor( String firstName,  String lastName, String email, boolean sex){
        if (isValidAuthor(firstName,lastName, email)){
            return Author.newAuthor(firstName,lastName,email,sex);
        }

        return null;
    }

    public static Author newAuthor( String firstName, String lastName, boolean sex){
        if (isValidFirstName(firstName.strip())){
            if (isValidLastName(lastName.trim())){
                return Author.newAuthor(firstName,lastName,sex);
            }
        }

        return null;
    }

    public static Author get(String code){
        if(existsCode(code.trim())){
            Author a = Author.get(code);
            if(a != null){
                if(!a.getCode().equals(""))
                  return a;
            }
        }

        return null;
    }

    public static Author delete(String code) throws Exception {
        if(existsCode(code.trim())){
            Author a = Author.get(code);
            if(a != null){
                if(!a.delete()){
                    return a;
                }
            }
        }

        return null;
    }

    public List<Author> all(){
        return Author.all();
    }

    private static boolean existsCode(String code){
        Author a = new Author();
        if(a.get(code) != null){
            return true;
        }
        return false;
    }

/*    private static boolean isValidCode(String code){
        pattern = Pattern.compile("[A-Z][0-9]{1,8}");
        matcher = pattern.matcher(code);

        if(matcher.matches()){
            return true;
        }
        return false;
    }*/

    private static boolean isValidFirstName(String firstName){
        pattern = Pattern.compile("^(?=.{3,50}$)[A-ZÁÉÍÓÚ][a-zñáéíóú]+(?: [A-ZÁÉÍÓÚ][a-zñáéíóú]+)?$");
        matcher = pattern.matcher(firstName);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    private static boolean isValidLastName(String lastName){
        pattern = Pattern.compile("^(?=.{3,50}$)[A-ZÁÉÍÓÚ][a-zñáéíóú]+(?: [A-ZÁÉÍÓÚ][a-zñáéíóú]+)?$");
        matcher = pattern.matcher(lastName);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    private static boolean isValidEmail(String email){
        pattern = Pattern.compile("^([a-zA-Z0-9_.+-])+\\@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        matcher = pattern.matcher(email);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public static Boolean isValidAuthor(String firstName,  String lastName, String email){
        if(!isValidFirstName(firstName.trim())){
            return false;
        }
        if(!isValidLastName(lastName.trim())){
            return false;
        }
        if(!isValidEmail(email.trim())){
            return false;
        }

        return true;
    }

/*    private static boolean isValidSex(boolean sex){
        if(matcher.matches()){
            return true;
        }
        return false;
    }*/
}
