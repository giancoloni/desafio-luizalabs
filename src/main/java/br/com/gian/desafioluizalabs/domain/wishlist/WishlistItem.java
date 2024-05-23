package br.com.gian.desafioluizalabs.domain.wishlist;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "wishlist")
public class WishlistItem {
    @Id
    private String id;
    private String userId;
    private String productId;

}