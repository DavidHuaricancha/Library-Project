package com.devel.babas.library;

import com.devel.babas.controllers.*;
import com.devel.babas.models.*;

import java.sql.Date;
import java.sql.DriverAction;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {





    public static void main(String args[]){


        try {

/*            Category c = CCategory.newCategory("NOVELAS");
            c.save();*/

/*            Author a = CAuthor.newAuthor("Cesar","Vallejo","cesar@gmail.com",true);
            System.out.println(a);
            a.save();*/

/*            Author c = CAuthor.get("A0000001");
            Author a = CAuthor.get("A0000002");

            System.out.println(c);
            System.out.println(a);

            List<Author> la = new ArrayList<>();

            la.add(c);
            la.add(a);

            Book b = CBook.newBook("Fundamentos De Programacion", Date.valueOf("2000-04-12"),3,"NOVELAS","COREFO",la);
            b.save();
            System.out.println(b);*/


/*            Worker w = CWorker.newWorker("09191066","David","Huaricanca Abad","147258369",true, Date.valueOf("2000-04-24"),"Barrio Nuevo PSJ Los olivos MZ-F L15B","huaricancha2000@gmail.com","123456789",Boolean.valueOf("1"), Boolean.valueOf("1"),1000,Time.valueOf("7:00:00"),Time.valueOf("17:00:00"));
            w.save();
            System.out.println(w);*/

//            Reader r = CReader.newReader("")

/*            Reader e=CReader.get("77055584");
            System.out.println(e.getFirstName());
            Book r=CBook.get("B0000001");
            System.out.println(r.getTitle());
            Worker a= CWorker.login("DAVID@GMAIL.COM","77084232");

            if(a!=null){
                System.out.println("Hola "+a.getFirstName()+" como estas");
                Loan i=CLoan.newLoan(a.getId_people(),"B0000002","12365498",Date.valueOf("2020-12-11"));
                if(i!=null){
                    System.out.println(i.getLoanCode());
                    System.out.println(i);
                }
                else{
                    System.out.println("Libro o lector no existe asegurece de que el lector no este en deuda");
                }
                // i.save();
                ;
            }else{
                System.out.println("contra incorrecta o fuera de horario");
            }*/

/*            Reader r = CReader.newReader("12345698","Nick","Macedo Cordova", "948285658",Boolean.valueOf("1"),Date.valueOf("2000-06-10"),"Av Tambopata","macedo123@gmail.com","123456789",Boolean.valueOf("1"),Boolean.valueOf( "0"),"Estudiante");
            System.out.println(r);
            r.save();*/

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception in main");
        }
    }

}

