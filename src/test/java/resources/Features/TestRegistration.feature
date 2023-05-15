Feature: Test Registration form

  Scenario Outline: From the registration page the user can register
    Given the browser is open and we are on the registration page
    When the userId: <usrId> and password: <passWd> are provided
    And the Registration Button is clicked
    Then A successful registration is completed

    Examples:
      | usrId | passWd           |
      | Mark  | sZvmGMWVZYY9GpX  |
#      | Jeff  | sZvmGMWVZYY9GpX |