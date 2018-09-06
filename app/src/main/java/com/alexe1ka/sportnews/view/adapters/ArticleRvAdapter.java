package com.alexe1ka.sportnews.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexe1ka.sportnews.R;
import com.alexe1ka.sportnews.model.articles.Article;

import java.util.List;

public class ArticleRvAdapter extends RecyclerView.Adapter<ArticleRvAdapter.ViewHolder> {
    private List<Article> mArticleList;
    private Context mContext;

    public ArticleRvAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = mArticleList.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setArticleList(List<Article> articleList) {
        mArticleList = articleList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mHeaderTv;
        TextView mTextTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mHeaderTv = itemView.findViewById(R.id.art_header_tv);
            mTextTv = itemView.findViewById(R.id.art_text_tv);
        }

        public void bind(Article article) {
            mHeaderTv.setText(article.getHeader());
            mTextTv.setText(article.getText());
        }
    }
}
