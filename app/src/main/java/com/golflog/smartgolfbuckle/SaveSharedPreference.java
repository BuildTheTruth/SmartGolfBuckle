package com.golflog.smartgolfbuckle;

import com.golflog.smartgolfbuckle.vo.GolfClub;
import com.golflog.smartgolfbuckle.vo.User;

import java.util.ArrayList;

public class SaveSharedPreference {
    static private User loggedUser;
    static private int changedTagPosition;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setClubTagByPosition(String tag, int position) {
        ArrayList<GolfClub> mGolfClubList = loggedUser.getGolfClubList();
        mGolfClubList.get(position).setTag(tag);
        loggedUser.setGolfClubList(mGolfClubList);
        changedTagPosition = position;
    }

    public static void setGolfClubList(ArrayList<GolfClub> golfClubList) {
        loggedUser.setGolfClubList(golfClubList);
    }

    public static void setLoggedUser(User user) {
        changedTagPosition = -1;
        if(user == null)
            loggedUser = new User("01012345678", "123", "배형진", "남성", "20대");
        else
            loggedUser = user;
    }

    public static int getChangedTagPosition() {
        return changedTagPosition;
    }
}
