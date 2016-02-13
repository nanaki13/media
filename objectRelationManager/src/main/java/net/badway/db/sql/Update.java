/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

import java.io.IOException;
import java.util.List;
import net.badway.db.util.Utils;

/**
 *
 * @author jonathan
 */
public class Update extends SqlPurpose{

    public Update(List<SqlCol> fields, String table) {
        super(fields, table);
    }

    @Override
    public void starting(Appendable builder) throws IOException {
        builder.append("UPDATE ").append(table).append(" SET");
    }

    @Override
    public void fields(Appendable builder) throws IOException {
        builder.append(' ');
       Utils.append(sqlCols, " = ?,"," = ?", builder);
    }
    

    @Override
    public void afterField(Appendable builder) throws IOException {
        
    }

    @Override
    public void ending(Appendable builder) throws IOException {
    }

    @Override
    public boolean doWhere() {
        return true;
    }

    

  
    
}
