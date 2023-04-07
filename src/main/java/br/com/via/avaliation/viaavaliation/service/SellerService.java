package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.Seller;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
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
    public SellerDTO create(SellerRequest seller) {
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
        return sellerRepository.save(entity).toDTO();
    }

    @Override
    public void verifyIfExists(Seller seller) {
        var emailExists = sellerRepository.findByEmail(seller.getEmail());
        if (emailExists != null) {
            throw new SellerExistsException("Email already exists");
        }
        var cpfExists = sellerRepository.findByCpf(seller.getCpf());
        if (cpfExists != null) {
            throw new SellerExistsException("CPF already exists");
        }
        var registerExists = sellerRepository.findByRegister(seller.getRegister());
        if (registerExists != null) {
            throw new SellerExistsException("Register already exists");
        }
    }

    @Override
    public SellerDTO findById(Long id) {
        var seller = this.sellerRepository.findById(id);
        if (seller == null) {
            throw new ResourceNotFoundException("Seller not found");
        }
        System.out.println(seller);
        System.out.println(seller.toDTO().toString());
        return seller.toDTO();
    }

    @Override
    public SellerDTO findByRegister(String register) {
        var seller = this.sellerRepository.findByRegister(register);
        if (seller == null) {
            throw new ResourceNotFoundException("Seller not found");
        }
        return seller.toDTO();
    }

    @Override
    public SellerDTO findByEmail(String email) {
        var seller = this.sellerRepository.findByEmail(email);
        if (seller == null) {
            throw new ResourceNotFoundException("Seller not found");
        }
        return seller.toDTO();
    }

    @Override
    public SellerDTO findByCpf(String cpf) {
        var seller = this.sellerRepository.findByCpf(cpf);
        if (seller == null) {
            throw new ResourceNotFoundException("Seller not found");
        }
        return seller.toDTO();
    }


}
