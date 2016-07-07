package com.pushtech.crawler.launcher;

import static com.pushtech.commons.UriHandler.cleanPath;
import static com.pushtech.crawler.launcher.Crawl.getPageFromUrl;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pushtech.commons.SpeedUpExclusionHelper;
import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.connection.EngineContext;

/**
 * Created by Workdev on 10/06/2016.
 */
public class CrawlHome {

   private static ArrayList<String> getAllListing(String link) {
      Page homePage = getPageFromUrl(link, EngineContext.MethodType.GET_METHOD);
      return getAllListing(homePage);
   }

   public static ArrayList<String> getAllListing(Page homePage) {
      ArrayList<String> allListing = new ArrayList<String>();
      Document doc = homePage.getDoc();
      Elements elts = doc.select(Selectors.ALL_LISTING);
      SpeedUpExclusionHelper sph = new SpeedUpExclusionHelper();
      if (elts.size() > 0) {
         for (Element data : elts) {
            String url = data.attr("href");
            System.out.println("Url :" + url);
            boolean toExclude = false;
            for (String str : sph.getExclude()) {
               if (url.contains(str)) {
                  toExclude = true;
                  break;
               }
            }
            if (toExclude) {
               System.err.println("toexclude : " + url);
            } else {
               allListing.add(cleanPath(url));
            }

         }
      }
      return allListing;
   }
}
