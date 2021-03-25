package mrsisa.projekat.model;
/***********************************************************************
 * Module:  Lek.java
 * Author:  rajta
 * Purpose: Defines the Class Lek
 ***********************************************************************/

import java.util.*;

/** @pdOid 41606ee9-2d7f-4db1-a447-8ba100e6a742 */
public class Lek {
   /** @pdOid c8671a32-80d4-4d68-9a61-90dd4e81b1e1 */
   private int sifra;
   /** @pdOid 8dd6f71f-6ee9-41ea-afe7-878db16ac6b8 */
   private String naziv;
   /** @pdOid 395d1dbd-947b-4269-96fc-165a43172b62 */
   private String vrstaLeka;
   /** @pdOid 530ada5e-bf66-4dc0-9bb9-c4caa6ea55c1 */
   private String oblikLeka;
   /** @pdOid 0f2331bb-2eda-451a-80ae-fcbd5378cdec */
   private String sastav;
   /** @pdOid d04d6715-7bd6-4b02-aa6f-f4f941287e3c */
   private String proizvodjac;
   /** @pdOid 388776fc-4ee2-4883-9f3b-c402a20d86f6 */
   private RezimIzdavanja rezimIzdavanja;
   /** @pdOid ee9cd17a-180c-4359-859d-761ae709f9da */
   private String napomene;
   
   /** @pdRoleInfo migr=no name=Lek assc=association7 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Lek> zamenskiLekovi;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Lek> getZamenskiLekovi() {
      if (zamenskiLekovi == null)
         zamenskiLekovi = new java.util.HashSet<Lek>();
      return zamenskiLekovi;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorZamenskiLekovi() {
      if (zamenskiLekovi == null)
         zamenskiLekovi = new java.util.HashSet<Lek>();
      return zamenskiLekovi.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newZamenskiLekovi */
   public void setZamenskiLekovi(java.util.Collection<Lek> newZamenskiLekovi) {
      removeAllZamenskiLekovi();
      for (java.util.Iterator iter = newZamenskiLekovi.iterator(); iter.hasNext();)
         addZamenskiLekovi((Lek)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newLek */
   public void addZamenskiLekovi(Lek newLek) {
      if (newLek == null)
         return;
      if (this.zamenskiLekovi == null)
         this.zamenskiLekovi = new java.util.HashSet<Lek>();
      if (!this.zamenskiLekovi.contains(newLek))
         this.zamenskiLekovi.add(newLek);
   }
   
   /** @pdGenerated default remove
     * @param oldLek */
   public void removeZamenskiLekovi(Lek oldLek) {
      if (oldLek == null)
         return;
      if (this.zamenskiLekovi != null)
         if (this.zamenskiLekovi.contains(oldLek))
            this.zamenskiLekovi.remove(oldLek);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllZamenskiLekovi() {
      if (zamenskiLekovi != null)
         zamenskiLekovi.clear();
   }

}