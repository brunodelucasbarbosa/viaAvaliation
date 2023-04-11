package br.com.via.avaliation.viaavaliation.integration;

import br.com.via.avaliation.viaavaliation.dto.BranchDTO;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BranchService implements InterfaceBranchService {

    private static final String URL_BRANCHES = "http://localhost:1080/api/v1/branches";

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @CircuitBreaker(name = "branchServiceCB")
    public List<BranchDTO> getAllBranches() {
        return Arrays.stream(
                Objects.requireNonNull(
                        restTemplate.getForObject(
                                URL_BRANCHES, BranchDTO[].class))).toList();
    }

    @Override
    @CircuitBreaker(name = "branchServiceCB")
    public BranchDTO getBranchById(Long id) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(URL_BRANCHES, BranchDTO[].class)))
                .filter(element -> Objects.equals(element.id(), id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
    }

    @Override
    @CircuitBreaker(name = "branchServiceCB")
    public void validateIfBranchExists(Long id) {
        var exists = Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(URL_BRANCHES, BranchDTO[].class))).filter(
                element -> Objects.equals(element.id(), id)
        ).findFirst();
        if (exists.isEmpty()) {
            throw new ResourceNotFoundException("Branch not found");
        }
    }
}
