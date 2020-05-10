package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

public class SelectionSort extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        int size = sortingVisualizer.data.size();

        for (int firstIdx = 0; firstIdx < size - 1; firstIdx++) {
            int min_idx = firstIdx;
            for (int secondIdx = firstIdx + 1; secondIdx < size; secondIdx++) {
                if (sortingVisualizer.data.get(secondIdx) < sortingVisualizer.data.get(min_idx))
                    min_idx = secondIdx;
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            int temp = sortingVisualizer.data.get(min_idx);
            sortingVisualizer.data.set(min_idx, sortingVisualizer.data.get(firstIdx));
            sortingVisualizer.data.set(firstIdx, temp);
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
