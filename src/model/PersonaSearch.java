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
	
	
}
