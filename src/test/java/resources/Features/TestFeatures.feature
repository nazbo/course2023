Feature: Common application Feature

  Scenario: Open the browser and get to the home page
    Given the browser is open
    When homepage is entered
    Then the user is on the correct homepage
    And the homepage contains 3 slides

  Scenario: Go to the my account home page
    Given the browser is open and we are on the home page
    When the user follows the My account hyper link
    Then the user is on the login/register page

  #Scenario: Use the correct id and password to successfully sing in
