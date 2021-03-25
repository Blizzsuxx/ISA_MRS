package mrsisa.projekat.administratorApoteke;
import java.util.*;

import javax.persistence.*;

import mrsisa.projekat.narucbenica.*;
import mrsisa.projekat.administrator.Administrator;
import mrsisa.projekat.apoteka.Apoteka;


public class AdministratorApoteke extends Administrator {
  
   @OneToMany(mappedBy = "administratorApoteke", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public java.util.Collection<Narucbenica> narucbenice;
   
  @OneToOne(mappedBy = "administratorApoteke", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   public Apoteka apoteka;
   
   
   
   public java.util.Collection<Narucbenica> getNarucbenice() {
      if (narucbenice == null)
         narucbenice = new java.util.HashSet<Narucbenica>();
      return narucbenice;
   }
   

   public void setNarucbenice(java.util.Collection<Narucbenica> newNarucbenice) {
      removeAllNarucbenice();
      for (java.util.Iterator iter = newNarucbenice.iterator(); iter.hasNext();)
         addNarucbenice((Narucbenica)iter.next());
   }
   

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