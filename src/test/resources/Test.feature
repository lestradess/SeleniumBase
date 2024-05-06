@TestSuit
  Feature: Validar login usuario
    Se validará el login en la web SwagLabs

  @LoginOK
  Scenario: Validar login correcto
    Given Navega a la weg de SwagLabs
    When Introduce credenciales
    And Presiona el boton Login
    Then Valida que se entra en la página home
