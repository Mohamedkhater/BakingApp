package com.example.bakingapp;

import android.support.test.runner.AndroidJUnit4;

import androidx.test.espresso.Espresso;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)


public class MainActivityTest {
    @Rule   public ActivityTestRule<MainActivity> aiRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ScrollToPosition(){
       // Espresso.onView(ViewMatchers.withId(R.id.recipe_rv))
                //.perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));
    }

}