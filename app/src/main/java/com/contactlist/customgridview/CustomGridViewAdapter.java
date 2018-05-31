package com.contactlist.customgridview;

import java.util.ArrayList;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.opensource.widget.RecyclerView;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * 
 * @author manish.s
 *
 */
public class CustomGridViewAdapter extends RecyclerView.Adapter<CustomGridViewAdapter.ContactHolder> {
    private Context mContext;
    private int mLayoutResourceId;
	private ArrayList<Contact> mContacts = null;
    private RequestOptions mOptions = new RequestOptions();
    private int mHeader_w = 200;
    private int mHeader_h = 200;
    private int mHeader_r = 10;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

	public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<Contact> data) {
        mOptions = mOptions.centerCrop();
		this.mContext = context;
        mLayoutResourceId = layoutResourceId;
        this.mContacts = data;
	}
    public void setHeaderSize(int width, int height) {
        mHeader_w = width;
        mHeader_h = height;
    }
    public void setHeaderRadius(int radius) {
        mHeader_r = radius;
    }
    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactHolder holder = new ContactHolder(parent, mLayoutResourceId);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactHolder holder, final int position) {
        MultiTransformation multi = new MultiTransformation(
                new CropTransformation(mHeader_w, mHeader_h),
                new RoundedCornersTransformation(10, 0, RoundedCornersTransformation.CornerType.ALL));

        Glide.with(mContext)
                .load(mContacts.get(position).imageUrl)
                .apply(bitmapTransform(multi))
                .into(holder.headerView);
        holder.nameView.setText(mContacts.get(position).getNickname());

        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void addData(int position, Contact item) {
        mContacts.add(position, item);
        notifyItemInserted(position);
    }
    class ContactHolder extends BaseHolder<Contact> {

        TextView nameView;
        ImageView headerView;

        public ContactHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            nameView = getView(R.id.item_text);
            headerView = getView(R.id.item_image);
        }

        @Override
        public void setData(Contact item) {
        }
    }
}