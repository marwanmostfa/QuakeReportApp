package com.example.android.quakereport;

import java.util.List;

interface EarthQuakeAsyncTask {
    void onPostExecute(List<Earthquake> data);
}
