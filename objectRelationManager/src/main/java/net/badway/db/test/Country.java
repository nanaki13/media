/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.test;

import net.badway.db.annotation.SqlField;
import net.badway.db.annotation.Id;

/**
 *
 * @author jonathan
 */
public class Country {
    @Id
    @SqlField(sqlType = "CHAR(3)")
    private String code;
    @SqlField( sqlType = "VARCHAR(75)")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + '}';
    }
    
}
