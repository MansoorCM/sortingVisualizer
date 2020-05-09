package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

public class SelectionSort extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        int n = sortingVisualizer.data.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
            {
                if (sortingVisualizer.data.get(j) < sortingVisualizer.data.get(min_idx))
                    min_idx = j;
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            // Swap the found minimum element with the first
            // element
            int temp = sortingVisualizer.data.get(min_idx);
            sortingVisualizer.data.set(min_idx, sortingVisualizer.data.get(i));
            sortingVisualizer.data.set(i, temp);
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
        sortingVisualizer.sorted=true;
        sortingVisualizer.drawSomething(sortingVisualizer.mImageView);
        sortingVisualizer.mImageView.setClickable(true);
    }
}
