package tari.model.relationModel;

import model.E;

public class Rel_Dichiarazione_PrecDichiara extends E {

	private Integer iddichiarazione;
	private Integer idprecedentedichiarazione;

	public void setIddichiarazione(Integer a) {

		this.iddichiarazione = a;
	}
	public Integer getIddichiarazione() {

		return iddichiarazione;
	}
	public void setIdprecedentedichiarazione(Integer a) {

		this.idprecedentedichiarazione = a;
	}
	public Integer getIdprecedentedichiarazione() {

		return idprecedentedichiarazione;
	}
}