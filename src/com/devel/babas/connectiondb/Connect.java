package com.devel.babas.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public abstract class Connect {
    protected static Connection connection;
    protected static PreparedStatement query;
    protected static PreparedStatement query02;
    protected static ResultSet result;

    private static String Driver = "org.mariadb.jdbc.Driver";
    private static String DB_URL = "jdbc:mariadb://192.168.1.51:3306/library_db";
    private static String USER = "walle";
    private static String PASSWORD = "974408723";

    protected static boolean openConnection(){

        try {

            Class.forName(Driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            return false;

        }catch (Exception e){

            e.printStackTrace();
            System.out.print("Error en conexion");
        }

        return true;

    }

    protected static boolean closeConnection(){

        try {

            if(!connection.isClosed()){

                if(result != null){

                    if(!result.isClosed()){

                        //result.close();
                    }

                }

                query.close();
                connection.close();

                return false;
            }


        }catch (Exception e){

            e.printStackTrace();;

        }

        return true;
    }

    protected static String toTitle(String text){
        String returnText= "";
        char character;
        boolean startLetter = true;

        for (int i=0; i< text.length() ; i++){

            character = (char) text.charAt(i);

            if(character == ' '){
                returnText = (returnText + character);
                startLetter = true;

            }else {
                if(startLetter == true){
                if(Character.isLowerCase(character)){

                        character = Character.toUpperCase(character);
                        returnText = (returnText + character);
                    }else {
                        returnText = (returnText + character);
                    }
                    startLetter = false;
                }else {
                    if(Character.isUpperCase(character)){

                        character = Character.toLowerCase(character);
                        returnText = (returnText + character);
                    }else {
                        returnText = (returnText + character);
                    }
                }
            }
        }

        return returnText;
    }
}
