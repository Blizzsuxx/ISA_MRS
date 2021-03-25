package mrsisa.projekat.model;
/***********************************************************************
 * Module:  ERecept.java
 * Author:  User
 * Purpose: Defines the Class ERecept
 ***********************************************************************/

import java.util.*;

/** @pdOid eedad80d-67f0-4ce0-ab88-4410f56c57ff */
public class ERecept {
   /** @pdOid e8d3bf6f-af77-4dad-adc9-cddcf611e31a */
   private int sifra;
   /** @pdOid f26febfc-6ebe-4e68-a3d7-bbb9bfa29c53 */
   private Date datum;
   
   /** @pdRoleInfo migr=no name=Lek assc=association10 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Lek> lekovi;
   /** @pdRoleInfo migr=no name=Poseta assc=association9 mult=0..1 side=A */
   public Poseta poseta;
   
   
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
   /** @pdGenerated default parent getter */
   public Poseta getPoseta() {
      return poseta;
   }


}