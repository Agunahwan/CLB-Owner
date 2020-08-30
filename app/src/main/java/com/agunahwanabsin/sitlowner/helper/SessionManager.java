package com.agunahwanabsin.sitlowner.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.agunahwanabsin.sitlowner.model.Owner;
import com.agunahwanabsin.sitlowner.ui.login.LoginActivity;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class SessionManager {
    // Shared Preferences
    SharedPreferences mPrefs;

    // Editor for Shared preferences
    SharedPreferences.Editor mPrefsEditor;

    // Context
    Context mContext;

    // Sharedpref file name
    private static final String PREF_NAME = "SitlPref";

    // Key name (make variable public to access from outside)
    public static final String KEY_NAME = "User";

    // Constructor
    public SessionManager(Context context) {
        this.mContext = context;
        mPrefs = mContext.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        mPrefsEditor = mPrefs.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(Owner owner) {
        Gson gson = new Gson();
        String json = gson.toJson(owner);
        mPrefsEditor.putString(KEY_NAME, json);
        mPrefsEditor.commit();
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return mPrefs.contains(KEY_NAME);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);
        }
    }

    /**
     * Get stored session data
     */
    public Owner getUserDetails() {
        Gson gson = new Gson();
        String json = mPrefs.getString(KEY_NAME, "");
        Owner owner = gson.fromJson(json, Owner.class);

        // return user
        return owner;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        mPrefsEditor.clear();
        mPrefsEditor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }
}
