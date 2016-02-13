/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

import net.badway.db.sql.SqlPurpose;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Insert extends SqlPurpose{

    public Insert(List<SqlCol> fields, String table) {
        super(fields, table);
    }

    @Override
    public void starting(Appendable builder) throws IOException {
        builder.append("INSERT INTO ").append(table).append('(');
    }

    @Override
    public void afterField(Appendable builder) throws IOException {
        builder.append(')');
    }

    @Override
    public void ending(Appendable builder) throws IOException {
        builder.append(" VALUES (");
        for(int i =0 ; i< sqlCols.size() ; i++){
            builder.append('?');
            if(i != sqlCols.size()-1){
                builder.append(',');
            }
        }
         builder.append(" )");
    }

    @Override
    public boolean doWhere() {
        return false;
    }

   
    
}
