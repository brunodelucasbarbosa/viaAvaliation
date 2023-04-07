package br.com.via.avaliation.viaavaliation.dto;

import br.com.via.avaliation.viaavaliation.entity.Branch;
import br.com.via.avaliation.viaavaliation.entity.ContractType;

import java.io.Serializable;
import java.time.LocalDate;

public class SellerDTO implements Serializable {

    private final Long id;
    private final String register;
    private final String name;
    private final LocalDate birthdate;
    private final String cpf;
    private final String email;
    private final ContractType contractType;
    private final Branch branch;

    public SellerDTO(Long id, String register, String name, LocalDate birthdate, String cpf, String email,
                     ContractType contractType, Branch branch) {
        this.id = id;
        this.register = register;
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.email = email;
        this.contractType = contractType;
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public String getRegister() {
        return register;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
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

    public Branch getBranch() {
        return branch;
    }
}
