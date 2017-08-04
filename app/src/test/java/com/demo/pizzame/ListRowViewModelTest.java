package com.demo.pizzame;

import android.content.Context;

import com.demo.pizzame.model.PizzaPlace;
import com.demo.pizzame.util.DefaultConfig;
import com.demo.pizzame.util.MockUtils;
import com.demo.pizzame.viewModel.ListRowViewModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class ListRowViewModelTest {

    private Context mContext;
    private ListRowViewModel mListRowViewModel;
    private PizzaPlace mPlace;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
        mPlace = MockUtils.createMockPizzaPlace();
        mListRowViewModel = new ListRowViewModel(mContext, mPlace);
    }

    @Test
    public void shouldGetPostTitle() throws Exception {
        Assert.assertEquals(mPlace.getTitle(), mListRowViewModel.getPlaceName());
    }

    @Test
    public void shouldGetAddress() throws Exception {
        Assert.assertEquals(mPlace.getFullAddress(), mListRowViewModel.getAddress());
    }

    @Test
    public void shouldGetPhoneNumber() throws Exception {
        Assert.assertEquals(mPlace.getPhone(), mListRowViewModel.getContactNumber());
    }

    @Test
    public void shouldGetDistance() throws Exception {
        Assert.assertEquals(mPlace.getDistance() + " miles", mListRowViewModel.getDistance());
    }
}
