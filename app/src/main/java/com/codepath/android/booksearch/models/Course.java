package com.codepath.android.booksearch.models;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    private String prefix;
    private String credit;
    private String description;
    private String instructor;

    public String getCourseNumber() {
        return courseNumber;
    }

    private String courseNumber;

    public String getCredit() {
        return credit;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getTitle() { return getPrefix()+getCourseNumber(); }
    // Returns a Course given the expected JSON
    public static Course fromJson(JSONObject jsonObject) {
        Course course = new Course();
        try {
            course.courseNumber = jsonObject.getString("courseNumber");
            course.credit =  jsonObject.getString("credit");
            course.description = jsonObject.getString("description");
            course.prefix = jsonObject.getString("prefix");
            JSONArray tmpa = jsonObject.getJSONArray("instructors");
            JSONObject tmp = tmpa.getJSONObject(0);
            course.instructor = tmp.getString("name");
            Log.d("Courses.Course", "Done making object" + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return course;
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Course> fromJson(JSONArray jsonArray) {
        ArrayList<Course> courses = new ArrayList<Course>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject courseJson = null;
            try {
                courseJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Course course = Course.fromJson(courseJson);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }
}
