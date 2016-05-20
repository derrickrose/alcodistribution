package com.pushtech.crawler.launcher;

public class Selectors {

   // home page
   // category page
   // listing page

   // product page
   public static final String PRODUCT_PAGE_IDENTIFIER = "#productSidebar";
   public static final String PRODUCT_NAME = ".productName.noBg>span";
   public static final String PRODUCT_LINK = "head > link";
   public static final String PRODUCT_DESCRIPTION = "meta[name=Description]";
   public static final String PRODUCT_BRAND = "";
   public static final String PRODUCT_CATEGORY = ".crumb0>a";
   public static final String PRODUCT_IMAGE = ".slide > img";
   public static final String PRODUCT_PRICE = "#articlePrice.price >span > span#price";
   public static final String PRODUCT_DECIMAL_PRICE = "#price_decimals";
   public static final String PRODUCT_SHIPPING_COST = ".inlineBlock:contains(Livraison)";
   public static final String PRODUCT_SHIPPING_DELAY = ".availability.inctdclks>p>strong:not([itemprop])";
   // public static final String PRODUCT_QUANTITY = "#stocks_listing ul>li";
   public static final String PRODUCT_QUANTITY = ".max_stock";

   // listing page
   public static final String LISTING_PAGE_IDENTIFIER = "ul#productsList>li";
   public static final String LISTING_PAGE_PRODUCTS = "ul#productsList>li";
   public static final String LISTING_PAGE_PRODUCT_LINK = "div.productBox>a";

}
