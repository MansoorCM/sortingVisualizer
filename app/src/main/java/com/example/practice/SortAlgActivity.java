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
    int k;
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
    void bubbleSort(final int arr[]) {
        final int n = arr.length;
        final ViewGroup viewGroup = (ViewGroup) number4.getParent();
        for (int b = 0; b < n - 1; b++) {
            final int i = b;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String s= "iteration "+String.valueOf(i);
                    Toast.makeText(SortAlgActivity.this, s, Toast.LENGTH_SHORT).show();
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
                                    k = j;
                                    viewGroup.removeView(image[k]);
                                    k += 1;
                                    while (k < n) {
                                        viewGroup.removeView(image[k]);
                                        k += 1;
                                    }

                                    viewGroup.addView(image[j + 1]);
                                    viewGroup.addView(image[j]);
                                    k = j + 2;
                                    while (k < n) {
                                        viewGroup.addView(image[k]);
                                        k += 1;
                                    }
                                    View view = image[j];
                                    image[j] = image[j + 1];
                                    image[j + 1] = view;

                                }

                            }
                        },500*j);
                        // image[j].setBackgroundColor(getResources().getColor(R.color.special));
                    }
                }

            }, 1000*i);

            String res = String.format("%d %d %d %d %d", arr[0], arr[1], arr[2], arr[3], arr[4]);
            Log.i("sorted array", res);
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
            else
            {
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
