package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdatePartialRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdateRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.Seller;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
import br.com.via.avaliation.viaavaliation.exception.SellerExistsException;
import br.com.via.avaliation.viaavaliation.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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
    public SellerDTO findByParam(String param) {
        var seller = this.sellerRepository.findById(Long.parseLong(param));
        if (seller != null) { return seller.toDTO(); }

        seller = this.sellerRepository.findByRegister(param);
        if (seller != null) { return seller.toDTO(); }

        seller = this.sellerRepository.findByEmail(param);
        if (seller != null) { return seller.toDTO(); }

        seller = this.sellerRepository.findByCpf(param);
        if (seller != null) { return seller.toDTO(); }

        throw new ResourceNotFoundException("Seller not found");
    }

    @Override
    public SellerDTO update(SellerUpdateRequest seller, String param) {
        var sellerEntity = this.findByParam(param).toEntity();
        sellerEntity.setName(seller.getName());
        if (seller.getBirthdate() != null) {
            sellerEntity.setBirthdate(LocalDate.parse(seller.getBirthdate()));
        } else {
            sellerEntity.setBirthdate(null);
        }
        sellerEntity.setCpf(seller.getCpf());
        sellerEntity.setEmail(seller.getEmail());
        return this.sellerRepository.save(sellerEntity).toDTO();
    }

    @Override
    public SellerDTO updatePartial(SellerUpdatePartialRequest seller, String param) {
        var sellerEntity = this.findByParam(param).toEntity();

        var name = Optional.ofNullable(seller.getName()).orElse(sellerEntity.getName());
        sellerEntity.setName(name);

        if (seller.getBirthdate() == null) {
            sellerEntity.setBirthdate(sellerEntity.getBirthdate());
        } else {
            sellerEntity.setBirthdate(LocalDate.parse(seller.getBirthdate()));
        }

        var cpf = Optional.ofNullable(seller.getCpf()).orElse(sellerEntity.getCpf());
        sellerEntity.setCpf(cpf);

        var email = Optional.ofNullable(seller.getEmail()).orElse(sellerEntity.getEmail());
        sellerEntity.setEmail(email);

        return this.sellerRepository.save(sellerEntity).toDTO();
    }

    @Override
    public void delete(String param) {
        var seller = this.findByParam(param).toEntity();
        this.sellerRepository.delete(seller);
    }

}
