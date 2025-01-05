package dao.custome;


import dao.CrudDAO;
import entity.Customer;

import java.util.List;

public interface CustomerDao extends CrudDAO<Customer> {

    List<Customer> getAllCustomer();


}
