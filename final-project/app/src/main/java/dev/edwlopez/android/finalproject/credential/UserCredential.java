package dev.edwlopez.android.finalproject.credential;

import android.os.Parcel;
import android.os.Parcelable;
import android.system.SystemCleaner;

import androidx.annotation.NonNull;

import java.lang.ref.Cleaner;

import dev.edwlopez.android.finalproject.data.entity.AuthUser;
import dev.edwlopez.android.finalproject.data.entity.User;

public class UserCredential implements Parcelable {
    private String username;
    private String password;

    private UserCredential (String username, String password) {
        this.username = username;
        this.password = password;
    }

    private UserCredential(Parcel in) {
        //in.setDataPosition(0);
        username = in.readString();
        //in.setDataPosition(1);
        password = in.readString();
    }

    public final AuthUser toAuthEntity () {
        return new AuthUser(username, password);
    }

    public static final Creator<UserCredential> CREATOR = new Creator<>() {
        @Override
        public UserCredential createFromParcel(Parcel in) {
            return new UserCredential(in);
        }

        @Override
        public UserCredential[] newArray(int size) {
            return new UserCredential[size];
        }
    };

    public boolean isNull () {
        return this.username == null && this.password == null;
    }

    public final static UserCredential fromEntity (User user) {
        if (user == null) return new UserCredential(null, null);

        return new UserCredential(user.getUsername(), user.getPassword());
    }

    public final boolean assertCredentials (String usr, String pw) {
        usr = usr.trim();
        pw = pw.trim();

        return this.username.equals(usr) && this.password.equals(pw);
    }

    public final boolean isEqual (UserCredential another) {
        return this.username.equals(another.username) && this.password.equals(another.password);
    }

    public final boolean assertUsername (String usr) {
        return this.username.equals(usr);
    }

    public final int passwordLength () {
        return password.length();
    }

    public final void destroy () {
        this.username = null;
        this.password = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        //dest.setDataSize(2);
        //dest.setDataPosition(0);
        dest.writeString(username);
        //dest.setDataPosition(1);
        dest.writeString(password);
    }
}
