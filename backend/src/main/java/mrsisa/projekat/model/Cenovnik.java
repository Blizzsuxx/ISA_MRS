package model;
/***********************************************************************
 * Module:  Cenovnik.java
 * Author:  User
 * Purpose: Defines the Class Cenovnik
 ***********************************************************************/

import java.util.*;

/** @pdOid 49950eb2-139c-4d79-ba84-d2a9b68131ed */
public class Cenovnik {
   /** @pdRoleInfo migr=no name=Lek assc=association6 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
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