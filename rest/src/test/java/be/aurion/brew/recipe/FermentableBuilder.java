package be.aurion.brew.recipe;

public final class FermentableBuilder {
    private Double weight;
    private Double extractionFactor;
    private Double ebc;
    private boolean isMash;

    private FermentableBuilder() {
        this.weight = 0.0;
        this.extractionFactor = 0.0;
        this.ebc = 0.0;
    }

    public static FermentableBuilder aFermentable() {
        return new FermentableBuilder();
    }

    public FermentableBuilder extractionFactor(Double extractionFactor) {
        this.extractionFactor = extractionFactor;
        return this;
    }

    public FermentableBuilder ebc(Double ebc) {
        this.ebc = ebc;
        return this;
    }

    public FermentableBuilder weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public FermentableBuilder isMash(boolean isMash) {
        this.isMash = isMash;
        return this;
    }

    public Fermentable build() {
        Fermentable fermentable = new Fermentable(weight, extractionFactor, ebc, isMash);
        return fermentable;
    }
}
