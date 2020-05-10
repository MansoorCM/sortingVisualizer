package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

public class InsertionSort extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        int size = sortingVisualizer.data.size();
        for (int firstIdx = 1; firstIdx < size; ++firstIdx) {
            int key = sortingVisualizer.data.get(firstIdx);
            int secondIdx = firstIdx - 1;

            while (secondIdx >= 0 && sortingVisualizer.data.get(secondIdx) > key) {

                sortingVisualizer.data.set(secondIdx + 1, sortingVisualizer.data.get(secondIdx));
                secondIdx = secondIdx - 1;
                publishProgress();
                try {
                    Thread.sleep(sortingVisualizer.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sortingVisualizer.data.set(secondIdx + 1, key);
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
        sortingVisualizer.sorted = true;
        sortingVisualizer.drawSomething(sortingVisualizer.mImageView);
        sortingVisualizer.mImageView.setClickable(true);
    }
}
