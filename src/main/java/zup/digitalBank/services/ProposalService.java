package zup.digitalBank.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zup.digitalBank.models.*;
import zup.digitalBank.repositories.ProposalRepository;

import java.net.URI;
import java.net.URISyntaxException;
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

    public Proposal getProposal(UUID proposalId) throws SQLException {
        return proposalRepository.getProposal(proposalId);
    }

    public ResponseEntity createProposalAddress(CustomerAddress customerAddress, String proposalId, ProposalStep proposalStep) throws SQLException, URISyntaxException {
        try{
            boolean previousStepIsOk = CheckIfPreviousProposalStepIsOk(proposalId, proposalStep);

            if(previousStepIsOk){
                Proposal customerProposal = getProposal(UUID.fromString(proposalId));
                customerService.createCustomerAddress(customerAddress, customerProposal.getCustomerId());

                String uri = String.format("http://localhost:8080/proposal/3/%s/document",proposalId);
                HttpHeaders responseHeaders = getHeader(uri);
                return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
            }else{
                return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch (SQLException | URISyntaxException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerAddress);
        }
    }

    private boolean CheckIfPreviousProposalStepIsOk(String proposalId, ProposalStep proposalStep) throws SQLException {
        return proposalRepository.checkIfPreviousProposalStepIsOk(proposalId, proposalStep.ordinal());
    }

    private HttpHeaders getHeader(String location) throws URISyntaxException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(new URI(location));
        return responseHeaders;
    }
}