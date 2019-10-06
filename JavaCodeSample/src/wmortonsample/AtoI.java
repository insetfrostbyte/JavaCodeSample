package wmortonsample;

/*
*  An A to I function that given a value returns the corresponding string in their Oct, Dec, or Hex
*/

import static java.lang.Math.*;

public class AtoI {
    private String minOctString = "20000000000";
    private String minDecString = "-2147483648";
    private String minHexString = "80000000";

    public String ItoA(int value, int base) {

        //Check for valid bases
        if (base != 10 && base != 8 && base != 16) {
            return null;
        }

        // Special Cases need to be handled
        // 0 should always just return 0
        // Integer Min has issues in that you can't store it's absolute value which is generally used everywhere else
        //      in the implementation.
        if (value == 0) {
            return "0";
        } else if (value == Integer.MIN_VALUE) {
            return getIntegerMinString(base);
        }

        boolean negativeNumber = value < 0;

        // Get the number of digits needed for the buffer size
        int digits = getStringSize(value, base, negativeNumber);

        // Get the character buffer of the array
        char[] buffer = getNumberCharArray(value, base, digits, negativeNumber);

        return new String(buffer);
    }

    private char[] getNumberCharArray(int value, int base, int digits, boolean isNegativeNumber) {
        int absValue;
        char[] buffer = new char[digits--];
        int remainder;
        boolean startNegativeCompliment = true;
        absValue = absoluteValue(value);

        //Perform the conversion
        while (absValue > 0) {
            remainder = absValue % base;
            absValue /= base;

            buffer[digits] = convertDigit(remainder, base, isNegativeNumber, startNegativeCompliment);

            // When doing negative numbers the initial twos compliment doesn't start till
            // you get to the first non 0 character
            if (isNegativeNumber && startNegativeCompliment && buffer[digits] != '0') {
                startNegativeCompliment = false;
            }

            // When doing octal, the digit, as defined by java, needs to be 8 - Value
            if(digits == 0 && base ==8 && isNegativeNumber)
            {
                buffer[0] = (char)(((int)'8' +(int)'0') - (int)buffer[0]);
            }
            digits--;
        }

        // When doing negative numbers you need to fill in the proper values that signify negative
        if (isNegativeNumber && digits >=0) {
            while (digits > 0) {
                buffer[digits--] = (base == 16) ? 'F' : '7';
            }

            if (base == 10) {
                buffer[0] = '-';
            } else if(digits >=0){
                buffer[0] = (base == 16) ? 'F' : '3';
            }
        }
        return buffer;
    }

    private int getStringSize(int value, int base, boolean isNegativeNumber) {
        int absValue = absoluteValue(value), digits = 0;

        // If the number is positive or base 10, we need to go through and actually calculate the size
        // For Hex and Oct we know how big the character buffer needs to be
        if (base == 10 || value > 0) {
            while (absValue > 0) {
                absValue /= base;
                digits++;
            }
            if (isNegativeNumber) {
                digits++;
            }
        } else if (base == 8) {
            digits = 11;
        } else {
            digits = 8;
        }
        return digits;
    }

    private char convertDigit(int digit, int base, boolean isNegativeNumber, boolean startNegativeCompliment) {

        // This is a bit of a hack, but for the way I'm doing two's compliment, I need to know when to have the
        // the plus 1
        if (startNegativeCompliment && base != 10 && isNegativeNumber) {
            digit--;
        }

        // If the digit is negative we need to wrap it around (this happens in doing negative numbers)
        if (digit < 0) {
            digit = base + digit;
        }

        // Do some extra twos compliment math
        int value = (base != 10 && isNegativeNumber) ? base - (digit + 1) : digit;

        switch (value) {
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            default:
                return (char) (value + '0'); //Characters in java are ascii so we need to add the 0 to start at numerical characters
        }
    }

    // I wasn't sure what use no functions meant, so I went ahead and wrote my own absolute value function
    private int absoluteValue(int num) {
        return (num < 0) ? num * -1 : num;
    }

    private String getIntegerMinString(int base) {
        switch (base) {
            case 8:
                return minOctString;
            case 16:
                return minHexString;
            default:
                return minDecString;
        }
    }
}
