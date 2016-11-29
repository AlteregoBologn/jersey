package tari.model.relationModel;

import model.E;

public class Rel_PersonaTari_Dichiarazione extends E {

	private Integer iddichiarazione;
	private Integer idpersona;

	public void setIddichiarazione(Integer a) {

		this.iddichiarazione = a;
	}
	public Integer getIddichiarazione() {

		return iddichiarazione;
	}
	public void setIdpersona(Integer a) {

		this.idpersona = a;
	}
	public Integer getIdpersona() {

		return idpersona;
	}
}
