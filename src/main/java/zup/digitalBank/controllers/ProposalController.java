package zup.digitalBank.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zup.digitalBank.models.CustomerAddress;
import zup.digitalBank.models.CustomerPersonalDetail;
import zup.digitalBank.models.Proposal;
import zup.digitalBank.models.ProposalStep;
import zup.digitalBank.services.CustomerService;
import zup.digitalBank.services.ProposalService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private final ProposalService proposalService;
    private final CustomerService customerService;

    public ProposalController(CustomerService customerService, ProposalService proposalService) {
        this.customerService = customerService;
        this.proposalService = proposalService;
    }

    @PostMapping("/init")
    public ResponseEntity<CustomerPersonalDetail> create(@Valid @RequestBody CustomerPersonalDetail customerpersonalDetail) throws URISyntaxException, SQLException {
        Proposal proposalCreated = proposalService.createInitProposal(customerpersonalDetail);

        if(proposalCreated != null){
            String uri = String.format("http://localhost:8080/proposal/2/%s/address",proposalCreated.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(new URI(uri));
            return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerpersonalDetail);
        }
    }

    @RequestMapping("/{proposalStepId}/{proposalId}/address")
    public ResponseEntity<CustomerAddress> createCustomerAddress(@Valid @RequestBody CustomerAddress customerAddress,
                                                                 @PathVariable("proposalStepId") Integer proposalStepId,
                                                                 @PathVariable("proposalId") String proposalId)
            throws SQLException, URISyntaxException {

        ProposalStep proposalStep = ProposalStep.values()[proposalStepId-1];

        return proposalService.createProposalAddress(customerAddress, proposalId, proposalStep);
    }

    @RequestMapping("/{proposalStepId}/{proposalId}/document")
    public ResponseEntity<CustomerAddress> createCustomerDocumentImage(@Valid @RequestBody CustomerAddress customerAddress,
                                                                       @PathVariable("proposalStepId") Integer proposalStepId,
                                                                       @PathVariable("proposalId") String proposalId) throws SQLException, URISyntaxException {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
