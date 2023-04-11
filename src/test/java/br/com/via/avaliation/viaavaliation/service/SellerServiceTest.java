package br.com.via.avaliation.viaavaliation.service;

import br.com.via.avaliation.viaavaliation.controller.request.UpdateRequest.SellerUpdateRequest;
import br.com.via.avaliation.viaavaliation.entity.Seller;
import br.com.via.avaliation.viaavaliation.exception.ResourceNotFoundException;
import br.com.via.avaliation.viaavaliation.exception.SellerExistsException;
import br.com.via.avaliation.viaavaliation.integration.BranchService;
import br.com.via.avaliation.viaavaliation.integration.InterfaceBranchService;
import br.com.via.avaliation.viaavaliation.mock.MockFactory;
import br.com.via.avaliation.viaavaliation.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    MockFactory mockFactory;

    @InjectMocks
    private InterfaceSellerService sellerService = new SellerService();


    @Mock
    SellerRepository sellerRepository;
    @Mock
    InterfaceBranchService branchService;

    @BeforeEach
    void setUp() {
        mockFactory = new MockFactory();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        var seller = mockFactory.mockEntity();
        var sellerDTO = mockFactory.mockSellerDTO();
        var sellerRequest = mockFactory.mockSellerRequest();


        when(sellerRepository.save(any(Seller.class))).thenReturn(seller);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            return arg0;
        }).when(branchService).validateIfBranchExists(any(Long.class));
        var result = sellerService.create(sellerRequest);
        assertNotNull(result);
        assertEquals(sellerDTO.register(),result.register());
    }

    @Test
    void verifyIfExists() {
        var existingSeller = mockFactory.mockEntity();
        when(sellerRepository.findByEmail(existingSeller.getEmail())).thenReturn(existingSeller);

        var newSeller = mockFactory.mockEntity();

        assertThrows(SellerExistsException.class, () -> {
            sellerService.verifyIfExists(newSeller);
        });
    }

    @Test
    void findByParam() {
        var seller = mockFactory.mockEntity();
        var param = seller.getRegister();

        when(sellerRepository.findByRegister(param)).thenReturn(seller);

        var result = sellerService.findByParam(param);

        assertNotNull(result);
        assertEquals(result.register(), param);
        verify(sellerRepository, times(1)).findByRegister(param);
    }

    @Test
    void update() {
        String param = "1";
        var existingSeller = mockFactory.mockSellerDTO();
        var updatedSeller = mockFactory.mockSellerUpdateRequest();
        when(sellerRepository.findById(Long.parseLong(param))).thenReturn(existingSeller.toEntity());
        when(sellerRepository.save(any(Seller.class))).thenReturn(existingSeller.toEntity());

        var result = sellerService.update(updatedSeller, param);

        assertEquals(existingSeller.id(), result.id());
        assertEquals(updatedSeller.getName(), result.name());
        assertEquals(LocalDate.parse(updatedSeller.getBirthdate()), result.birthdate());
    }

    @Test
    public void testUpdateSellerNotFound() {
        String param = "1";
        SellerUpdateRequest updatedSeller = mockFactory.mockSellerUpdateRequest();
        when(sellerRepository.findById(Long.parseLong(param))).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            sellerService.update(updatedSeller, param);
        });
    }

    @Test
    void updatePartial() {
    }

    @Test
    void delete() {
        var sellerToDelete = mockFactory.mockEntity();
        when(sellerRepository.findByRegister("param")).thenReturn(sellerToDelete);

        sellerService.delete("param");

        verify(sellerRepository, times(1)).delete(sellerToDelete);
    }
}