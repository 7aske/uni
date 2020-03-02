/***********************************************************************
 * Module:  Shop.java
 * Author:  nik
 * Purpose: Defines the Class Shop
 ***********************************************************************/

import java.util.*;

/** @pdOid 9483438e-3084-42b9-83c8-5d397ae622ee */
public class Shop {
   /** @pdOid 7534fb00-f475-4660-9579-218d59c9ebad */
   private String name;
   /** @pdOid 411832c8-cc47-459d-9a94-7c15cb2ffa82 */
   private String address;
   
   /** @pdRoleInfo migr=no name=Product assc=association1 mult=0..* type=Aggregation */
   public Product[] product;

}