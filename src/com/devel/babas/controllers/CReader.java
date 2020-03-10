package com.devel.babas.controllers;

import java.io.File;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.devel.babas.models.Reader;
import com.devel.babas.models.Worker;

public class CReader {
    private static Pattern pattern;
    private static Matcher matcher;

    public CReader() {
    }

    public static Reader newReader(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String adress, String email, String password, boolean isStaff, boolean isSuperUser, String occupation) {
        if(isValidReader(dni,firstName, lastName, phone, adress, email, password)){
            return Reader.newReader(dni, firstName, lastName, phone, sex, birthDay, adress, email, password, isStaff, isSuperUser, occupation);
        }
        return null;
    }

    public static Reader newReader(String dni, String firstName, String occupation) {
        return isValidDni(dni.trim()) && !isExistDni(dni.trim()) ? Reader.newReader(dni, firstName, occupation) : null;
    }

    public static Reader get(String dni) {
        if (isValidDni(dni.trim())) {
            Reader p = Reader.get(dni.trim());
            if (p != null && !p.getDni().equals("none")) {
                return p;
            }
        }

        return null;
    }

    public static boolean isExistDni(String dni) {
        return get(dni) != null;
    }

    public static boolean isValidDni(String dni) {
        pattern = Pattern.compile("[0-9]{8}");
        matcher = pattern.matcher(dni);

        return matcher.matches();
    }

    public static boolean isValidFirsName(String name) {
        pattern = Pattern.compile("[A-Za-z]{1,50}");
        matcher = pattern.matcher(name);

        return matcher.matches();
    }

    public static boolean isValidLastName(String lastName) {
        pattern = Pattern.compile("^(?=.{3,50}$)[A-ZÁÉÍÓÚ][a-zñáéíóú]+(?: [A-ZÁÉÍÓÚ][a-zñáéíóú]+)?$");
        matcher = pattern.matcher(lastName);

        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        pattern = Pattern.compile("[0-9]{9,14}");
        matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        pattern = Pattern.compile("[A-Za-z0-9@ ]{8,30}");
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        pattern = Pattern.compile("^([a-zA-Z0-9_.+-])+\\@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidAdress(String adress){
        pattern = Pattern.compile("^(?=.{2,50}$)[#.0-9a-zA-Z\\s,-]+$");
        matcher = pattern.matcher(adress);

        return matcher.matches();
    }

    public static Boolean isValidReader(String dni, String firstName, String lastName, String phone, String adress, String email, String password){

        if(!isValidDni(dni.trim())){
            return false;
        }

        if(!isValidFirsName(firstName.trim())){
            return false;
        }

        if(!isValidLastName(lastName.trim())){
            return false;
        }

        if(!isValidPhone(phone.trim())){
            return false;
        }

        if(!isValidAdress(adress.trim())){
            return false;
        }

        if(!isValidEmail(email.trim())){
            return false;
        }

        if(!isValidPassword(password.trim())){
            return false;
        }

        return true;
    }

    public static Reader login(String email, String password) {
        return Reader.login(email.trim(), password.trim());
    }

/*    public static Reader newPhoto(String fromFile, String dni) throws Exception {
        Reader c = Reader.get(dni.trim());
        String d = System.getProperty("user.dir") + "\\src\\com\\babas\\devel\\assets\\users\\";
        File fichero = new File(d + c.getFrom_url());
        if (fichero.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no puede ser borrado");
        }

        return Reader.newPhoto(fromFile, dni);
    }*/
}