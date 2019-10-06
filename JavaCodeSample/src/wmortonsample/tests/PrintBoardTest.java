package wmortonsample.tests;

import wmortonsample.PrintBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintBoardTest {

    @Test
    void ProvidedTest() {
        int board[][] = new int[][]
                {
                        {2,3,4,8},
                        {5,7,9,12},
                        {1,0,6,10}
                };

        String result = "2, 3, 4, 8, 12, 10, 6, 0, 1, 5, 7, 9";
        TestBoardAndOutput(board, result);
    }

    @Test
    void Test4x4() {
        int board[][] = new int[][]
                {
                        {2,3,4,8},
                        {5,7,9,12},
                        {1,0,6,10},
                        {67,4,87,100}
                };

        String result = "2, 3, 4, 8, 12, 10, 100, 87, 4, 67, 1, 5, 7, 9, 6, 0";
        TestBoardAndOutput(board, result);
    }

    @Test
    void Test2x2() {
        int board[][] = new int[][]
                {
                        {2,3},
                        {5,7}
                };

        String result = "2, 3, 7, 5";
        TestBoardAndOutput(board, result);
    }

    @Test
    void Test1x4() {
        int board[][] = new int[][]
                {
                        {2,3,4,8}
                };

        String result = "2, 3, 4, 8";
        TestBoardAndOutput(board, result);
    }

    @Test
    void Test4x1() {
        int board[][] = new int[][]
                {
                        {2},
                        {5},
                        {1},
                        {67}
                };

        String result = "2, 5, 1, 67";
        TestBoardAndOutput(board, result);
    }

    @Test
    void Test4x4Negative() {
        int board[][] = new int[][]
                {
                        {-2,-3,-4,-8},
                        {-5,-7,-9,-12},
                        {-1,-0,-6,-10},
                        {-67,-4,-87,-100}
                };

        String result = "-2, -3, -4, -8, -12, -10, -100, -87, -4, -67, -1, -5, -7, -9, -6, 0";
        TestBoardAndOutput(board, result);
    }

    @Test
    void TestUnevenArrayReturnsNull() {
        int board[][] = new int[][]
                {
                        {-2,-3,-4,-8},
                        {-5,-9,-12},
                        {-1,-0,-6,-10},
                        {-87,-100}
                };
        TestBoardAndOutput(board, null);
    }

    @Test
    void TestEmptyOuterArrayReturnsNull() {
        int board[][] = new int[0][];
        TestBoardAndOutput(board, null);
    }

    @Test
    void TestEmptyInnerArrayReturnsNull() {
        int board[][] = new int[][]
                {
                        new int[0],
                        new int[0]
                };
        TestBoardAndOutput(board, null);
    }

    @Test
    void TestNullArrayReturnsNull() {
        TestBoardAndOutput(null, null);
    }

    private void TestBoardAndOutput(int[][] board, String expectedOutput)
    {
        String result = PrintBoard.Print(board);
        assertEquals(expectedOutput, result, "We expected the input and output to work");
    }
}