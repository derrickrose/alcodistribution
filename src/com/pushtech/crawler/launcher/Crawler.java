package com.pushtech.crawler.launcher;

import static com.pushtech.crawler.launcher.CrawlListing.getNextPageLink;

import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.pushtech.commons.CSVService;
import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.connection.ConnectionHandler;
import com.pushtech.crawler.connection.EngineContext;
import com.pushtech.crawler.parsing.ParsingTemplate;

public class Crawler {

   public static void main(String[] args) {

      try {

         ArrayList<Product> products = new ArrayList<Product>();
         boolean continueCrawl = true;
         Page page = null;
         String url = "http://www.alcodistributions.fr/catalogo/categorias/030201//CADEAU/COMPL%C3%89MENTS%20ET%20TEXTILE/Sacs";
         while (continueCrawl) {
            page = getPageFromUrl(url, EngineContext.MethodType.GET_METHOD);
            if (PageType.isProductPage(page)) {
               Product product = new CrawlOffer().doAction(page);
               products.add(product);
               continueCrawl = false;
            } else if (PageType.isListingPage(page)) {
               int id = 0;
               for (String link : CrawlListing.getProductLinks(page)) {

                  try {
                     Page productPage = getPageFromUrl(link, EngineContext.MethodType.GET_METHOD);
                     Product product = new CrawlOffer().doAction(productPage);

                     product.setLink(link);
                     System.out.println("Link : " + link);
                     product.setId(getIdFromLink(link));
                     products.add(product);
                     id++;
                     // break;
                  } catch (Exception e) {
                     System.out.println("error =>>> IMPOSSIBLE DE SE CONNECTER");
                  }

               }
               url = getNextPageLink(page.getDoc());
               continueCrawl = url != null ? true : false;
            } else continueCrawl = false;
         }

         CSVService csvService = new CSVService();
         csvService.buildCSV(products, ";");

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
      }
   }

   private static Page getPageFromUrl(final String url, EngineContext.MethodType methodeType) {
      Page page = null;
      HttpResponse response = null;
      response = ConnectionHandler.getResponse(url, null, null, methodeType);
      page = (Page) ParsingTemplate.getAppropriateParsingTemplate(response).parse(url, response, null);
      return page;
   }

   private static String getIdFromLink(String url) {
      String id = null;
      if (url.contains("articulo")) {
         id = url.substring(url.indexOf("articulo/") + "articulo/".length());
         id = id.substring(0, id.indexOf("/"));
      }
      System.out.println("Id : " + id);
      return id;
   }

}
