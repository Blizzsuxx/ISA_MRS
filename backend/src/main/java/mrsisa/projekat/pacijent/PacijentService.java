package mrsisa.projekat.pacijent;

import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.stereotype.Service;

import mrsisa.projekat.adresa.Adresa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacijentService {
	public Pacijent dobaviPacijenta(){
		Pacijent p=new Pacijent("pera", "pera", "pera","pera", LocalDateTime.now());
		List<Lijek> lek=new ArrayList<>();
		Adresa a=new Adresa("mesto", "ptt", "ulica",  "45", 50,50);
		p.setAdresa(a);
		lek.add(new Lijek(
				1L,
				"Paracetamol",
				"Protiv bolova",
				"tableta",
				"ljiek",
				"Biofarm",
				"Lijek"
		));
		lek.add(new Lijek(
				2L,
				"Brufen",
				"Protiv bolova",
				"tableta",
				"lek",
				"Biofarm",
				"Lek"
		));
		lek.add(new Lijek(
				3L,
				"Buscopan",
				"Protiv bolova",
				"tableta",
				"lek",
				"Biofarm",
				"Lek"
		));
		p.setAlergije(lek);
		return p;
	}

	public boolean promeni(List<String> podaci) { //pazi, uvek salji sve, i to istim redom
		System.out.println(podaci);
		return true;
	}
}
