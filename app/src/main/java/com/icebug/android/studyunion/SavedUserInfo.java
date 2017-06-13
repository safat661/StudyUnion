package com.icebug.android.studyunion;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by nafis on 11-Jun-17.
 */

public class SavedUserInfo {

    public static Preferences userSaved;


    public SavedUserInfo(User user){

        userSaved.put("User",user.getName());

        try {
            userSaved.flush();
        }catch(BackingStoreException e){

        }
    }


}
