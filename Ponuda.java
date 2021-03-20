/***********************************************************************
 * Module:  Ponuda.java
 * Author:  rajta
 * Purpose: Defines the Class Ponuda
 ***********************************************************************/

import java.util.*;

/** @pdOid 7f9ce11b-1d03-465e-8c25-868817f16433 */
public class Ponuda {
   /** @pdOid fa3fad83-59e0-4553-ac97-61beeefb7db0 */
   private double cena;
   
   /** @pdRoleInfo migr=no name=Narucbenica assc=association25 mult=1..1 */
   public Narucbenica narudzbenica;
   /** @pdRoleInfo migr=no name=Dobavljac assc=association24 mult=0..1 side=A */
   public Dobavljac dobavljac;
   
   
   /** @pdGenerated default parent getter */
   public Dobavljac getDobavljac() {
      return dobavljac;
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