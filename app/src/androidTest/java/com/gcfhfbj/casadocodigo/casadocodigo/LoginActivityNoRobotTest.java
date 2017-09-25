package com.gcfhfbj.casadocodigo.casadocodigo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gcfhfbj.casadocodigo.casadocodigo.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Guilherme on 24/09/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityNoRobotTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginSuccess() throws Exception {
        String user = "gcfhfbj@teste.com";
        String pass = "123456";

        onView(withId(R.id.login_email)).perform(typeText(user), closeSoftKeyboard());
        onView(withId(R.id.login_senha)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.login_logar)).perform(click());
        Thread.sleep(5000);

        //onView(withText(user)).check(matches(isDisplayed()));

        onView(withId(R.id.signout)).perform(click());
        onView(withId(R.id.login_email)).check(matches(isDisplayed()));
    }

    @Test
    public void loginFailed() throws Exception {
        String user = "invalid@email.com";
        String pass = "invalidPass";

        onView(withId(R.id.login_email)).perform(typeText(user), closeSoftKeyboard());
        onView(withId(R.id.login_senha)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.login_logar)).perform(click());

        Thread.sleep(1500);
        onView(withText(R.string.error_access_denied)).check(matches(isDisplayed()));
    }

    @Test
    public void emptyLogin() throws Exception {
        String user = "";
        String pass = "";

        onView(withId(R.id.login_email)).perform(typeText(user), closeSoftKeyboard());
        onView(withId(R.id.login_senha)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.login_logar)).perform(click());

        //Thread.sleep(1000);
        onView(withText(R.string.error_form_incomplete)).check(matches(isDisplayed()));
    }
}
