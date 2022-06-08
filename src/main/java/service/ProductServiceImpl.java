package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService<Product> {

    static String jdbcURL = "jdbc:mysql://localhost:3306/demo2006?useSSL=false";
    static String jdbcUsername = "root";
    static String jdbcPassword = "12111992";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(ProductServiceImpl.jdbcURL, ProductServiceImpl.jdbcUsername, ProductServiceImpl.jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public void add(Product product) {


    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM product ")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");

                products.add(new Product(id,name,price,quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> findByPrice(int begin, int end){
        List<Product> list = new ArrayList<>();
        List<Product> products = findAll();
        for (Product p : products) {
            if (p.getPrice() >= begin && p.getPrice() <= end) {
                list.add(p);
            }
        }
        return list;
    }
//    public List<Product> findByName(String names) {
//        List<Product> products = new ArrayList<>();
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement =
//                     connection.prepareStatement("SELECT * FROM product WHERE name LIKE ?")) {
//            System.out.println(preparedStatement);
//            preparedStatement.setString(1, "%" + names + "%");
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int price = rs.getInt("price");
//                int quantity = rs.getInt("quantity");
//
//                products.add(new Product(id,name,price,quantity));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
public List<Product> findByName(String name){
    List<Product> list = new ArrayList<>();
    List<Product> products = findAll();
    for (Product p : products) {
        if (p.getName().contains(name)) {
            list.add(p);
        }
    }
    return list;
}
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }
}
