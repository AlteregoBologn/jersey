package tari.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;

import model.Decodifica;
import model.DecodificaSearch;
import model.Indirizzo;
import model.IndirizzoSearch;
import model.Rel_Persona_Indirizzo;
import model.Rel_Persona_IndirizzoSearch;
import modelExt.MedicoDiPersona;
import modelExt.PersonaCompleta;
import tari.model.Allegato;
import tari.model.AllegatoSearch;
import tari.model.Dichiarazione;
import tari.model.DichiarazioneSearch;
import tari.model.Immobile;
import tari.model.ImmobileSearch;
import tari.model.Locale;
import tari.model.LocaleSearch;
import tari.model.PersonaGiuridica;
import tari.model.PersonaGiuridicaSearch;
import tari.model.PersonaTari;
import tari.model.PersonaTariSearch;
import tari.model.PrecedenteDichiarazione;
import tari.model.PrecedenteDichiarazioneSearch;
import tari.model.relationModel.Rel_Dichiarazione_Immobile;
import tari.model.relationModel.Rel_Dichiarazione_ImmobileSearch;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiara;
import tari.model.relationModel.Rel_Dichiarazione_PrecDichiaraSearch;
import tari.model.relationModel.Rel_Immobile_Locale;
import tari.model.relationModel.Rel_Immobile_LocaleSearch;
import tari.model.relationModel.Rel_PersGiur_Dichiarazione;
import tari.model.relationModel.Rel_PersonaGiuridica_Allegato;
import tari.model.relationModel.Rel_PersonaTari_Allegato;
import tari.model.relationModel.Rel_PersonaTari_AllegatoSearch;
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_DichiarazioneSearch;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridica;
import tari.model.relationModel.Rel_PersonaTari_PersonaGiuridicaSearch;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.ImmobileDiDichiarazione;
import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import util.JACK;
import web.c.MyCheckBox;

public class TariManagerExt extends TariManager {

	public List<PersonaTariCompleta> loadPersoneTariCompleta(PersonaTariSearch ps) {
		List<PersonaTariCompleta> ret = new ArrayList<PersonaTariCompleta>();

		List<PersonaTari> personeTari = cercaPersonaTari(ps);
		for (PersonaTari p : personeTari) {

			PersonaTariCompleta pc = new PersonaTariCompleta();

			// pc.setOperation(pc.OP_NOP);
			pc.setOperation(pc.OP_UPDATE);
			pc.setPersonaTari(p);

			loadPersonaGiuridicaDiPersona(pc);

			//loadAllegatiDiPersonaTariCompleta(pc);

			loadIndirizzoDiPersonaTari(pc);

			loadDichiarazioniDiPersonaTari(p, pc);
			ret.add(pc);
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
			insertPersonaTari(p);
		} else if (p.isUpdate()) {
			updatePersonaTari(p);
		} else if (p.isDelete()) {
			p.setCanc("S");
			updatePersonaTari(p);
		}
	}

	private void savePersonaGiuridica(PersonaGiuridica pg) {
		if (pg.isInsert()) {
			insertPersonaGiuridica(pg);
		} else if (pg.isUpdate()) {
			updatePersonaGiuridica(pg);
		} else if (pg.isDelete()) {
			deletePersonaGiuridica(pg);
		}
	}

//	private void salvaAllegato(Allegato allegato) {
//		if (allegato.isInsert()) {
//			insertAllegato(allegato);
//		} else if (allegato.isUpdate()) {
//			updateAllegato(allegato);
//		} else if (allegato.isDelete()) {
//			deleteAllegato(allegato);
//		}
//
//	}

//	private void saveCartaIdentita(PersonaTariCompleta pc) {
//		if (pc.isInsert()) {
//
//			salvaAllegato(pc.getCartaIdentita());
//
//			Rel_PersonaTari_Allegato rel = new Rel_PersonaTari_Allegato();
//			rel.setIdAllegato(pc.getCartaIdentita().getUnid());
//			rel.setIdPersonaTari(pc.getPersonaTari().getUnid());
//			insertRel_PersonaTari_Allegato(rel);
//		}
//		if (pc.isUpdate()) {
//			salvaAllegato(pc.getCartaIdentita());
//		}
//	}

//	private void saveVisuraCamerale(PersonaTariCompleta pc) {
//		if (pc.isInsert()) {
//			salvaAllegato(pc.getVisuraCamerale());
//
//			Rel_PersonaGiuridica_Allegato rel = new Rel_PersonaGiuridica_Allegato();
//			rel.setIdPersonaGiuridica(pc.getPersonaGiuridica().getUnid());
//			rel.setIdAllegato(pc.getVisuraCamerale().getUnid());
//			insertRel_PersonaGiuridica_Allegato(rel);
//		}
//		if (pc.isUpdate()) {
//			salvaAllegato(pc.getVisuraCamerale());
//		}
//	}

