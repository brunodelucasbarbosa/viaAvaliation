package br.com.via.avaliation.viaavaliation.controller;

import br.com.via.avaliation.viaavaliation.controller.request.SellerRequest;
import br.com.via.avaliation.viaavaliation.dto.SellerDTO;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
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

    @GetMapping("/{field}/{param}")
    @ResponseStatus(HttpStatus.OK)
    public SellerDTO findByParam(@PathVariable String field, @PathVariable String param) {
        if (field.equals("register")) {
            return sellerService.findByRegister(param);
        } else if (field.equals("email")) {
            return sellerService.findByEmail(param);
        } else if (field.equals("cpf")) {
            return sellerService.findByCpf(param);
        } else if (field.equals("id")) {
            return sellerService.findById(Long.parseLong(param));
        } else {
            throw new ResourceNotFoundException("Seller not found");
        }
    }

}
