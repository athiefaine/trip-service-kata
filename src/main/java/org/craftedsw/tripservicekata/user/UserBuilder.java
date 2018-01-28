package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

public class UserBuilder {

    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder newUser() {
        return new UserBuilder();
    }


    public UserBuilder friendWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addFriendsTo(user);
        addTripsTo(user);
        return user;
    }

    private void addTripsTo(User user) {
        for (Trip trip : this.trips) {
            user.addTrip(trip);
        }
    }

    private void addFriendsTo(User user) {
        for (User friend : this.friends) {
            user.addFriend(friend);
        }
    }
}
