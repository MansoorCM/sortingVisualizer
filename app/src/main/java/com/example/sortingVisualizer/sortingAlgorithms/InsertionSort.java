package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

public class InsertionSort extends AsyncTask<Void,Void,Void> {



    @Override
    protected Void doInBackground(Void... voids) {
        final int n = sortingVisualizer.data.size();
        for (int i = 1; i < n; ++i) {
            int key = sortingVisualizer.data.get(i);
            int j = i - 1;
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && sortingVisualizer.data.get(j) > key) {

                sortingVisualizer.data.set(j + 1, sortingVisualizer.data.get(j));
                j = j - 1;
                publishProgress();
                try {
                    Thread.sleep(sortingVisualizer.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sortingVisualizer.data.set(j + 1, key);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        sortingVisualizer.drawSomething(sortingVisualizer.mImageView);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        sortingVisualizer.sorted=true;
        sortingVisualizer.drawSomething(sortingVisualizer.mImageView);
        sortingVisualizer.mImageView.setClickable(true);
    }
}
