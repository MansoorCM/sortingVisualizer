package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

import java.util.ArrayList;

public class MergeSort extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        sort(sortingVisualizer.data, 0, sortingVisualizer.data.size() - 1);
        return null;
    }

    void merge(ArrayList<Integer> myArray, int left, int middle, int right) {
        int sizeOne = middle - left + 1;
        int sizeTwo = right - middle;

        int[] LeftArray = new int[sizeOne];
        int[] RightArray = new int[sizeTwo];

        for (int i = 0; i < sizeOne; ++i)
            LeftArray[i] = myArray.get(left + i);
        for (int j = 0; j < sizeTwo; ++j)
            RightArray[j] = myArray.get(middle + 1 + j);


        int firstIdx = 0, secondIdx = 0;

        int mergedArrayIdx = left;
        while (firstIdx < sizeOne && secondIdx < sizeTwo) {
            if (LeftArray[firstIdx] <= RightArray[secondIdx]) {
                myArray.set(mergedArrayIdx, LeftArray[firstIdx]);
                firstIdx++;
            } else {
                myArray.set(mergedArrayIdx, RightArray[secondIdx]);
                secondIdx++;
            }
            mergedArrayIdx++;
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (firstIdx < sizeOne) {
            myArray.set(mergedArrayIdx, LeftArray[firstIdx]);
            firstIdx++;
            mergedArrayIdx++;
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (secondIdx < sizeTwo) {
            myArray.set(mergedArrayIdx, RightArray[secondIdx]);
            secondIdx++;
            mergedArrayIdx++;
            publishProgress();
            try {
                Thread.sleep(sortingVisualizer.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sort(ArrayList<Integer> myArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            sort(myArray, left, middle);
            sort(myArray, middle + 1, right);

            merge(myArray, left, middle, right);
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
