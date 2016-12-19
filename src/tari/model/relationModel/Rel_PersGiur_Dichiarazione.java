package tari.model.relationModel;

import model.E;

public class Rel_PersGiur_Dichiarazione extends E {

	private Integer iddichiarazione;
	private Integer idpersonagiuridica;

	public void setIddichiarazione(Integer a) {

		this.iddichiarazione = a;
	}
	public Integer getIddichiarazione() {

		return iddichiarazione;
	}
	public void setIdpersonagiuridica(Integer a) {

		this.idpersonagiuridica = a;
	}
	public Integer getIdpersonagiuridica() {

		return idpersonagiuridica;
	}
}
