package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.Branch;
import br.com.via.avaliation.viaavaliation.entity.Seller;
import br.com.via.avaliation.viaavaliation.exception.SellerExistsException;
import br.com.via.avaliation.viaavaliation.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SellerService implements InterfaceSellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Seller create(SellerRequest seller) {
        var register = "98767367-OUT";
        var birthdate = LocalDate.parse(seller.getBirthdate());
        var entity = new Seller(
                register,
                seller.getName(),
                birthdate,
                seller.getCpf(),
                seller.getEmail(),
                seller.getContractType()
        );
        verifyIfExists(entity);
        return sellerRepository.save(entity);
    }

    @Override
    public void verifyIfExists(Seller seller) {
        var emailExists = sellerRepository.findByEmail((String) seller.getEmail());
        if (emailExists != null) {
            throw new SellerExistsException("Email already exists");
        }
        var cpfExists = sellerRepository.findByCpf((String) seller.getCpf());
        if (cpfExists != null) {
            throw new SellerExistsException("CPF already exists");
        }
        var registerExists = sellerRepository.findByRegister((String) seller.getRegister());
        if (registerExists != null) {
            throw new SellerExistsException("Register already exists");
        }
    }
}
