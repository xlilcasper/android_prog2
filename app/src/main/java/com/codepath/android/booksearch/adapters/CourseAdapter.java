package com.codepath.android.booksearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.models.Course;

import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<Course> {
    // View lookup cache
    private static class ViewHolder {
        public TextView tvTitle;
        public TextView tvAuthor;
    }

    public CourseAdapter(Context context, ArrayList<Course> aCourses) {
        super(context, 0, aCourses);
    }

    // Translates a particular `Course` given a position
    // into a relevant row within an AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Course course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_course, parent, false);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvAuthor = (TextView)convertView.findViewById(R.id.tvAuthor);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(course.getTitle());
        viewHolder.tvAuthor.setText(course.getInstructor());
        // Return the completed view to render on screen
        return convertView;
    }
}
