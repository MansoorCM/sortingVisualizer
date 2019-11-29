package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class SortAlgActivity extends AppCompatActivity {
    ImageView number1,number2,number3,number4,number5,number6,number7,number8,number9,number10;
    int[] data={520,870,1020,410,1190,321,665,989,432,1000};
    View[] image;
    Handler handler;
    Handler inhandler;
    String algo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);
        Intent intent=getIntent();
        algo=intent.getStringExtra("algo");

        number1=findViewById(R.id.number1);
        number1.requestLayout();
        number1.getLayoutParams().height=520;
        number2=findViewById(R.id.number2);
        number2.requestLayout();
        number2.getLayoutParams().height=870;
        number3=findViewById(R.id.number3);
        number3.requestLayout();
        number3.getLayoutParams().height=1020;
        number4=findViewById(R.id.number4);
        number4.requestLayout();
        number4.getLayoutParams().height=410;
        number5=findViewById(R.id.number5);
        number5.requestLayout();
        number5.getLayoutParams().height=1190;
        number6=findViewById(R.id.number6);
        number6.requestLayout();
        number6.getLayoutParams().height=321;
        number7=findViewById(R.id.number7);
        number7.requestLayout();
        number7.getLayoutParams().height=665;
        number8=findViewById(R.id.number8);
        number8.requestLayout();
        number8.getLayoutParams().height=989;
        number9=findViewById(R.id.number9);
        number9.requestLayout();
        number9.getLayoutParams().height=432;
        number10=findViewById(R.id.number10);
        number10.requestLayout();
        number10.getLayoutParams().height=1000;
        image= new View[]{number1, number2, number3, number4, number5,number6,number7,number8,number9,number10};
        handler=new Handler();
        inhandler=new Handler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.sort_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    void bubbleSort(final int[] arr) {
        final int n = arr.length;
        for (int b = 0; b < n - 1; b++) {
            final int i = b;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    for (int c = 0; c < n - i - 1; c++)
                    {
                        final int j=c;
                        inhandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (arr[j] > arr[j + 1]) {
                                    // swap temp and arr[i]
                                    int temp = arr[j];
                                    arr[j] = arr[j + 1];
                                    arr[j + 1] = temp;
                                    image[j].requestLayout();
                                    int height=image[j].getLayoutParams().height;
                                    image[j+1].requestLayout();
                                    image[j].getLayoutParams().height=image[j+1].getLayoutParams().height;
                                    image[j+1].getLayoutParams().height=height;

                                }

                            }
                        },500*j);
                    }
                }

            }, 1000*i);
        }
    }

    void selectionSort(final int[] arr)
    {

            final int n = arr.length;
            final ViewGroup viewGroup = (ViewGroup) number4.getParent();
            // One by one move boundary of unsorted subarray
            for ( int b = 0; b < n-1; b++)
            {
                final int i=b;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        int min_idx = i;
                        for (int f = i+1; f < n; f++) {
                            int j=f;

                            if (arr[j] < arr[min_idx])
                                min_idx = j;
                        }

                        // Swap the found minimum element with the first
                        // element
                        if(i!=min_idx)
                        {
                            int c=i;
                            viewGroup.removeView(image[i]);
                            c+=1;
                            while(c<min_idx)
                            {
                                viewGroup.removeView(image[c]);
                                c+=1;
                            }
                            c+=1;
                            while (c<n)
                            {
                                viewGroup.removeView(image[c]);
                                c+=1;
                            }
                            c=i+1;
                            while(c<min_idx)
                            {
                                viewGroup.addView(image[c]);
                                c+=1;
                            }
                            viewGroup.addView(image[i]);
                            c+=1;
                            while (c<n)
                            {
                                viewGroup.addView(image[c]);
                                c+=1;
                            }
                            View view=image[i];
                            image[i]=image[min_idx];
                            image[min_idx]=view;



                            int temp = arr[min_idx];
                            arr[min_idx] = arr[i];
                            arr[i] = temp;
                        }

                        for(int k=0;k<n;k++)
                        {
                            String data= String.valueOf(k);
                            Log.i("num"+k, String.valueOf(arr[k]));
                        }


                    }
                },1000*i);
                // Find the minimum element in unsorted array

            }


    }
    void InsertionSort(final int[] arr)
    {
        final int n = arr.length;
        final ViewGroup viewGroup = (ViewGroup) number4.getParent();
        for (int b = 1; b < n; ++b) {
            final int i=b;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int key = arr[i];
                    View keyImg=image[i];
                    int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
                    while (j >= 0 && arr[j] > key) {
                        int idx=j+1;
                        while(idx<n)
                        {
                            viewGroup.removeView(image[idx]);
                            idx+=1;
                        }
                        idx=j+1;
                        image[j+1]=image[j];
                        while(idx<n)
                        {
                            if(viewGroup!=null)
                            {
                                viewGroup.removeView(image[idx]);
                            }
                            viewGroup.addView(image[idx]);
                            idx+=1;
                        }
                        arr[j + 1] = arr[j];

                        j = j - 1;

                    }
                    arr[j + 1] = key;
                    int idx=j+1;
                    while(idx<n)
                    {
                        viewGroup.removeView(image[idx]);
                        idx+=1;
                    }
                    image[j+1]=keyImg;
                    idx=j+1;
                    while(idx<n)
                    {
                        viewGroup.addView(image[idx]);
                        idx+=1;
                    }


                }
            },1000*i);

        }
        for(int k=0;k<n;k++)
        {
            String data= String.valueOf(k);
            Log.i("num"+k, String.valueOf(arr[k]));
        }

    }


        void merge(int arr[], int l, int m, int r)
        {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int [n1];
            int R[] = new int [n2];

            /*Copy data to temp arrays*/
            for (int i=0; i<n1; ++i)
                L[i] = arr[l + i];
            for (int j=0; j<n2; ++j)
                R[j] = arr[m + 1+ j];


            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarry array
            int k = l;
            while (i < n1 && j < n2)
            {
                if (L[i] <= R[j])
                {
                    arr[k] = L[i];
                    i++;
                }
                else
                {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1)
            {
                arr[k] = L[i];
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2)
            {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

        // Main function that sorts arr[l..r] using
        // merge()
        void Mergesort(int arr[], int l, int r)
        {
            if (l < r)
            {
                // Find the middle point
                int m = (l+r)/2;

                // Sort first and second halves
                Mergesort(arr, l, m);
                Mergesort(arr ,m+1, r);

                // Merge the sorted halves
                merge(arr, l, m, r);
            }
        }

    public void heapSort(final int[] arr)
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int b=n-1; b>=0; b--)
        {
            final int i=b;
            handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                image[i].requestLayout();
                int height=image[i].getLayoutParams().height;
                Log.i("value", String.valueOf(height));
                image[0].requestLayout();
                image[i].getLayoutParams().height=image[0].getLayoutParams().height;
                Log.i("value", String.valueOf(image[0].getLayoutParams().height));
                image[0].getLayoutParams().height=height;
                heapify(arr, i, 0);
            }
        },(n-b)*1000);

        }}

    void heapify(int[] arr, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            image[i].requestLayout();
            int height=image[i].getLayoutParams().height;
            image[largest].requestLayout();
            image[i].getLayoutParams().height=image[largest].getLayoutParams().height;
            image[largest].getLayoutParams().height=height;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    void quickSort()
    {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.sort)
        {
            if(algo.equals("Bubble Sort"))
            {
                bubbleSort(data);
            }
            else if(algo.equals("Selection Sort"))
            {
                selectionSort(data);
            }
            else if(algo.equals("Insertion Sort"))
            {
                InsertionSort(data);
            }
            else if(algo.equals("Merge Sort"))
            {
                Mergesort(data,0,data.length-1);
            }
            else if(algo.equals("Heap Sort"))
            {
                heapSort(data);
            }
            else if(algo.equals("Quick Sort"))
            {
                quickSort();
            }
            else
            {
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
