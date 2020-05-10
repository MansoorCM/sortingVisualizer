package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

public class BubbleSort extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {

        int size = sortingVisualizer.data.size();
        for (int firstIdx = 0; firstIdx < size - 1; firstIdx++) {

            for (int secondIdx = 0; secondIdx < size - firstIdx - 1; secondIdx++) {

                try {
                    Thread.sleep(sortingVisualizer.speed/10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sortingVisualizer.data.get(secondIdx) > sortingVisualizer.data.get(secondIdx + 1)) {

                    int temp = sortingVisualizer.data.get(secondIdx);
                    sortingVisualizer.data.set(secondIdx, sortingVisualizer.data.get(secondIdx + 1));
                    sortingVisualizer.data.set(secondIdx + 1, temp);

                    publishProgress();
                    try {
                        Thread.sleep(sortingVisualizer.speed/10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
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