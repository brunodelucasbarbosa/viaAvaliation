package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.SellerCreateRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdatePartialRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdateRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.entity.Seller;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
import br.com.via.avaliation.viaavaliation.exception.SellerExistsException;
import br.com.via.avaliation.viaavaliation.integration.InterfaceBranchService;
import br.com.via.avaliation.viaavaliation.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class SellerService implements InterfaceSellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    InterfaceBranchService branchService;

    private final Logger logger = LoggerFactory.getLogger(SellerService.class.getName());

    @Override
    public SellerDTO create(SellerCreateRequest seller) {
        var register = generateRegister();
        while (true) {
            var registerExists = sellerRepository.findByRegister(register);
            if (registerExists == null) {
                break;
            }
        }
        var birthdate = LocalDate.parse(seller.getBirthdate());
        var entity = new Seller(
                register,
                seller.getName(),
                birthdate,
                seller.getCpf(),
                seller.getEmail(),
                seller.getContractType(),
                seller.getBranch_id()
        );

        this.verifyIfExists(entity);
        branchService.validateIfBranchExists(seller.getBranch_id());
        logger.info("Seller created: " + entity);
        return toDTO(sellerRepository.save(entity));
    }

    @Override
    public void verifyIfExists(Seller seller) {
        var emailExists = sellerRepository.findByEmail(seller.getEmail());
        if (emailExists != null) {
            logger.error("Email already exists: " + seller.getEmail());
            throw new SellerExistsException("Email already exists");
        }
        var cpfExists = sellerRepository.findByCpf(seller.getCpf());
        if (cpfExists != null) {
            logger.error("CPF already exists: " + seller.getCpf());
            throw new SellerExistsException("CPF already exists");
        }
    }

    @Override
    public SellerDTO findByParam(String param) {
        var seller = new Seller();
        if (param.matches("\\d+")) {
            seller = this.sellerRepository.findById(Long.parseLong(param));
            if (seller != null) {
                return toDTO(seller);
            }
        }

        seller = this.sellerRepository.findByRegister(param);
        if (seller != null) { return toDTO(seller); }

        seller = this.sellerRepository.findByEmail(param);
        if (seller != null) { return toDTO(seller); }

        seller = this.sellerRepository.findByCpf(param);
        if (seller != null) { return toDTO(seller); }

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
        logger.info("Seller updated: " + sellerEntity);
        return toDTO(this.sellerRepository.save(sellerEntity));
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

        logger.info("Seller updated: " + sellerEntity);
        return toDTO(this.sellerRepository.save(sellerEntity));
    }

    @Override
    public void delete(String param) {
        var seller = this.findByParam(param).toEntity();
        logger.info("Seller deleted: " + seller);
        this.sellerRepository.delete(seller);
    }

    @Override
    public Long getCountSellers() {
        return this.sellerRepository.count();
    }

    @Override
    public Page<SellerDTO> findAll(Pageable pageable) {
        return this.sellerRepository.findAll(pageable).map(this::toDTO);
    }

    public String generateRegister() {
        String[] contractTypes = {"OUT", "CLT", "PJ"};

        Random random = new Random();
        String numbers = String.format("%08d", random.nextInt(100000000));
        String contractType = contractTypes[random.nextInt(contractTypes.length)];

        return numbers + "-" + contractType;
    }

    public SellerDTO toDTO(Seller seller) {
        var branch = branchService.getBranchById(seller.getBranchId());
        return new SellerDTO(
                seller.getId(),
                seller.getRegister(),
                seller.getName(),
                seller.getBirthdate(),
                seller.getCpf(),
                seller.getEmail(),
                seller.getContractType(),
                seller.getBranchId(), branch);
    }
}
