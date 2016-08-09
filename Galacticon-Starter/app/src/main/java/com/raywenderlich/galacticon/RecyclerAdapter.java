package com.raywenderlich.galacticon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP_Spectre on 8/1/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder> {
    private ArrayList<Photo> mPhotos;

    public RecyclerAdapter(ArrayList<Photo> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        //View sub-items
        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mItemDescription;

        //object to be used to populate the View sub-items
        private Photo mPhoto;

        private static final String PHOTO_KEY = "PHOTO";

        //initializing the holder ctor with references to the view layout  sub-items
        public PhotoHolder(View v) {
            super(v);

            mItemImage = (ImageView) v.findViewById(R.id.item_image);
            mItemDate = (TextView) v.findViewById(R.id.item_date);
            mItemDescription = (TextView) v.findViewById(R.id.item_description);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, PhotoActivity.class);
            showPhotoIntent.putExtra(PHOTO_KEY, mPhoto);
            context.startActivity(showPhotoIntent);
        }

        //method to be used by the recyclerview to set the view sub-items
        public void bindPhoto(Photo photo) {
            mPhoto = photo;
            Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);
            mItemDate.setText(photo.getHumanDate());
            mItemDescription.setText(photo.getExplanation());
        }
    }

    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        return new PhotoHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {
        Photo itemPhoto = mPhotos.get(position);
        holder.bindPhoto(itemPhoto);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