	private void loadAllegatiDiPersonaTariCompleta(PersonaTariCompleta pc) {
		Rel_PersonaTari_AllegatoSearch rs = new Rel_PersonaTari_AllegatoSearch();
		rs.setIdPersonaTari(pc.getPersonaTari().getUnid());
		List<Rel_PersonaTari_Allegato> listaRel = searchRel_PersonaTari_Allegato(rs);
		for (Rel_PersonaTari_Allegato rel : listaRel) {
			AllegatoSearch allegatoSearch = new AllegatoSearch();
			allegatoSearch.setUnid(rel.getIdAllegato());
			List<Allegato> allegati = searchAllegato(allegatoSearch);
			for (Allegato a : allegati) {
				if ("CI".equals(a.getTipo())) {
					pc.setCartaIdentita(a);
				}
				if ("VC".equals(a.getTipo())) {
					pc.setVisuraCamerale(a);
				} else {
					//
				}
			}
		}

	}

	public void salvaPersonaGiuridicaDiPersona(PersonaTariCompleta p) {
		if (p.isInsert()) {
			savePersonaGiuridica(p.getPersonaGiuridica());

			Rel_PersonaTari_PersonaGiuridica rel = new Rel_PersonaTari_PersonaGiuridica();
			rel.setIdPersTari(p.getPersonaTari().getUnid());
			rel.setIdPersGiur(p.getPersonaGiuridica().getUnid());
			insertRel_PersonaTari_PersonaGiuridica(rel);
		} else if (p.isUpdate()) {
			savePersonaGiuridica(p.getPersonaGiuridica());
		}
	}

	public void loadPersonaGiuridicaDiPersona(PersonaTariCompleta p) {
		Rel_PersonaTari_PersonaGiuridicaSearch s = new Rel_PersonaTari_PersonaGiuridicaSearch();
		s.setIdPersTari(p.getPersonaTari().getUnid());
		List<Rel_PersonaTari_PersonaGiuridica> listRel = searchRel_PersonaTari_PersonaGiuridica(s);
		for (Rel_PersonaTari_PersonaGiuridica rel : listRel) {
			PersonaGiuridicaSearch ps = new PersonaGiuridicaSearch();
			ps.setUnid(rel.getIdPersGiur());
			List<PersonaGiuridica> listaPg = cercaPersonaGiuridica(ps);
			if (!listaPg.isEmpty()) {
				p.setPersonaGiuridica(listaPg.get(0));
			}
		}
	}

	public void savePersonaTariCompleta(PersonaTariCompleta p) {
		if (p.isInsert()) {
			p.getPersonaTari().setOperation(p.OP_INSERT);
			savePersonaTari(p.getPersonaTari());

			salvaPersonaGiuridicaDiPersona(p);
			//saveCartaIdentita(p);
			//saveVisuraCamerale(p);

			saveIndirizzoDiPersonaTari(p);

			for (DichiarazioneDiPersonaTari d : p.getDichiarazioniDiPersona()) {
				saveDichiarazioneDiPersonaTari(d, p);

			}

			for (DichiarazioneDiPersonaTari d : p.getDichiarazioniDiPersona()) {
				saveDichiarazioneDiPersonaGiuridica(d, p);

			}
		} else if (p.isUpdate()) {
			p.getPersonaTari().setOperation(p.OP_UPDATE);
			savePersonaTari(p.getPersonaTari());

			salvaPersonaGiuridicaDiPersona(p);
			//saveCartaIdentita(p);
			//saveVisuraCamerale(p);

			saveIndirizzoDiPersonaTari(p);

			// TODO da rivedere

		} else if (p.isDelete()) {
			savePersonaTari(p.getPersonaTari());

			p.getPersonaGiuridica().setCanc("S");
			salvaPersonaGiuridicaDiPersona(p);

			for (DichiarazioneDiPersonaTari d : p.getDichiarazioniDiPersona()) {
				saveDichiarazioneDiPersonaTari(d, p);
			}
		}
		p.setOperation(p.OP_NOP);
	}

