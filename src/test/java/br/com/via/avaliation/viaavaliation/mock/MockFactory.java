package br.com.via.avaliation.viaavaliation.mock;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdateRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.ContractType;
import br.com.via.avaliation.viaavaliation.entity.Seller;

import java.time.LocalDate;

public class MockFactory {

    public Seller mockEntity() {
        return new Seller(1L,
                "98767367-OUT",
                "João Testinho",
                LocalDate.now(),
                "999.999.999-54",
                "email@email.com",
                ContractType.CLT);

    }

    public SellerDTO mockSellerDTO() {
        return new SellerDTO(1L,
                "98767367-OUT",
                "João Testinho",
                LocalDate.now(),
                "999.999.999-54",
                "email@email.com",
                ContractType.CLT,
                null);
    }

    public SellerRequest mockSellerRequest() {
        return new SellerRequest("João Testinho",
                LocalDate.now().toString(),
                "999.999.999-54",
                "email@email.com",
                ContractType.CLT);
    }

    public SellerUpdateRequest mockSellerUpdateRequest() {
        var seller = new SellerUpdateRequest();
        seller.setName("João Testinho");
        seller.setBirthdate(LocalDate.now().toString());
        return seller;
    }
}