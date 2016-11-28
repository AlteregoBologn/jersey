package model;

public class Esenzione extends E{
	private Integer unid;
	private String descrizione;
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getUnid() {
		return unid;
	}
	public void setUnid(Integer unid) {
		this.unid = unid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unid == null) ? 0 : unid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esenzione other = (Esenzione) obj;
		if (unid == null) {
			if (other.unid != null)
				return false;
		} else if (!unid.equals(other.unid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Esenzione [unid=" + unid + ", descrizione=" + descrizione + "]";
	}
	
	
}
