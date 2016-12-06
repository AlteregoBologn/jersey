package model;

import java.io.Serializable;

public class E implements Serializable {
	final static public String OP_INSERT = "I";
	final static public String OP_NOP = "NOP";
	final static public String OP_DELETE = "D";
	final static public String OP_UPDATE = "U";

	boolean isNew=false;
	Long pageFrom = 1L;
	Long pageTo = 10L;
	String orderBy;
	String operation = OP_NOP; // I,U,D,NOP

	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Long getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(Long pageFrom) {
		this.pageFrom = pageFrom;
	}

	public Long getPageTo() {
		return pageTo;
	}

	public void setPageTo(Long pageTo) {
		this.pageTo = pageTo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String op) {
		if( isInsert(operation) ) {
			if( isInsert(op) ) ;
			if( isDelete(op) ) operation=OP_NOP ;
			if( isUpdate(op) ) ;
			if( isNop(op) ) ;
		}
		if( isDelete(operation) ) {
			if( isInsert(op) ) operation=OP_INSERT ;
			if( isDelete(op) ) ;
			if( isUpdate(op) ) operation=OP_UPDATE;
			if( isNop(op) ) operation= OP_NOP;
		}
		if( isUpdate(operation) ) {
			if( isInsert(op) ) ;
			if( isDelete(op) ) operation=OP_DELETE;
			if( isUpdate(op) ) ;
			if( isNop(op) ) operation=OP_NOP;
		}
		if( isNop(operation) ) {
			if( isInsert(op) ) operation=OP_INSERT;
			if( isDelete(op) ) operation=OP_DELETE;
			if( isUpdate(op) ) operation=OP_UPDATE;
			if( isNop(op) ) ;
		}
		this.operation = operation;
	}

	static public boolean isInsert(String operation) {
		return OP_INSERT.equals(operation);
	}

	static public boolean isDelete(String operation) {
		return OP_DELETE.equals(operation);
	}

	static public boolean isUpdate(String operation) {
		return OP_UPDATE.equals(operation);
	}

	static public boolean isNop(String operation) {
		return OP_NOP.equals(operation);
	}
	
	public boolean isInsert() {
		return OP_INSERT.equals(operation);
	}

	public boolean isDelete() {
		return OP_DELETE.equals(operation);
	}

	public boolean isUpdate() {
		return OP_UPDATE.equals(operation);
	}

	public boolean isNop() {
		return OP_NOP.equals(operation);
	}
}
