package model;

public class PersonaSearch extends Persona {
	
	String orderBy;
	
	String cognomeLike;

	public String getCognomeLike() {
		return cognomeLike;
	}

	public void setCognomeLike(String cognomeLike) {
		this.cognomeLike = cognomeLike;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "PersonaSearch [orderBy=" + orderBy + ", cognomeLike=" + cognomeLike + ", getCognomeLike()=" + getCognomeLike() + ", getOrderBy()=" + getOrderBy() + ", getDatanascita()=" + getDatanascita() + ", getComunenascita()=" + getComunenascita() + ", getSesso()=" + getSesso() + ", getUnid()=" + getUnid() + ", getNome()=" + getNome() + ", getCognome()=" + getCognome() + ", getCf()=" + getCf() + ", getCanc()=" + getCanc() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", hashCode()=" + hashCode() + ", getPageFrom()=" + getPageFrom() + ", getPageTo()=" + getPageTo() + ", getOperation()=" + getOperation() + ", isInsert()=" + isInsert() + ", isDelete()=" + isDelete() + ", isUpdate()=" + isUpdate() + ", isNop()=" + isNop() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
	
	
}
