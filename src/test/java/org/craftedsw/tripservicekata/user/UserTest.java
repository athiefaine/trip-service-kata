package org.craftedsw.tripservicekata.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UserTest {

    private static final User LUKE = new User();
    private static final User HAN = new User();

    @Test
    public void should_tell_when_users_are_not_friends() {
        // GIVEN
        User user = UserBuilder.newUser()
                .friendWith(LUKE)
                .build();
        //WHEN

        //THEN
        Assertions.assertThat(user.isFriendWith(HAN)).isFalse();
    }

    @Test
    public void should_tell_when_users_are_friends() {
        // GIVEN
        User user = UserBuilder.newUser()
                .friendWith(LUKE, HAN)
                .build();
        //WHEN

        //THEN
        Assertions.assertThat(user.isFriendWith(HAN)).isTrue();
    }


}
