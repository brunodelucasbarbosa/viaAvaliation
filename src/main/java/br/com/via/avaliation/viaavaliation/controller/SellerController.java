package br.com.via.avaliation.viaavaliation.controller;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdatePartialRequest;
import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdateRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SellerDTO create(@RequestBody @Valid SellerRequest request) {
        return sellerService.create(request);
    }

    @GetMapping("/{param}")
    @ResponseStatus(HttpStatus.OK)
    public SellerDTO findByParam(@PathVariable String param) {
        return sellerService.findByParam(param);
    }

    @PutMapping("/{param}")
    @ResponseStatus(HttpStatus.OK) // Update entidade inteira
    public SellerDTO update(@RequestBody @Valid SellerUpdateRequest request, @PathVariable String param) {
        return sellerService.update(request, param);
    }

    @PatchMapping("/{param}")
    @ResponseStatus(HttpStatus.OK) // Update parcial
    public SellerDTO updatePartial(@RequestBody @Valid SellerUpdatePartialRequest request, @PathVariable String param) {
        return sellerService.updatePartial(request, param);
    }

    @PatchMapping("/{sellerParam}/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    public SellerDTO linkBranch(@PathVariable String sellerParam, @PathVariable String branchId) {
        return sellerService.linkToBranch(sellerParam, Long.parseLong(branchId));
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String param) {
        sellerService.delete(param);
    }
}
