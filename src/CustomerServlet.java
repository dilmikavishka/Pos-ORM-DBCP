import bo.BoFactory;
import bo.custome.CustomerBo;
import dto.CustomerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBO(BoFactory.BoType.Customer);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerDTO> customerList = customerBo.getAllCustomers();
        // Use JSON-P to construct the JSON response
        JsonArrayBuilder allCustomer = Json.createArrayBuilder();
        for (CustomerDTO customer : customerList) {
            JsonObjectBuilder customerJson = Json.createObjectBuilder();
            customerJson.add("id", customer.getId());
            customerJson.add("name", customer.getName());
            customerJson.add("address", customer.getAddress());
            allCustomer.add(customerJson);
        }

        // Set response type and write the JSON array to the output
        resp.setContentType("application/json");
        resp.getWriter().print(allCustomer.build().toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String name = req.getParameter("name");
        String address = req.getParameter("address");
        CustomerDTO customerDTO = new CustomerDTO();
        System.out.println(name + " " + address);
        customerDTO.setName(name);
        customerDTO.setAddress(address);
        boolean issaved =customerBo.save(customerDTO);
        if (issaved){
            resp.getWriter().println("Customer Saved");
        }else {
            resp.getWriter().println("Customer Not Saved");
        }
        System.out.println(name + " " + address);
    }
}