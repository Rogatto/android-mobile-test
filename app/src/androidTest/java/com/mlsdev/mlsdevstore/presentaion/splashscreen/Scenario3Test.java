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
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Scenario3Test {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void browseAndBuyAnItemWithSuccessFromHealthBeautyAndAnotherFromBabyCategories() throws InterruptedException {

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
            // click button option Health & Beauty
            onView(allOf(withId(R.id.text_category_name), withText("Health & Beauty"))).perform(click());

            Thread.sleep(5000);
            // select product
            onView(allOf(withId(R.id.iv_item_title), withText("Large Oils"))).perform(click());

            Thread.sleep(5000);
            // add this step, because have a issue in APP (bug reported - evidence name: alert_interruped_process)
            //onView(withId(R.id.refreshLayout)).perform(click());
            Espresso.pressBackUnconditionally();

            Thread.sleep(5000);
            // click for add product to cart
            onView(allOf(withText("Add to cart"))).perform(click());

            Thread.sleep(5000);
            // back to store
            onView(allOf(withId(R.id.largeLabel), withText("Store"))).perform(click());

            Thread.sleep(5000);
            // click button option Baby Category
            onView(allOf(withId(R.id.text_category_name), withText("Baby"))).perform(click());

            Thread.sleep(5000);
            // select product
            onView(allOf(withId(R.id.iv_item_title), withText("ALLEN BRADLEY 100-C12D10 / 100C12D10 (RQANS1)"))).perform(click());

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
            onView(withText("Large Oils"))
                    .check(matches(withText("Large Oils")));

            Thread.sleep(5000);
            //check product into the cart
            onView(withText("ALLEN BRADLEY 100-C12D10 / 100C12D10 (RQANS1)"))
                    .check(matches(withText("ALLEN BRADLEY 100-C12D10 / 100C12D10 (RQANS1)")));

            Thread.sleep(5000);
            // add this step, because have a issue in APP (bug reported - evidence name: alert_interruped_process)
            //onView(withId(R.id.refreshLayout)).perform(click());
            Espresso.pressBackUnconditionally();

            Thread.sleep(5000);
            // click for add product to cart
            onView(allOf(withText("Add to cart"))).perform(click());

            Thread.sleep(5000);

            onView(allOf(withId(R.id.button_checkout))).perform(click());

            //input credit card
            Thread.sleep(5000);

            onView(allOf(withId(R.id.text_card_number))).perform(typeText("4407130562870366"));
            onView(allOf(withId(R.id.text_expiration_date))).perform(typeText("10/23"));
            onView(allOf(withId(R.id.text_cvv))).perform(typeText("310"));
            onView(allOf(withId(R.id.text_card_holder))).perform(clearText()).perform(typeText("Guilherme Rogatto"));

            String filename = "scenario3Test.png";
            device.takeScreenshot(new File(path, filename));

        } catch (Exception e){
            String filename = "scenario3testError.png";
            device.takeScreenshot(new File(path, filename));
        }

    }
}
