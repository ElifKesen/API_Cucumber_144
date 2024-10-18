Feature: Restful Booker API Testleri

  Scenario: GET isteği ile belirli bir booking kaydını getir
    Given Kullanici ID'si 10 olan booking kaydini getirir
    Then Status kodunun 200 oldugunu ve firstname'in "Susan" oldugunu dogrular