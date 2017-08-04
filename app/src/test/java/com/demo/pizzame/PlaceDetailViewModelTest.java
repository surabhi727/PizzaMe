package com.demo.pizzame;

import android.content.Context;
import android.view.View;

import com.demo.pizzame.model.PizzaPlace;
import com.demo.pizzame.util.DefaultConfig;
import com.demo.pizzame.util.MockUtils;
import com.demo.pizzame.viewModel.ListRowViewModel;
import com.demo.pizzame.viewModel.PlaceDetailViewModel;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class PlaceDetailViewModelTest {

    private Context mContext;
    private PlaceDetailViewModel mPlaceDetailViewModel;
    private PlaceDetailViewModel mEmptyPlaceDetailViewModel;
    private PizzaPlace mPlace;
    private PizzaPlace mEmptyPlace;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
        mPlace = MockUtils.createMockPizzaPlace();
        mEmptyPlace = MockUtils.createEmptyMockPizzaPlace();
        PlaceDetailViewModel.RequestCallPermissionInterface requestCallPermissionInterface = new PlaceDetailViewModel.RequestCallPermissionInterface() {
            @Override
            public void requestCallPermission() {

            }
        };
        mEmptyPlaceDetailViewModel = new PlaceDetailViewModel(mContext, mEmptyPlace, requestCallPermissionInterface);
        mPlaceDetailViewModel = new PlaceDetailViewModel(mContext, mPlace, requestCallPermissionInterface);
    }

    @Test
    public void shouldGetPostTitle() throws Exception {
        Assert.assertEquals(mPlace.getTitle(), mPlaceDetailViewModel.getPlaceName());
    }

    @Test
    public void shouldGetAddress() throws Exception {
        Assert.assertEquals(mPlace.getFullAddress(), mPlaceDetailViewModel.getAddress());
    }

    @Test
    public void shouldGetPhoneNumber() throws Exception {
        Assert.assertEquals(mPlace.getPhone(), mPlaceDetailViewModel.getContactNumber());
    }

    @Test
    public void shouldGetDistance() throws Exception {
        Assert.assertEquals(mPlace.getDistance() + " miles", mPlaceDetailViewModel.getDistance());
    }

    @Test
    public void shouldGetPlaceLatLng() throws Exception {
        LatLng latLng = mPlaceDetailViewModel.getPlaceLatLng();
        Assert.assertEquals(mPlace.getLatitude(), latLng.latitude);
        Assert.assertEquals(mPlace.getLongitude(), latLng.longitude);
    }

    @Test
    public void shouldGetPhoneNumberVisibility() throws Exception {
        Assert.assertEquals(View.VISIBLE, mPlaceDetailViewModel.getContactNumberVisibility());
        Assert.assertEquals(View.GONE, mEmptyPlaceDetailViewModel.getContactNumberVisibility());
    }

    @Test
    public void shouldGetAverageRatingVisibility() throws Exception {
        Assert.assertEquals(View.VISIBLE, mPlaceDetailViewModel.getAverageRatingVisibility());
        Assert.assertEquals(View.GONE, mEmptyPlaceDetailViewModel.getAverageRatingVisibility());
    }

    @Test
    public void shouldGetWebAddressVisibility() throws Exception {
        Assert.assertEquals(View.VISIBLE, mPlaceDetailViewModel.getWebAddressVisibility());
        Assert.assertEquals(View.GONE, mEmptyPlaceDetailViewModel.getWebAddressVisibility());
    }
}
