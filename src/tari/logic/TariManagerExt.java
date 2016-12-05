package tari.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.E;
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
			
			pc.setOperation(pc.OP_NOP);
			pc.setPersonaTari(p);
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
				saveDichiarazioneDiPersonaTari(d, p);
				
			}
		} else if (p.isDelete()) {
			savePersonaTari(p.getPersonaTari());
			for (DichiarazioneDiPersonaTari d : p.getDichiarazioniDiPersona()) {
				saveDichiarazioneDiPersonaTari(d, p);
			}
		}
		p.setOperation(p.OP_NOP);
	}

	/***** DICHIARAZIONE DI PERSONATARI ****/	
	
	public void loadDichiarazioniDiPersonaTari (PersonaTari p, PersonaTariCompleta pc) {
		
		Rel_PersonaTari_DichiarazioneSearch rpds = new Rel_PersonaTari_DichiarazioneSearch();
		rpds.setIdpersona(p.getUnid());
		List<Rel_PersonaTari_Dichiarazione> rel_Persona_Dichiarazione = cercaRel_Persona_Dichiarazione(rpds);
		
		// TODO gestire meglio l'errore
		if (rel_Persona_Dichiarazione.isEmpty()) {
			new RuntimeException("nessuna dichiarazione corrispondente tra i due");
		} 
		else {

		for (Rel_PersonaTari_Dichiarazione rpd : rel_Persona_Dichiarazione) {
			rpd.setOperation(pc.OP_NOP);
			DichiarazioneSearch ds = new DichiarazioneSearch();
			ds.setUnid(rpd.getIddichiarazione());
			List<Dichiarazione> dichiarazioni = cercaDichiarazione(ds);

			DichiarazioneDiPersonaTari d = new DichiarazioneDiPersonaTari();
			d.setOperation(pc.OP_NOP);
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
			saveDichiarazione(mp.getDichiarazione());
			rpd.setIddichiarazione(mp.getDichiarazione().getUnid());
			rpd.setIdpersona(pc.getPersonaTari().getUnid());			
			inserisciRel_Persona_Dichiarazione(rpd);
			pc.setOperation(pc.OP_INSERT);
			saveImmobileDiDichiarazione(mp, pc);
			savePrecedenteDichiarazioneDiDichiarazione(mp, pc);
		}
		
		if(mp.isUpdate()){
			mp.getDichiarazione().setOperation(mp.OP_UPDATE);
			saveDichiarazione(mp.getDichiarazione());
			
			pc.setOperation(pc.OP_UPDATE);			
			saveImmobileDiDichiarazione(mp, pc);
			savePrecedenteDichiarazioneDiDichiarazione(mp, pc);
		}
	}
	
	/***** IMMOBILE DI DICHIARAZIONE ****/	
	public void loadImmobileDiDichiarazione (DichiarazioneDiPersonaTari d, PersonaTariCompleta pc){
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
	
	public void saveImmobile(Immobile i){
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
		
		else if (pc.isUpdate()){
			ImmobileDiDichiarazione idd = mp.getDichiarazioneImmobile();
			idd.setOperation(idd.OP_UPDATE);
			idd.getImmobile().setOperation(idd.OP_UPDATE);
			saveImmobile(idd.getImmobile());
			saveLocaleDiImmobile(idd);	
		}
		} 

	/***** LOCALE DI IMMOBILE ****/	
	public void loadLocaleDiImmobile (DichiarazioneDiPersonaTari d, PersonaTariCompleta pc, ImmobileDiDichiarazione di){
		
		Rel_Immobile_LocaleSearch rils = new Rel_Immobile_LocaleSearch();
		rils.setIdimmobile(di.getImmobile().getUnid());
		List<Rel_Immobile_Locale> rel_Immobile_Locale = cercaRel_Immobile_Locale(rils);
		
		for(Rel_Immobile_Locale ril : rel_Immobile_Locale) {
			ril.setOperation(pc.OP_NOP);
			LocaleSearch ls = new LocaleSearch();
			ls.setUnid(ril.getIdlocale());
			List<Locale> locali = cercaLocale(ls);
			//TODO gestire meglio l'errore
			if (locali.isEmpty()) {
					locali.get(0).setParticella("Nessun locale corrispondente, è illegale se c'è l'immobile");
			}
			LocaleDiImmobile li = new LocaleDiImmobile();
			li.setOperation(pc.OP_NOP);
			li.setLocale(locali.get(0));
			li.setRel_Immobile_Locale(ril);
			d.getDichiarazioneImmobile().getLocaliDiImmobile().add(li);
		}
	}
	
	public void saveLocale(Locale l){
		if ( l.isInsert()) {
			inserisciLocale(l);
		} else if (l.isUpdate()) {
			updateLocale(l);
		} else if (l.isDelete()) {
			deleteLocale(l);
		}
		l.setOperation(l.OP_NOP);
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

		else if(idd.isUpdate()){
			for (LocaleDiImmobile ldi : idd.getLocaliDiImmobile()) {
				ldi.setOperation(ldi.OP_UPDATE);
				ldi.getLocale().setOperation(ldi.OP_UPDATE);
				saveLocale(ldi.getLocale());
			}
		}
	}
	/***** PRECEDENTEDICHIARAZIONE DI DICHIARAZIONE****/	
	public void loadPrecedenteDichiarazioneDiDichiarazione(DichiarazioneDiPersonaTari d, PersonaTariCompleta pc){
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
	
	public void savePrecedenteDichiarazioneDiDichiarazione(DichiarazioneDiPersonaTari mp, PersonaTariCompleta pc){
		if (pc.isInsert() || pc.isUpdate()) {
			Rel_Dichiarazione_PrecDichiara rdpd = new Rel_Dichiarazione_PrecDichiara();
			for (DichiarazioneDiPersonaTari dpt : pc.getDichiarazioniDiPersona()) {
				if(dpt.getPrecedenteDichiarazione() != null) {
					savePrecedenteDichiarazione(dpt.getPrecedenteDichiarazione());
					rdpd.setIddichiarazione(dpt.getDichiarazione().getUnid());
					rdpd.setIdPrecedenteDichiarazione(dpt.getPrecedenteDichiarazione().getUnid());				
					inserisciRel_Dichiarazione_PrecDichiara(rdpd);
				}
				else new RuntimeException("Nessuna precedente dichiarazione inserita, auguri per la nuova casa");
			}			
		}
	}
	/***** CREAZIONE DI PERSONATARICOMPLETA ****/	
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
		dich1.setDichiarazioneImmobile(di2);
		
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

	public DichiarazioneDiPersonaTari createNuovaDichiarazioneDiPersonaTari() {
		Dichiarazione dichiarazione1=new Dichiarazione();
		dichiarazione1.setData(new Date());
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
		dich1.setDichiarazioneImmobile(di2);
		
		return dich1;
	}

	public LocaleDiImmobile createLocaleDiImmobile() {
		
		Locale l = new Locale();
		
		LocaleDiImmobile lim=new LocaleDiImmobile();
		lim.setLocale(l);
		lim.setOperation(lim.OP_NOP);

		return lim;
	}
	
}
