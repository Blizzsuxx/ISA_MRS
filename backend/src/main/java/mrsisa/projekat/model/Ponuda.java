package mrsisa.projekat.model;
/***********************************************************************
 * Module:  Ponuda.java
 * Author:  rajta
 * Purpose: Defines the Class Ponuda
 ***********************************************************************/

import java.util.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/** @pdOid 7f9ce11b-1d03-465e-8c25-868817f16433 */
public class Ponuda {

   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   /** @pdOid fa3fad83-59e0-4553-ac97-61beeefb7db0 */
   @Column(name = "cena", nullable = false)
   private double cena;
   
   /** @pdRoleInfo migr=no name=Narucbenica assc=association25 mult=1..1 */
   @ManyToOne(fetch = FetchType.EAGER)
   private Narucbenica narudzbenica;
   /** @pdRoleInfo migr=no name=Dobavljac assc=association24 mult=0..1 side=A */
   @ManyToOne(fetch = FetchType.EAGER)
   private Dobavljac dobavljac;
   
   
   /** @pdGenerated default parent getter */
   public Dobavljac getDobavljac() {
      return dobavljac;
   }
   
   public Narucbenica getNarudzbenica() {
      return narudzbenica;
   }

   public void setNarudzbenica(Narucbenica narudzbenica) {
      this.narudzbenica = narudzbenica;
   }

   public double getCena() {
      return cena;
   }

   public void setCena(double cena) {
      this.cena = cena;
   }

   /** @pdGenerated default parent setter
     * @param newDobavljac */
   public void setDobavljac(Dobavljac newDobavljac) {
      if (this.dobavljac == null || !this.dobavljac.equals(newDobavljac))
      {
         if (this.dobavljac != null)
         {
            Dobavljac oldDobavljac = this.dobavljac;
            this.dobavljac = null;
            oldDobavljac.removePonude(this);
         }
         if (newDobavljac != null)
         {
            this.dobavljac = newDobavljac;
            this.dobavljac.addPonude(this);
         }
      }
   }

}