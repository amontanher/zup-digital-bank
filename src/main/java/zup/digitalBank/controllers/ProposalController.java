package zup.digitalBank.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zup.digitalBank.models.CustomerPersonalDetail;

import javax.validation.Valid;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @PostMapping("/init")
    public String create(@Valid @RequestBody CustomerPersonalDetail customerpersonalDetail) {
        return "OK";
    }
}
