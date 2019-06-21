package com.mlsdev.mlsdevstore.presentaion.splashscreen;

import android.os.Environment;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import com.mlsdev.mlsdevstore.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.File;
import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Scenario1Test {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void addAnItemAsFavorite() throws InterruptedException {

        //Evidence into sd
        File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/screenshots/" + getTargetContext().getPackageName());
        if (!path.exists()) {
            path.mkdirs();
        }

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        try{
            Thread.sleep(5000);
            // click button categories
            onView(withId(R.id.button_browse_categories)).perform(click());
            //onView(isRoot()).perform(waitId(R.id.dialogEditor, TimeUnit.SECONDS.toMillis(15)));

            Thread.sleep(5000);
            // click button option Dolls & Bears
            onView(allOf(withId(R.id.text_category_name), withText("Dolls & Bears"))).perform(click());

            Thread.sleep(5000);
            // select product
            onView(allOf(withId(R.id.iv_item_title), withText("iMac"))).perform(click());

            Thread.sleep(5000);
            // add this step, because have a issue in APP (bug reported - evidence name: alert_interruped_process)
            //onView(withId(R.id.refreshLayout)).perform(click());
            Espresso.pressBackUnconditionally();

            Thread.sleep(5000);
            // add item to favorites
            onView(withId(R.id.add_to_favorites)).perform(click());

            Thread.sleep(5000);
            // click in favorites
            onView(withId(R.id.favorites_flow_fragment)).perform(click());

            Thread.sleep(5000);


            String filename = "scenario1test.png";
            device.takeScreenshot(new File(path, filename));

            //check product include into favorites
            onView(withId(R.id.iv_item_title))
                    .check(matches(withText("iMac")));

        } catch (Exception e){
            String filename = "scenario1testError.png";
            device.takeScreenshot(new File(path, filename));
        }


    }

}
