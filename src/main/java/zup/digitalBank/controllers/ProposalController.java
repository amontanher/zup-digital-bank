package zup.digitalBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zup.digitalBank.models.CustomerAddress;
import zup.digitalBank.models.CustomerPersonalDetail;
import zup.digitalBank.services.ProposalService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @PostMapping("/init")
    public ResponseEntity<CustomerPersonalDetail> create(@Valid @RequestBody CustomerPersonalDetail customerpersonalDetail) throws URISyntaxException {
        boolean proposalCreated = proposalService.createInitProposal(customerpersonalDetail);

        if(proposalCreated){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(new URI("http://localhost:8080/proposal/init"));
            return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerpersonalDetail);
        }
    }

    @PostMapping("/ale")
    public ResponseEntity<CustomerAddress> createAle(@Valid @RequestBody CustomerAddress customerAddress){
        return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
