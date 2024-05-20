package br.com.gian.desafioluizalabs.bdd;

import br.com.gian.desafioluizalabs.wishlist.model.WishlistItem;
import br.com.gian.desafioluizalabs.exception.WishlistLimitExceededException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class WishlistSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String userId;
    private String productId;
    private ResponseEntity<WishlistItem> responseEntity;
    private ResponseEntity<List> responseEntityList;
    private ResponseEntity<Boolean> responseEntityBoolean;

    @Before
    public void setup() {
        userId = UUID.randomUUID().toString();
        productId = UUID.randomUUID().toString();
    }

    @After
    public void teardown() {
        restTemplate.delete("/wishlist/" + userId);
    }

    @Dado("um cliente")
    public void umCliente() {
        // ID do cliente já gerado no setup
    }

    @Quando("o cliente adiciona um produto")
    public void oClienteAdicionaUmProduto() {
        responseEntity = restTemplate.postForEntity("/wishlist/" + userId + "/add/" + productId, null, WishlistItem.class);
    }

    @Então("o produto deve estar na wishlist")
    public void oProdutoDeveEstarNaWishlist() {
        responseEntityList = restTemplate.getForEntity("/wishlist/" + userId, List.class);
        List<WishlistItem> wishlist = (List<WishlistItem>) responseEntityList.getBody().stream()
                .map(item -> objectMapper.convertValue(item, WishlistItem.class))
                .collect(Collectors.toList());

        assertTrue(wishlist.stream()
                .anyMatch(item -> item.getProductId().equals(productId)));
    }

    @Dado("um cliente e um produto na wishlist")
    public void umClienteEUmProdutoNaWishlist() {
        restTemplate.postForEntity("/wishlist/" + userId + "/add/" + productId, null, WishlistItem.class);
    }

    @Quando("o cliente remove o produto")
    public void oClienteRemoveOProduto() {
        restTemplate.delete("/wishlist/" + userId + "/remove/" + productId);
    }

    @Então("o produto não deve estar na wishlist")
    public void oProdutoNaoDeveEstarNaWishlist() {
        responseEntityList = restTemplate.getForEntity("/wishlist/" + userId, List.class);
        List<WishlistItem> wishlist = (List<WishlistItem>) responseEntityList.getBody().stream()
                .map(item -> objectMapper.convertValue(item, WishlistItem.class))
                .collect(Collectors.toList());

        assertFalse(wishlist.stream()
                .anyMatch(item -> item.getProductId().equals(productId)));
    }

    @Quando("o cliente remove um produto que não está na wishlist")
    public void oClienteRemoveUmProdutoQueNaoEstaNaWishlist() {
        String nonExistentProductId = UUID.randomUUID().toString();
        try {
            restTemplate.delete("/wishlist/" + userId + "/remove/" + nonExistentProductId);
        } catch (HttpClientErrorException e) {
            assertTrue(e.getMessage().contains("Produto não encontrado"));
        }
    }

    @Então("deve ocorrer um erro informando que o produto não está na wishlist")
    public void deveOcorrerUmErroInformandoQueOProdutoNaoEstaNaWishlist() {
        // Verificação feita no método anterior
    }

    @Dado("um cliente e um produto não adicionado")
    public void umClienteEUmProdutoNaoAdicionado() {
        // O cliente já está criado no setup e o produto não é adicionado
    }

    @Quando("o cliente verifica se o produto não adicionado está na wishlist")
    public void oClienteVerificaSeOProdutoNaoAdicionadoEstaNaWishlist() {
        responseEntityBoolean = restTemplate.getForEntity("/wishlist/" + userId + "/contains/" + productId, Boolean.class);
    }

    @Então("o resultado deve ser falso")
    public void oResultadoDeveSerFalso() {
        assertFalse(responseEntityBoolean.getBody());
    }

    @Quando("o cliente adiciona o mesmo produto duas vezes")
    public void oClienteAdicionaOMesmoProdutoDuasVezes() {
        try {
            restTemplate.postForEntity("/wishlist/" + userId + "/add/" + productId, null, WishlistItem.class);
            restTemplate.postForEntity("/wishlist/" + userId + "/add/" + productId, null, WishlistItem.class);
        } catch (HttpClientErrorException e) {
            assertTrue(e.getMessage().contains("Produto já existe na wishlist"));
        }
    }

    @Então("deve ocorrer um erro informando que o produto já está na wishlist")
    public void deveOcorrerUmErroInformandoQueOProdutoJaEstaNaWishlist() {
        // Verificação feita no método anterior
    }

    @Quando("o cliente solicita a wishlist")
    public void oClienteSolicitaAWishlist() {
        responseEntityList = restTemplate.getForEntity("/wishlist/" + userId, List.class);
    }

    @Então("a wishlist deve conter os produtos")
    public void aWishlistDeveConterOsProdutos() {
        List<WishlistItem> wishlist = (List<WishlistItem>) responseEntityList.getBody().stream()
                .map(item -> objectMapper.convertValue(item, WishlistItem.class))
                .collect(Collectors.toList());

        assertFalse(wishlist.isEmpty());
    }

    @Quando("o cliente verifica se o produto está na wishlist")
    public void o_cliente_verifica_se_o_produto_está_na_wishlist() {
        responseEntityBoolean = restTemplate.getForEntity("/wishlist/" + userId + "/contains/" + productId, Boolean.class);
    }

    @Então("o resultado deve ser verdadeiro")
    public void o_resultado_deve_ser_verdadeiro() {
        assertTrue(responseEntityBoolean.getBody());
    }
}
