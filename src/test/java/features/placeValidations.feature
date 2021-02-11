Feature: Validating Place API

  @AddPlace
  Scenario Outline: Verify if a place can be added successfully using AddPlaceAPI
    Given Add Place Payload is available with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API call is sucessful with status code 200
    And "status" in response body is "OK"
    And place_Id mapped to "<name>" is captured to be used in "GetPlaceAPI" with "Get" request

    Examples:
      | name    | language | address           |
      | Raffy   | Filipino | Melbourne         |
      | Fat     | English   | South             |


  @DeletePlace
  Scenario Outline: Verify if a place can be deleted successfully using DeletePlaceAPI
    Given Delete Place Payload is available for "<placeId>"
    When User calls "DeletePlaceAPI" with "Delete" http request
    Then The API call is sucessful with status code 200
    And "status" in response body is "OK"

    Examples:
      | placeId                                     |
      | fea5e225a0208cebfdfb0beaca174d53            |