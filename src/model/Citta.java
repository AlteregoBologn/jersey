package model;

public class Citta {

	private String nomecitta;
	private String unid;

	public void setNomecitta(String a) {

		this.nomecitta = a;
	}
	public String getNomecitta() {

		return nomecitta;
	}
	public void setUnid(String a) {

		this.unid = a;
	}
	public String getUnid() {

		return unid;
	}
	@Override
	public String toString() {
		return "Citta [nomecitta=" + nomecitta + ", unid=" + unid + "]";
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
		Citta other = (Citta) obj;
		if (unid == null) {
			if (other.unid != null)
				return false;
		} else if (!unid.equals(other.unid))
			return false;
		return true;
	}
	
	
}
