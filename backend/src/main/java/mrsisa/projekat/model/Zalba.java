package mrsisa.projekat.model;
import javax.persistence.*;
/***********************************************************************
 * Module:  Zalba.java
 * Author:  rajta
 * Purpose: Defines the Class Zalba
 ***********************************************************************/

import java.util.*;

/** @pdOid 0c6e5a58-36ab-470a-abae-5b577bcfe7ed */
public class Zalba {

   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   /** @pdOid 25b22584-6674-4425-aa4c-b97d2d3e5582 */
   @Column(name = "poruka", nullable = false)
   private String poruka;

}