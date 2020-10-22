package zup.digitalBank.models;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import zup.digitalBank.validation.Age;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "CustomerPersonalDetail")
@Age
public class CustomerPersonalDetail {
    @Id
    private UUID id;

    @NotBlank(message = "Customer Name cannot be blank")
    private String name;

    @NotBlank(message = "Customer Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Customer Email cannot be blank")
    @Email
    private String email;

    @NotNull(message = "Customer Birth Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthDate;

    @NotBlank(message = "Customer CPF cannot be blank")
    @CPF
    private String cpf;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
