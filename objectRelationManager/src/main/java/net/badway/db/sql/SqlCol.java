/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.sql;

/**
 *
 * @author jonathan
 */
public class SqlCol {

    private String table;
    private String column;
    private boolean distante;

    private SqlCol(boolean distante){
        this.distante = distante;
    }
    public SqlCol(String table, String column, boolean distante) {
        this.table = table;
        this.column = column;
        this.distante = distante;
    }

    public SqlCol(String column, boolean distante) {
        this.distante = distante;
        this.column = column;
    }

    public String getTable() {
        return table;
    }

    public String getColumn() {
        return column;
    }

    public String toString() {
        if (table != null && !table.isEmpty()) {
            return table + "." + column;
        }
        return column;
    }

    public boolean isDistante() {
        return distante;
    }

    public SqlCol copy(boolean distant) {
        SqlCol copy = new SqlCol(distant);
        copy.column = column;
        copy.table = table;
        return copy;
    }

}
