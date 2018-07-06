package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.android.builditbigger.AndroidLibraryJokesMain;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    public String myJoke = null;
    private Context mContext = null;

    public EndpointsAsyncTask(Context context){
        this.mContext = context.getApplicationContext();
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {
            try {
                MyApi.Builder builder = new MyApi.Builder( AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null )
                        .setRootUrl( "http://10.0.2.2:8080/_ah/api/" )
                        .setGoogleClientRequestInitializer( new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent( true );
                            }
                        });

                myApiService = builder.build();
            } catch (Exception ex) {
                return ex.getMessage();
            }
        }

//        mContext = params[0].first;

        try {
            myJoke = myApiService.getJoke().execute().getData();
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent (mContext, AndroidLibraryJokesMain.class);
        intent.putExtra("Joke", myJoke);
        mContext.startActivity( intent );
    }

}
