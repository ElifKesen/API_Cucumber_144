Feature: Restful Booker API Testleri

  Scenario: POST isteği ile yeni bir booking oluştur
    Given Kullanici yeni bir booking olusturur
    Then Status kodunun 200 oldugunu ve booking ID'nin geldigini dogrular
