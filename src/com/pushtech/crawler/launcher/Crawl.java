package com.pushtech.crawler.launcher;

import static com.pushtech.crawler.launcher.CrawlListing.getNextPageLink;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.connection.ConnectionHandler;
import com.pushtech.crawler.connection.EngineContext;
import com.pushtech.crawler.parsing.ParserFactory;
import com.pushtech.crawler.serialization.AbstractDAOEntity;
import com.pushtech.crawler.serialization.DAOFactory;
import com.pushtech.crawler.serialization.DataBaseDAO;
import com.pushtech.crawler.serialization.ProductDAO;

/**
 * Created by Workdev on 10/06/2016.
 */
public class Crawl {
	public Crawl(String entryPointUrl) {
		try {
			Page page = null;
			String urlToConnect = entryPointUrl;
			try {
				page = getPageFromUrl(urlToConnect,
						EngineContext.MethodType.GET_METHOD);
				if (PageType.isProductPage(page)) {
					offerCrawling(page, urlToConnect);
				} else if (PageType.isListingPage(page)) {
					listingCrawling(page);
				} else if (entryPointUrl
						.equals("http://www.alcodistributions.fr")
						|| entryPointUrl
								.endsWith("http://www.alcodistributions.fr/")) {
					homeCrawling(page);// home page � faire
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

	public static Page getPageFromUrl(final String url,
			EngineContext.MethodType methodeType) {
		Page page = null;
		HttpResponse response = null;
		response = ConnectionHandler.getResponse(url, null, null, methodeType);
		page = (Page) ParserFactory.getAppropriateParsingTemplate(response)
				.parse(url, response, null);
		return page;
	}

	private void offerCrawling(Page page, String productPath) {
		Product product = new CrawlOffer().doAction(page);
		System.out.println("Link : " + productPath);
		String productId = CrawlListing.getIdFromLink(productPath);
		System.out.println("Product Id :" + productId);
		// if(Persistance.lireEnBase()){
		// continue;
		// }
		product.setLink(productPath);
		product.setId(productId);
		DAOFactory daoFactory = new DataBaseDAO().getFactoryInstance();
		AbstractDAOEntity daoEntity = new ProductDAO(daoFactory);
		daoEntity.updateEntity(product);
	}

	private void homeCrawling(Page homePage) {
		ArrayList<String> allListing = CrawlHome.getAllListing(homePage);
		for (String listing : allListing) {
			Page listingPage = getPageFromUrl(listing,
					EngineContext.MethodType.GET_METHOD);
			listingCrawling(listingPage);
		}
	}

	private String listingProcess(Page listingPage) {
		int indexProduit = 0;
		for (String productPath : CrawlListing.getProductLinks(listingPage)) {
			System.out.println("-------------------- Produit n* "
					+ indexProduit + " --------------------");

			try {
				Page productPage = getPageFromUrl(productPath,
						EngineContext.MethodType.GET_METHOD);
				offerCrawling(productPage, productPath);
				// System.out.println("-------------");
				indexProduit++;
				// break;
			} catch (Exception e) {
				System.out.println("error =>>> IMPOSSIBLE DE SE CONNECTER");
			}
		}
		return getNextPageLink(listingPage.getDoc());
	}

	private void listingCrawling(Page firstListingPage) {
		boolean continueCrawl = true;
		Page page = firstListingPage;
		String nextPageLink = null;
		while (continueCrawl) {
			nextPageLink = listingProcess(page);
			continueCrawl = nextPageLink != null ? true : false;
			if (continueCrawl) {
				page = getPageFromUrl(nextPageLink,
						EngineContext.MethodType.GET_METHOD);
			}
		}
	}
}