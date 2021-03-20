package model;
/***********************************************************************
 * Module:  Pacijent.java
 * Author:  rajta
 * Purpose: Defines the Class Pacijent
 ***********************************************************************/

import java.util.*;

/** @pdOid a3edc026-25bb-4551-8da4-2007119bdf84 */
public class Pacijent extends Korisnik {
   /** @pdOid 3e22637d-2efd-4398-8cac-572aab4fdb49 */
   private int poeni;
   /** @pdOid 97b2336a-e11a-4403-9f86-3e5302a625c3 */
   private String email;
   
   /** @pdRoleInfo migr=no name=Poseta assc=association8 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Poseta> posete;
   /** @pdRoleInfo migr=no name=Lek assc=association12 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Lek> alerije;
   /** @pdRoleInfo migr=no name=Zalba assc=association17 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Zalba> Zalbe;
   /** @pdRoleInfo migr=no name=Radnik assc=association18 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Radnik> istorijaPoseta;
   /** @pdRoleInfo migr=no name=Lek assc=izdatiLekovi coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Lek> izdatLek;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Poseta> getPosete() {
      if (posete == null)
         posete = new java.util.HashSet<Poseta>();
      return posete;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPosete() {
      if (posete == null)
         posete = new java.util.HashSet<Poseta>();
      return posete.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPosete */
   public void setPosete(java.util.Collection<Poseta> newPosete) {
      removeAllPosete();
      for (java.util.Iterator iter = newPosete.iterator(); iter.hasNext();)
         addPosete((Poseta)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPoseta */
   public void addPosete(Poseta newPoseta) {
      if (newPoseta == null)
         return;
      if (this.posete == null)
         this.posete = new java.util.HashSet<Poseta>();
      if (!this.posete.contains(newPoseta))
      {
         this.posete.add(newPoseta);
         newPoseta.setPacijent(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldPoseta */
   public void removePosete(Poseta oldPoseta) {
      if (oldPoseta == null)
         return;
      if (this.posete != null)
         if (this.posete.contains(oldPoseta))
         {
            this.posete.remove(oldPoseta);
            oldPoseta.setPacijent((Pacijent)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPosete() {
      if (posete != null)
      {
         Poseta oldPoseta;
         for (java.util.Iterator iter = getIteratorPosete(); iter.hasNext();)
         {
            oldPoseta = (Poseta)iter.next();
            iter.remove();
            oldPoseta.setPacijent((Pacijent)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Lek> getAlerije() {
      if (alerije == null)
         alerije = new java.util.HashSet<Lek>();
      return alerije;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAlerije() {
      if (alerije == null)
         alerije = new java.util.HashSet<Lek>();
      return alerije.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAlerije */
   public void setAlerije(java.util.Collection<Lek> newAlerije) {
      removeAllAlerije();
      for (java.util.Iterator iter = newAlerije.iterator(); iter.hasNext();)
         addAlerije((Lek)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newLek */
   public void addAlerije(Lek newLek) {
      if (newLek == null)
         return;
      if (this.alerije == null)
         this.alerije = new java.util.HashSet<Lek>();
      if (!this.alerije.contains(newLek))
         this.alerije.add(newLek);
   }
   
   /** @pdGenerated default remove
     * @param oldLek */
   public void removeAlerije(Lek oldLek) {
      if (oldLek == null)
         return;
      if (this.alerije != null)
         if (this.alerije.contains(oldLek))
            this.alerije.remove(oldLek);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAlerije() {
      if (alerije != null)
         alerije.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Zalba> getZalbe() {
      if (Zalbe == null)
         Zalbe = new java.util.HashSet<Zalba>();
      return Zalbe;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorZalbe() {
      if (Zalbe == null)
         Zalbe = new java.util.HashSet<Zalba>();
      return Zalbe.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newZalbe */
   public void setZalbe(java.util.Collection<Zalba> newZalbe) {
      removeAllZalbe();
      for (java.util.Iterator iter = newZalbe.iterator(); iter.hasNext();)
         addZalbe((Zalba)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newZalba */
   public void addZalbe(Zalba newZalba) {
      if (newZalba == null)
         return;
      if (this.Zalbe == null)
         this.Zalbe = new java.util.HashSet<Zalba>();
      if (!this.Zalbe.contains(newZalba))
         this.Zalbe.add(newZalba);
   }
   
   /** @pdGenerated default remove
     * @param oldZalba */
   public void removeZalbe(Zalba oldZalba) {
      if (oldZalba == null)
         return;
      if (this.Zalbe != null)
         if (this.Zalbe.contains(oldZalba))
            this.Zalbe.remove(oldZalba);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllZalbe() {
      if (Zalbe != null)
         Zalbe.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Radnik> getIstorijaPoseta() {
      if (istorijaPoseta == null)
         istorijaPoseta = new java.util.HashSet<Radnik>();
      return istorijaPoseta;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorIstorijaPoseta() {
      if (istorijaPoseta == null)
         istorijaPoseta = new java.util.HashSet<Radnik>();
      return istorijaPoseta.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newIstorijaPoseta */
   public void setIstorijaPoseta(java.util.Collection<Radnik> newIstorijaPoseta) {
      removeAllIstorijaPoseta();
      for (java.util.Iterator iter = newIstorijaPoseta.iterator(); iter.hasNext();)
         addIstorijaPoseta((Radnik)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRadnik */
   public void addIstorijaPoseta(Radnik newRadnik) {
      if (newRadnik == null)
         return;
      if (this.istorijaPoseta == null)
         this.istorijaPoseta = new java.util.HashSet<Radnik>();
      if (!this.istorijaPoseta.contains(newRadnik))
      {
         this.istorijaPoseta.add(newRadnik);
         newRadnik.setPregledaniPacijenti(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRadnik */
   public void removeIstorijaPoseta(Radnik oldRadnik) {
      if (oldRadnik == null)
         return;
      if (this.istorijaPoseta != null)
         if (this.istorijaPoseta.contains(oldRadnik))
         {
            this.istorijaPoseta.remove(oldRadnik);
            oldRadnik.setPregledaniPacijenti((Pacijent)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllIstorijaPoseta() {
      if (istorijaPoseta != null)
      {
         Radnik oldRadnik;
         for (java.util.Iterator iter = getIteratorIstorijaPoseta(); iter.hasNext();)
         {
            oldRadnik = (Radnik)iter.next();
            iter.remove();
            oldRadnik.setPregledaniPacijenti((Pacijent)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Lek> getIzdatLek() {
      if (izdatLek == null)
         izdatLek = new java.util.HashSet<Lek>();
      return izdatLek;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorIzdatLek() {
      if (izdatLek == null)
         izdatLek = new java.util.HashSet<Lek>();
      return izdatLek.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newIzdatLek */
   public void setIzdatLek(java.util.Collection<Lek> newIzdatLek) {
      removeAllIzdatLek();
      for (java.util.Iterator iter = newIzdatLek.iterator(); iter.hasNext();)
         addIzdatLek((Lek)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newLek */
   public void addIzdatLek(Lek newLek) {
      if (newLek == null)
         return;
      if (this.izdatLek == null)
         this.izdatLek = new java.util.HashSet<Lek>();
      if (!this.izdatLek.contains(newLek))
         this.izdatLek.add(newLek);
   }
   
   /** @pdGenerated default remove
     * @param oldLek */
   public void removeIzdatLek(Lek oldLek) {
      if (oldLek == null)
         return;
      if (this.izdatLek != null)
         if (this.izdatLek.contains(oldLek))
            this.izdatLek.remove(oldLek);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllIzdatLek() {
      if (izdatLek != null)
         izdatLek.clear();
   }

}