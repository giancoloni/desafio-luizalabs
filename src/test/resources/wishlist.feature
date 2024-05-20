Feature: Gestão da Wishlist

  Scenario: Adicionar produto à wishlist
    Given um cliente
    When o cliente adiciona um produto
    Then o produto deve estar na wishlist

  Scenario: Remover produto da wishlist
    Given um cliente e um produto na wishlist
    When o cliente remove o produto
    Then o produto não deve estar na wishlist

  Scenario: Obter wishlist
    Given um cliente e um produto na wishlist
    When o cliente solicita a wishlist
    Then a wishlist deve conter os produtos

  Scenario: Verificar se produto está na wishlist
    Given um cliente e um produto na wishlist
    When o cliente verifica se o produto está na wishlist
    Then o resultado deve ser verdadeiro

  Scenario: Remover um produto que não está na wishlist
    Given um cliente
    When o cliente remove um produto que não está na wishlist
    Then deve ocorrer um erro informando que o produto não está na wishlist

  Scenario: Verificar se um produto não está na wishlist
    Given um cliente e um produto não adicionado
    When o cliente verifica se o produto não adicionado está na wishlist
    Then o resultado deve ser falso

  Scenario: Adicionar o mesmo produto duas vezes
    Given um cliente
    When o cliente adiciona o mesmo produto duas vezes
    Then deve ocorrer um erro informando que o produto já está na wishlist
