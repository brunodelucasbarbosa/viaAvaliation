package br.com.via.avaliation.viaavaliation.repository;

import br.com.via.avaliation.viaavaliation.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

    Seller findByCpf(String cpf);
    Seller findByRegister(String register);
    Seller findByEmail(String email);
}
