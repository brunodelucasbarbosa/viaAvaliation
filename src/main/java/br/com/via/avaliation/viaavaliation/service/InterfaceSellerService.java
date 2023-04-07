package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.Seller;

public interface InterfaceSellerService {

    Seller create(SellerRequest seller);
    void verifyIfExists(Seller seller);
}
