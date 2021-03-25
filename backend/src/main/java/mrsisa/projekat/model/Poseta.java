package mrsisa.projekat.model;

/***********************************************************************
 * Module:  Poseta.java
 * Author:  rajta
 * Purpose: Defines the Class Poseta
 ***********************************************************************/

/** @pdOid a2e1e821-c8a6-4ef7-8811-fcb40a04f9bf */

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Poseta {

   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   @ManyToOne(fetch = FetchType.EAGER)
   private ERecept recept;
   /** @pdRoleInfo migr=no name=Radnik assc=association23 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   @ManyToOne(fetch = FetchType.EAGER)
   private Radnik radnik;
   /** @pdRoleInfo migr=no name=Pacijent assc=association8 mult=0..1 side=A */
   @ManyToOne(fetch = FetchType.EAGER)
   private Pacijent pacijent;
   
   
   /** @pdGenerated default getter */
   public ERecept getRecept() {
      return recept;
   }
   
   public void setRecept(ERecept newRecept){
      this.recept = newRecept;
   }


   public Radnik getRadnik() {
      return this.radnik;
   }
   
   public void setRadnik(Radnik newRadnik){
      this.radnik = newRadnik;
   }


   public Pacijent getPacijent() {
      return this.pacijent;
   }
   
   public void setPacijent(Pacijent newPacijent){
      this.pacijent = newPacijent;
   }

}