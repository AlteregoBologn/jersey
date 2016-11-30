package tari.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tari.model.Dichiarazione;
import tari.model.DichiarazioneSearch;
import tari.model.Immobile;
import tari.model.ImmobileSearch;
import tari.model.Locale;
import tari.model.LocaleSearch;
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
import tari.model.relationModel.Rel_PersonaTari_Dichiarazione;
import tari.model.relationModel.Rel_PersonaTari_DichiarazioneSearch;
import tari.modelExt.DichiarazioneDiPersonaTari;
import tari.modelExt.ImmobileDiDichiarazione;
import tari.modelExt.LocaleDiImmobile;
import tari.modelExt.PersonaTariCompleta;
import util.JACK;

public class TariManagerExt extends TariManager {

	public List<PersonaTariCompleta> loadPersoneTariCompleta(PersonaTariSearch ps) {
		List<PersonaTariCompleta> ret = new ArrayList<PersonaTariCompleta>();

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
				pc.getDichiarazioniDiPersona().add(d);

				Rel_Dichiarazione_ImmobileSearch rdis = new Rel_Dichiarazione_ImmobileSearch();
				rdis.setIddichiarazione(d.getDichiarazione().getUnid());
				List<Rel_Dichiarazione_Immobile> rel_Dichiarazione_Immobile = cercarel_Dichiarazione_Immobile(rdis);

				for (Rel_Dichiarazione_Immobile rdi : rel_Dichiarazione_Immobile) {
					rdi.setOperation(pc.OP_UPDATE);
					ImmobileSearch is = new ImmobileSearch();
					is.setUnid(rdi.getIdimmobile());
					List<Immobile> immobile = cercaImmobile(is);
					//
					ImmobileDiDichiarazione di = new ImmobileDiDichiarazione();
					di.setOperation(pc.OP_UPDATE);
					di.setImmobile(immobile.get(0));
					di.setRel_Dichiarazione_Immobile(rdi);
					d.getDichiarazioniImmobili().add(di);
					pc.getDichiarazioniDiPersona().add(d);
					
					Rel_Immobile_LocaleSearch rils = new Rel_Immobile_LocaleSearch();
					rils.setIdimmobile(di.getImmobile().getUnid());
					List<Rel_Immobile_Locale> rel_Immobile_Locale = cercaRel_Immobile_Locale(rils);
					
					for(Rel_Immobile_Locale ril : rel_Immobile_Locale) {
						ril.setOperation(pc.OP_UPDATE);
						LocaleSearch ls = new LocaleSearch();
						ls.setUnid(ril.getIdlocale());
						List<Locale> locali = cercaLocale(ls);
						//
						LocaleDiImmobile li = new LocaleDiImmobile();
						li.setOperation(pc.OP_UPDATE);
						li.setLocale(locali.get(0));
						li.setRel_Immobile_Locale(ril);
						d.getDichiarazioniImmobili().get(0).getLocaliDiImmobile().add(li);
						pc.getDichiarazioniDiPersona().add(d);
					}
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
					//pc.getDichiarazioniDiPersona().get(0).setPrecedenteDichiarazione(precedenteDichiarazione.get(0));
					//pc.getDichiarazioniDiPersona().get(0).setRel_Dichiarazione_PrecDichiara(rdp);
					pc.getDichiarazioniDiPersona().add(dpt);
					System.out.println(pc.toString());
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
			for (DichiarazioneDiPersonaTari d : p.getDichiarazioniDiPersona()) {
				saveDichiarazioneDiPersonaTari(d);
			}
		} else if (p.isDelete()) {
			savePersonaTari(p.getPersonaTari());
			for (DichiarazioneDiPersonaTari d : p.getDichiarazioniDiPersona()) {
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


	public PersonaTariCompleta createPersonaTariCompleta(PersonaTari p) throws Exception{
			
		
		PersonaTariCompleta pc=new PersonaTariCompleta();
		pc.setPersonaTari(p);
		
		Dichiarazione dichiarazione1=new Dichiarazione();
		/* TODO Inserire il setData corretto
		 * dichiarazione1.setData(new Date());
		 */
		dichiarazione1.setAgricoltore("S");
		PrecedenteDichiarazione precedenteDichiarazione1=new PrecedenteDichiarazione();
		precedenteDichiarazione1.setDataDa(new Date());
		
		Immobile immobile1=new Immobile();
		immobile1.setDatada(new Date());
		immobile1.setCivico("1");
		
		Locale l=new Locale();
		
		LocaleDiImmobile lim=new LocaleDiImmobile();
		lim.setLocale(l);	

		ImmobileDiDichiarazione di1=new ImmobileDiDichiarazione();
		di1.setImmobile(immobile1);
		di1.getLocaliDiImmobile().add(lim);
		
		Immobile immobile2=new Immobile();
		immobile2.setDatada(new Date());
		immobile2.setCivico("2");
		
		ImmobileDiDichiarazione di2=new ImmobileDiDichiarazione();
		di2.setImmobile(immobile2);
		
		DichiarazioneDiPersonaTari dich1=new DichiarazioneDiPersonaTari();
		dich1.setDichiarazione(dichiarazione1);
		dich1.setPrecedenteDichiarazione(precedenteDichiarazione1);
		dich1.getDichiarazioniImmobili().add(di2);
		dich1.getDichiarazioniImmobili().add(di1);
		
		Dichiarazione dichiarazione2=new Dichiarazione();
		/* TODO Inserire il setData corretto
		 * dichiarazione2.setData(new Date());
		 */
		dichiarazione2.setAgricoltore("N");		
		DichiarazioneDiPersonaTari dich2=new DichiarazioneDiPersonaTari();		
		dich2.setDichiarazione(dichiarazione2);
		dich2.setPrecedenteDichiarazione(null);
		//dich2.getDichiarazioniImmobili().add(e); illegale !
		
		pc.getDichiarazioniDiPersona().add(dich1);
		pc.getDichiarazioniDiPersona().add(dich2);
		System.out.println(JACK.toJSON(pc));
		return pc;
	}
	
}
