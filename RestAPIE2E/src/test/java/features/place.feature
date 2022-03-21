Feature: Validating Place API's

Scenario Outline: Adding Place
	Given Add Place Payload with "<name>"  "<language>" "<address>" "<phonenumber>" "<website>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	
Examples:
	|name 	 | language |address|phonenumber |website   |
	|Naresh  |  English |pamur  |9874563210  |google.com|
	
	
Scenario: Get place Details
	Given Add placeid as queryparameter
	When user call "getPlaceAPI" with "GET" http request
	Then verify name in response body is "Naresh"