	/***** INDIRIZZO DI PERSONATARI ****/
	public void loadIndirizzoDiPersonaTari(PersonaTariCompleta pc) {
		Rel_Persona_IndirizzoSearch rpis = new Rel_Persona_IndirizzoSearch();
		rpis.setIdPersona(pc.getPersonaTari().getUnid());
		List<Rel_Persona_Indirizzo> relazioni = cercaRel_Persona_Indirizzo(rpis);
		if (!relazioni.isEmpty()) {
			IndirizzoSearch rs = new IndirizzoSearch();
			rs.setUnid(relazioni.get(0).getIdIndirizzo());
			pc.setResidenza(cercaIndirizzo(rs).get(0));
		}
	}

	public void saveIndirizzo(Indirizzo i) {
		if (i.isInsert()) {
			inserisciIndirizzo(i);
		} else if (i.isUpdate()) {
			updateIndirizzo(i);
		} else if (i.isDelete()) {
			deleteIndirizzo(i);
		}
	}

	public void saveIndirizzoDiPersonaTari(PersonaTariCompleta pc) {

		if (pc.isInsert()) {
			saveIndirizzo(pc.getResidenza());
			Rel_Persona_Indirizzo rpi = new Rel_Persona_Indirizzo();
			rpi.setIdPersona(pc.getPersonaTari().getUnid());
			rpi.setIdIndirizzo(pc.getResidenza().getUnid());
			inserisciRel_Persona_Indirizzo(rpi);
		}
		if (pc.isUpdate()) {
			saveIndirizzo(pc.getResidenza());
		}

	}

	/***** DICHIARAZIONE DI PERSONATARI ****/
	public void loadDichiarazioniDiPersonaTari(PersonaTari p, PersonaTariCompleta pc) {

		Rel_PersonaTari_DichiarazioneSearch rpds = new Rel_PersonaTari_DichiarazioneSearch();
		rpds.setIdpersona(p.getUnid());
		List<Rel_PersonaTari_Dichiarazione> rel_Persona_Dichiarazione = cercaRel_Persona_Dichiarazione(rpds);

		// TODO gestire meglio l'errore
		if (rel_Persona_Dichiarazione.isEmpty()) {
			new RuntimeException("nessuna dichiarazione corrispondente tra i due");
		} else {

			for (Rel_PersonaTari_Dichiarazione rpd : rel_Persona_Dichiarazione) {
				DichiarazioneSearch ds = new DichiarazioneSearch();
				ds.setUnid(rpd.getIddichiarazione());
				List<Dichiarazione> dichiarazioni = cercaDichiarazione(ds);

				DichiarazioneDiPersonaTari d = new DichiarazioneDiPersonaTari();
				d.setDichiarazione(dichiarazioni.get(0));
				d.setRel_Persona_Dichiarazione(rpd);
				pc.getDichiarazioniDiPersona().add(d);
				loadImmobileDiDichiarazione(d, pc);
				loadPrecedenteDichiarazioneDiDichiarazione(d, pc);
			}
		}

	}

	private void saveDichiarazione(Dichiarazione d) {
		if (d.isInsert()) {
			inserisciDichiarazione(d);
		} else if (d.isUpdate()) {
			updateDichiarazione(d);
		} else if (d.isDelete()) {
			deleteDichiarazione(d);
		}
	}

