package com.pushtech.commons;

import java.sql.Date;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;


public class Product {

   private String id = "" + 0, parentId = "" + 0;
   private String name = "", link = "", description = "", brand = "", category = "", image = "";
   private String keyWord = "";
   private Date updated = null;
   
   public String getKeyword() {
      return keyWord;
   }

   public void setKeyWord(String keyword) {
      this.keyWord = keyWord;
   }

   public Date getUpdated() {
	return updated;
}

public void setUpdated(Date date) {
	this.updated = date;
}

public String getKeyWord() {
	return keyWord;
}

private float price = -1f, shippingCost = -1f;
   private int shippingDelay = 0, quantity = 0;

   public String getId() {
      return id;
   }

   public String getParentId() {
      return parentId;
   }

   public String getName() {
      return name;
   }

   public String getLink() {
      return link;
   }

   public String getDescription() {
      return description;
   }

   public String getBrand() {
      return brand;
   }

   public String getCategory() {
      return category;
   }

   public String getImage() {
      return image;
   }

   public float getPrice() {
      return price;
   }

   public float getShippingCost() {
      return shippingCost;
   }

   public int getShippingDelay() {
      return shippingDelay;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setParentId(String parentId) {
      this.parentId = parentId;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setLink(String link) {
      this.link = link;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public void setShippingCost(float shippingCost) {
      this.shippingCost = shippingCost;
   }

   public void setShippingDelay(int shippingDelay) {
      this.shippingDelay = shippingDelay;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
}
