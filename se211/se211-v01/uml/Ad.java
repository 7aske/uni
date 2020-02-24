/***********************************************************************
 * Module:  Ad.java
 * Author:  nik
 * Purpose: Defines the Class Ad
 ***********************************************************************/

import java.util.*;

/** @pdOid 523b986d-0a92-4512-abce-8fc94a73f21e */
public class Ad {
   /** @pdOid 5c7e40a1-7f2d-4b3c-8f6c-461fb971332c */
   private double price;
   /** @pdOid 7487c89e-8a87-436e-95aa-2a3586902841 */
   private String paymentMethod;
   /** @pdOid 7562afc0-dcb5-4ee5-87f2-6c174a4991ff */
   private int limit;
   
   /** @pdRoleInfo migr=no name=ParkingSpot assc=Association_1 coll=java.util.Collection impl=java.util.HashSet mult=0..1 type=Aggregation */
   public ParkingSpot parkingSpot;

}