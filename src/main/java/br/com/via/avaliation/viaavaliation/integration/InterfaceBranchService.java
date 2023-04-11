package br.com.via.avaliation.viaavaliation.integration;

import br.com.via.avaliation.viaavaliation.dto.BranchDTO;

import java.util.List;

public interface InterfaceBranchService {

    List<BranchDTO> getAllBranches();
    BranchDTO getBranchById(Long id);
    void validateIfBranchExists(Long id);
}
