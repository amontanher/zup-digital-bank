package zup.digitalBank.models;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import zup.digitalBank.validation.Age;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Age
public class CustomerPersonalDetail {
    @NotNull(message = "Customer Name cannot be null")
    private String name;

    @NotNull(message = "Customer Surname cannot be null")
    private String surname;

    @NotNull(message = "Customer Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "Customer Birth Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthDate;

    @NotNull(message = "Customer CPF cannot be null")
    @CPF
    private String cpf;

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
