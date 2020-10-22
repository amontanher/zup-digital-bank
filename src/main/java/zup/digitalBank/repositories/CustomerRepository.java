package zup.digitalBank.repositories;

import org.springframework.stereotype.Repository;
import zup.digitalBank.database.DBFactory;
import zup.digitalBank.models.Customer;
import zup.digitalBank.models.CustomerAddress;
import zup.digitalBank.models.CustomerPersonalDetail;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class CustomerRepository{
    public void createCustomerPersonalDetail(CustomerPersonalDetail customerPersonalDetail) throws SQLException {
        final String sql = "INSERT INTO CustomerPersonalDetail" +
                "  (id, name, surname, email, birthdate, cpf) VALUES " +
                " (?, ?, ?, ?, ?, ?);";

        try (Connection connection = DBFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, customerPersonalDetail.getId());
            preparedStatement.setString(2, customerPersonalDetail.getName());
            preparedStatement.setString(3, customerPersonalDetail.getSurname());
            preparedStatement.setString(4, customerPersonalDetail.getEmail());
            preparedStatement.setDate(5, java.sql.Date.valueOf(customerPersonalDetail.getBirthDate()));
            preparedStatement.setString(6, customerPersonalDetail.getCpf());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBFactory.printSQLException(e);
        }
    }

    public void createCustomerAddress(CustomerAddress customerAddress) throws SQLException {
        final String sql = "INSERT INTO CustomerAddress" +
                "  (id, cep, neighborhood, complement, city, state) VALUES " +
                " (?, ?, ?, ?, ?, ?);";

        try (Connection connection = DBFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, customerAddress.getId());
            preparedStatement.setString(2, customerAddress.getCep());
            preparedStatement.setString(3, customerAddress.getNeighborhood());
            preparedStatement.setString(4, customerAddress.getComplement());
            preparedStatement.setString(5, customerAddress.getCity());
            preparedStatement.setString(6, customerAddress.getState());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBFactory.printSQLException(e);
        }
    }

    public void createCustomer(Customer customer) {
        final String sql = "INSERT INTO Customer" +
                "  (id, CustomerPersonalDetail, CustomerAddress) VALUES " +
                " (?, ?, ?);";

        try (Connection connection = DBFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, customer.getId());
            preparedStatement.setObject(2, customer.getCustomerPersonalDetailId());
            preparedStatement.setObject(3, customer.getCustomerAddressId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBFactory.printSQLException(e);
        }
    }

    public void updateCustomer(Customer customer){
        final String sql = "UPDATE Customer SET CustomerPersonalDetail = ?, CustomerAddress = ? WHERE id = ? ";

        try (Connection connection = DBFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, customer.getCustomerPersonalDetailId());
            preparedStatement.setObject(2, customer.getCustomerAddressId());
            preparedStatement.setObject(3, customer.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBFactory.printSQLException(e);
        }
    }
}
