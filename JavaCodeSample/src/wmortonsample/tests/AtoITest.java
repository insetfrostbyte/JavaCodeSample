package wmortonsample.tests;

import wmortonsample.AtoI;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AtoITest {

    @Test
    void TestPositiveAtoIGoldenPath() {

        Random rand = new Random();

        for (int x = 0; x < 10000; x++) {
            int base = rand.nextInt(Integer.MAX_VALUE-2) + 1;

            TestDifferentBases(base);
        }
    }

    @Test
    void TestZeroAtoIGoldenPath() {
        TestDifferentBases(0);
    }

    @Test
    void TestNegativeAtoIGoldenPath() {
        Random rand = new Random();

        for (int x = 0; x < 10000; x++) {
            int base = rand.nextInt(Integer.MAX_VALUE-2) + 1;
            TestDifferentBases(base * -1);
        }
    }

    @Test
    void TestMaxIntegerAtoIGoldenPath() {
        Random rand = new Random();

        TestDifferentBases(Integer.MAX_VALUE);
    }

    @Test
    void TestMinIntegerAtoIGoldenPath() {
        Random rand = new Random();

        TestDifferentBases(Integer.MIN_VALUE);
    }

    private void TestDifferentBases(int number) {
        AtoI atoI = new AtoI();

        String numDecValue = Integer.toString(number);
        assertEquals(numDecValue, atoI.ItoA(number, 10), "Decimal should line up" + number);

        String numOctValue = Integer.toOctalString(number);
        assertEquals(numOctValue, atoI.ItoA(number, 8), "Octal number should line up " + number);

        String numHexValue = Integer.toHexString(number);
        assertEquals(numHexValue.toLowerCase(), atoI.ItoA(number, 16).toLowerCase(), "Hex should line up" + number);
    }
}