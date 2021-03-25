package mrsisa.projekat.model;
/***********************************************************************
 * Module:  Radnik.java
 * Author:  rajta
 * Purpose: Defines the Class Radnik
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid 6c7c4bc6-5d4f-4f8b-9192-e5a299b19e3c */
@Entity
public class Radnik extends Korisnik implements Ocenljiv {

   @OneToOne(mappedBy = "radnik", cascade = CascadeType.ALL)
   private RadniKalendar kalendar;

   @OneToOne(mappedBy = "radnik", cascade = CascadeType.ALL)
   private GodisnjiOdmor godisnjiOdmor;

   @OneToMany(mappedBy = "radnik", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private java.util.Collection<Ocena> ocena;

   @ManyToMany
	@JoinTable(name = "radi", joinColumns = @JoinColumn(name = "apoteka_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "radnik_id", referencedColumnName = "id"))
   private Apoteka[] apoteka;

   @OneToMany(mappedBy = "radnik", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private java.util.Collection<Pacijent> pregledaniPacijenti;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Ocena> getOcena() {
      if (ocena == null)
         ocena = new java.util.HashSet<Ocena>();
      return ocena;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOcena() {
      if (ocena == null)
         ocena = new java.util.HashSet<Ocena>();
      return ocena.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOcena */
   public void setOcena(java.util.Collection<Ocena> newOcena) {
      removeAllOcena();
      for (java.util.Iterator iter = newOcena.iterator(); iter.hasNext();)
         addOcena((Ocena)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOcena */
   public void addOcena(Ocena newOcena) {
      if (newOcena == null)
         return;
      if (this.ocena == null)
         this.ocena = new java.util.HashSet<Ocena>();
      if (!this.ocena.contains(newOcena))
         this.ocena.add(newOcena);
   }
   
   /** @pdGenerated default remove
     * @param oldOcena */
   public void removeOcena(Ocena oldOcena) {
      if (oldOcena == null)
         return;
      if (this.ocena != null)
         if (this.ocena.contains(oldOcena))
            this.ocena.remove(oldOcena);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOcena() {
      if (ocena != null)
         ocena.clear();
   }
   /** @pdGenerated default parent getter */
   public Collection<Pacijent> getPregledaniPacijenti() {
      return pregledaniPacijenti;
   }


   public void addPacijent(Pacijent pacijent){

      if (pacijent != null){
         return;
      }

      this.pregledaniPacijenti.add(pacijent);


   }
   
   /** @pdGenerated default parent setter
     * @param newPacijent */
   public void setPregledaniPacijenti(Collection<Pacijent> newPacijent) {
      this.pregledaniPacijenti.clear();
      for (java.util.Iterator iter = newPacijent.iterator(); iter.hasNext();)
         addPacijent((Pacijent)iter.next());
   }

}