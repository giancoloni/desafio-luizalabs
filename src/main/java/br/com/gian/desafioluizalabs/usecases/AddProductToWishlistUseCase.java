package br.com.gian.desafioluizalabs.usecases;

import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddProductToWishlistUseCase {

    @Autowired
    private WishlistService wishlistService;

    public WishlistItem execute(String userId, String productId) {
        return wishlistService.addProductToWishlist(userId, productId);
    }
}
