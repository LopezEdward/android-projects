package dev.edwlopez.android.finalproject.view.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.edwlopez.android.finalproject.R;

public class TabFragment extends Fragment {
    private int layoutId = -1;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId == -1) throw new IllegalArgumentException("Must be put the layout id");

        return inflater.inflate(layoutId, container, false);
    }

    public void setLayoutId (int id) {
        this.layoutId = id;
    }
}
