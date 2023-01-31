package com.mirea.taxiapp.adapter;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.taxiapp.R;
import com.mirea.taxiapp.data.CarNote;

import java.util.ArrayList;
import java.util.List;

public class CarNoteAdapter extends RecyclerView.Adapter<CarNoteAdapter.CarNotesViewHolder> {

    private List<CarNote> carNotes = new ArrayList<>();

    private onCatNoteClickListener onCatNoteClickListener;

    public void setCarNotes(List<CarNote> carNotes) {
        this.carNotes = carNotes;
    }

    public void setOnCatNoteClickListener(CarNoteAdapter.onCatNoteClickListener onCatNoteClickListener) {
        this.onCatNoteClickListener = onCatNoteClickListener;
    }

    public List<CarNote> getCarNotes() {
        return new ArrayList<>(carNotes);
    }

    @NonNull
    @Override
    public CarNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cer_list_item,
                parent,
                false
        );
        return new CarNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarNotesViewHolder holder, int position) {

        CarNote carNote = carNotes.get(position);
        holder.textViewTitleCar.setText(carNote.getTitleCar());
        holder.textViewSaleCar.setText(carNote.getSaleCar());
        holder.textViewSpeedCar.setText(carNote.getSpeedCar());

        int colorResultId;
        switch (carNote.getPriority()){
            case 0:
                colorResultId = R.color.green;
                break;
            case 1:
                colorResultId = R.color.orange;
                break;
            default:
                colorResultId = R.color.red;
        }

//        holder.cardView.setBackgroundColor(
//                ContextCompat.getColor(holder.itemView.getContext(), colorResultId)
//        );

        holder.cardView.getBackground().setColorFilter(
                holder.itemView.getContext().getColor(colorResultId),
                PorterDuff.Mode.MULTIPLY
        );

//        holder.cardView.setBackgroundTintList(
//                ContextCompat.getColor(holder.itemView.getContext(), colorResultId)
//        );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCatNoteClickListener != null){
                    onCatNoteClickListener.onCLickCarNote(carNote);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return carNotes.size();
    }

    public static class CarNotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitleCar;
        private TextView textViewSaleCar;
        private TextView textViewSpeedCar;
        private CardView cardView;

        public CarNotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleCar = itemView.findViewById(R.id.textViewTitleCar);
            textViewSaleCar = itemView.findViewById(R.id.textViewSaleCar);
            textViewSpeedCar = itemView.findViewById(R.id.textViewSpeedCar);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    interface onCatNoteClickListener{
        void onCLickCarNote(CarNote carNote);
    }
}
