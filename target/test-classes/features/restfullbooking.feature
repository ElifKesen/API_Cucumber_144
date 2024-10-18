Feature: Restful Booker API Testleri

  Scenario: GET isteği ile tüm booking kayıtlarını getir
    Given Kullanici booking endpointine GET istegi yapar
    Then Status kodunun 200 oldugunu dogrular