	public void saveDichiarazioneDiPersonaTari(DichiarazioneDiPersonaTari mp, PersonaTariCompleta pc) {

		if (mp.isInsert()) {
			Rel_PersonaTari_Dichiarazione rpd = new Rel_PersonaTari_Dichiarazione();
			mp.getDichiarazione().setOperation(mp.OP_INSERT);
			if (mp.getDichiarazione().getData() == null)
				mp.getDichiarazione().setData(new Date());
			saveDichiarazione(mp.getDichiarazione());
			rpd.setIddichiarazione(mp.getDichiarazione().getUnid());
			rpd.setIdpersona(pc.getPersonaTari().getUnid());
			inserisciRel_Persona_Dichiarazione(rpd);
			pc.setOperation(pc.OP_INSERT);
			saveImmobileDiDichiarazione(mp, pc);
			savePrecedenteDichiarazioneDiDichiarazione(mp);
		}

		if (mp.isUpdate()) {
			mp.getDichiarazione().setOperation(mp.OP_UPDATE);
			saveDichiarazione(mp.getDichiarazione());

			pc.setOperation(pc.OP_UPDATE);
			saveImmobileDiDichiarazione(mp, pc);
			savePrecedenteDichiarazioneDiDichiarazione(mp);
		}
	}

	public void saveDichiarazioneDiPersonaGiuridica(DichiarazioneDiPersonaTari mp, PersonaTariCompleta pc) {

		if (mp.isInsert()) {
			Rel_PersGiur_Dichiarazione rpgd = new Rel_PersGiur_Dichiarazione();
			mp.getDichiarazione().setOperation(mp.OP_INSERT);
			if (mp.getDichiarazione().getData() == null)
				mp.getDichiarazione().setData(new Date());
			saveDichiarazione(mp.getDichiarazione());
			rpgd.setIddichiarazione(mp.getDichiarazione().getUnid());
			rpgd.setIdpersonagiuridica(pc.getPersonaGiuridica().getUnid());
			insertRel_PersGiuridica_Dichiarazione(rpgd);
			pc.setOperation(pc.OP_INSERT);
			saveImmobileDiDichiarazione(mp, pc);
			savePrecedenteDichiarazioneDiDichiarazione(mp);
		}

		if (mp.isUpdate()) {
			mp.getDichiarazione().setOperation(mp.OP_UPDATE);
			saveDichiarazione(mp.getDichiarazione());

			pc.setOperation(pc.OP_UPDATE);
			saveImmobileDiDichiarazione(mp, pc);
			savePrecedenteDichiarazioneDiDichiarazione(mp);
		}
	}

	/***** IMMOBILE DI DICHIARAZIONE ****/
	public void loadImmobileDiDichiarazione(DichiarazioneDiPersonaTari d, PersonaTariCompleta pc) {
		Rel_Dichiarazione_ImmobileSearch rdis = new Rel_Dichiarazione_ImmobileSearch();
		rdis.setIddichiarazione(d.getDichiarazione().getUnid());
		List<Rel_Dichiarazione_Immobile> rel_Dichiarazione_Immobile = cercarel_Dichiarazione_Immobile(rdis);

		for (Rel_Dichiarazione_Immobile rdi : rel_Dichiarazione_Immobile) {
			rdi.setOperation(pc.OP_NOP);
			ImmobileSearch is = new ImmobileSearch();
			is.setUnid(rdi.getIdimmobile());
			List<Immobile> immobile = cercaImmobile(is);
			//
			ImmobileDiDichiarazione di = new ImmobileDiDichiarazione();
			di.setOperation(pc.OP_NOP);
			di.setImmobile(immobile.get(0));
			di.setRel_Dichiarazione_Immobile(rdi);
			d.setDichiarazioneImmobile(di);
			loadLocaleDiImmobile(d, pc, di);
		}
	}

	public void saveImmobile(Immobile i) {
		if (i.isInsert()) {
			inserisciImmobile(i);
		} else if (i.isUpdate()) {
			updateImmobile(i);
		} else if (i.isDelete()) {
			deleteImmobile(i);
		}
		i.setOperation(i.OP_NOP);
	}

	public void saveImmobileDiDichiarazione(DichiarazioneDiPersonaTari mp, PersonaTariCompleta pc) {

		if (pc.isInsert()) {
			Rel_Dichiarazione_Immobile rdi = new Rel_Dichiarazione_Immobile();
			ImmobileDiDichiarazione idd = mp.getDichiarazioneImmobile();

			idd.setOperation(idd.OP_INSERT);
			idd.getImmobile().setOperation(idd.OP_INSERT);
			saveImmobile(idd.getImmobile());
			rdi.setIdimmobile(idd.getImmobile().getUnid());
			rdi.setIddichiarazione(mp.getDichiarazione().getUnid());
			inseriscirel_Dichiarazione_Immobile(rdi);

			saveLocaleDiImmobile(idd);
		}

		else if (pc.isUpdate()) {
			ImmobileDiDichiarazione idd = mp.getDichiarazioneImmobile();
			idd.setOperation(idd.OP_UPDATE);
			idd.getImmobile().setOperation(idd.OP_UPDATE);
			saveImmobile(idd.getImmobile());
			saveLocaleDiImmobile(idd);
		}
	}

