package br.com.via.avaliation.viaavaliation.mock;

import br.com.via.avaliation.viaavaliation.controller.request.SellerCreateRequest;
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
                1L, null);
    }

    public SellerCreateRequest mockSellerRequest() {
        return new SellerCreateRequest("João Testinho",
                LocalDate.now().toString(),
                "721.842.490-25",
                "em231ail@email.com",
                ContractType.CLT,
                1L);
    }

    public SellerUpdateRequest mockSellerUpdateRequest() {
        var seller = new SellerUpdateRequest();
        seller.setName("João Testinho");
        seller.setBirthdate(LocalDate.now().toString());
        return seller;
    }
}