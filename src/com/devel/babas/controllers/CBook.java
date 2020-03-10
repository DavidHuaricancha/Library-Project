package com.devel.babas.controllers;

import com.devel.babas.models.Author;
import com.devel.babas.models.Book;
import com.devel.babas.models.Category;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;


public class CBook {
    private static Pattern pattern;
    private static Matcher matcher;

    public static Book newBook(String title, Date editionYear, int stock, String categoryName, String editorialName, List<Author> authors) throws IOException {
        if(isValidTitle(title.trim())) {
            return Book.newBook(title, editionYear, stock, categoryName, editorialName, authors);
        }

        return null;
    }

    public static Book get(String code){
        Book b = Book.get(code);
        if(b != null){
            if(!b.getCode().equals(""))
                return b;
        }
        return null;
    }

    public static Book delete(String code) throws Exception{
        Book b = Book.get(code);
        if(b != null){
            if(!b.delete()){
                return b;
            }
        }

        return null;
    }


    public List<Book> all(){
        return Book.all();
    }

    public static boolean isValidTitle(String title){
        pattern = Pattern.compile("^[#.a-zA-Z\\s]+$");
        matcher = pattern.matcher(title);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

/*
    public static Boolean isValidBook(String title, String categoryName, String editorialName){
        if(!isValidTitle(title)){
            return false;
        }
        if(isVa)

        return true;
    }
*/

/*    public static Book newPhoto(String fromFile, String code){
        Book b = Book.get(code.trim());
        String d = System.getProperty("user.dir")+"/src/com/devel/babas/assets/";
        File fichero = new File(d+b.getFrontUrl());
        if (fichero.delete())
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
            System.out.println("El fichero no puede ser borrado");

        return Book.newPhoto(fromFile, code);
    }*/


}
