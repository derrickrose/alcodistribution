package com.pushtech.crawler.launcher;

import static com.pushtech.crawler.launcher.CrawlListing.getNextPageLink;
import static com.pushtech.crawler.launcher.CrawlListing.getProductLinks;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.connection.ConnectionHandler;
import com.pushtech.crawler.connection.EngineContext;
import com.pushtech.crawler.parsing.ParsingTemplate;
import com.pushtech.crawler.persistance.Persistance;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.xml.bind.Element;

public class Crawler {

   public static void main(String[] args) {

      try {

         ArrayList<Product> products = new ArrayList<Product>();

         Page page = null;

         String url = "http://alcodistributions.fr/";

         try {
           ArrayList<String> alllisting=getAllListing(url);
            for(String listing:alllisting){
               boolean continueCrawl = true;
               String rayon=listing;
               while (continueCrawl) {
                  page = getPageFromUrl(rayon, EngineContext.MethodType.GET_METHOD);
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
                           // products.add(product);
                           Persistance.sauverEnBase(product);
                           id++;
                           // break;
                        } catch (Exception e) {
                           System.out.println("error =>>> IMPOSSIBLE DE SE CONNECTER");
                        }

                     }
                     rayon = getNextPageLink(page.getDoc());
                     continueCrawl = rayon != null ? true : false;
                  } else continueCrawl = false;
               }
            }
         } catch (Exception e) {

         }

         // CSVService csvService = new CSVService();
         // csvService.buildCSV(products, ";");

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
      }
   }
   private static ArrayList<String> getAllListing(String link){
      ArrayList<String> listes=new ArrayList<String>();
      Page productPage = getPageFromUrl(link, EngineContext.MethodType.GET_METHOD);
      Document doc=productPage.getDoc();
      Elements elts=doc.select(Selectors.ALL_LISTING);
      if(elts.size()>0){
         for(org.jsoup.nodes.Element data:elts){
            System.out.println("Url :"+data.attr("href"));
            listes.add(cleanPath(data.attr("href")));
         }
      }
   return listes;
   }
   private static String cleanPath(String path) {
      if (path == null) return null;
      path = path.replace("" + (char) 201, "%C3%89").replace(" ", "%20").replace("" + (char) 232, "%C3%A8");
      path = path.replace("" + ((char) 96), "%60").replace("" + ((char) 233), "%C3%A9").replace("" + ((char) 146), "%E2%80%99");
      if (!StringUtils.startsWith(path, "http:")) {
         path = "http://www.alcodistributions.fr" + path;

      }
      // try {
      // path = URIUtil.encodeQuery(URIUtil.decode(path));
      // } catch (URIException e) {
      // // TODO Auto-generated catch block
      // // return path;
      // System.out.println("tsssssssss mety ttttttttttttt");
      // e.printStackTrace();
      // }
      return path;
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
