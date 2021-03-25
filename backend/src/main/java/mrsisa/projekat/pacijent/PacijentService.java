package mrsisa.projekat.pacijent;

import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.stereotype.Service;

import mrsisa.projekat.adresa.Adresa;

@Service
public class PacijentService {

	public Pacijent dobaviPacijenta() {
		Pacijent p=new Pacijent("pera", "peric", "pera", "peric");
		Adresa a=new Adresa("Kazahstan", "ptt", "ulica", 60, 50, 50);
		p.setAdresa(a);
		return p;
	}
	

}
