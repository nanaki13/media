package org.nanaki.db;

import java.util.function.Function;
import java.util.function.Predicate;

import org.nanaki.util.Strings;

public class Table {

	private String name;
	private SQLCol[] sqlCols;
	private SQLCol[] fkCols;

	public Table(String tableName, SQLCol[] sqlCols) {
		this.name = tableName;
		this.sqlCols = sqlCols;
		fkCols = filter(sqlCols , (e) -> e.isFk() );
	}

	private static SQLCol[] filter(SQLCol[] sqlCols,
			Predicate<SQLCol> predi) {
		int count = 0;
		for(SQLCol t : sqlCols){
			if(predi.test(t)){
				count++;
			}
		}
		SQLCol[] ret = new SQLCol[count];
		count = 0;
		for(SQLCol t : sqlCols){
			if(predi.test(t)){
				ret[count] = t;
				count++;
			}
		}
		return ret;
	}

	public String create() {
		StringBuilder builder = new StringBuilder(
				"CREATE TABLE IF NOT EXISTS ");
		Function<SQLCol, String> nameAndType = (e) -> e.getName()+" "+e.getType();
		Function<SQLCol, String> name = (e) -> e.getName();
		Function<SQLCol, String> fk = (e) -> "FOREIGN KEY ("+e.getName()+") REFERENCES " +e.getFkReference() ;
		builder.append(this.name).append(" (");
		builder.append(Strings.implode(sqlCols, nameAndType, ", "));
		builder.append("\n,Primary Key ( ");
		builder.append(Strings.implode(sqlCols, name, ", "));
		builder.append(" ), ");
		builder.append(Strings.implode(fkCols, fk, ", "));
		
		builder.append(" )");

		return builder.toString();
	}

	public String drop() {
		return "DROP TABLE " + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SQLCol[] getSqlCols() {
		return sqlCols;
	}

	public void setSqlCols(SQLCol[] sqlCols) {
		this.sqlCols = sqlCols;
	}

	public String insert() {
		StringBuilder bl = new StringBuilder();
		bl.append("INSERT INTO ").append(name).append(" (")
				.append(Strings.implode(sqlCols,
						(s) -> s.getName(), ", "))
				.append(") VALUES (").append(Strings.implode("?", ",", sqlCols.length)).append(")");
		return bl.toString();
	}

	public String selectWherePrepared(SQLCol[] cols) {
		StringBuilder bl = new StringBuilder();
		bl.append("SELECT * FROM ").append(name).append(" WHERE ")
				.append(Strings.implode(cols,
						(s) -> s.getName()+" = ?", ", "));
		return bl.toString();
	}

}
