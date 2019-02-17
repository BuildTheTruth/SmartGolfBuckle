package com.golflog.smartgolfbuckle;

import com.golflog.smartgolfbuckle.vo.GolfClub;
import com.golflog.smartgolfbuckle.vo.User;

import java.util.ArrayList;

public class SaveSharedPreference {
    static private User loggedUser;

    public static void setClubTagByPosition(String id, int position) {
        ArrayList<GolfClub> mGolfClubList = loggedUser.getGolfClubList();
        mGolfClubList.get(position).setTagID(id);
        mGolfClubList.get(position).setTagChanged(true);
        loggedUser.setGolfClubList(mGolfClubList);
    }

    public static void setGolfClubList(ArrayList<GolfClub> golfClubList) {
        loggedUser.setGolfClubList(golfClubList);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User user) {
        if (user == null)
            loggedUser = new User("01012345678", "123", "배형진", "남성", "20대");
        else
            loggedUser = user;
    }
}
