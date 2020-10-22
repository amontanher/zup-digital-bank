package zup.digitalBank.services;

import org.springframework.stereotype.Service;
import zup.digitalBank.models.Customer;
import zup.digitalBank.models.CustomerPersonalDetail;
import zup.digitalBank.models.Proposal;
import zup.digitalBank.repositories.ProposalRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ProposalService {

    final
    CustomerService customerService;
    ProposalRepository proposalRepository;

    public ProposalService(CustomerService customerService, ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
        this.customerService = customerService;
    }

    public Proposal createInitProposal(CustomerPersonalDetail customerPersonalDetail)throws SQLException{
        try{
            Customer customerCreated = createCustomer();
            Proposal proposalCreated = createProposal(customerCreated.getId());

            UUID customerPersonalDetailId = UUID.randomUUID();
            customerPersonalDetail.setId(customerPersonalDetailId);
            customerService.createCustomerPersonalDetail(customerPersonalDetail);

            customerCreated.setCustomerPersonalDetailId(customerPersonalDetailId);
            customerService.updateCustomer(customerCreated);

            return proposalCreated;
        }catch (Exception exception){
            throw exception;
        }
    }

    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customerService.createCustomer(customer);

        return customer;
    }

    private Proposal createProposal(UUID customerId) {
        Proposal proposal = new Proposal();
        proposal.setId(UUID.randomUUID());
        proposal.setCustomerId(customerId);
        proposal.setProposalStep(1);
        proposal.setProposalStepDateConcluded(LocalDate.now());

        proposalRepository.createProposal(proposal);

        return proposal;
    }
}
