package br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public class SellerUpdateRequest {

    @NotEmpty(message = "Name is required")
    private String name;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Birthdate must be in the format yyyy-mm-dd")
    private String birthdate;

    @CPF(message = "CPF must be valid and in the format 000.000.000-00")
    @NotEmpty(message = "CPF is required")
    private String cpf;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    public SellerUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
