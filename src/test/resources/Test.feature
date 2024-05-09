
@TestSuit01
Feature: Validar login usuario
  Se validará el login en la web SwagLabs

  @LoginOK
  Scenario: Validar login correcto
    Given Navega a la weg de SwagLabs
    When Introduce credenciales
    And Presiona el boton Login
    Then Valida que se entra en la pagina home

  @OrdenarPorPrecio
  Scenario: Ordena los productos por precio
    Given Navega a la weg de SwagLabs
    When Introduce credenciales
    And Presiona el boton Login
    When Valida que se entra en la pagina home
    And Ordena productos por precio

