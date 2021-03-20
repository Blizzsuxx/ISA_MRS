/***********************************************************************
 * Module:  Poseta.java
 * Author:  rajta
 * Purpose: Defines the Class Poseta
 ***********************************************************************/

import java.util.*;

/** @pdOid a2e1e821-c8a6-4ef7-8811-fcb40a04f9bf */
public class Poseta {
   /** @pdRoleInfo migr=no name=ERecept assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<ERecept> recept;
   /** @pdRoleInfo migr=no name=Radnik assc=association23 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Radnik> radnik;
   /** @pdRoleInfo migr=no name=Pacijent assc=association8 mult=0..1 side=A */
   public Pacijent pacijent;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<ERecept> getRecept() {
      if (recept == null)
         recept = new java.util.HashSet<ERecept>();
      return recept;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRecept() {
      if (recept == null)
         recept = new java.util.HashSet<ERecept>();
      return recept.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRecept */
   public void setRecept(java.util.Collection<ERecept> newRecept) {
      removeAllRecept();
      for (java.util.Iterator iter = newRecept.iterator(); iter.hasNext();)
         addRecept((ERecept)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newERecept */
   public void addRecept(ERecept newERecept) {
      if (newERecept == null)
         return;
      if (this.recept == null)
         this.recept = new java.util.HashSet<ERecept>();
      if (!this.recept.contains(newERecept))
      {
         this.recept.add(newERecept);
         newERecept.setPoseta(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldERecept */
   public void removeRecept(ERecept oldERecept) {
      if (oldERecept == null)
         return;
      if (this.recept != null)
         if (this.recept.contains(oldERecept))
         {
            this.recept.remove(oldERecept);
            oldERecept.setPoseta((Poseta)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRecept() {
      if (recept != null)
      {
         ERecept oldERecept;
         for (java.util.Iterator iter = getIteratorRecept(); iter.hasNext();)
         {
            oldERecept = (ERecept)iter.next();
            iter.remove();
            oldERecept.setPoseta((Poseta)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Radnik> getRadnik() {
      if (radnik == null)
         radnik = new java.util.HashSet<Radnik>();
      return radnik;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRadnik() {
      if (radnik == null)
         radnik = new java.util.HashSet<Radnik>();
      return radnik.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRadnik */
   public void setRadnik(java.util.Collection<Radnik> newRadnik) {
      removeAllRadnik();
      for (java.util.Iterator iter = newRadnik.iterator(); iter.hasNext();)
         addRadnik((Radnik)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRadnik */
   public void addRadnik(Radnik newRadnik) {
      if (newRadnik == null)
         return;
      if (this.radnik == null)
         this.radnik = new java.util.HashSet<Radnik>();
      if (!this.radnik.contains(newRadnik))
         this.radnik.add(newRadnik);
   }
   
   /** @pdGenerated default remove
     * @param oldRadnik */
   public void removeRadnik(Radnik oldRadnik) {
      if (oldRadnik == null)
         return;
      if (this.radnik != null)
         if (this.radnik.contains(oldRadnik))
            this.radnik.remove(oldRadnik);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRadnik() {
      if (radnik != null)
         radnik.clear();
   }
   /** @pdGenerated default parent getter */
   public Pacijent getPacijent() {
      return pacijent;
   }
   
   /** @pdGenerated default parent setter
     * @param newPacijent */
   public void setPacijent(Pacijent newPacijent) {
      if (this.pacijent == null || !this.pacijent.equals(newPacijent))
      {
         if (this.pacijent != null)
         {
            Pacijent oldPacijent = this.pacijent;
            this.pacijent = null;
            oldPacijent.removePosete(this);
         }
         if (newPacijent != null)
         {
            this.pacijent = newPacijent;
            this.pacijent.addPosete(this);
         }
      }
   }

}