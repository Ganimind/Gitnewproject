#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Google maps Feature

  @tag1
  Scenario: Successful add place
    Given user have api headers information and Payload
    When user calls the Addplaceapi using Post http method 
    Then user validate the Response
    
    @tag2
    Scenario: Successful update place
    Given user have update api headers information and Payload
    When user calls the Updateplaceapi using Put http method 
    Then user validate the updateapiResponse
    
    @tag3
    Scenario: Successful get place
    Given user have get api information 
    When user calls the Getplaceapi using Get http method 
    Then user validate the getapiResponse
    
    @tag4
    Scenario: Successful delete place
    Given user have delete api headers information and Payload
    When user calls the Deleteplaceapi using Delete http method 
    Then user validate the deleteapiResponse
    
    
    
    

