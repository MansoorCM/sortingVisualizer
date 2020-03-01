package com.example.sortingVisualizer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>
{
    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, LinkedList<String> mWordList) {
        mInflater=LayoutInflater.from(context);
        this.mWordList = mWordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerview_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {

        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        public WordViewHolder(@NonNull View itemView, WordListAdapter mAdapter) {
            super(itemView);
            this.wordItemView = itemView.findViewById(R.id.textView);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = mWordList.get(mPosition);
// Change the word in the mWordList.

                Context context=view.getContext();
                Intent intent=new Intent(context, SortAlgActivity.class);
                if(element.equals("Bubble Sort"))
                {
                    intent.putExtra("algo","Bubble Sort");
                }else if(element.equals("Insertion Sort"))
                {
                    intent.putExtra("algo","Insertion Sort");
                }else if(element.equals("Selection Sort"))
                {
                    intent.putExtra("algo","Selection Sort");
                }else if(element.equals("Quick Sort"))
                {
                    intent.putExtra("algo","Quick Sort");
                }else if(element.equals("Heap Sort"))
                {
                    intent.putExtra("algo","Heap Sort");
                }else if(element.equals("Merge Sort"))
                {
                    intent.putExtra("algo","Merge Sort");
                }


                context.startActivity(intent);




        }
    }
}
