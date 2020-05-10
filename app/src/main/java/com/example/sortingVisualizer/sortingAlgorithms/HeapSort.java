package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

import java.util.ArrayList;

public class HeapSort extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        int size = sortingVisualizer.data.size();

        // Build heap
        for (int idx = size / 2 - 1; idx >= 0; idx--) {
            heapify(sortingVisualizer.data, size, idx);
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (int idx = size - 1; idx > 0; idx--) {
            int temp = sortingVisualizer.data.get(0);
            sortingVisualizer.data.set(0, sortingVisualizer.data.get(idx));
            sortingVisualizer.data.set(idx, temp);
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            heapify(sortingVisualizer.data, idx, 0);
        }
        return null;
    }

    //max heap
    private void heapify(ArrayList<Integer> myArray, int heapSize, int idx) {
        int largest = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if (left < heapSize && myArray.get(left) > myArray.get(largest))
            largest = left;

        if (right < heapSize && myArray.get(right) > myArray.get(largest))
            largest = right;

        if (largest != idx) {
            int swap = myArray.get(idx);
            myArray.set(idx, myArray.get(largest));
            myArray.set(largest, swap);

            heapify(myArray, heapSize, largest);
        }
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
