package be.aurion.brew.recipe;

public class Fermentable extends Ingredient {
    private Double weight;
    private Double extractionFactor;
    private Double ebc;
    private boolean isMash;

    public Fermentable(Double weight, Double extractionFactor, Double ebc, boolean isMash) {
        this.weight = weight;
        this.extractionFactor = extractionFactor;
        this.ebc = ebc;
        this.isMash = isMash;
    }

    public Double getWeight() {
        return weight;
    }

    public boolean isMash() {
        return isMash;
    }

    public Double calculateSG(Double volume, Double efficiency) {
        if (!volume.equals(0.0) && !weight.equals(0.0)) {
            return (extractionFactor * weight * efficiency * 3.722)/volume;
        }
        return 0.0;
    }

    public Double calculateEBC(Double volume, Double efficiency) {
        if (!volume.equals(0.0) && !weight.equals(0.0)) {
            return (extractionFactor * weight * efficiency * ebc)/(volume * 8.6);
        }
        return 0.0;
    }
}
