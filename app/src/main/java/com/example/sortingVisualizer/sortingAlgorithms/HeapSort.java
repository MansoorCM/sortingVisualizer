package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

import java.util.ArrayList;

public class HeapSort extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        int n = sortingVisualizer.data.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(sortingVisualizer.data, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>0; i--)
        {
            // Move current root to end
            int temp = sortingVisualizer.data.get(0);
            sortingVisualizer.data.set(0, sortingVisualizer.data.get(i));
            sortingVisualizer.data.set(i, temp);
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // call max heapify on the reduced heap
            heapify(sortingVisualizer.data, i, 0);
        }
        return null;
    }

    void heapify(ArrayList<Integer> arr, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr.get(l) > arr.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr.get(r) > arr.get(largest))
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
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
