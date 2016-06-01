package com.pushtech.crawler.serialization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pushtech.commons.Product;

public class ProductDAO extends AbstractDAOEntity {

   private static final String TABLE_NAME = "alcodistribution";
   private static final String INSERT_REQUEST = initInsertRequest();
   private static final String READ_REQUEST = initReadRequest();
   private static final String SEARCH_REQUEST = initSearchRequest();

   private DAOFactory daoFactory = null;

   public ProductDAO(DAOFactory daoFactory) {
      // TODO Auto-generated constructor stub
      this.daoFactory = daoFactory;
   }

   public Product searchEntity(Product product) {
      return searchEntity(product.getId());
   }

   @Override
   public Product searchEntity(String entityIdentifier) {
      Product product = null;
      Connection connection = null;
      PreparedStatement searchStatement = null;
      ResultSet resultSet = null;
      try {
         connection = daoFactory.getConnection();
         searchStatement = initPreparedRequest(connection, SEARCH_REQUEST, false, entityIdentifier);
         resultSet = searchStatement.executeQuery();
         while (resultSet.next()) {
            product = mapper(resultSet);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return product;
   }

   @Override
   public int saveEntity(Product product) {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      ResultSet autoGeneratedResult = null;
      int status = 0;
      try {
         // System.out.println("aty koa iz zeo");
         connection = daoFactory.getConnection();
         preparedStatement = initPreparedRequest(connection, INSERT_REQUEST, true, product.getId(), product.getName(), product.getLink(), product.getImage(), product.getDescription(), product.getKeyword(), product.getPrice(), product.getShippingCost(), product.getShippingDelay(), product.getBrand(), product.getCategory(), product.getQuantity(), product.getUpdated());
         status = preparedStatement.executeUpdate();
         if (status == 0) {
            System.err.println("Save product failed");
         } else System.out.println("Save product OK");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return status;
   }

   // @Override
   // public void setEntity(AbstractDAOEntity abstractDAOEntity) {
   // // TODO Auto-generated method stub
   //
   // }

   public AbstractDAOEntity getAbstractDAOEntity(DAOFactory DAOFactoryInstance) {
      // TODO Auto-generated method stub
      return null;
   }

   public static PreparedStatement initPreparedRequest(Connection connection, String request, boolean returnGeneratedKeys, Object... objects) throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement(request, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
      for (int i = 0; i < objects.length; i++) {
         preparedStatement.setObject(i + 1, objects[i]);
      }
      return preparedStatement;
   }

   private Product mapper(ResultSet resultSet) throws SQLException {
      Product product = new Product();
      product.setId(resultSet.getString("productId"));
      // product.setParentId(resultSet.getString( "parentid" ) );
      product.setName(resultSet.getString("Name"));
      product.setLink(resultSet.getString("link"));
      product.setDescription(resultSet.getString("description"));
      product.setBrand(resultSet.getString("brand"));
      product.setCategory(resultSet.getString("category"));
      product.setImage(resultSet.getString("image"));
      product.setKeyWord(resultSet.getString("motclef"));
      product.setPrice(resultSet.getFloat("price"));
      product.setShippingCost(resultSet.getFloat("shippingCost"));
      product.setShippingDelay(resultSet.getInt("shippingDealy"));
      product.setQuantity(resultSet.getInt("quantity"));
      product.setUpdated(resultSet.getDate("update_time"));

      return product;
   }

   @Override
   public int setEntity(Product product) {
      // TODO Auto-generated method stub
      return 0;
   }

   private static String initInsertRequest() {
      String insertRequest = "INSERT INTO " + TABLE_NAME + " ";
      insertRequest += "(productId, Name, link, image, description, motclef, price, shippingCost, shippingDealy, brand, category, quantity, update_time)";
      insertRequest += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      return insertRequest;
   }

   private static String initSearchRequest() {
      String searchRequest = "SELECT * FROM " + TABLE_NAME + " WHERE productId = ? ";
      return searchRequest;
   }

   private static String initReadRequest() {
      final String readRequest = "SELECT * FROM " + TABLE_NAME + " LIMIT 10";
      return readRequest;
   }
}
