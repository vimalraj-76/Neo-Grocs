package io.grocery.backend.controller;

import io.grocery.backend.dto.CartItemDto;
import io.grocery.backend.dto.CartItemResponseDto;
import io.grocery.backend.dto.CustomerDetailsDto;
import io.grocery.backend.entity.User;
import io.grocery.backend.service.CartItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartItemController {
    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public void addToCart(@AuthenticationPrincipal User user, @RequestBody CartItemDto cartItemDTO) {
        cartItemService.addToCart(user, cartItemDTO);
    }

    @GetMapping("/get")
    public List<CartItemResponseDto> retrieveCart(@AuthenticationPrincipal User user) {
        return cartItemService.retrieveCart(user);
    }

    @GetMapping("/customer-details")
    public List<CustomerDetailsDto> getCustomerDetailsWithCartProducts() {
        return cartItemService.getCustomerDetailsWithCartProducts();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeCartItem(@AuthenticationPrincipal User user, @PathVariable Long id) {
        if (cartItemService.removeCartItem(user, id)) {
            return new ResponseEntity<>("Cart item removed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart item not found or couldn't be removed", HttpStatus.NOT_FOUND);
        }
    }

}
