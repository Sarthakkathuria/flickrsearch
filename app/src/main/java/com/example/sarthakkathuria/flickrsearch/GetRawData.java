package com.example.sarthakkathuria.flickrsearch;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Sarthak kathuria on 29-08-2018.
 */
enum DownloadsStatus {IDLE,PROCESSING, NOT_INITALISED ,FAILED_OR_EMPTZ ,OK}

class GetRawData extends AsyncTask<String, Void, String>{
    private static final String TAG = "GetRawData";

    private DownloadsStatus mDownloadStatus;


    public GetRawData() {
       this.mDownloadStatus = DownloadsStatus.IDLE;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection =null;
        BufferedReader reader =null;

        if (strings == null){
            mDownloadStatus =DownloadsStatus.NOT_INITALISED;
            return null;
        }
        try{
            mDownloadStatus= DownloadsStatus.PROCESSING;
            URL url= new URL(strings[0]);

            connection =(HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

        }catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: Invalid URL"+e.getMessage());
        }catch(IOException e){
            Log.e(TAG, "doInBackground: IO exception reading data:"+e.getMessage());

        }catch (SecurityException e){
            Log.e(TAG, "doInBackground: Security Exception. Needs permission?"+e.getMessage() );
        }

        return null;
    }
}
