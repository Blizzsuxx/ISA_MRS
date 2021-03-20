package model;
/***********************************************************************
 * Module:  Narucbenica.java
 * Author:  rajta
 * Purpose: Defines the Class Narucbenica
 ***********************************************************************/

import java.util.*;

/** @pdOid 20933e3b-7024-4dc6-ac30-983e38e7cbbd */
public class Narucbenica {
   /** @pdRoleInfo migr=no name=Lek assc=association16 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Lek> lekovi;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Lek> getLekovi() {
      if (lekovi == null)
         lekovi = new java.util.HashSet<Lek>();
      return lekovi;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorLekovi() {
      if (lekovi == null)
         lekovi = new java.util.HashSet<Lek>();
      return lekovi.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newLekovi */
   public void setLekovi(java.util.Collection<Lek> newLekovi) {
      removeAllLekovi();
      for (java.util.Iterator iter = newLekovi.iterator(); iter.hasNext();)
         addLekovi((Lek)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newLek */
   public void addLekovi(Lek newLek) {
      if (newLek == null)
         return;
      if (this.lekovi == null)
         this.lekovi = new java.util.HashSet<Lek>();
      if (!this.lekovi.contains(newLek))
         this.lekovi.add(newLek);
   }
   
   /** @pdGenerated default remove
     * @param oldLek */
   public void removeLekovi(Lek oldLek) {
      if (oldLek == null)
         return;
      if (this.lekovi != null)
         if (this.lekovi.contains(oldLek))
            this.lekovi.remove(oldLek);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllLekovi() {
      if (lekovi != null)
         lekovi.clear();
   }

}