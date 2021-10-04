Feature: Frontend Task - Dropdown/Filters
  This feature deals display and filters of the application

  Scenario: Select the dropdown value to hundred
    Given I launch the application
    And I select the Showrows drop dwon value to 100
    Then I should see the 100 rows

  Scenario: Filter records as per different criteria
    Given I launch the application
    Then Click on 'Filters' button
    And Filter records by MarketCap 1B  10B and Price 101  1000
    Then Check records displayed on page are correct as per the filter applied