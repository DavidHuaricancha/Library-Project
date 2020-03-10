package com.devel.babas.controllers;

import com.devel.babas.models.Book;
import com.devel.babas.models.Loan;
import com.devel.babas.models.Reader;
import org.mariadb.jdbc.internal.com.send.parameters.StringParameter;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLoan {
    private static Pattern pattern;
    private static Matcher matcher;
    public static Loan newLoan(int idWorker,String codeBook, String dniReader, Date loanReturn){
        if(isValidCodeBook(codeBook.trim()) && isExistCodeBook(codeBook.trim()) ){
           if( isValidCodeReader(dniReader.trim())&& isExistDniReader(dniReader.trim())) {
                  return Loan.newLoan(idWorker, codeBook, dniReader, loanReturn);
           }
        }
        return null;
    }
    public static Reader getReader(String dniReader){
        if(isValidCodeReader(dniReader.trim())){
            Reader p=Reader.get(dniReader.trim());
            if(p!=null && !p.getDni().equals("")){
                return p;
            }
        }
        return null;
    }
    public static boolean isExistDniReader(String dniReader){
        return getReader(dniReader.trim())!=null;
    }

    public static boolean isValidCodeReader(String dniReader){
        pattern = Pattern.compile("[0-9]{8}");
        matcher = pattern.matcher(String.valueOf(dniReader));
        return matcher.matches();
    }
    public static Book getbook(String codeBook) {
        if (isValidCodeBook(codeBook)) {
            Book p = CBook.get(codeBook);
            if (p != null && !p.getCode().equals("")) {
                return p;
            }
        }
        return null;
    }
    public static boolean isExistCodeBook(String codeBook) {
        return getbook(codeBook.trim())!= null;
    }
    public static boolean isValidCodeBook(String codeBook) {
        pattern = Pattern.compile("[0-9A-Za-z]{8}");
        matcher = pattern.matcher(String.valueOf(codeBook));
        return matcher.matches();
    }
}
