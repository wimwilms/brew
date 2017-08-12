package be.aurion.brew.recipe;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

public class RecipeCalculatingWaterTest {
    @Test
    public void testCalculateForMediumThicknessAnd25LAnd10KgsOfMalts() {
        Fermentable fermentable = FermentableBuilder.aFermentable()
                .weight(10.0)
                .isMash(true)
                .build();

        Recipe recipe = RecipeBuilder.aRecipe()
                .volume(25.0)
                .thickness(MashingThickness.MEDIUM)
                .build();
        recipe.addFermentable(fermentable);
        Assertions.assertThat(recipe.calculateMashingWater()).isEqualTo(34.4, Offset.offset(0.05));
        Assertions.assertThat(recipe.calculateRinshingWater()).isEqualTo(17.6, Offset.offset(0.05));
    }

    @Test
    public void testCalculateForThickThicknessAnd25LAnd10KgsOfMalts() {
        Fermentable fermentable = FermentableBuilder.aFermentable()
                .weight(10.0)
                .isMash(true)
                .build();

        Recipe recipe = RecipeBuilder.aRecipe()
                .volume(25.0)
                .thickness(MashingThickness.THICK)
                .build();
        recipe.addFermentable(fermentable);
        Assertions.assertThat(recipe.calculateMashingWater()).isEqualTo(28.2, Offset.offset(0.05));
        Assertions.assertThat(recipe.calculateRinshingWater()).isEqualTo(19, Offset.offset(0.05));
    }

    @Test
    public void testCalculateForThinThicknessAnd25LAnd10KgsOfMalts() {
        Fermentable fermentable = FermentableBuilder.aFermentable()
                .weight(10.0)
                .isMash(true)
                .build();

        Recipe recipe = RecipeBuilder.aRecipe()
                .volume(25.0)
                .thickness(MashingThickness.THIN)
                .build();
        recipe.addFermentable(fermentable);
        Assertions.assertThat(recipe.calculateMashingWater()).isEqualTo(41, Offset.offset(0.05));
        Assertions.assertThat(recipe.calculateRinshingWater()).isEqualTo(16.2, Offset.offset(0.05));
    }

    @Test
    public void testCalculateOnlyUsesFermentablesThatAreMash() {
        Fermentable fermentable = FermentableBuilder.aFermentable()
                .weight(10.0)
                .isMash(true)
                .build();

        Fermentable nonMashFermentable = FermentableBuilder.aFermentable()
                .weight(10.0)
                .isMash(false)
                .build();

        Recipe recipe = RecipeBuilder.aRecipe()
                .volume(25.0)
                .thickness(MashingThickness.THICK)
                .build();
        recipe.addFermentable(fermentable);
        recipe.addFermentable(nonMashFermentable);
        Assertions.assertThat(recipe.calculateMashingWater()).isEqualTo(28.2, Offset.offset(0.05));
        Assertions.assertThat(recipe.calculateRinshingWater()).isEqualTo(19, Offset.offset(0.05));
    }

    @Test
    public void testCalculateForMaltWeightOf0() {
        Fermentable fermentable = FermentableBuilder.aFermentable()
                .weight(0.0)
                .isMash(true)
                .build();

        Recipe recipe = new Recipe();
        recipe.setVolume(25.0);
        recipe.setThickness(MashingThickness.THICK);
        recipe.addFermentable(fermentable);
        Assertions.assertThat(recipe.calculateMashingWater()).isZero();
        Assertions.assertThat(recipe.calculateRinshingWater()).isZero();
    }
}
