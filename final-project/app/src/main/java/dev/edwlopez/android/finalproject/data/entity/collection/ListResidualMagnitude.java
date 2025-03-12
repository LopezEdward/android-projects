package dev.edwlopez.android.finalproject.data.entity.collection;

import java.util.List;

import dev.edwlopez.android.finalproject.data.entity.ResidualMagnitude;

public class ListResidualMagnitude {
    public ListResidualMagnitude() {
    }

    private List<ResidualMagnitude> magnitudes;

    public ListResidualMagnitude(List<ResidualMagnitude> magnitudes) {
        this.magnitudes = magnitudes;
    }

    public List<ResidualMagnitude> getMagnitudes() {
        return magnitudes;
    }

    public void setMagnitudes(List<ResidualMagnitude> magnitudes) {
        this.magnitudes = magnitudes;
    }
}
