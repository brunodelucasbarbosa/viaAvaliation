package br.com.via.avaliation.viaavaliation.controller.request;

import br.com.via.avaliation.viaavaliation.entity.ContractType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;


public class SellerCreateRequest {

    @NotEmpty(message = "Name is required")
    private String name;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Birthdate must be in the format yyyy-mm-dd")
    //@NotNull(message = "Birthdate is required and must be in the format yyyy-mm-dd")
    private String birthdate;

    @CPF(message = "CPF must be valid and in the format 000.000.000-00")
    @NotEmpty(message = "CPF is required")
    private String cpf;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Contract type is required and must be one of the following: CLT, PJ, OUTSORCING")
    @JsonProperty("contract_type")
    private ContractType contractType;

    @NotNull(message = "Branch id is required")
    private Long branch_id;

    public Long getBranch_id() {
        return this.branch_id;
    }

    public SellerCreateRequest(String name, String birthdate, String cpf, String email, ContractType contractType, Long branch_id) {
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.email = email;
        this.contractType = contractType;
        this.branch_id = branch_id;
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

    public String getEmail() {
        return email;
    }

    public ContractType getContractType() {
        return contractType;
    }

    @Override
    public String toString() {
        return "SellerRequest{" +
                "name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", contractType=" + contractType +
                ", branch_id=" + branch_id +
                '}';
    }
}
