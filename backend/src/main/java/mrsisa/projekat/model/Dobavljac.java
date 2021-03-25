package mrsisa.projekat.model;
/***********************************************************************
 * Module:  Dobavljac.java
 * Author:  rajta
 * Purpose: Defines the Class Dobavljac
 ***********************************************************************/

import java.util.*;

/** @pdOid 76945596-48b4-44c7-9490-767784757e18 */
public class Dobavljac extends Korisnik {
   /** @pdRoleInfo migr=no name=Lek assc=association13 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Lek> lekoviNaStanju;
   /** @pdRoleInfo migr=no name=Ponuda assc=association24 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Ponuda> ponude;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Lek> getLekoviNaStanju() {
      if (lekoviNaStanju == null)
         lekoviNaStanju = new java.util.HashSet<Lek>();
      return lekoviNaStanju;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorLekoviNaStanju() {
      if (lekoviNaStanju == null)
         lekoviNaStanju = new java.util.HashSet<Lek>();
      return lekoviNaStanju.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newLekoviNaStanju */
   public void setLekoviNaStanju(java.util.Collection<Lek> newLekoviNaStanju) {
      removeAllLekoviNaStanju();
      for (java.util.Iterator iter = newLekoviNaStanju.iterator(); iter.hasNext();)
         addLekoviNaStanju((Lek)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newLek */
   public void addLekoviNaStanju(Lek newLek) {
      if (newLek == null)
         return;
      if (this.lekoviNaStanju == null)
         this.lekoviNaStanju = new java.util.HashSet<Lek>();
      if (!this.lekoviNaStanju.contains(newLek))
         this.lekoviNaStanju.add(newLek);
   }
   
   /** @pdGenerated default remove
     * @param oldLek */
   public void removeLekoviNaStanju(Lek oldLek) {
      if (oldLek == null)
         return;
      if (this.lekoviNaStanju != null)
         if (this.lekoviNaStanju.contains(oldLek))
            this.lekoviNaStanju.remove(oldLek);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllLekoviNaStanju() {
      if (lekoviNaStanju != null)
         lekoviNaStanju.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Ponuda> getPonude() {
      if (ponude == null)
         ponude = new java.util.HashSet<Ponuda>();
      return ponude;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPonude() {
      if (ponude == null)
         ponude = new java.util.HashSet<Ponuda>();
      return ponude.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPonude */
   public void setPonude(java.util.Collection<Ponuda> newPonude) {
      removeAllPonude();
      for (java.util.Iterator iter = newPonude.iterator(); iter.hasNext();)
         addPonude((Ponuda)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPonuda */
   public void addPonude(Ponuda newPonuda) {
      if (newPonuda == null)
         return;
      if (this.ponude == null)
         this.ponude = new java.util.HashSet<Ponuda>();
      if (!this.ponude.contains(newPonuda))
      {
         this.ponude.add(newPonuda);
         newPonuda.setDobavljac(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldPonuda */
   public void removePonude(Ponuda oldPonuda) {
      if (oldPonuda == null)
         return;
      if (this.ponude != null)
         if (this.ponude.contains(oldPonuda))
         {
            this.ponude.remove(oldPonuda);
            oldPonuda.setDobavljac((Dobavljac)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPonude() {
      if (ponude != null)
      {
         Ponuda oldPonuda;
         for (java.util.Iterator iter = getIteratorPonude(); iter.hasNext();)
         {
            oldPonuda = (Ponuda)iter.next();
            iter.remove();
            oldPonuda.setDobavljac((Dobavljac)null);
         }
      }
   }

}