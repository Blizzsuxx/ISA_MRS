package model;
/***********************************************************************
 * Module:  Radnik.java
 * Author:  rajta
 * Purpose: Defines the Class Radnik
 ***********************************************************************/

import java.util.*;

/** @pdOid 6c7c4bc6-5d4f-4f8b-9192-e5a299b19e3c */
public class Radnik extends Korisnik implements Ocenljiv {
   /** @pdRoleInfo migr=no name=RadniKalendar assc=association2 mult=1..1 type=Composition */
   public RadniKalendar kalendar;
   /** @pdRoleInfo migr=no name=GodisnjiOdmor assc=association3 mult=1..1 type=Composition */
   public GodisnjiOdmor godisnjiOdmor;
   /** @pdRoleInfo migr=no name=Ocena assc=association19 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Ocena> ocena;
   /** @pdRoleInfo migr=no name=Apoteka assc=association1 mult=1..* side=A */
   public Apoteka[] apoteka;
   /** @pdRoleInfo migr=no name=Pacijent assc=association18 mult=0..1 side=A */
   public Pacijent pregledaniPacijenti;
   
   
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
   public Pacijent getPregledaniPacijenti() {
      return pregledaniPacijenti;
   }
   
   /** @pdGenerated default parent setter
     * @param newPacijent */
   public void setPregledaniPacijenti(Pacijent newPacijent) {
      if (this.pregledaniPacijenti == null || !this.pregledaniPacijenti.equals(newPacijent))
      {
         if (this.pregledaniPacijenti != null)
         {
            Pacijent oldPacijent = this.pregledaniPacijenti;
            this.pregledaniPacijenti = null;
            oldPacijent.removeIstorijaPoseta(this);
         }
         if (newPacijent != null)
         {
            this.pregledaniPacijenti = newPacijent;
            this.pregledaniPacijenti.addIstorijaPoseta(this);
         }
      }
   }

}