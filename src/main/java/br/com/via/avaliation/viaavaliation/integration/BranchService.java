package br.com.via.avaliation.viaavaliation.integration;

import br.com.via.avaliation.viaavaliation.dto.BranchDTO;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class BranchService implements InterfaceBranchService {

    RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<BranchDTO> getAllBranches() {
        return Arrays.stream(
                Objects.requireNonNull(
                        restTemplate.getForObject(
                                "http://localhost:1080/api/v1/branches", BranchDTO[].class))).toList();
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject("http://localhost:1080/api/v1/branches", BranchDTO[].class)))
                .filter(element -> Objects.equals(element.id(), id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
    }

    @Override
    public void validateIfBranchExists(Long id) {
        var exists = Arrays.stream(Objects.requireNonNull(restTemplate.getForObject("http://localhost:1080/api/v1/branches", BranchDTO[].class))).filter(
                element -> Objects.equals(element.id(), id)
        ).findFirst();
        if (exists.isEmpty()) {
            throw new ResourceNotFoundException("Branch not found");
        }
    }
}
