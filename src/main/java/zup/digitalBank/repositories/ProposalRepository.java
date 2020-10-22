package zup.digitalBank.repositories;

import org.springframework.stereotype.Repository;
import zup.digitalBank.database.DBFactory;
import zup.digitalBank.models.Customer;
import zup.digitalBank.models.Proposal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ProposalRepository{
    public void createProposal(Proposal proposal) {
        final String sql = "INSERT INTO Proposal" +
                "  (id, CustomerId, ProposalStep, ProposalStepDateConcluded) VALUES " +
                " (?, ?, ?, ?);";

        try (Connection connection = DBFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, proposal.getId());
            preparedStatement.setObject(2, proposal.getCustomerId());
            preparedStatement.setObject(3, proposal.getProposalStep());
            preparedStatement.setObject(4, proposal.getProposalStepDateConcluded());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBFactory.printSQLException(e);
        }
    }
}
