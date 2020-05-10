package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

import java.util.ArrayList;

public class QuickSort extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        sort(sortingVisualizer.data, 0, sortingVisualizer.data.size() - 1);

        return null;
    }

    private int partition(ArrayList<Integer> myArray, int low, int high) {
        int pivot = myArray.get(high);
        int firstIdx = (low - 1);
        for (int secondIdx = low; secondIdx < high; secondIdx++) {
            if (myArray.get(secondIdx) < pivot) {
                firstIdx++;

                int temp = myArray.get(firstIdx);
                myArray.set(firstIdx, myArray.get(secondIdx));
                myArray.set(secondIdx, temp);

            }
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int temp = myArray.get(firstIdx + 1);
        myArray.set(firstIdx + 1, myArray.get(high));
        myArray.set(high, temp);
        publishProgress();
        try {
            Thread.sleep(sortingVisualizer.speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return firstIdx + 1;
    }


    private void sort(ArrayList<Integer> myArray, int low, int high) {
        if (low < high) {
            int pivot = partition(myArray, low, high);
            publishProgress();

            sort(myArray, low, pivot - 1);
            sort(myArray, pivot + 1, high);
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
