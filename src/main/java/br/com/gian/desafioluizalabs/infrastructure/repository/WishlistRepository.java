package br.com.gian.desafioluizalabs.infrastructure.repository;

import br.com.gian.desafioluizalabs.adapters.gateway.WishlistGateway;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WishlistRepository implements WishlistGateway {

    @Autowired
    private SpringDataWishlistRepository springDataWishlistRepository;

    @Override
    public WishlistItem save(WishlistItem item) {
        return springDataWishlistRepository.save(item);
    }

    @Override
    public void deleteByUserIdAndProductId(String userId, String productId) {
        springDataWishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<WishlistItem> findByUserId(String userId) {
        return springDataWishlistRepository.findByUserId(userId);
    }

    @Override
    public Optional<WishlistItem> findByUserIdAndProductId(String userId, String productId) {
        return springDataWishlistRepository.findByUserIdAndProductId(userId, productId);
    }
}