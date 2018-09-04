package com.alexe1ka.sportnews.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.model.events.Event;
import com.alexe1ka.sportnews.model.events.Events;

public class NewsRvAdapter extends RecyclerView.Adapter<NewsRvAdapter.ViewHolder> {
    private Events mEvents;
    private Context mContext;

    public NewsRvAdapter( Context context) {
        mContext = context;
    }

    public Events getEvents() {
        return mEvents;
    }

    public void setEvents(Events events) {
        this.mEvents = events;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = mEvents.getEvents().get(position);
        holder.mTitleTv.setText(event.getTitle());
        holder.mCoefficientTv.setText(event.getCoefficient());
        holder.mTimeTv.setText(event.getTime());
        holder.mPlaceTv.setText(event.getPlace());
        holder.mPreviewTv.setText(event.getPreview());
    }


    @Override
    public int getItemCount() {
        return mEvents.getEvents().size();
    }

    //todo заимплементить сюда онклик когда будет все готово с первым экраном
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mCoefficientTv;
        TextView mTimeTv;
        TextView mPlaceTv;
        TextView mPreviewTv;


        ViewHolder(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.title_tv);
            mCoefficientTv = itemView.findViewById(R.id.coefficient_tv);
            mTimeTv = itemView.findViewById(R.id.time_tv);
            mPlaceTv = itemView.findViewById(R.id.place_tv);
            mPreviewTv = itemView.findViewById(R.id.preview_tv);
        }
    }
}
