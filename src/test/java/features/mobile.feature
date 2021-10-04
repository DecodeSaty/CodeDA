Feature: Mobile Task - Zoom
  This feature to check zoom application

  Scenario: Zoom Application feature check
    Given I launch the zooom app
    When  Click Join a meeting
    Then Join button is disabled

  Scenario: Zoom Application invalid meeting check
    Given I launch the zooom app
    When  Click Join a meeting
    When  Enter any 9 digits meeting id and Join button is enabled

  Scenario: Zoom Application invalid meeting check
    Given I launch the zooom app
    When  Click Join a meeting
    When  Enter any 9 digits meeting id and join the meeting
    Then Toggle on Turn off my video and Put the app in background
    Then launch to foreground Invalid meeting Id text in pop up