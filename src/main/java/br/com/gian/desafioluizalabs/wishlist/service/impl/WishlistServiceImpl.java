package br.com.gian.desafioluizalabs.wishlist.service.impl;

import br.com.gian.desafioluizalabs.exception.WishlistDuplicatedIdException;
import br.com.gian.desafioluizalabs.exception.WishlistLimitExceededException;
import br.com.gian.desafioluizalabs.exception.WishlistProductNotFoundException;
import br.com.gian.desafioluizalabs.wishlist.model.WishlistItem;
import br.com.gian.desafioluizalabs.wishlist.repository.WishlistRepository;
import br.com.gian.desafioluizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private static final int MAX_WISHLIST_ITEMS = 20;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public WishlistItem addProductToWishlist(String userId, String productId) {

        List<WishlistItem> wishlist = wishlistRepository.findByUserId(userId);

        if(wishlist.size() >= MAX_WISHLIST_ITEMS) {
            throw new WishlistLimitExceededException();
        }

        if(isProductInWishlist(userId, productId)) {
            throw new WishlistDuplicatedIdException();
        }

        WishlistItem item = new WishlistItem();
        item.setUserId(userId);
        item.setProductId(productId);
        return wishlistRepository.save(item);
    }

    @Override
    public void removeProductFromWishlist(String userId, String productId) {
        if(!isProductInWishlist(userId, productId)) {
            throw new WishlistProductNotFoundException(productId);
        }
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<WishlistItem> getWishlist(String userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @Override
    public boolean isProductInWishlist(String userId, String productId) {
        return wishlistRepository.findByUserIdAndProductId(userId, productId).isPresent();
    }
}
