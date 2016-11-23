package model;

import java.io.Serializable;

public class E implements Serializable {
	final public String OP_INSERT = "I";
	final public String OP_NOP = "NOP";
	final public String OP_DELETE = "D";
	final public String OP_UPDATE = "U";

	Long pageFrom = 1L;
	Long pageTo = 10L;
	String orderBy;
	String operation = "NOP"; // I,U,D,NOP

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

	public void setOperation(String operation) {
		this.operation = operation;
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
