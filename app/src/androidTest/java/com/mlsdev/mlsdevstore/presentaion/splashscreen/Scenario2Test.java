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
public class Scenario2Test {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void addAndRemoveAnItemFromTheCart() throws InterruptedException {

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
            // click for add product to cart
            onView(allOf(withText("Add to cart"))).perform(click());

            Thread.sleep(5000);
            // go to cart
            onView(withId(R.id.cart_flow_fragment)).perform(click());

            Thread.sleep(5000);
            //check product into the cart
            onView(withText("iMac"))
                    .check(matches(withText("iMac")));

            Thread.sleep(5000);
            // delete product into cart
            onView(withId(R.id.button_remove_from_cart)).perform(click());

            Thread.sleep(5000);


            String filename = "scenario2Test.png";
            device.takeScreenshot(new File(path, filename));

            // check cart is void
            onView(withText("No items - no fun :("))
                    .check(matches(withText("No items - no fun :(")));

        } catch (Exception e){
            String filename = "scenario2testError.png";
            device.takeScreenshot(new File(path, filename));
        }

    }
}
