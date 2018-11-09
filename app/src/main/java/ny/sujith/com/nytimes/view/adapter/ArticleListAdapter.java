package ny.sujith.com.nytimes.view.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import ny.sujith.com.nytimes.BR;
import ny.sujith.com.nytimes.R;
import ny.sujith.com.nytimes.model.Result;
import ny.sujith.com.nytimes.view.NYArticleDetailActivity;
import ny.sujith.com.nytimes.view.NYArticleDetailFragment;
import ny.sujith.com.nytimes.view.HomeActivity;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArtcleViewHolder> {
    private List<Result> resultList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ArtcleViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;


        public ArtcleViewHolder(View v) {
            super(v);

            binding = DataBindingUtil.bind(v);


        }

        public ViewDataBinding getBinding() {

            return binding;

        }
    }

    public FragmentManager fragmentManager;
    public HomeActivity mainActivity;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ArticleListAdapter(List<Result> resultList, HomeActivity mainActivity,
                              FragmentManager fragmentManager) {
        this.resultList = resultList;
        this.fragmentManager = fragmentManager;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ArtcleViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.layout_fragment_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ArtcleViewHolder vh = new ArtcleViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ArtcleViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Result result = resultList.get(position);
        holder.getBinding().setVariable(BR.news, result);

        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, NYArticleDetailActivity.class);
                    intent.putExtra(NYArticleDetailFragment.ARG_ITEM_URL, resultList.get(position)
                            .getUrl());
                    intent.putExtra(NYArticleDetailFragment.ARG_ITEM_TITLE, resultList.get(position)
                            .getTitle());

                    context.startActivity(intent);


            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return resultList.size();
    }

}
