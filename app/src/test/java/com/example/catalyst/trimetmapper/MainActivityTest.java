package com.example.catalyst.trimetmapper;

import android.widget.TextView;

import com.example.catalyst.trimetmapper.activities.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by dsloane on 7/14/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class MainActivityTest {

    @Test
    public void promptIsCorrect() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        TextView textView = (TextView) activity.findViewById(R.id.destination_prompt);

        assertEquals(textView.getText().toString(), "Enter your destination");
    }
}
