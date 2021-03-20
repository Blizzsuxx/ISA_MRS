/***********************************************************************
 * Module:  AdministratorApoteke.java
 * Author:  User
 * Purpose: Defines the Class AdministratorApoteke
 ***********************************************************************/

import java.util.*;

/** @pdOid 56b3ebae-3efb-4723-9a14-fc8dc140c91a */
public class AdministratorApoteke extends Administrator {
   /** @pdRoleInfo migr=no name=Narucbenica assc=association15 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Narucbenica> narucbenice;
   /** @pdRoleInfo migr=no name=Apoteka assc=association4 mult=1..1 side=A */
   public Apoteka apoteka;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Narucbenica> getNarucbenice() {
      if (narucbenice == null)
         narucbenice = new java.util.HashSet<Narucbenica>();
      return narucbenice;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorNarucbenice() {
      if (narucbenice == null)
         narucbenice = new java.util.HashSet<Narucbenica>();
      return narucbenice.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newNarucbenice */
   public void setNarucbenice(java.util.Collection<Narucbenica> newNarucbenice) {
      removeAllNarucbenice();
      for (java.util.Iterator iter = newNarucbenice.iterator(); iter.hasNext();)
         addNarucbenice((Narucbenica)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newNarucbenica */
   public void addNarucbenice(Narucbenica newNarucbenica) {
      if (newNarucbenica == null)
         return;
      if (this.narucbenice == null)
         this.narucbenice = new java.util.HashSet<Narucbenica>();
      if (!this.narucbenice.contains(newNarucbenica))
         this.narucbenice.add(newNarucbenica);
   }
   
   /** @pdGenerated default remove
     * @param oldNarucbenica */
   public void removeNarucbenice(Narucbenica oldNarucbenica) {
      if (oldNarucbenica == null)
         return;
      if (this.narucbenice != null)
         if (this.narucbenice.contains(oldNarucbenica))
            this.narucbenice.remove(oldNarucbenica);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllNarucbenice() {
      if (narucbenice != null)
         narucbenice.clear();
   }

}