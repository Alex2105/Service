package com.kerimovalex.test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kerimovalex.test.R;
import com.kerimovalex.test.model.HitModel;
import com.kerimovalex.test.tools.DateConverter;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<HitModel> dateList;

    public DataAdapter(List<HitModel> dateList) {
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HitModel item = dateList.get(position);
        holder.title.setText(item.getTitle());
        DateTime dateTime = new DateTime(item.getCreatedAt());
        holder.date.setText(DateConverter.convertDate(dateTime));
    }

    public void updateDataPagination(List<HitModel> dateList) {
        this.dateList.addAll(dateList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }


    }
}
