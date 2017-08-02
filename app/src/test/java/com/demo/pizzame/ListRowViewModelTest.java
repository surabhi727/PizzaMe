package com.demo.pizzame;

import android.content.Context;

import com.demo.pizzame.model.Result;
import com.demo.pizzame.util.DefaultConfig;
import com.demo.pizzame.util.MockUtils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.MockUtil;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class ListRowViewModelTest {

    private Context mContext;
    private ListRowViewModel mListRowViewModel;
    private Result mPlace;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
        mPlace = MockUtils.createMockResult();
        mListRowViewModel = new ListRowViewModel(mContext, mPlace);
    }

    @Test
    public void shouldGetPostTitle() throws Exception {
        Assert.assertEquals(mListRowViewModel.getPlaceName(), mPlace.getTitle());
    }

    @Test
    public void shouldGetAddress() throws Exception {
        Assert.assertEquals(mListRowViewModel.getAddress(), mPlace.getAddress());
    }

    @Test
    public void shouldGetPhoneNumber() throws Exception {
        Assert.assertEquals(mListRowViewModel.getContactNumber(), mPlace.getPhone());
    }
}
