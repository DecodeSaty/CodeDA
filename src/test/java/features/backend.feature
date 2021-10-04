Feature: Validating Crypto API's

  Scenario: Verify if Place is being Succesfully added using AddPlaceAPI

    Given The crytpo map API
    When Retrieve the ID of bitcoin , usd tether , and Ethereum
    Then Once you have retrieved the IDs of these currencies, convert them to Bolivian Boliviano

  Scenario: Verify the API functionality is working
    Given The crytpo info API Retrieve the Ethereum ID 1027 technical documentation website
    Then Confirm that the following logo URL is present
    Then Confirm that the technical_doc URL is present
    Then Confirm that the symbol of the currency is ETH
    Then Confirm the date added
    Then Confirm that the currency has the mineable tag associated with it

  Scenario Outline: Verify the API functionality is working

    Given The crytpoInfo  API Retrieve the first "<ID>"  currencies
    Then Check which currencies have the mineable tag associated with them "<ID>"
    Then Verify that the correct cryptocurrencies have been printed out "<ID>"

    Examples:
      | ID |
      | 1  |
      | 2 |
      | 3  |
      | 4  |
      | 5 |
      | 6  |
      | 7  |
      | 8 |
      | 9 |
      | 10  |