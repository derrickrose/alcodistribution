package com.pushtech.crawler.launcher;

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

      HttpResponse response = null;
      try {
         String url = "http://www.grossiste-en-ligne.com/vetement-femme/robe";
         response = ConnectionHandler.getResponse(url, null, null, EngineContext.MethodType.GET_METHOD);
         Page page = (Page) ParsingTemplate.getAppropriateParsingTemplate(response).parse(url, response, null);
         ArrayList<Product> products = new ArrayList<Product>();
         if (PageType.isProductPage(page)) {
            Product product = new CrawlOffer().doAction(page);
            products.add(product);
         } else if (PageType.isListingPage(page)) {
            int id = 0;
            for (String link : CrawlListing.getProductLinks(page)) {
               HttpResponse productPageResponse = ConnectionHandler.getResponse(link, null, null, EngineContext.MethodType.GET_METHOD);
               Page productPage = (Page) ParsingTemplate.getAppropriateParsingTemplate(productPageResponse).parse(link, productPageResponse, null);
               Product product = new CrawlOffer().doAction(productPage);
               product.setId(id);
               products.add(product);
               id++;
            }
         }

         CSVService csvService = new CSVService();
         csvService.buildCSV(products, ";");

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
      }

   }

}
