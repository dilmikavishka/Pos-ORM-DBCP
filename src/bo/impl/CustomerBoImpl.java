package bo.impl;

import bo.custome.CustomerBo;
import dao.custome.CustomerDao;
import dao.DaoFactory;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoFactory().getDAOType(DaoFactory.DAOTYPE.Customer);
    @Override
    public boolean save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        return customerDao.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customerList = customerDao.getAllCustomer();
        for (Customer customer  : customerList) {
            customerDTOList.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOList;
    }


}
