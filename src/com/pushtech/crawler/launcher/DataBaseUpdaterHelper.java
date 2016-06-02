/*****
 * 
 * VERY IMPORTANT
 * to set a blank field value in database, site field value must be null and force update
 * siteProductValue = null && forceUpdate = true;
 * 
 * */

package com.pushtech.crawler.launcher;

import java.util.Date;
import com.pushtech.commons.Product;

public class DataBaseUpdaterHelper {
	// forceUpdate <==> to Force update product in the data base
	private boolean forceUpdate = true;
	private UpdateWay updateWay;

	public static enum UpdateWay {
		SIMPLE_UPDATE, DELETE_THEN_UPDATE;
	}

	private String getFieldValueToSave(final String siteProductFieldValue,
			final String dataBaseProductFieldValue) {
		String valueToSave = siteProductFieldValue;
		if (updateWay == UpdateWay.SIMPLE_UPDATE) {
			if (forceUpdate) {
				valueToSave = siteProductFieldValue;
			}
			if (valueToSave.equals("")) {
				valueToSave = dataBaseProductFieldValue; // if blank siteProduct
															// field value we do
															// not
															// change anything
															// from
															// database
			}
			if (valueToSave == null) {
				valueToSave = ""; // not null constraint on dataBase
			}
		}
		return valueToSave;
	}

	// TODO may be needed one day
	private float getFieldValueToSave(final float siteProductFieldValue,
			final float dataBaseProductFieldValue) {
		float valueToSave = siteProductFieldValue;
		if (updateWay == UpdateWay.SIMPLE_UPDATE) {
			if (forceUpdate) {
				valueToSave = siteProductFieldValue;
			}
			if (valueToSave == -1f) {
				valueToSave = dataBaseProductFieldValue; // if -1f siteProduct
															// field
															// value
				// we do not
				// change anything from
				// database
			}
		}

		return valueToSave;
	}

	private int getFieldValueToSave(final int siteProductFieldValue,
			final int dataBaseProductFieldValue) {
		int valueToSave = siteProductFieldValue;
		if (updateWay == UpdateWay.SIMPLE_UPDATE) {
			if (forceUpdate) {
				valueToSave = siteProductFieldValue;
			}
			if (valueToSave == 0) {
				valueToSave = dataBaseProductFieldValue; // if 0 siteProduct
															// field
															// value
															// we do not
															// change anything
															// from
															// database
			}
		}

		return valueToSave;
	}

	private DataBaseUpdaterHelper(boolean forceUpdate, UpdateWay updateWay) {
		this.forceUpdate = forceUpdate;
		this.updateWay = updateWay;
	}

	public static DataBaseUpdaterHelper getUpdaterMode(boolean forceUpdate,
			UpdateWay updateWay) {
		return new DataBaseUpdaterHelper(forceUpdate, updateWay);
	}

	public Product updateProduct(Product siteProduct,
			final Product databaseProduct) {
		siteProduct.setName(getFieldValueToSave(siteProduct.getName(),
				databaseProduct.getName()));
		siteProduct.setLink(getFieldValueToSave(siteProduct.getLink(),
				databaseProduct.getLink()));
		siteProduct
				.setDescription(getFieldValueToSave(
						siteProduct.getDescription(),
						databaseProduct.getDescription()));
		siteProduct.setBrand(getFieldValueToSave(siteProduct.getBrand(),
				databaseProduct.getBrand()));
		siteProduct.setCategory(getFieldValueToSave(siteProduct.getCategory(),
				databaseProduct.getCategory()));
		siteProduct.setImage(getFieldValueToSave(siteProduct.getImage(),
				databaseProduct.getImage()));
		siteProduct.setKeyWord(getFieldValueToSave(siteProduct.getKeyWord(),
				databaseProduct.getKeyWord()));
		// siteProduct.setDate(new Date());
		siteProduct.setPrice(getFieldValueToSave(siteProduct.getPrice(),
				databaseProduct.getPrice()));
		siteProduct.setShippingCost(getFieldValueToSave(
				siteProduct.getShippingCost(),
				databaseProduct.getShippingCost()));
		siteProduct.setShippingDelay(getFieldValueToSave(
				siteProduct.getShippingDelay(),
				databaseProduct.getShippingDelay()));
		siteProduct.setQuantity(getFieldValueToSave(siteProduct.getQuantity(),
				databaseProduct.getQuantity()));
		return siteProduct;
	}

}