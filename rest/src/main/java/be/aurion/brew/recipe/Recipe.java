package be.aurion.brew.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Recipe {
    private static Double ZERO = 0.0;

    private Double volume;
    private Integer cookingTime;
    private Integer infuseSteps;
    private MashingThickness thickness;

    private List<Fermentable> fermentables;
    private List<Hop> hops;
    private List<Yeast> yeasts;
    private List<Herbs> herbs;

    public Recipe() {
        infuseSteps = 3;
        fermentables = new ArrayList<>();
        hops = new ArrayList<>();
        yeasts = new ArrayList<>();
        herbs = new ArrayList<>();
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public MashingThickness getThickness() {
        return thickness;
    }

    public void setThickness(MashingThickness thickness) {
        this.thickness = thickness;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void addFermentable(Fermentable fermentable) {
        fermentables.add(fermentable);
    }

    public Double calculateMashingWater() {
        return calculateMaltWeight()
                .filter(d -> !d.equals(ZERO))
                .map(maltWeight -> ((maltWeight*thickness.getFactor()*4) + volume/3)/4.2)
                .orElse(ZERO);
    }


    public Double calculateRinshingWater() {
        Double mashingWater = calculateMashingWater();
        if (!mashingWater.equals(ZERO)) {
            return ((volume * 2) - mashingWater)/2 + (mashingWater/3.5);
        }
        return ZERO;
    }

    public Double calculateSG() {
        return 1000 + fermentables.stream()
                .map(fermentable -> fermentable.calculateSG(volume, calculateEfficiency()))
                .reduce((d1, d2) -> d1 + d2)
                .orElse(ZERO);
    }

    public Double calculateEBC() {
        if (calculateSG() >= 1000.3) {
            return (cookingTime * 0.02) + fermentables.stream()
                    .map(fermentable -> fermentable.calculateEBC(volume, calculateEfficiency()))
                    .reduce((d1, d2) -> d1 + d2)
                    .orElse(ZERO);
        }
        return ZERO;
    }

    private Optional<Double> calculateMaltWeight() {
        return fermentables.stream()
                .filter(Fermentable::isMash)
                .map(Fermentable::getWeight)
                .reduce((d1, d2) -> d1 + d2);
    }

    private Double calculateEfficiency() {
        return ((infuseSteps * 2.3) + (88 - (3.58 * thickness.getFactor())))/100.0;
    }
}
