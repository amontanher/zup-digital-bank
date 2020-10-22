package zup.digitalBank.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="Proposal")
public class Proposal {

    @Id
    private UUID id;

    private UUID customerId;

    private Integer proposalStep;

    private LocalDate proposalStepDateConcluded;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public Integer getProposalStep() {
        return proposalStep;
    }

    public void setProposalStep(Integer proposalStep) {
        this.proposalStep = proposalStep;
    }

    public LocalDate getProposalStepDateConcluded() {
        return proposalStepDateConcluded;
    }

    public void setProposalStepDateConcluded(LocalDate proposalStepDateConcluded) {
        this.proposalStepDateConcluded = proposalStepDateConcluded;
    }
}
