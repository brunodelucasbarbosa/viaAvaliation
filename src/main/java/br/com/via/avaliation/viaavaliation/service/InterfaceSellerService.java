package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdatePartialRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdateRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfaceSellerService {

    SellerDTO create(SellerRequest seller);
    void verifyIfExists(Seller seller);
    SellerDTO findByParam(String param);
    SellerDTO update(SellerUpdateRequest seller, String param);
    SellerDTO updatePartial(SellerUpdatePartialRequest seller, String param);
    void delete(String param);

    SellerDTO linkToBranch(Object param, Long branchId);

    Long getCountSellers();

    Page<SellerDTO> findAll(Pageable pageable);

}
