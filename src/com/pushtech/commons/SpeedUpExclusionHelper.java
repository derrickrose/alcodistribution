package com.pushtech.commons;

import java.util.ArrayList;

public class SpeedUpExclusionHelper {

   private ArrayList<String> exclude = new ArrayList<String>();

   public SpeedUpExclusionHelper() {
      // TODO Auto-generated constructor stub
      initList();
   }

   // private void initList() {
   // exclude.add("CADEAU");
   // exclude.add("EXPOSANTS");
   // exclude.add("JOUETS"); // masque
   // exclude.add("//%C3%89T%C3%89/");
   // exclude.add("//" + ((char) 201) + "T" + ((char) 201) + "/");
   // exclude.add("//LICENCES");
   // exclude.add("OUTLET");
   // exclude.add("PAPETERIE/ARTICLES");
   // exclude.add("PAPETERIE/" + ((char) 201) + "CRITURE");
   // exclude.add("PAPETERIE/%C3%89" + "CRITURE"); //
   // exclude.add("MARQUES%20PAPETERIE");
   // exclude.add("0407");
   // exclude.add("0408");
   // // jouets disney
   // // licence pepa pig
   //
   // }

   private void initList() {
      // exclude.add("CADEAU");
      // exclude.add("EXPOSANTS");
      // exclude.add("//%C3%89T%C3%89/");
      // exclude.add("//" + ((char) 201) + "T" + ((char) 201) + "/");
      // // exclude.add("//LICENCES");
      // exclude.add("OUETS/D%C3%89GUISEMENTS%");
      // exclude.add("JOUETS/D" + ((char) 201) + "GUISEMENT");
      // exclude.add("OUTLET");
      // exclude.add("PAPETERIE");
      // exclude.add("PROMOTION");
      // exclude.add("SUPER%20PROMO");
      // exclude.add("UEFA%20EURO");
   }

   public ArrayList<String> getExclude() {
      return exclude;
   }

   public void setExclude(ArrayList<String> exclude) {
      this.exclude = exclude;
   }

}