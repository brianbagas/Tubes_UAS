package com.example.tubes_uts_e_2.activity;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.tubes_uts_e_2.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void registerActivityTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnRegister), withText("DAFTAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etNama),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Agus"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnRegister), withText("DAFTAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("agus@agus.com"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnRegister), withText("DAFTAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                4),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.etUsername),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("agushh"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnRegister), withText("DAFTAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                4),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.etPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("agusagus"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnRegister), withText("DAFTAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_page),
                                        0),
                                4),
                        isDisplayed()));
        materialButton5.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
