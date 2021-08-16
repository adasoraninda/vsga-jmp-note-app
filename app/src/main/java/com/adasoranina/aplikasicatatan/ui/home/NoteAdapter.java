package com.adasoranina.aplikasicatatan.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    @LayoutRes
    private final int layoutRes = R.layout.item_note;
    private final NoteClickListener noteClickListener;

    public NoteAdapter(NoteClickListener noteClickListener) {
        super(new DiffCallback());
        this.noteClickListener = noteClickListener;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardRootItem;
        private final TextView textFileName;
        private final TextView textDate;
        private final TextView textDescription;

        public NoteViewHolder(View itemView) {
            super(itemView);

            cardRootItem = itemView.findViewById(R.id.card_root_item);
            textFileName = itemView.findViewById(R.id.text_file_name);
            textDate = itemView.findViewById(R.id.text_date);
            textDescription = itemView.findViewById(R.id.text_description);
        }

        public void bind(Note note) {
            textFileName.setText(note.getFileName());
            textDate.setText(note.getDate());
            textDescription.setText(note.getDescription());

            cardRootItem.setOnClickListener(v -> noteClickListener.onItemClick(note.getFileName()));
            cardRootItem.setOnLongClickListener(v -> {
                noteClickListener.onLongItemClick(note);
                return true;
            });
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutRes, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public interface NoteClickListener {
        void onItemClick(@Nullable String fileName);

        void onLongItemClick(@Nullable Note note);
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<Note> {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getFileName().equals(newItem.getFileName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    }

}
