package com.example.sortingVisualizer.sortingAlgorithms;

import android.os.AsyncTask;

import com.example.sortingVisualizer.sortingVisualizer;

public class BubbleSort extends AsyncTask<Void,Void,Void>
{

    @Override
    protected Void doInBackground(Void... voids) {

        int n = sortingVisualizer.data.size();
        for(int b = 0;b<n -1;b++)
        {

            for (int c = 0; c < n - b - 1; c++) {

                try {
                    Thread.sleep(sortingVisualizer.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sortingVisualizer.data.get(c) > sortingVisualizer.data.get(c +1)) {

                    int temp = sortingVisualizer.data.get(c);
                    sortingVisualizer.data.set(c, sortingVisualizer.data.get(c + 1));
                    sortingVisualizer.data.set(c + 1, temp);

                    publishProgress();
                    try {
                        Thread.sleep(sortingVisualizer.speed);
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
        sortingVisualizer.sorted=true;
        sortingVisualizer.drawSomething(sortingVisualizer.mImageView);
        sortingVisualizer.mImageView.setClickable(true);
    }
}