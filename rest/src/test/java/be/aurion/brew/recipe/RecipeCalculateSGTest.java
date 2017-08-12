package be.aurion.brew.recipe;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

public class RecipeCalculateSGTest {
    @Test
    public void testCalculateSG() {
        Fermentable fermentable1 = FermentableBuilder.aFermentable()
                .weight(10.0)
                .extractionFactor(81.0)
                .build();

        Fermentable fermentable2 = FermentableBuilder.aFermentable()
                .weight(2.0)
                .extractionFactor(77.0)
                .build();

        Recipe recipe = RecipeBuilder
                .aRecipe()
                .volume(25.0)
                .thickness(MashingThickness.MEDIUM)
                .build();

        recipe.addFermentable(fermentable1);
        recipe.addFermentable(fermentable2);

        Assertions.assertThat(recipe.calculateSG()).isEqualTo(1118.7, Offset.offset(0.05));
    }
}
