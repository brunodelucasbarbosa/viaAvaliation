package br.com.via.avaliation.viaavaliation.dto;

import br.com.via.avaliation.viaavaliation.entity.ContractType;
import br.com.via.avaliation.viaavaliation.entity.Seller;

import java.io.Serializable;
import java.time.LocalDate;

public record SellerDTO(Long id,
                        String register,
                        String name,
                        LocalDate birthdate,
                        String cpf,
                        String email,
                        ContractType contractType,
                        Long branchId,
                        BranchDTO branch) implements Serializable {

    public Seller toEntity() {
        return new Seller(this.id, this.register, this.name, this.birthdate, this.cpf, this.email, this.contractType);
    }
}