	/***** LOCALE DI IMMOBILE ****/
	public void loadLocaleDiImmobile(DichiarazioneDiPersonaTari d, PersonaTariCompleta pc, ImmobileDiDichiarazione di) {

		Rel_Immobile_LocaleSearch rils = new Rel_Immobile_LocaleSearch();
		rils.setIdimmobile(di.getImmobile().getUnid());
		List<Rel_Immobile_Locale> rel_Immobile_Locale = cercaRel_Immobile_Locale(rils);

		for (Rel_Immobile_Locale ril : rel_Immobile_Locale) {
			ril.setOperation(pc.OP_NOP);
			LocaleSearch ls = new LocaleSearch();
			ls.setUnid(ril.getIdlocale());
			List<Locale> locali = cercaLocale(ls);
			// TODO gestire meglio l'errore
			if (locali.isEmpty()) {
				locali.get(0).setParticella("Nessun locale corrispondente, è illegale se c'è l'immobile");
			}
			LocaleDiImmobile li = new LocaleDiImmobile();
			li.setOperation(pc.OP_NOP);
			li.setLocale(locali.get(0));
			DecodificaSearch ds = new DecodificaSearch();
			ds.setUnid(li.getLocale().getTipo());
			List<Decodifica> dec = cercaDecodifica(ds);
			li.getDecodifica().setDescrizione(dec.get(0).getDescrizione());
			li.setRel_Immobile_Locale(ril);
			d.getDichiarazioneImmobile().getLocaliDiImmobile().add(li);
		}
	}

	public void saveLocale(Locale l) {
		if (l.isInsert()) {
			inserisciLocale(l);
		} else if (l.isUpdate()) {
			updateLocale(l);
		} else if (l.isDelete()) {
			deleteLocale(l);
		}
	}

	public void saveLocaleDiImmobile(ImmobileDiDichiarazione idd) {
		if (idd.isInsert()) {
			Rel_Immobile_Locale ril = new Rel_Immobile_Locale();
			for (LocaleDiImmobile ldi : idd.getLocaliDiImmobile()) {

				ldi.setOperation(ldi.OP_INSERT);
				ldi.getLocale().setOperation(ldi.OP_INSERT);
				saveLocale(ldi.getLocale());
				ril.setIdlocale(ldi.getLocale().getUnid());
				ril.setIdimmobile(idd.getImmobile().getUnid());
				inserisciRel_Immobile_Locale(ril);

			}
		}

		else if (idd.isUpdate()) {
			for (LocaleDiImmobile ldi : idd.getLocaliDiImmobile()) {

				// ldi.getLocale().setOperation(idd.getLocaliDiImmobile().iterator().next().getOperation());

				if (ldi.isNew()) {
					Rel_Immobile_Locale ril = new Rel_Immobile_Locale();
					ril.setIdlocale(ldi.getLocale().getUnid());
					ril.setIdimmobile(idd.getImmobile().getUnid());
					inserisciRel_Immobile_Locale(ril);
					ldi.getLocale().setOperation(ldi.OP_INSERT);
				}
				saveLocale(ldi.getLocale());
			}
		}
	}

