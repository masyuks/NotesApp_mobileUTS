package com.example.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private List<Notes> items;
    private OnItemNotesListener listener;

    public interface OnItemNotesListener {
        void onTransactionClicked(int index, Notes item);
    }

    public NotesAdapter(List<Notes> items, OnItemNotesListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView contentText;
        TextView timeText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            contentText = itemView.findViewById(R.id.text_content);
            timeText = itemView.findViewById(R.id.text_time);
        }
        public void bind(final int index, final Notes item) {
            titleText.setText(item.getTitle());
            contentText.setText(item.getContent());
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm a");
            timeText.setText(sdf.format(dt));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }
}
