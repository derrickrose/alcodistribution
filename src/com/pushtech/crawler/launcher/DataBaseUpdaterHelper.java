package com.pushtech.crawler.launcher;

public class DataBaseUpdaterHelper {
   // forceUpdate <==> to Force update product in the data base
   public static String getFieldValueToSave(final String siteProductFieldValue, final String dataBaseProductFieldValue, boolean forceUpdate) {
      String valueToSave = siteProductFieldValue;
      if (forceUpdate) {
         valueToSave = siteProductFieldValue;
      }
      if (valueToSave.equals("")) {
         valueToSave = dataBaseProductFieldValue; // if blank siteProduct field value we do not change anything from database
      }
      if (valueToSave == null) {
         valueToSave = ""; // not null constraint on dataBase
      }
      return valueToSave;
   }
}
// to set a blank field value site = null && force update true;