	/***** PRECEDENTEDICHIARAZIONE DI DICHIARAZIONE ****/
	public void loadPrecedenteDichiarazioneDiDichiarazione(DichiarazioneDiPersonaTari d, PersonaTariCompleta pc) {
		Rel_Dichiarazione_PrecDichiaraSearch rdps = new Rel_Dichiarazione_PrecDichiaraSearch();
		rdps.setIddichiarazione(d.getDichiarazione().getUnid());
		List<Rel_Dichiarazione_PrecDichiara> rel_Dichiarazione_PrecDichiara = cercaRel_Dichiarazione_PrecDichiara(rdps);

		for (Rel_Dichiarazione_PrecDichiara rdp : rel_Dichiarazione_PrecDichiara) {
			rdp.setOperation(pc.OP_NOP);
			PrecedenteDichiarazioneSearch pds = new PrecedenteDichiarazioneSearch();
			pds.setUnid(rdp.getIdPrecedenteDichiarazione());
			List<PrecedenteDichiarazione> precedenteDichiarazione = cercaPrecedenteDichiarazione(pds);
			//
			DichiarazioneDiPersonaTari dpt = new DichiarazioneDiPersonaTari();
			d.setOperation(pc.OP_NOP);
			d.setPrecedenteDichiarazione(precedenteDichiarazione.get(0));
			d.setRel_Dichiarazione_PrecDichiara(rdp);
		}
	}

	public void savePrecedenteDichiarazione(PrecedenteDichiarazione pd) {
		if (pd.isInsert()) {
			inserisciPrecedenteDichiarazione(pd);
		} else if (pd.isUpdate()) {
			updatePrecedenteDichiarazione(pd);
		} else if (pd.isDelete()) {
			deletePrecedenteDichiarazione(pd);
		}
	}

	public void savePrecedenteDichiarazioneDiDichiarazione(DichiarazioneDiPersonaTari mp) {
		if (mp.isInsert()) {
			Rel_Dichiarazione_PrecDichiara rdpd = new Rel_Dichiarazione_PrecDichiara();
			PrecedenteDichiarazione dpt = mp.getPrecedenteDichiarazione();
			if (dpt != null) {
				dpt.setOperation(dpt.OP_INSERT);
				savePrecedenteDichiarazione(dpt);
				rdpd.setIddichiarazione(mp.getDichiarazione().getUnid());
				rdpd.setIdPrecedenteDichiarazione(dpt.getUnid());
				inserisciRel_Dichiarazione_PrecDichiara(rdpd);
			}
		} else if (mp.isUpdate()) {

		}
	}

	/***** CREAZIONE DI PERSONATARICOMPLETA ****/
	public PersonaTariCompleta createPersonaTariCompleta(PersonaTari p) throws Exception {

		PersonaTariCompleta pc = new PersonaTariCompleta();
		pc.setPersonaTari(p);

		Dichiarazione dichiarazione1 = new Dichiarazione();
		dichiarazione1.setData(new Date());
		dichiarazione1.setAgricoltore(1);
		PrecedenteDichiarazione precedenteDichiarazione1 = new PrecedenteDichiarazione();
		precedenteDichiarazione1.setDataDa(new Date());

		Immobile immobile1 = new Immobile();
		immobile1.setDatada(new Date());
		immobile1.setCivico("1");

		Locale l = new Locale();

		LocaleDiImmobile lim = new LocaleDiImmobile();
		lim.setLocale(l);

		ImmobileDiDichiarazione di1 = new ImmobileDiDichiarazione();
		di1.setImmobile(immobile1);
		di1.getLocaliDiImmobile().add(lim);

		Immobile immobile2 = new Immobile();
		immobile2.setDatada(new Date());
		immobile2.setCivico("2");

		ImmobileDiDichiarazione di2 = new ImmobileDiDichiarazione();
		di2.setImmobile(immobile2);

		DichiarazioneDiPersonaTari dich1 = new DichiarazioneDiPersonaTari();
		dich1.setDichiarazione(dichiarazione1);
		dich1.setPrecedenteDichiarazione(precedenteDichiarazione1);
		dich1.setDichiarazioneImmobile(di2);

		Dichiarazione dichiarazione2 = new Dichiarazione();
		dichiarazione2.setData(new Date());
		dichiarazione2.setAgricoltore(0);

		DichiarazioneDiPersonaTari dich2 = new DichiarazioneDiPersonaTari();
		dich2.setDichiarazione(dichiarazione2);
		dich2.setPrecedenteDichiarazione(null);
		// dich2.getDichiarazioniImmobili().add(e); illegale !

		pc.getDichiarazioniDiPersona().add(dich1);
		pc.getDichiarazioniDiPersona().add(dich2);
		System.out.println(JACK.toJSON(pc));
		return pc;
	}

