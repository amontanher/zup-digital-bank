package zup.digitalBank.repositories;

import org.springframework.stereotype.Repository;
import zup.digitalBank.database.DBFactory;
import zup.digitalBank.models.Customer;
import zup.digitalBank.models.Proposal;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.UUID;

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

    public Proposal getProposal(UUID proposalId) throws SQLException {
        Proposal proposal = new Proposal();

        final String sql = "SELECT * FROM Proposal WHERE Id = ?";

        try(Connection connection = DBFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            proposal.setId((UUID) rs.getObject("id"));
            proposal.setCustomerId((UUID) rs.getObject("customerId"));
            proposal.setProposalStep(rs.getInt("proposalStep"));
            proposal.setProposalStepDateConcluded(rs.getDate("proposalStepDateConcluded").toLocalDate());
            return proposal;
        }
    }
}
