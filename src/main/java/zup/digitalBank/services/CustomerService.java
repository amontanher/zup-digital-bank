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

    public void createCustomerAddress(CustomerAddress customerAddress, UUID customerId)throws SQLException {
        try{
            UUID customerAddressId = UUID.randomUUID();
            customerAddress.setId(customerAddressId);

            customerRepository.createCustomerAddress(customerAddress);

            Customer customerToUpdate = customerRepository.getCustomer(customerId);
            customerToUpdate.setCustomerAddressId(customerAddressId);
            customerRepository.updateCustomer(customerToUpdate);
        }catch (SQLException exception){
            throw  exception;
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

    public Customer getCustomer(UUID customerId){
        return customerRepository.getCustomer(customerId);
    }
}
