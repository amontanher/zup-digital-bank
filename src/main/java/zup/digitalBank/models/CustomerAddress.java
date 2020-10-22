package zup.digitalBank.models;

import zup.digitalBank.validation.CEP;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name="CustomerAddress")
public class CustomerAddress {

    @Id
    private UUID id;

    @NotBlank(message = "Customer CEP cannot be blank")
    @CEP
    private String cep;

    @NotBlank(message = "Customer Street cannot be blank")
    @Size(min = 1, max = 20)
    private String street;

    @NotBlank(message = "Customer Neighborhood cannot be blank")
    @Size(min = 1, max = 20)
    private String neighborhood;

    @NotBlank(message = "Customer Complement cannot be blank")
    @Size(min = 1, max = 20)
    private String complement;

    @NotBlank(message = "Customer City cannot be blank")
    @Size(min = 1, max = 20)
    private String city;

    @NotBlank(message = "Customer State cannot be blank. Ex.: SP")
    @Size(min = 2, max = 2)
    private String state;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
