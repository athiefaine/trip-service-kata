# trip-service-kata
Provides a step by step reproduction of Sandro Mancuso's Trip Service kata.

It follows the refactoring session provided [there](https://www.youtube.com/watch?v=_NnElPO5BU0),
taking only small liberties (like use of AssertJ and BDD-flavoured test methods).

The aim is to refactor some legacy code that has no dedicated tests.
The Test-Driven Development approach is used.

# Step 1: write some unit tests for TripService
* use of Extract Method to isolate dependencies to UserSession and TripDAO
* creating a dedicated TripService subclass for the testcase allows to test TripService
* once green, refactoring in testcase to go to code more domain-oriented

# Step 2: define a builder for Users
* :warning: through it helps improving testcase readability, I see no use in the implementation

# Step 3: provide a test case for User class
* testing for friendShip allows to provide isFriendWith() method

# Step 4: refactor code related to users in TripService
* User.isFriendWith() allows a great step in TripService refactoring
* no testcase modification, this is pure *refactoring in the green*
* there is some kind of DSL alignment between testcase and implementation

# Step 5: fix dependency to logged in User
* the goal of step 5 and 6 is to get rid of dependencies issues in TripService
  * dependency with UserSession for loggedInUser (step 5)
  * dependency with TripDAO (step 6)
* by doing so, we get rid of the TestableTripService subclass used in testcase

# Step 6: fix dependency to TripDAO
* use of mock in the testcase
* use of dependency injection in the implementation