	public DichiarazioneDiPersonaTari createNuovaDichiarazioneDiPersonaTari() {

		Dichiarazione dichiarazione1 = new Dichiarazione();
		dichiarazione1.setAgricoltore(0);// TODO Rivedere se non porta problemi
		PrecedenteDichiarazione precedenteDichiarazione1 = new PrecedenteDichiarazione();
		precedenteDichiarazione1.setInterno("");

		Immobile immobile1 = new Immobile();
		immobile1.setCivico("");
		immobile1.setInterno("");
		immobile1.setNomeprecedentedetentore("");

		Locale l = new Locale();

		LocaleDiImmobile lim = new LocaleDiImmobile();
		lim.setLocale(l);

		ImmobileDiDichiarazione di1 = new ImmobileDiDichiarazione();
		di1.setImmobile(immobile1);
		di1.getLocaliDiImmobile().add(lim);

		Immobile immobile2 = new Immobile();
		immobile2.setCivico("");

		ImmobileDiDichiarazione di2 = new ImmobileDiDichiarazione();
		di2.setImmobile(immobile2);

		DichiarazioneDiPersonaTari dich1 = new DichiarazioneDiPersonaTari();
		dich1.setDichiarazione(dichiarazione1);
		dich1.setPrecedenteDichiarazione(precedenteDichiarazione1);
		dich1.setDichiarazioneImmobile(di2);

		return dich1;
	}

	public LocaleDiImmobile createLocaleDiImmobile() {

		Locale l = new Locale();

		LocaleDiImmobile lim = new LocaleDiImmobile();
		lim.setLocale(l);
		lim.setOperation(lim.OP_NOP);

		return lim;
	}

	/***** OPERAZIONI PER INTERFACCIA *****/
	public List<Decodifica> caricaDecodificaPosizioneDiDichiarazione() {
		DecodificaSearch ds = new DecodificaSearch();
		ds.setTipo("TIPOPOSIZIONEDICHIARAZIONE");
		return cercaDecodifica(ds);
	}

	public List<String> caricaDecodificaPosizioneDiDichiarazioneSBAGLIATO() {
		DecodificaSearch ds = new DecodificaSearch();
		ds.setTipo("TIPOPOSIZIONEDICHIARAZIONE");
		List<Decodifica> decodifiche = cercaDecodifica(ds);
		List<String> posizioni = new ArrayList<String>();
		for (Decodifica d : decodifiche) {
			posizioni.add(d.getDescrizione());
		}
		return posizioni;
	}

	public List<Decodifica> caricaDecodificaDiTipoLocale() {
		DecodificaSearch ds = new DecodificaSearch();
		ds.setTipo("TIPOLOCALE");
		return cercaDecodifica(ds);
	}

	public List<Decodifica> caricaDecodificaDiQualitaDi() {
		DecodificaSearch ds = new DecodificaSearch();
		ds.setTipo("QUALITADI");
		return cercaDecodifica(ds);
	}

	public void salvaLocaleDiImmobile(LocaleDiImmobile locale, DichiarazioneDiPersonaTari dpt) {

		if (locale.isInsert() && locale.isNew()) {

			dpt.getDichiarazioneImmobile().getLocaliDiImmobile().add(locale);
			locale.setOperation(locale.OP_INSERT);

		}
		visualizzaTipoSuGridOnSalva(locale);
		/*
		 * else if(locale.isUpdate() && !locale.isNew()){
		 * locale.setOperation(locale.OP_DELETE);
		 * locale.setOperation(locale.OP_UPDATE); }
		 * 
		 * else if(locale.isUpdate() && locale.isNew()){
		 * locale.setOperation(locale.OP_DELETE);
		 * locale.setOperation(locale.OP_INSERT); }
		 * 
		 * if(locale.isNew()){
		 * 
		 * }
		 */
	}

	public void visualizzaTipoSuGridOnSalva(LocaleDiImmobile locale) {
		DecodificaSearch ds = new DecodificaSearch();
		ds.setUnid(locale.getLocale().getTipo());
		List<Decodifica> decodifica = cercaDecodifica(ds);
		locale.getDecodifica().setDescrizione(decodifica.get(0).getDescrizione());
	}

	public Allegato saveAllegato() {
		return null;

	}

}
