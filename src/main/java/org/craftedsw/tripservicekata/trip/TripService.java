package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    @Autowired
    private TripDAO tripDAO;

    public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
        validate(loggedInUser);
        return friend.isFriendWith(loggedInUser)
                ? tripsBy(friend)
                : noTrips();

    }

    private void validate(User loggedInUser) {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    private List<Trip> tripsBy(User user) {
        return tripDAO.tripsBy(user);
    }

}
