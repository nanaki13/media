/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Utils {

    public static void append(List<?> fiels, char c, Appendable builder) throws IOException {
        Iterator<?> iterator = fiels.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());
            if (iterator.hasNext()) {
                builder.append(c);
            }
        }
    }

    public  static void append(List<?> fields, String allWithoutEnd,String end, Appendable builder) throws IOException {
          Iterator<?> iterator = fields.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());
            if (iterator.hasNext()) {
                builder.append(allWithoutEnd);
            }else{
                builder.append(end);
            }
        }
    }

    public static void appendWithSuffix(String table, List<?> fields, char c, Appendable builder) throws IOException {
          Iterator<?> iterator = fields.iterator();
        while (iterator.hasNext()) {
            builder.append(table);
            builder.append(iterator.next().toString());
            if (iterator.hasNext()) {
                builder.append(c);
            }
        }
    }

}
