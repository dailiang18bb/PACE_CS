package com.pace.cs639spring.week3adapterviewsteps;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kachi on 2/10/18.
 */

public class TestAdapter extends BaseAdapter {

    Context mContext;
    List<ExampleObject> mExamples;

    TestAdapter(Context context, List<ExampleObject> exampleObjectList) {
        mContext = context;
        mExamples = exampleObjectList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_item, null);
            ViewHolder viewHolder = new ViewHolder((ImageView) convertView.findViewById(R.id.image),
                    (TextView) convertView.findViewById(R.id.text));
            convertView.setTag(viewHolder);
        }

        ExampleObject object = (ExampleObject) getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.mImageView.setBackgroundColor(object.mColor);
        viewHolder.mTextView.setText(object.mName);
        return convertView;
    }

    @Override
    public int getCount() {
        return mExamples.size();
    }

    @Override
    public Object getItem(int position) {
        return mExamples.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        ViewHolder(ImageView imageView, TextView textView) {
            mImageView = imageView;
            mTextView = textView;
        }

    }
}
