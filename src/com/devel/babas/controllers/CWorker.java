package com.devel.babas.controllers;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.devel.babas.models.Worker;

public class CWorker  {
    private static Pattern pattern;
    private static Matcher matcher;

    public CWorker() {
    }

    public static Worker newWorker(String dni, String firstName, String lastName, String phone, boolean sex, Date birthDay, String adress, String email, String password, boolean isUserDiscontinued, boolean isSuperUser,double salary, Time entryTime, Time departureTime) {
        if(isValidWorker( dni, firstName, lastName, phone, adress, email, password,salary)){
            return Worker.newWorker(dni, firstName, lastName, phone, sex, birthDay, adress, email, password, isUserDiscontinued, isSuperUser, salary, entryTime, departureTime);
        }

        return null;
    }

/*    public static Worker newWorker(String dni, String firstName, double salary) {
        return isValidDni(dni.trim()) && isValidName(firstName.trim()) && !isExistDni(dni.trim()) ? Worker.newWorker(dni, firstName, salary) : null;
    }*/

    public static Worker get(String dni) {
        if (isValidDni(dni.trim())) {
                Worker w = Worker.get(dni);
                if (w != null && !w.getDni().equals("none")) {
                return w;
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


    public static Worker login(String email, String password) {
        return Worker.login(email.trim(), password.trim());
    }

    public static Boolean isValidWorker(String dni, String firstName, String lastName, String phone, String adress, String email, String password,double salary){

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

/*    public static Worker newPhoto(String fromFile, String dni) throws Exception {
        Worker c = Worker.get(dni.trim());
        String d = System.getProperty("user.dir") + "/src/com/babas/devel/assets/users/";
        String r=fromFile.substring(fromFile.lastIndexOf(".")+1);
        File fichero = new File(d + c.getDni()+"."+r);
        System.out.println("fichero"+fichero);
        if (fichero.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no puede ser borrado puede que no exista");
        }
        c.copyPhoto(fromFile);
        return Worker.newPhoto(fromFile, dni) ;

    }*/

}
