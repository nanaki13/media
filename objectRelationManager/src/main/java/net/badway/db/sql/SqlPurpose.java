/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.badway.db.util.Utils;

/**
 *
 * @author jonathan
 */
public abstract class SqlPurpose {

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Jointure> getJointures() {
        return jointures;
    }

    public void setJointures(List<Jointure> jointures) {
        this.jointures = jointures;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public List<SqlCol> getSqlCols() {
        return sqlCols;
    }

    public void setSqlCols(List<SqlCol> fields) {
        this.sqlCols = fields;
    }

    protected String table;
    protected List<Jointure> jointures;
    protected String whereClause;
    protected List<SqlCol> sqlCols;
    public SqlPurpose(List<SqlCol> fields,String table) {
        this.sqlCols = fields;
        this.table = table;
    }

     
    public void appendMe(Appendable builder) throws IOException {
        starting(builder);
        fields(builder);
        afterField(builder);
        jointure(builder);
        if(doWhere())
            where(builder);
        ending(builder);
        
        
    }
    public void fields(Appendable builder) throws IOException {

        if (sqlCols != null && !sqlCols.isEmpty()) {
            builder.append(' ');
         
                Utils.append(sqlCols, ',', builder);
     

        }

    }

    public void jointure(Appendable builder) throws IOException {
        builder.append(' ');

        if (jointures != null && !jointures.isEmpty()) {
            Utils.append(jointures, ',', builder);
        }

    }

    public void add(Jointure j){
        if(jointures == null){
            jointures = new LinkedList<>();
        }
        jointures.add(j);
    }
    public void where(Appendable builder) throws IOException {
        if (whereClause != null) {
            builder.append(" WHERE ").append(whereClause);
        }
    }

    public abstract void starting(Appendable builder) throws IOException;

    public abstract void afterField(Appendable builder) throws IOException;

    public abstract void ending(Appendable builder)throws IOException;

    public abstract boolean doWhere() ;
   
    
    @Override
    public String toString() {
        try {
            StringBuilder builder = new StringBuilder();
            appendMe(builder);
            return builder.toString();
        } catch (IOException ex) {
            Logger.getLogger(Select.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

}
