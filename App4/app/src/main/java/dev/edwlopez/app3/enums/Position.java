package dev.edwlopez.app3.enums;

public enum Position {
    PASAJE(-0.1), JIRON(0.05), CALLE(0.05), AVENIDA(0.1), FRENTE_PARQUE(0.18);

    private double variation;
    Position (double variation) {
        this.variation = variation;
    }

    public double getVariation () {
        return variation;
    }
}
