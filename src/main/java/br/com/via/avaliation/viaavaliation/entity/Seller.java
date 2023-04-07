package br.com.via.avaliation.viaavaliation.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sellers")
public class Seller implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 12)
    private String register;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private LocalDate birthdate;

    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private ContractType contractType;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Seller(String register, String name, LocalDate birthdate, String cpf, String email,
                  ContractType contractType, Branch branch) {
        this.register = register;
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.email = email;
        this.contractType = contractType;
        this.branch = branch;
    }

    public Seller() {

    }

    public Seller(String register, String name, LocalDate birthdate, String cpf, String email, ContractType contractType) {
        this.register = register;
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.email = email;
        this.contractType = contractType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return getId().equals(seller.getId()) && getRegister().equals(seller.getRegister()) && getName().equals(seller.getName()) && getBirthdate().equals(seller.getBirthdate()) && getCpf().equals(seller.getCpf()) && getEmail().equals(seller.getEmail()) && getContractType() == seller.getContractType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRegister(), getName(), getBirthdate(), getCpf(), getEmail(), getContractType());
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", register='" + register + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", contractType=" + contractType +
                ", branch=" + branch +
                '}';
    }
}
