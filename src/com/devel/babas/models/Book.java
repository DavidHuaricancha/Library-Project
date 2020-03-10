package com.devel.babas.models;

import com.devel.babas.connectiondb.Connect;
import com.devel.babas.interfaces.IModels;
import com.devel.babas.models.Editorial;
import com.devel.babas.models.Category;
import com.devel.babas.models.Author;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

public class Book extends Connect implements IModels {

    private int idBook;
    private String title;
    private String code;
    private Date editionYear;
    private int stock;
    private Category category;
    private Editorial editorial;
    private List<Author> authors;


    public Book() {
        idBook=0;
        title="";
        code="";
        editionYear=null;
        stock=0;
        category=null;
        editorial=null;
        authors=null;
    }

    public Book(int idBook, String title, String code, Date editionYear, int stock, Category category, Editorial editorial, List<Author> authors) {
        this.idBook = idBook;
        this.title = title;
        this.code = code;
        this.editionYear = editionYear;
        this.stock = stock;
        this.category = category;
        this.editorial = editorial;
        this.authors = authors;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return toTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEditionYear() {
        return editionYear;
    }

    public void setEditionYear(Date editionYear) {
        this.editionYear = editionYear;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean save() throws Exception {

        try {

            openConnection();

            query = connection.prepareCall("CALL spManagerBook(?,?,?,?,?,?,?)");

            query.setString(1,getTitle());
            query.setString(2,getCode());
            query.setDate(3,getEditionYear());
            query.setInt(4,getStock());
            query.setString(5,category.getName());
            query.setString(6,editorial.getName());
            query.setString(7,codes(authors));

            query.execute();

            return false;

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
            query = connection.prepareStatement("CALL spDeleteBook(?)");
            query.setString(1,getCode());
            query.execute();

            return false;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return true;
    }



    public static Book newBook(String title, Date editionYear, int stock, String categoryName,  String editorialName, List<Author> authors) throws IOException{
        Book b = new Book();
        b.setTitle(title);
        b.setEditionYear(editionYear);
        b.setStock(stock);
        b.setCode(fnGenerateCode());
        b.setCategory(Category.get(categoryName));
        b.setEditorial(Editorial.get(editorialName));

        b.setAuthors(authors);
        return b;
    }

    public static Book get(String code){
        Book b = new Book();
        List<Author> la = new ArrayList<>();
        try {
            openConnection();
            query = connection.prepareStatement("select  book_tbl.id_book,\n" +
                    "        book_tbl.title,\n" +
                    "        book_tbl.code,\n" +
                    "        book_tbl.edition_year,\n" +
                    "        book_tbl.stock,\n" +
                    "        category_tbl.name,\n" +
                    "        editorial_tbl.name\n" +
                    "from book_tbl inner join category_tbl\n" +
                    "on book_tbl.id_category=category_tbl.id_category\n" +
                    "inner join editorial_tbl \n" +
                    "on book_tbl.id_editorial=editorial_tbl.id_editorial\n" +
                    "WHERE  book_tbl.code=?");

            query.setString(1,code);
            result = query.executeQuery();

            if(result.next()){
                b.setIdBook(result.getInt(1));
                b.setTitle(result.getString(2));
                b.setCode(result.getString(3));
                b.setEditionYear(result.getDate(4));
                b.setStock(result.getInt(5));
                b.setCategory(Category.get(result.getString(6)));
//                b.setEditorial(Editorial.get(result.getString(8)));
            }

// Se sobre escribe y se cierra la DB
//------------------------------------------------------------------------------------------------------------------------
            openConnection();
            query = connection.prepareStatement("select  \n" +
                    "        editorial_tbl.name\n" +
                    "from book_tbl inner join category_tbl\n" +
                    "on book_tbl.id_category=category_tbl.id_category\n" +
                    "inner join editorial_tbl \n" +
                    "on book_tbl.id_editorial=editorial_tbl.id_editorial\n" +
                    "WHERE  book_tbl.code=?");

            query.setString(1,code);
            result = query.executeQuery();

            if(result.next()){
                b.setEditorial(Editorial.get(result.getString(1)));
            }
// Se sobre escribe y se cierra la DB
//------------------------------------------------------------------------------------------------------------------------

            openConnection();
            query = connection.prepareStatement("SELECT  author_tbl.code ,\n" +
                    "\t\tauthor_tbl.id_author ,\n" +
                    "\t\tauthor_tbl.first_name ,\n" +
                    "\t\tauthor_tbl.last_name ,\n" +
                    "\t\tauthor_tbl.email ,\n" +
                    "\t\tauthor_tbl.sex\n" +
                    "from author_tbl inner join book_author_tbl \n" +
                    "on author_tbl.id_author = book_author_tbl.id_author \n" +
                    "inner join book_tbl \n" +
                    "on book_tbl.id_book = book_author_tbl.id_book \n" +
                    "WHERE  book_tbl.code=?");

            query.setString(1,code);
            result = query.executeQuery();

            while (result.next()){

                Author a = new Author();
                a.setCode(result.getString(1));
                a.setIdAuthor(result.getInt(2));
                a.setFirstName(result.getString(3));
                a.setLastName(result.getString(4));
                a.setEmail(result.getString(5));
                a.setSex(result.getBoolean(6));

                la.add(a);
                b.setAuthors(la);
            }

            return b;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();

        }
        return null;
    }

    public static List<Book> all(){
        List<Book> lb = new ArrayList<>();
        try {
            openConnection();
            query = connection.prepareStatement("select  \n" +
                    "\t\tbook_tbl.id_book,\n" +
                    "\t\tbook_tbl.title,\n" +
                    "\t   \tbook_tbl.code,\n" +
                    "\t   \tbook_tbl.edition_year,\n" +
                    "\t   \tbook_tbl.stock, \n" +
                    "\t   \tcategory_tbl.name,\n" +
                    "\t   \teditorial_tbl.name\n" +
                    "from book_tbl inner join category_tbl \n" +
                    "on book_tbl.id_category=category_tbl.id_category\n" +
                    "inner join editorial_tbl \n" +
                    "on book_tbl.id_editorial=editorial_tbl.id_editorial");

            result = query.executeQuery();

            while (result.next()){
                Book b = new Book();
                b.setIdBook(result.getInt(1));
                b.setTitle(result.getString(2));
                b.setCode(result.getString(3));
                b.setEditionYear(result.getDate(4));
                b.setStock(result.getInt(5));
                b.setCategory(Category.get(result.getString(6)));
                b.setEditorial(Editorial.get(result.getString(7)));

                lb.add(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return  lb;
    }

    public String toString() {
        return "Book{" +
                "idBook='" + idBook +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", editionYear='" + editionYear + '\'' +
                ", stock='" + stock + '\'' +
                ", category='" + category.getName() + '\'' +
                ", editorial='" + editorial.getName() + '\'' +
                '}' + "\n";
    }

    private static String codes(List<Author> lA){

        String codes="";

        for (Author la:lA) {
            codes = (codes+la.getCode());
        }

        return codes.trim();
    }

    public static String fnGenerateCode() {

        String fnCode="";

        try {
            openConnection();
            query = connection.prepareStatement("SELECT fnGenerateCode(4)");
            result = query.executeQuery();

            if(result.next()) {
                fnCode = result.getString(1);
                return fnCode;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

/*    public String savePhoto(String frontUrl) throws IOException{
        String fronUrlS;

        fronUrlS = fnGenerateCode() + "." + frontUrl.substring(1 + frontUrl.lastIndexOf('.'));
        copyPhoto(frontUrl);

        return fronUrlS;
    }*/

/*    public boolean updatePhoto(String frontUrl){
*//*        try {
            openConnection();
            query=connection.prepareStatement("UPDATE book_tbl SET front_url=? WHERE code=?");
            query.setString(1,getCode()+"."+getFrontUrl().substring(1+getFrontUrl().lastIndexOf('.')));
            query.setString(2,getCode());

            if(!query.execute()){
                copyPhoto(getFrontUrl());
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;*//*
        setFrontUrl(getCode()+"."+getFrontUrl().substring(1+getFrontUrl().lastIndexOf('.')));
        try {
            copyPhoto(frontUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }*/

/*    public static String savePhotoNewBook(String frontUrl) throws IOException{
        try {
            Book b = new Book();
            b.setFrontUrl(b.getCode() + "." + frontUrl.substring(1 + frontUrl.lastIndexOf('.')));
            b.copyPhoto(frontUrl);
            return b.getFrontUrl();
        }catch (IOException e){
            e.fillInStackTrace();
        }
        return null;
    }*/

/*    public boolean savePhoto() throws Exception {
        boolean var1;
        try {
            openConnection();
            query = connection.prepareStatement("update people_tbl set from_url=? where dni=?");
            query.setString(1, this.getCode() + "." + this.getFrontUrl().substring(1 + this.getFrontUrl().lastIndexOf(46)));
            query.setString(2, this.getCode());
            if (query.execute()) {
                return true;
            }

            this.copyPhoto(this.getFrontUrl());
            var1 = false;
        } catch (Exception var5) {
            var5.printStackTrace();
            return true;
        } finally {
            closeConnection();
        }

        return var1;
    }*/

/*    public boolean copyPhoto(String frontUrl) throws IOException {
      try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            File archivoOriginal = new File(frontUrl);
            String r = String.format(archivoOriginal.toString());
            r = r.substring(r.lastIndexOf('.') + 1);
            String d = System.getProperty("user.dir") + "/src/com/devel/babas/assets/";
            File archivoCopia = new File(d + getCode()+"."+r);

            inputStream = new FileInputStream(archivoOriginal);
            outputStream = new FileOutputStream(archivoCopia);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("Archivo copiado.");

            return true;

      }catch (IOException e){
          e.fillInStackTrace();
      }

      return false;
    }*/

/*    public static Book newPhoto(String photo,String code){
        Book b = new Book();
        b.setCode(code);
        b.setFrontUrl(photo);

        return b;
    }*/

}

