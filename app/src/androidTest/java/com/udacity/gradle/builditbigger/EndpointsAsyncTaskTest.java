package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class EndpointsAsyncTaskTest extends AndroidTestCase {

    private Context context = InstrumentationRegistry.getTargetContext();

    @SmallTest
    public void testBlah(){
        assertEquals(1,1);
    }


    @Test
    public void testJokeReceived() {


        EndpointsAsyncTask task = new EndpointsAsyncTask(context);
        task.execute();

        try {
            String joke = task.get();
            assertNotNull(joke);
            assertTrue(joke.length() > 0);

        } catch (InterruptedException | ExecutionException e) {
            Log.e("JokesAsyncTaskTest", Log.getStackTraceString(e));
        }
    }


}
