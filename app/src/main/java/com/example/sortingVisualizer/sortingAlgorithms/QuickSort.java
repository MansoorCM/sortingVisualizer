package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

import java.util.ArrayList;

public class QuickSort extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        sort(sortingVisualizer.data,0, sortingVisualizer.data.size()-1);

        return null;
    }
    int partition(ArrayList<Integer> arr, int low, int high)
    {
        int pivot = arr.get(high);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr.get(j) < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                publishProgress();
                try {
                    Thread.sleep(sortingVisualizer.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        publishProgress();
        try {
            Thread.sleep(sortingVisualizer.speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(ArrayList<Integer> arr, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);
            publishProgress();

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
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
        sortingVisualizer.sorted=true;
        sortingVisualizer.drawSomething(sortingVisualizer.mImageView);
        sortingVisualizer.mImageView.setClickable(true);
    }
}
