# CasaDoCodigoGCFHFBJ

Testes Espresso
@RunWith(AndroidJunit.class)
@LargeTest
Public class LoginActivityTest {
	@Rule
	ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);
	@Test
	Public void loginSucesso throws Exception{
		onView(withId(R.id.email)).perform(typeText(username),closeSoftKeyboard());
		onView(withId(R.id.senha)).perform(typeText(senha),closeSoftKeyboard());
		onView(withText(R.id.logar)).perform(click());
		Thread.sleep(300);
		onView(withText(email)).check(matches(isDisplayed));
    onView(withId(R.id.sair)).perform(click());
    onView(withId(R.id.login_email)).;
  }
}

public class LoginRobot {
  public void email(String username){
    onView(withId(R.id.email)).perform(typeText(username),closeSoftKeyboard());
    return this
  }
}
