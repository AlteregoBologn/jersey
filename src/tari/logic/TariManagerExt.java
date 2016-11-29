package tari.logic;

import java.util.ArrayList;
import java.util.List;

import tari.model.Dichiarazione;
import tari.model.DichiarazioneSearch;
import tari.model.Immobile;
import tari.model.ImmobileSearch;
import tari.model.PersonaTari;
import tari.model.PersonaTariSearch;
import tari.model.PrecedenteDichiarazione;
import tari.model.PrecedenteDichiarazioneSearch;
import tari.model.relationModel.Rel_Dichiarazione_Immobile;
import tari.model.relationModel.Rel_Dichiarazione_ImmobileSearch;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiara;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiaraSearch;
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_DichiarazioneSearch;
import tari.modelExt.DichiarazioneDiImmobile;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.PersonaTariCompleta;

public class TariManagerExt extends TariManager {

	public List<PersonaTariCompleta> loadPersoneTariCompleta(PersonaTariSearch ps) {
		List<PersonaTariCompleta> ret = new ArrayList();

		List<PersonaTari> personeTari = cercaPersonaTari(ps);
		for (PersonaTari p : personeTari) {
			
			PersonaTariCompleta pc = new PersonaTariCompleta();
			
			pc.setOperation(pc.OP_UPDATE);
			pc.setPersonaTari(p);
			ret.add(pc);

			Rel_PersonaTari_DichiarazioneSearch rpds = new Rel_PersonaTari_DichiarazioneSearch();
			rpds.setIdpersona(p.getUnid());
			List<Rel_PersonaTari_Dichiarazione> rel_Persona_Dichiarazione = cercaRel_Persona_Dichiarazione(rpds);

			for (Rel_PersonaTari_Dichiarazione rpd : rel_Persona_Dichiarazione) {
				rpd.setOperation(pc.OP_UPDATE);
				DichiarazioneSearch ds = new DichiarazioneSearch();
				ds.setUnid(rpd.getIddichiarazione());
				List<Dichiarazione> dichiarazioni = cercaDichiarazione(ds);
				//
				DichiarazioneDiPersonaTari d = new DichiarazioneDiPersonaTari();
				d.setOperation(pc.OP_UPDATE);
				d.setDichiarazione(dichiarazioni.get(0));
				d.setRel_Persona_Dichiarazione(rpd);
				pc.getDichiarazioni().add(d);

				Rel_Dichiarazione_ImmobileSearch rdis = new Rel_Dichiarazione_ImmobileSearch();
				rdis.setIddichiarazione(d.getDichiarazione().getUnid());
				List<Rel_Dichiarazione_Immobile> rel_Dichiarazione_Immobile = cercarel_Dichiarazione_Immobile(rdis);

				for (Rel_Dichiarazione_Immobile rdi : rel_Dichiarazione_Immobile) {
					rdi.setOperation(pc.OP_UPDATE);
					ImmobileSearch is = new ImmobileSearch();
					is.setUnid(rdi.getIdimmobile());
					List<Immobile> immobile = cercaImmobile(is);
					//
					DichiarazioneDiImmobile di = new DichiarazioneDiImmobile();
					di.setOperation(pc.OP_UPDATE);
					di.setImmobile(immobile.get(0));
					di.setRel_Dichiarazione_Immobile(rdi);
					pc.getDichiarazioniImmobili().add(di);
				}
				
				Rel_Dichiarazione_PrecDichiaraSearch rdps = new Rel_Dichiarazione_PrecDichiaraSearch();
				rdps.setIddichiarazione(d.getDichiarazione().getUnid());
				List<Rel_Dichiarazione_PrecDichiara> rel_Dichiarazione_PrecDichiara = cercaRel_Dichiarazione_PrecDichiara(rdps);

				for (Rel_Dichiarazione_PrecDichiara rdp : rel_Dichiarazione_PrecDichiara) {
					rdp.setOperation(pc.OP_UPDATE);
					PrecedenteDichiarazioneSearch pds = new PrecedenteDichiarazioneSearch();
					pds.setUnid(rdp.getIdprecedentedichiarazione());
					List<PrecedenteDichiarazione> precedenteDichiarazione = cercaPrecedenteDichiarazione(pds);
					//
					DichiarazioneDiPersonaTari dpt = new DichiarazioneDiPersonaTari();
					dpt.setOperation(pc.OP_UPDATE);
					dpt.setPrecedenteDichiarazione(precedenteDichiarazione.get(0));
					dpt.setRel_Dichiarazione_PrecDichiara(rdp);
					pc.getDichiarazioni().add(dpt);
				}
			}
		}
		return ret;
	}

	public PersonaTariCompleta loadPersonaTariCompleta(Integer unid) {
		PersonaTariSearch ps = new PersonaTariSearch();
		ps.setUnid(unid);
		List<PersonaTariCompleta> ret = loadPersoneTariCompleta(ps);
		if (ret.isEmpty())
			throw new RuntimeException("Non trovo una persona con unid: " + unid);
		return ret.get(0);
	}

	private void savePersonaTari(PersonaTari p) {
		if (p.isInsert()) {
			inserisciPersonaTari(p);
		} else if (p.isUpdate()) {
			updatePersonaTari(p);
		} else if (p.isDelete()) {
			p.setCanc("S");
			updatePersonaTari(p);
		}
	}

	public void savePersonaTariCompleta(PersonaTariCompleta p) {
		if (p.isInsert() || p.isUpdate()) {
			savePersonaTari(p.getPersonaTari());
			for (DichiarazioneDiPersonaTari d : p.getDichiarazioni()) {
				saveDichiarazioneDiPersonaTari(d);
			}
		} else if (p.isDelete()) {
			savePersonaTari(p.getPersonaTari());
			for (DichiarazioneDiPersonaTari d : p.getDichiarazioni()) {
				saveDichiarazioneDiPersonaTari(d);
			}
		}
		p.setOperation(p.OP_NOP);
	}

	/***** DICHIRAZIONE DI PERSONATARI ****/	

	private void saveDichiarazione(Dichiarazione d) {
		if (d.isInsert()) {
			inserisciDichiarazione(d);
		} else if (d.isUpdate()) {
			updateDichiarazione(d);
		} else if (d.isDelete()) {
			deleteDichiarazione(d);
		}
	}

	public void saveDichiarazioneDiPersonaTari(DichiarazioneDiPersonaTari mp) {
		if (mp.isInsert()) {
			Rel_PersonaTari_Dichiarazione rpd = new Rel_PersonaTari_Dichiarazione();
			rpd.setIddichiarazione(mp.getDichiarazione().getUnid());
			inserisciRel_Persona_Dichiarazione(rpd);
		} else if (mp.isUpdate()) {

		} else if (mp.isDelete()) {

		} else if (mp.isNop()) {

		}


	}


}
