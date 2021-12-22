#Author: ahmed.davila@generalsoftwareinc.com
#Keywords Summary : Driver UHs automated Testing Examples
#Feature: Add Driver.
#Scenario:
#------------------------------------------
#Mobile numbers and email addresses should be associated with only one Driver.
#A user registered in the system as Customer and/or Fleet owner can also be registered as Driver.
#A user registered in the system as Admin or Dispatcher cannot be registered as Driver.
#The "Add Driver" button is not shown if the user who is accessing the "Drivers List" view is the GoHeavy Admin, and he comes from the "Fleet Owners List" view.
#The minimum value allowed for the years of experience will be 3 years.
#------------------------------------------
#Given: Some precondition step
#------------------------------------------
#1. The GoHeavy Admin / Fleet Owner must be logged in the system.
#2. The GoHeavy Admin / Fleet Owner is on the "Drivers List" view.
#------------------------------------------
#When: Some key actions
#Then: To observe outcomes or validation
#------------------------------------------
#1. Clicks on the "Add Driver" button.
#------------------------------------------
#And,But:
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: Given Any user is logged
#Sample Feature Definition Template

Feature: Driver 2.3
  As a: GoHeavy Administrator / Fleet Owner
  I Want To: approve a driver documentation and vehicle
  So That: a new Driver is activated in the system.

  Background:
    Given Any "GoHeavy Admin / Fleet Owner" is logged

  Scenario: Add new Driver
    Given The user is in "Drivers List" view.
    And User clicks on "Add Driver" button.
    And The system opens the "Add Driver" view.
    When The user inserts valid driver data AND clicks on the "Done" button.
    And The user search the new Driver in "On-boarding" status.
    And User clicks on Vehicle button.
    And System returns to the "Vehicles & Insurance List" view.
    And User clicks on "Add Vehicle" button.
    And User hover overs a Vehicle document image component with an image loaded
    And The user inserts valid vehicle data AND clicks on the Done button.
    And The user is in "Drivers List" view.
    And The user search the new Driver in "On-boarding" status.
    Then User clicks on Documents button.
    And The system displays the documents view.
    And User clicks on "Approve" button.
    And The user search the new Driver in "Clear" status.
    And User clicks on "Edit Driver" button.
    And The system opens the "Edit Driver" view.
    And The user inserts valid driver data AND clicks on the "Update" button.
    And The user search the new Driver in "GoHeavy Ready" status.