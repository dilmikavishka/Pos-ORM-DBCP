package dao.impl;

import dao.custome.CustomerDao;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/checks?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Ijse@123";

    @Override
    public boolean save(Customer entity) {
        String newId = "C001";
        String name = entity.getName();
        String address = entity.getAddress();

        try {

            Class.forName("com.mysql.jdbc.Driver");


            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {

                String getMaxIdQuery = "SELECT MAX(id) AS max_id FROM customer";
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(getMaxIdQuery)) {
                    if (rs.next()) {
                        String maxId = rs.getString("max_id");
                        if (maxId != null) {
                            int numericPart = Integer.parseInt(maxId.substring(1));
                            newId = String.format("C%03d", numericPart + 1);
                        }
                    }
                }

                String insertQuery = "INSERT INTO customer (id, name, address) VALUES (?, ?, ?)";
                try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {
                    pst.setString(1, newId);
                    pst.setString(2, name);
                    pst.setString(3, address);
                    int rowsAffected = pst.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Customer search(String id) {
        return null;
    }




    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {

                String getAllQuery = "SELECT * FROM customer";

                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(getAllQuery)) {


                    while (rs.next()) {
                        Customer customer = new Customer();
                        customer.setId(rs.getString("id"));
                        customer.setName(rs.getString("name"));
                        customer.setAddress(rs.getString("address"));
                        customerList.add(customer);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
}
