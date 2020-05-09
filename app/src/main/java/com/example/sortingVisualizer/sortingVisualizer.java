package com.example.sortingVisualizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sortingVisualizer.settings.SettingsActivity;
import com.example.sortingVisualizer.sortingAlgorithms.BubbleSort;
import com.example.sortingVisualizer.sortingAlgorithms.HeapSort;
import com.example.sortingVisualizer.sortingAlgorithms.InsertionSort;
import com.example.sortingVisualizer.sortingAlgorithms.MergeSort;
import com.example.sortingVisualizer.sortingAlgorithms.QuickSort;
import com.example.sortingVisualizer.sortingAlgorithms.SelectionSort;

import java.util.ArrayList;
import java.util.Random;

public class sortingVisualizer extends AppCompatActivity {

    // Initial offset for rectangle.
    private static final int OFFSET = 120;
    // Multiplier for randomizing color.
    private static final int MULTIPLIER = 100;

    // The Canvas object stores information on WHAT to draw
    // onto its associated bitmap.
    // For example, lines, circles, text, and custom paths.
    private static Canvas mCanvas;

    // The Paint object stores HOW to draw.
    // For example, what color, style, line thickness, or text size.
    // The Paint class offers a rich set of coloring and drawing options.
    private static Paint mPaint = new Paint();

    // Create a Paint object for underlined text.
    // The Paint clas offers a full complement of typographical styling methods.
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);

    // The bitmap represents the pixels that will be displayed.
    private static Bitmap mBitmap;

    // The view is the container for the bitmap.
    // Layout on the screen and all user interaction is through the view.
    public static ImageView mImageView;
    public static TextView mTextView;
    public static TextView mTextViewAlgorithm;
    public static Button mButton;

    private static Rect[] mRect;
    // Distance of rectangle from edge of canvas.
    private int mOffset = OFFSET;
    // Bounding box for text.
    private Rect mBounds = new Rect();

    private static int mColorBackground;
    private static int mColorFinal;
    private int mColorAccent;

    private static int size;
    public static ArrayList<Integer> data = new ArrayList<>();
    static Random random = new Random();
    private static int maxWidth;
    private static int maxHeight;
    public static int speed;
    public static boolean sorted = false;
    private static String algorithm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize);
        getSupportActionBar().setTitle(sortingVisualizer.class.getSimpleName());

        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorGreen, null);
        mColorFinal = ResourcesCompat.getColor(getResources(),
                R.color.violet, null);

        // Set properties of the Paint used to draw on the canvas.
        mPaint.setColor(mColorBackground);
        mPaintText.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.colorPrimaryDark, null)
        );
        mPaintText.setTextSize(70);

        // Get a reference to the ImageView.
        mImageView = (ImageView) findViewById(R.id.myimageview);
        mTextView = findViewById(R.id.mytextview);
        mButton = findViewById(R.id.button);
        mTextViewAlgorithm = findViewById(R.id.tv_algorithm);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setVisibility(View.GONE);
                mImageView.setVisibility(View.VISIBLE);
                mButton.setClickable(true);
                drawSomething(mImageView);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton.setClickable(true);
                initializeArray();
                drawSomething(mImageView);
            }
        });


        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean vis_mode = preferences.getBoolean(SettingsActivity.KEY_SPEED, false);
        if (vis_mode) {
            speed = 40;
        } else {
            speed = 100;
        }
        algorithm = preferences.getString(SettingsActivity.KEY_ALGO, "Bubble Sort");
        mTextViewAlgorithm.setText(algorithm);
        size = preferences.getInt(SettingsActivity.KEY_SIZE, 15);
        mTextView.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.INVISIBLE);
        mButton.setClickable(false);
        if (data.size() != 0) {
            initializeArray();
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putIntegerArrayList("array", data);
        for (int val : data) {
            Log.d("TAG", "putting: " + String.valueOf(val));
        }


    }

    private static void initializeArray() {

        data.clear();
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(maxHeight);
            data.add(value);
            //Log.d("TAG","value is: "+String.valueOf(value));
        }

    }

    public static void drawSomething(View view) {
        //Log.d("TAG","imageview clicked");
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        if (sorted) {
            mPaint.setColor(mColorFinal);
        } else {
            mPaint.setColor(mColorBackground);
        }


        maxHeight = vHeight - 200;
        maxWidth = vWidth - 200;
        mRect = new Rect[size];
        if (data.size() == 0) {
            initializeArray();
        }

        mBitmap = Bitmap.createBitmap(
                vWidth, vHeight, Bitmap.Config.ARGB_8888);
        // Associate the bitmap to the ImageView.
        mImageView.setImageBitmap(mBitmap);
        // Create a Canvas with the bitmap.
        mCanvas = new Canvas(mBitmap);

        int width = maxWidth / size;
        //Log.d("TAG", String.valueOf(width));
        for (int i = 0; i < data.size(); i++) {
            //Log.d("TAG", "drawing rect: "+String.valueOf(i+1));
            mRect[i] = new Rect();
            mRect[i].set(
                    100 + width * i, (maxHeight + 100) - data.get(i), 100 + width * (i + 1), maxHeight + 100);
            mCanvas.drawRect(mRect[i], mPaint);
        }
        // Invalidate the view, so that it gets redrawn.
        view.invalidate();
        if (sorted) {
            sorted = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void sort(View view) {
//        Log.d("TAG", "speed:"+String.valueOf(speed));
//        Log.d("TAG", "algorithm:"+algorithm);
//        Log.d("TAG", "size:"+size);
        mImageView.setClickable(false);
        mButton.setClickable(false);
        switch (algorithm) {
            case "Insertion Sort":
                new InsertionSort().execute();
                break;
            case "Selection Sort":
                new SelectionSort().execute();
                break;
            case "Bubble Sort":
                new BubbleSort().execute();
                break;
            case "Quick Sort":
                new QuickSort().execute();
                break;
            case "Heap Sort":
                new HeapSort().execute();
                break;
            case "Merge Sort":
                new MergeSort().execute();
                break;
        }

    }

}
