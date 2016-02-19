package org.nanaki.db;

public class SQLCol {

	private String name;
	private String type;
	private boolean fk;
	private String tableFk;
	private boolean id;
	private String tableColumnFk;

	public void setName(String name) {
		this.name = name;
		
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFk(boolean b) {
		fk = b;
		
	}

	public void setTableFk(String table) {
		this.tableFk = table;
		
	}

	public void setIsId(boolean b) {
		id = b;
		
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	

	public String getTableFk() {
		return tableFk;
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public boolean isFk() {
		return fk;
	}

	public String getFkReference() {
		// TODO Auto-generated method stub
		return tableFk+"("+tableColumnFk+")";
	}

	public void setTableColumnFk(String string) {
		this.tableColumnFk = string;
		
	}

	public String getTableColumnFk() {
		return tableColumnFk;
	}
	
	

}
