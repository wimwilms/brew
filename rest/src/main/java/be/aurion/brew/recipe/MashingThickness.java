package be.aurion.brew.recipe;

public enum MashingThickness {
    THIN(4.1),
    MEDIUM(3.4),
    THICK(2.75);

    private Double factor;

    MashingThickness(Double factor) {
        this.factor = factor;
    }

    public Double getFactor() {
        return factor;
    }
}
