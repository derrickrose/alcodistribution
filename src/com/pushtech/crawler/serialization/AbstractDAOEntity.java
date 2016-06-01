package com.pushtech.crawler.serialization;

import com.pushtech.commons.Product;

public abstract class AbstractDAOEntity {

	DAOFactory DAOFactoryInstance = new DataBaseDAO().getFactoryInstance();
	
	public abstract AbstractDAOEntity  getAbstractDAOEntity(DAOFactory DAOFactoryInstance);
	
	public abstract AbstractDAOEntity searchEntity(String entityIdentifier);

	public abstract int saveEntity(Product product);

	public abstract int setEntity(Product product);

}
