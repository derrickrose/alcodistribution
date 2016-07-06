package com.pushtech.crawler.launcher;

import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.connection.EngineContext;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import static com.pushtech.commons.UriHandler.cleanPath;
import static com.pushtech.crawler.launcher.Crawl.getPageFromUrl;

/**
 * Created by Workdev on 10/06/2016.
 */
public class CrawlHome {

	private static ArrayList<String> getAllListing(String link) {
		Page homePage = getPageFromUrl(link,
				EngineContext.MethodType.GET_METHOD);
		return getAllListing(homePage);
	}

	public static ArrayList<String> getAllListing(Page homePage) {
		ArrayList<String> allListing = new ArrayList<String>();
		Document doc = homePage.getDoc();
		Elements elts = doc.select(Selectors.ALL_LISTING);
		if (elts.size() > 0) {
			for (Element data : elts) {
				System.out.println("Url :" + data.attr("href"));
				allListing.add(cleanPath(data.attr("href")));
			}
		}
		return allListing;
	}
}
