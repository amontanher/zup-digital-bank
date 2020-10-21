package zup.digitalBank.services;

import org.springframework.stereotype.Service;
import zup.digitalBank.models.CustomerPersonalDetail;
import zup.digitalBank.repositories.CustomerRepository;

import java.util.List;

@Service
public class ProposalService {

    private final CustomerRepository customerRepository;

    public ProposalService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean createInitProposal(CustomerPersonalDetail customerPersonalDetail){
        try{
            customerRepository.save(customerPersonalDetail);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
