package br.com.via.avaliation.viaavaliation.repository;

import br.com.via.avaliation.viaavaliation.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch findByCnpj(String cnpj);
}
