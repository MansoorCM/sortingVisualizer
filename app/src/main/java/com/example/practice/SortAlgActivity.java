package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Stack;

public class SortAlgActivity extends AppCompatActivity {
    ImageView number1,number2,number3,number4,number5,number6,number7,number8,number9,number10;
    int[] data={520,870,1020,410,1190,321,665,989,432,1000};
    View[] image;
    Handler handler;
    Handler inhandler;
    final Handler handler2=new Handler();
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
                                    setColor(j,false);
                                    setColor(j+1,false);
                                    // swap temp and arr[i]
                                    int temp = arr[j];
                                    arr[j] = arr[j + 1];
                                    arr[j + 1] = temp;
                                    swapImg(j,j+1);



                                }

                            }
                        },500*j);
                    }

                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for(int i=0;i<image.length;i++)
                            {
                                setColor(i,true);
                            }
                        }
                    },500);

                }

            }, 2000*i);
        }
    }

    void selectionSort(final int[] arr)
    {
            final int n = arr.length;
            // One by one move boundary of unsorted subarray
            for ( int b = 0; b < n-1; b++)
            {
                final int i=b;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        int min_idx = i;
                        for (int j = i+1; j < n; j++) {


                            if (arr[j] < arr[min_idx])
                                min_idx = j;
                        }

                        // Swap the found minimum element with the first
                        // element
                        if(i!=min_idx)
                        {
                            swapImg(i,min_idx);
                            int temp = arr[min_idx];
                            arr[min_idx] = arr[i];
                            arr[i] = temp;
                        }
                        setColor(i,false);

                        if(i==n-2)
                        {
                            setColor(i+1,false);
                            inhandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for(int i=0;i<image.length;i++)
                                    {
                                        setColor(i,true);
                                    }
                                }
                            },500);

                        }

                    }
                },1000*i);
                // Find the minimum element in unsorted array
            }
    }
    void InsertionSort(final int[] arr)
    {
        final int n = arr.length;
        setColor(0,false);
        for (int b = 1; b < n; ++b) {
            final int i=b;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int key = arr[i];
                    int j = i - 1;
                    setColor(i,false);

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
                    while (j >= 0 && arr[j] > key) {
                        swapImg(j,j+1);
                        arr[j + 1] = arr[j];

                        j = j - 1;

                    }
                    arr[j + 1] = key;

                    if(i==n-1)
                    {
                        inhandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for(int i=0;i<image.length;i++)
                                {
                                    setColor(i,true);
                                }
                            }
                        },500);

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
                swapImg(i,0);
                setColor(i,false);
                heapify(arr, i, 0);
                if(i==0)
                {
                    inhandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for(int i=0;i<image.length;i++)
                            {
                                setColor(i,true);
                            }
                        }
                    },500);

                }
            }

            },(n-b)*1000);

        }

    }

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
            swapImg(i,largest);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }




    void quickSort(final int[] arr, int high)
    {
        final Stack<Integer> stackl=new Stack<Integer>();
        final Stack<Integer> stackh=new Stack<Integer>();
        stackl.push(0);
        stackh.push(high);
        final int[] pivot = new int[1];
        final int[] i = new int[1];
        final int[] k=new int[1];
        final int[] currentLow = new int[1];
        final int[] currentHigh = new int[1];

        for(int p=1;p<15;p++)
        {
            final int d=p;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!stackl.empty())
                    {
                        Log.i("iteration", String.valueOf(d));
                        currentLow[0] =stackl.pop();
                        currentHigh[0] =stackh.pop();
                        k[0] =d;
                        if(currentLow[0] < currentHigh[0] && currentLow[0] >=0 && currentHigh[0] <arr.length)
                        {
                            pivot[0] = arr[currentHigh[0]];
                            setColor(currentHigh[0],false);
                            i[0] = currentLow[0] -1; // index of smaller element
                            for (int b = currentLow[0]; b<= currentHigh[0]; b++)
                            {

                                final int j=b;
                                handler2.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(j== currentHigh[0])
                                        {
                                            int temp = arr[i[0] +1];
                                            arr[i[0] +1] = arr[currentHigh[0]];
                                            arr[currentHigh[0]] = temp;
                                            setColor(i[0]+1,false);
                                            setColor(currentHigh[0],true);
                                            stackl.push(i[0] +2);
                                            stackh.push(currentHigh[0]);
                                            stackl.push(currentLow[0]);
                                            stackh.push(i[0]);
                                            swapImg(i[0] +1, currentHigh[0]);
                                        }
                                        else if (arr[j] < pivot[0])
                                        {
                                            i[0] +=1;

                                            // swap arr[i] and arr[j]
                                            int temp = arr[i[0]];
                                            arr[i[0]] = arr[j];
                                            arr[j] = temp;
                                            swapImg(i[0],j);
                                        }
                                    }
                                },100*j);


                            }
                        }

                    }else
                    {
                        Log.i("After iteration", String.valueOf(d));
                    }

                    if(d==14)
                    {
                        inhandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for(int i=0;i<image.length;i++)
                                {
                                    setColor(i,true);
                                }
                            }
                        },500);

                    }

                }
            },1000*p);

        }







    }







    void mergeSort(int[] array) {
        if (array.length>1)
        {
            int[] auxArray=new int[array.length];
            for (int i=0;i<array.length;i++)
            {
                auxArray[i]=array[i];
            }
            sortHelper(0,array.length-1,array,auxArray);
        }

    }

    void sortHelper(int left,int right,int[] array,int[] auxArray)
    {
        if (left<right)
        {
            int mIndex=(left+right)/2;
            sortHelper(left,mIndex,auxArray,array);
            sortHelper(mIndex+1,right,auxArray,array);
            merge(left,mIndex,right,array,auxArray);
        }
    }

    void merge(int left,int mIndex,int right,int[] array,int[] auxArray) {
        int i = left, j = mIndex + 1, idx = left;
        while (i <= mIndex && j <= right) {
            if (auxArray[i] <= auxArray[j]) {
                array[idx] = auxArray[i];
                assignImg(idx,auxArray[i]);
                i += 1;
            } else {
                array[idx] = auxArray[j];
                assignImg(idx,auxArray[j]);
                j += 1;
            }
            idx += 1;
        }
        while (i <= mIndex && idx <= right) {
            array[idx] = auxArray[i];
            assignImg(idx,auxArray[i]);
            i += 1;
            idx += 1;
        }
        while (j <= right && idx <= right) {
            array[idx] = auxArray[j];
            assignImg(idx,auxArray[j]);
            j += 1;
            idx += 1;
        }
    }

        void swapImg(int i,int j)
    {
        image[i].requestLayout();
        int height=image[i].getLayoutParams().height;
        image[j].requestLayout();
        image[i].getLayoutParams().height=image[j].getLayoutParams().height;
        image[j].getLayoutParams().height=height;
    }

    void assignImg(int i,int j)
    {
        image[i].requestLayout();
        image[i].getLayoutParams().height=j;
    }

    void setColor(int i,boolean time)
    {
        if(time)
        {
            image[i].requestLayout();
            image[i].setBackgroundColor(getResources().getColor(R.color.special));
        }
        else
        {
            image[i].requestLayout();
            image[i].setBackgroundColor(getResources().getColor(R.color.violet));
        }


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
                mergeSort(data);
            }
            else if(algo.equals("Heap Sort"))
            {
                heapSort(data);

            }
            else if(algo.equals("Quick Sort"))
            {
                //quickSort(data,0,data.length-1);
                quickSort(data,data.length-1);
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
