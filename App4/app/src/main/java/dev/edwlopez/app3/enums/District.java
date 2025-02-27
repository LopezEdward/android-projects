package dev.edwlopez.app3.enums;

public enum District {
    LIMA_CERCADO("Lima Cercado", 450), LA_VICTORIA("La Victoria", 550),
    LINCE("Lince", 650), JESUS_MARIA("Jesús María", 670),
    PUEBLO_LIBRE("Pueblo Libre", 750);

    private String realValue;
    private double price;
    District (String realValue, double priceToSquareMeters) {
        this.realValue = realValue;
        this.price = priceToSquareMeters;
    }

    public String getRealValue () {
        return this.realValue;
    }

    public double getPricePerSquareMeters() {
        return this.price;
    }
}
