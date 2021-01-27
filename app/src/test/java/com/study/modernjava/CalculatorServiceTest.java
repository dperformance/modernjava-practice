package com.study.modernjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {
    @Test
    public void testCalculate() throws Exception {
        CalculatorService calculatorService = new CalculatorService();
        final int actual = calculatorService.calculate('+', 1, 1);
//        assertTrue();
//        assertThat(actual).isEqualTo(2);
    }
}
