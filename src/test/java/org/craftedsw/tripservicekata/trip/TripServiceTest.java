package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.user.UserBuilder.newUser;


public class TripServiceTest {

    private static final User ANOTHER_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final Trip BALI = new Trip();
    private static final Trip PARIS = new Trip();

    private TestableTripService tripService;

    @Before
    public void setup() {
        tripService = new TestableTripService();

    }


    @Test(/*THEN*/ expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged_in() {
        // GIVEN

        // WHEN
        tripService.getTripsByUser(UNUSED_USER, GUEST);
    }

    @Test
    public void should_not_return_any_trips_when_users_are_not_friend() {
        // GIVEN
        User friend = newUser()
                .friendWith(ANOTHER_USER)
                .withTrips(BALI)
                .build();

        // WHEN
        List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERED_USER);

        // THEN
        Assertions.assertThat(friendTrips.size()).isEqualTo(0);
    }

    @Test
    public void should_return_friend_trips_when_users_are_friend() {
        // GIVEN
        User friend = newUser()
                .friendWith(ANOTHER_USER, REGISTERED_USER)
                .withTrips(BALI, PARIS)
                .build();

        // WHEN
        List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERED_USER);

        // THEN
        Assertions.assertThat(friendTrips.size()).isEqualTo(2);
    }

    private class TestableTripService extends TripService {
        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }

}
