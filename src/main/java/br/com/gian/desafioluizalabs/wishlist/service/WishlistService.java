package br.com.gian.desafioluizalabs.wishlist.service;

import br.com.gian.desafioluizalabs.wishlist.model.WishlistItem;

import java.util.List;

public interface WishlistService {

    WishlistItem addProductToWishlist(String userId, String productId);

    void removeProductFromWishlist(String userId, String productId);

    List<WishlistItem> getWishlist(String userId);

    boolean isProductInWishlist(String userId, String productId);
}
