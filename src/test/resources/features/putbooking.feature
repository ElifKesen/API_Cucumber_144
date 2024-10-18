Feature: Restful Booker API Testleri
@ek
Scenario: PUT isteği ile mevcut bir booking kaydını güncelle
  Given Kullanici ID'si 10 olan booking kaydini gunceller
  Then Status kodunun 200 oldugunu ve booking kaydinin guncellendigini dogrular

