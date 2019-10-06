package wmortonsample;

/*
* An object that, when given an two dimensional array of  integers will print them out in clockwise spiral pattern
*/

public class PrintBoard {
    public static String Print(int[][] board)
    {
        if (!IsValidBoard(board))
        {
            return null;
        }

        return TraverseBoard(board);
    }

    private static String TraverseBoard(int[][] board)
    {
        String output = "";

        int height = board.length, width = board[0].length;
        int totalSpaces = height * width;

        int xVelocity = 1, yVelocity = 0;
        int xPosition = 0, yPosition = 0, rowBumper = 0;

        int visitedSpaces = 0;

        while (visitedSpaces < totalSpaces)
        {
            output = output + board[yPosition][xPosition];
            if (visitedSpaces < totalSpaces - 1)
            {
                output = output + ", ";
            }

            /*
            This is the logic for when to make the turns
             */
            if((xPosition + rowBumper == width-1) &&
                    xVelocity > 0)
            {
                xVelocity = 0;
                yVelocity = 1;
            }
            else if ((xPosition - rowBumper == 0) &&
                    xVelocity < 0)
            {
                xVelocity = 0;
                yVelocity = -1;
                rowBumper++; // When we turn back up, we need to make sure we increase the bumper
            }
            else if ((yPosition + rowBumper == height - 1) &&
                    yVelocity > 0)
            {
                xVelocity = -1;
                yVelocity = 0;
            }
            else if ((yPosition - rowBumper == 0) &&
                    yVelocity < 0)
            {
                xVelocity = 1;
                yVelocity = 0;
            }

            //Move to the next space
            xPosition += xVelocity;
            yPosition += yVelocity;
            visitedSpaces++;
        }

        return output;
    }

    private static boolean IsValidBoard(int[][] board)
    {
        if(board == null)
        {
            return false;
        }

        if (board.length < 1)
        {
            return false;
        }

        int boardLength = board[0].length;

        if (boardLength < 1)
        {
            return false;
        }

        /*
         * We are not handling non rectangular boards.
         */
        for (int[] row : board)
        {
            if (row.length != boardLength)
            {
                return false;
            }
        }
        return true;
    }
}
