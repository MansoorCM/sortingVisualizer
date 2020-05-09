package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

import java.util.ArrayList;

public class MergeSort extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        sort(sortingVisualizer.data,0,sortingVisualizer.data.size()-1);
        return null;
    }
    void merge(ArrayList<Integer> arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr.get(l + i);
        for (int j=0; j<n2; ++j)
            R[j] = arr.get(m + 1 + j);


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr.set(k, L[i]);
                i++;
            }
            else
            {
                arr.set(k, R[j]);
                j++;
            }
            k++;
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr.set(k, L[i]);
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr.set(k, R[j]);
            j++;
            k++;
        }
        publishProgress();
        try {
            Thread.sleep(sortingVisualizer.speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(ArrayList<Integer> arr, int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
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
