package br.com.gian.desafioluizalabs.wishlist.repository;

import br.com.gian.desafioluizalabs.wishlist.model.WishlistItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<WishlistItem, String> {

    List<WishlistItem> findByUserId(String userId);

    Optional<WishlistItem> findByUserIdAndProductId(String userId, String productId);

    void deleteByUserIdAndProductId(String userId, String productId);
}