package mrsisa.projekat.pacijent;

import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.stereotype.Service;
import java.util.List;
import mrsisa.projekat.adresa.Adresa;

@Service
public class PacijentService {
	public Pacijent dobaviPacijenta(){
		return null;
	}
	public List<Pacijent> dobaviPacijente(){
		Pacijent p1 = new Pacijent();
		p1.setFirstName("John");
		p1.setLastName("Titor");
		Pacijent p2 = new Pacijent();
		p2.setFirstName("Marko");
		p2.setLastName("Polo");
		return List.of( p1, p2);
	}
}
