package model;
/***********************************************************************
 * Module:  Apoteka.java
 * Author:  rajta
 * Purpose: Defines the Class Apoteka
 ***********************************************************************/

import java.util.*;

/** @pdOid c1c4cf54-7961-47bf-9c21-88fc0752c0ac */
public class Apoteka implements Ocenljiv {
   /** @pdRoleInfo migr=no name=Radnik assc=association1 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Radnik> radnici;
   /** @pdRoleInfo migr=no name=AdministratorApoteke assc=association4 mult=1..1 type=Aggregation */
   public AdministratorApoteke administratorApoteke;
   /** @pdRoleInfo migr=no name=Cenovnik assc=association5 mult=0..* type=Composition */
   public Cenovnik[] cenovnici;
   /** @pdRoleInfo migr=no name=Ocena assc=association20 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Ocena> ocena;
   /** @pdRoleInfo migr=no name=Akcija assc=akcije coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Akcija> akcija;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Radnik> getRadnici() {
      if (radnici == null)
         radnici = new java.util.HashSet<Radnik>();
      return radnici;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRadnici() {
      if (radnici == null)
         radnici = new java.util.HashSet<Radnik>();
      return radnici.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRadnici */
   public void setRadnici(java.util.Collection<Radnik> newRadnici) {
      removeAllRadnici();
      for (java.util.Iterator iter = newRadnici.iterator(); iter.hasNext();)
         addRadnici((Radnik)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRadnik */
   public void addRadnici(Radnik newRadnik) {
      if (newRadnik == null)
         return;
      if (this.radnici == null)
         this.radnici = new java.util.HashSet<Radnik>();
      if (!this.radnici.contains(newRadnik))
         this.radnici.add(newRadnik);
   }
   
   /** @pdGenerated default remove
     * @param oldRadnik */
   public void removeRadnici(Radnik oldRadnik) {
      if (oldRadnik == null)
         return;
      if (this.radnici != null)
         if (this.radnici.contains(oldRadnik))
            this.radnici.remove(oldRadnik);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRadnici() {
      if (radnici != null)
         radnici.clear();
   }
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
   /** @pdGenerated default getter */
   public java.util.Collection<Akcija> getAkcija() {
      if (akcija == null)
         akcija = new java.util.HashSet<Akcija>();
      return akcija;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAkcija() {
      if (akcija == null)
         akcija = new java.util.HashSet<Akcija>();
      return akcija.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAkcija */
   public void setAkcija(java.util.Collection<Akcija> newAkcija) {
      removeAllAkcija();
      for (java.util.Iterator iter = newAkcija.iterator(); iter.hasNext();)
         addAkcija((Akcija)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAkcija */
   public void addAkcija(Akcija newAkcija) {
      if (newAkcija == null)
         return;
      if (this.akcija == null)
         this.akcija = new java.util.HashSet<Akcija>();
      if (!this.akcija.contains(newAkcija))
         this.akcija.add(newAkcija);
   }
   
   /** @pdGenerated default remove
     * @param oldAkcija */
   public void removeAkcija(Akcija oldAkcija) {
      if (oldAkcija == null)
         return;
      if (this.akcija != null)
         if (this.akcija.contains(oldAkcija))
            this.akcija.remove(oldAkcija);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAkcija() {
      if (akcija != null)
         akcija.clear();
   }

}