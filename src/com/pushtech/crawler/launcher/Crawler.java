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
         String url = "http://www.alcodistributions.fr/catalogo/categorias/030211//CADEAU/COMPL%C3%89MENTS%20ET%20TEXTILE/Bandouli%C3%A8res";
         while (continueCrawl) {
            page = getPageFromUrl(url, EngineContext.MethodType.GET_METHOD);
            if (PageType.isProductPage(page)) {
               Product product = new CrawlOffer().doAction(page);
               products.add(product);
               continueCrawl = false;
            } else if (PageType.isListingPage(page)) {
               int id = 0;
               for (String link : CrawlListing.getProductLinks(page)) {
                  Page productPage = getPageFromUrl(url, EngineContext.MethodType.GET_METHOD);
                  Product product = new CrawlOffer().doAction(productPage);
                  product.setLink(link);
                  // product.setId(id);
                  products.add(product);
                  id++;
                  // break;
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

}
