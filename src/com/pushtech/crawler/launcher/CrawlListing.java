package com.pushtech.crawler.launcher;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pushtech.crawler.beans.Page;

public class CrawlListing {

   public static ArrayList<String> getProductLinks(Page page) {
      ArrayList<String> products = new ArrayList<String>();
      Document document = page.getDoc();
      Elements items = getOffers(document);
      for (Element item : items) {
         String link = getProductLink(item);
         System.out.println("Path :" + link);
         products.add(link);
      }
      return products;
   }

   private static Elements getOffers(Document doc) {
      return doc.select(Selectors.LISTING_PAGE_PRODUCTS);
   }

   private static String getProductLink(Element item) {
      Element product = item.select(Selectors.LISTING_PAGE_PRODUCT_LINK).first();
      if (product != null) {
         return cleanPath(product.attr("href"));
      } else {
         System.out.println("Error listing");
      }
      return "";
   }

   private static String cleanPath(String path) {
      path = path.replace("" + (char) 201, "%C3%89").replace(" ", "%20").replace("" + (char) 232, "%C3%A8");
      if (!StringUtils.startsWith(path, "http:")) {
         return "http://www.alcodistributions.fr" + path;
      }
      return path;
   }
}