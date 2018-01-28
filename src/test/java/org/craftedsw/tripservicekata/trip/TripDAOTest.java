package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {

    @Test(/*THEN*/ expected = CollaboratorCallException.class)
    public void should_throw_exception_when_finding_user_trips() {
        // GIVEN
        //WHEN
        new TripDAO().tripsBy(new User());

    }


}
