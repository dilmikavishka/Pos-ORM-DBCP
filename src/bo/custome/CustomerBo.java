package bo.custome;

import bo.SuperBo;
import dto.CustomerDTO;

import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean save(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();
}
