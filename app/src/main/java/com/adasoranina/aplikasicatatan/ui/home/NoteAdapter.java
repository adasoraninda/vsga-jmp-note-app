package com.adasoranina.aplikasicatatan.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.adasoranina.aplikasicatatan.R;
import com.adasoranina.aplikasicatatan.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    @LayoutRes
    private final int layoutRes = R.layout.item_note;
    private final List<Note> notes = new ArrayList<>();
    private final NoteClickListener noteClickListener;

    @SuppressLint("NotifyDataSetChanged")
    public void setListNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    public NoteAdapter(NoteClickListener noteClickListener) {
        this.noteClickListener = noteClickListener;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardRootItem;
        private final TextView textFileName;
        private final TextView textDate;
        private final TextView textDescription;
        private final ImageButton imageCheckBox;
        private final NoteViewModel noteViewModel = new NoteViewModel();

        public NoteViewHolder(View itemView) {
            super(itemView);

            cardRootItem = itemView.findViewById(R.id.card_root_item);
            textFileName = itemView.findViewById(R.id.text_file_name);
            textDate = itemView.findViewById(R.id.text_date);
            textDescription = itemView.findViewById(R.id.text_description);
            imageCheckBox = itemView.findViewById(R.id.image_check);
        }

        public void bind(Note note) {
            noteViewModel.setNote(note);

            textFileName.setText(note.getFileName());
            textDate.setText(note.getDate());
            textDescription.setText(note.getDescription());

            imageCheckBox.setImageResource(noteViewModel.getCheckImageResource());

            imageCheckBox.setOnClickListener(v -> noteClickListener.onCheckClick(note));
            cardRootItem.setOnClickListener(v -> noteClickListener.onItemClick(note));
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
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public interface NoteClickListener {
        void onItemClick(@Nullable Note note);

        void onCheckClick(@Nullable Note note);
    }

}
