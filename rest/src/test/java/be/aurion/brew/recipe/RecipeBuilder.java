package be.aurion.brew.recipe;

public final class RecipeBuilder {
    private Double volume;
    private MashingThickness thickness;
    private Integer cookingTime;

    private RecipeBuilder() {
    }

    public static RecipeBuilder aRecipe() {
        return new RecipeBuilder();
    }

    public RecipeBuilder volume(Double volume) {
        this.volume = volume;
        return this;
    }

    public RecipeBuilder thickness(MashingThickness thickness) {
        this.thickness = thickness;
        return this;
    }

    public RecipeBuilder cookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public Recipe build() {
        Recipe recipe = new Recipe();
        recipe.setVolume(volume);
        recipe.setThickness(thickness);
        recipe.setCookingTime(cookingTime);
        return recipe;
    }
}
