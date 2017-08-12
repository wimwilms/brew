package be.aurion.brew.recipe;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

public class FermentableCalculateEBCTest {
    @Test
    public void testCalculateForVolumeOf0() {
        Fermentable fermentable = FermentableBuilder
                .aFermentable()
                .weight(10.0)
                .extractionFactor(0.5).build();

        Assertions.assertThat(fermentable.calculateSG(0.0, 50.)).isZero();
    }

    @Test
    public void testCalculateForWeightOf0() {
        Fermentable fermentable = FermentableBuilder
                .aFermentable()
                .weight(0.0)
                .extractionFactor(0.5).build();

        Assertions.assertThat(fermentable.calculateSG(25.0, 50.)).isZero();
    }

    @Test
    public void testCalculate() {
        Fermentable fermentable = FermentableBuilder
                .aFermentable()
                .weight(10.0)
                .ebc(3.5)
                .extractionFactor(81.0).build();

        Assertions.assertThat(fermentable.calculateEBC(25.0, 0.82728)).isEqualTo(10.91, Offset.offset(0.05));
    }
}
