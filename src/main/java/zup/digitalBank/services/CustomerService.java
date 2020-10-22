package zup.digitalBank.services;

import org.springframework.stereotype.Service;
import zup.digitalBank.models.Customer;
import zup.digitalBank.models.CustomerAddress;
import zup.digitalBank.models.CustomerPersonalDetail;
import zup.digitalBank.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(){
        customerRepository = new CustomerRepository();
    }

    public boolean createCustomerAddress(CustomerAddress customerAddress)throws SQLException {
        try{
            customerAddress.setId(UUID.randomUUID());
            customerRepository.createCustomerAddress(customerAddress);
            return true;
        }catch (SQLException exception){
            return false;
        }
    }

    public boolean createCustomerPersonalDetail(CustomerPersonalDetail customerPersonalDetail)throws SQLException {
        try{
            customerRepository.createCustomerPersonalDetail(customerPersonalDetail);
            return true;
        }catch (SQLException exception){
            return false;
        }
    }

    public boolean createCustomer(Customer customer) {
        customerRepository.createCustomer(customer);
        return true;
    }

    public void updateCustomer(Customer customer){
        customerRepository.updateCustomer(customer);
    }
}
