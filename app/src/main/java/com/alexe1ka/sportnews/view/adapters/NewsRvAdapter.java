package com.alexe1ka.sportnews.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.model.events.Event;
import com.alexe1ka.sportnews.model.events.Events;
import com.alexe1ka.sportnews.view.activities.EventDescriptionActivity;
import com.alexe1ka.sportnews.view.fragments.EventDescriptionFragment;

public class NewsRvAdapter extends RecyclerView.Adapter<NewsRvAdapter.ViewHolder> {
    private static final String TAG = NewsRvAdapter.class.getSimpleName();
    private Events mEvents;
    private Context mContext;

    public NewsRvAdapter(Context context) {
        mContext = context;
    }

    public Events getEvents() {
        return mEvents;
    }

    public void setEvents(Events events) {
        Log.d(TAG, "setEvents: Adapter set events");
        if (events != mEvents) {
            mEvents = events;
            notifyDataSetChanged();
        }
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
        holder.bind(event);
    }


    @Override
    public int getItemCount() {
        return mEvents != null ? mEvents.getEvents().size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mCoefficientTv;
        TextView mTimeTv;
        TextView mPlaceTv;
        TextView mPreviewTv;
        CardView mCardView;
        String url;


        ViewHolder(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.title_tv);
            mCoefficientTv = itemView.findViewById(R.id.coefficient_tv);
            mTimeTv = itemView.findViewById(R.id.time_tv);
            mPlaceTv = itemView.findViewById(R.id.place_tv);
            mPreviewTv = itemView.findViewById(R.id.preview_tv);
            mCardView = itemView.findViewById(R.id.event_card_view);
            mCardView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, EventDescriptionActivity.class);
                intent.putExtra(EventDescriptionFragment.URL_EXTRA, url);
                mContext.startActivity(intent);
            });
        }

        public void bind(Event event) {
            mTitleTv.setText(event.getTitle());

            //c сервера приходит "1.18 коэффициент" - трансформируем в "коэффициент =1.18"
            String[] coeffSplit = event.getCoefficient().split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(coeffSplit[1]);
            stringBuilder.append(" = ");
            stringBuilder.append(coeffSplit[0]);


            mCoefficientTv.setText(stringBuilder.toString());
            mTimeTv.setText(event.getTime());
            mPlaceTv.setText(event.getPlace());
            mPreviewTv.setText(event.getPreview());
            url = event.getArticle();
        }
    }
}
