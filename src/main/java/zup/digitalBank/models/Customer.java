package zup.digitalBank.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    private UUID id;

    private UUID customerPersonalDetailId;

    private UUID customerAddressId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerPersonalDetailId() {
        return customerPersonalDetailId;
    }

    public void setCustomerPersonalDetailId(UUID customerPersonalDetailId) {
        this.customerPersonalDetailId = customerPersonalDetailId;
    }

    public UUID getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(UUID customerAddressId) {
        this.customerAddressId = customerAddressId;
    }
}