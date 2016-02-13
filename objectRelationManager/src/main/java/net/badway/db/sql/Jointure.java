/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

import java.util.Objects;

/**
 *
 * @author jonathan
 */
public class Jointure {
    private String joitureName;
    private String tableName;
    private String jointureCondition;

    public Jointure(String joitureName, String tableName, String jointureCondition) {
        Objects.requireNonNull(joitureName);
        Objects.requireNonNull(tableName);
        Objects.requireNonNull(jointureCondition);
        this.joitureName = joitureName;
        this.tableName = tableName;
        this.jointureCondition = jointureCondition;
    }

   
    

    public String getJoitureName() {
        return joitureName;
    }

    public void setJoitureName(String joitureName) {
        this.joitureName = joitureName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getJointureCondition() {
        return jointureCondition;
    }

    public void setJointureCondition(String jointureCondition) {
        this.jointureCondition = jointureCondition;
    }

    @Override
    public String toString() {
        return joitureName +" "+tableName+" ON "+jointureCondition;
    }
    
}
