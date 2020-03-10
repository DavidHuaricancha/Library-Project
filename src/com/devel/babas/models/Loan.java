package com.devel.babas.models;
import com.devel.babas.connectiondb.Connect;
import com.devel.babas.interfaces.IModelsUser;
import java.sql.Date;
import java.util.Timer;

public class Loan extends Connect implements IModelsUser {
    private  int idLoan;
    private int idWorker;
    private String codeBook;
    private String dniReader;
    private Date loanDate;
    private Date loanReturn;
    private boolean state;
    private String loanCode;
    java.util.Date fecha = new java.util.Date();
    public Loan(){
        idLoan =getId();
        idWorker=0;
        codeBook ="";
        dniReader ="";
        loanDate=null;
        loanReturn=null;
        state=true;
        loanCode=getCode();
    }

    public Loan(int idLoan, int idWorker, String codeBook, String dniReader, Date loanDate, Date loanReturn, boolean state, String loanCode) {
        this.idLoan = idLoan;
        this.idWorker = idWorker;
        this.codeBook = codeBook;
        this.dniReader = dniReader;
        this.loanDate = loanDate;
        this.loanReturn = loanReturn;
        this.state = state;
        this.loanCode = loanCode;
    }

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public String getCodeBook() {
        return codeBook;
    }

    public void setCodeBook(String codeBook) {
        this.codeBook = codeBook;
    }

    public String getDniReader() {
        return dniReader;
    }

    public void setDniReader(String dniReader) {
        this.dniReader = dniReader;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanReturn() {
        return loanReturn;
    }

    public void setLoanReturn(Date loanReturn) {
        this.loanReturn = loanReturn;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public static Loan newLoan( int idWorker,String codeBook, String dniReader, Date loanReturn){
        Loan a= new Loan();
        a.setIdWorker(idWorker);
        a.setCodeBook(codeBook);
        a.setDniReader(dniReader);
        a.setLoanReturn(loanReturn);
        return a;
    }
    public boolean save() throws Exception {
        boolean var1;
        try {
            openConnection();
            query = connection.prepareStatement("call spManagerLoan(?,?,?,?)");
            query.setInt(1,this.getIdWorker());
            query.setString(2,this.getCodeBook());
            query.setString(3,this.getDniReader());
            query.setDate(4,this.getLoanReturn());
            if (query.execute()) {
                return true;
            }
            var1 = false;
        } catch (Exception var5) {
            var5.printStackTrace();
            return true;
        } finally {
            closeConnection();
        }
        return var1;
    }
    public static Loan get(String code) {
        Loan o = new Loan();

        try {
            openConnection();
            query = connection.prepareStatement("select id_loan,id_worker,id_book,id_reader,loan_date,loan_date,state,code from loan_tbl where code=? ");
            query.setString(1, code);
            result = query.executeQuery();

            if (result.next()) {
                o.setIdLoan(result.getInt(1));
                o.setIdWorker(result.getInt(2));
                o.setCodeBook(result.getString(3));
                o.setDniReader(result.getString(4));
                o.setLoanDate(result.getDate(5));
                o.setLoanReturn(result.getDate(6));
                o.setState(result.getBoolean(7));
                o.setLoanCode(result.getString(8));
            }

            Loan var2 = o;
            return var2;
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }
    private int getId() {
        int var1;
        try {
            openConnection();
            query = connection.prepareStatement("select  max(id_people)+1 from people_tbl");
            result = query.executeQuery();
            if (!result.next()) {
                return 1;
            }

            var1 = result.getInt(1);
        } catch (Exception var5) {
            var5.printStackTrace();
            return 1;
        } finally {
            closeConnection();
        }

        return var1;
    }
    private String getCode() {
        String code = "";

        try {
            openConnection();
            query = connection.prepareStatement("Select fnGenerateCode('3')");
            result = query.executeQuery();
            if (result.next()) {
                code = result.getString(1);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            closeConnection();
        }

        return code;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "idLoan=" + idLoan +
                ", idWorker=" + idWorker +
                ", codeBook='" + codeBook + '\'' +
                ", dniReader='" + dniReader + '\'' +
                ", loanDate=" + loanDate +
                ", loanReturn=" + loanReturn +
                ", state=" + state +
                ", loanCode='" + loanCode + '\'' +
                '}';
    }
}
