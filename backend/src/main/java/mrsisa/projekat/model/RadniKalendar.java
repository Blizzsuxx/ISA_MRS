package mrsisa.projekat.model;
/***********************************************************************
 * Module:  RadniKalendar.java
 * Author:  User
 * Purpose: Defines the Class RadniKalendar
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid 1f7aff46-1ce4-400f-9348-4dd6441bc87e */

@Entity
public class RadniKalendar {

   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   /** @pdRoleInfo migr=no name=Poseta assc=association11 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   @OneToMany(mappedBy = "radni_kalendar", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private java.util.Collection<Poseta> poseta;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Poseta> getPoseta() {
      if (poseta == null)
         poseta = new java.util.HashSet<Poseta>();
      return poseta;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPoseta() {
      if (poseta == null)
         poseta = new java.util.HashSet<Poseta>();
      return poseta.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPoseta */
   public void setPoseta(java.util.Collection<Poseta> newPoseta) {
      removeAllPoseta();
      for (java.util.Iterator iter = newPoseta.iterator(); iter.hasNext();)
         addPoseta((Poseta)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPoseta */
   public void addPoseta(Poseta newPoseta) {
      if (newPoseta == null)
         return;
      if (this.poseta == null)
         this.poseta = new java.util.HashSet<Poseta>();
      if (!this.poseta.contains(newPoseta))
         this.poseta.add(newPoseta);
   }
   
   /** @pdGenerated default remove
     * @param oldPoseta */
   public void removePoseta(Poseta oldPoseta) {
      if (oldPoseta == null)
         return;
      if (this.poseta != null)
         if (this.poseta.contains(oldPoseta))
            this.poseta.remove(oldPoseta);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPoseta() {
      if (poseta != null)
         poseta.clear();
   }

}