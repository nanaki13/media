/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

import net.badway.db.sql.Select;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class SqlGenerator {

    private StringBuilder buff = new StringBuilder();

    public static void main(String[] args) {
        Select s = new Select(Arrays.asList(new SqlCol("table1", "id",false)), "table1");
        s.setWhereClause("id=4");
        s.setJointures(Arrays.asList(new Jointure("JOIN", "ami", "ami.id=table")));
        System.out.println(s);
        
        Insert i = new Insert(Arrays.asList(new SqlCol("table1", "id",false)), "table1");
       
        System.out.println(i);
        
        Update u = new Update(Arrays.asList(new SqlCol("table1", "id",false)), "table1");
         u.setWhereClause("id=4");
        System.out.println(u);
    }
}
