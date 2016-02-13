/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Select extends SqlPurpose{

   
     private String groupByClause;

    public Select(List<SqlCol> fields, String table) {
        super(fields, table);
    }

    


    

    public String getGroupByClause() {
        return groupByClause;
    }

    public void setGroupByClause(String groupByClause) {
        this.groupByClause = groupByClause;
    }
    

    
     

    
   

   

    

    private void groubBy(Appendable builder) throws IOException {
        if(groupByClause!=null){
            builder.append(" GROUP BY "+groupByClause);
        }
    }

    @Override
    public void starting(Appendable builder) throws IOException {
         builder.append("SELECT");
    }

    @Override
    public void afterField(Appendable builder) throws IOException {
         builder.append(" FROM ").append(table);
    }

    @Override
    public void ending(Appendable builder)  throws IOException {
        groubBy(builder);
    }

    @Override
    public boolean doWhere() {
        return true;
    }

    public Select copyJustColsTableAndJointure() {
        Select s = new Select(sqlCols, table);
        s.setJointures(jointures);
       return s;
    }
    
    

}
