package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class TripServiceTest {

    private static final User ANOTHER_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final Trip BALI = new Trip();
    public static final Trip PARIS = new Trip();
    private User loggedInUser;
    private TestableTripService tripService;

    @Before
    public void setup() {
        tripService = new TestableTripService();
        loggedInUser = REGISTERED_USER;
    }


    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() {
        loggedInUser = GUEST;

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friend() {
        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(BALI);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        Assertions.assertThat(friendTrips.size()).isEqualTo(0);
    }

    @Test
    public void should_return_friend_trips_when_users_are_friend() {
        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addFriend(loggedInUser);
        friend.addTrip(BALI);
        friend.addTrip(PARIS);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        Assertions.assertThat(friendTrips.size()).isEqualTo(2);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }
	
}
