package com.demo.pizzame.util;

import com.demo.pizzame.model.Result;

/**
 * Created by vipulmittal on 01/08/17.
 */

public class MockUtils {
    public static Result createMockResult() {
        final Result result = new Result();
        result.setTitle("pizza hut");
        result.setAddress("randolph");
        result.setPhone("3342");
        return result;
    }
}
