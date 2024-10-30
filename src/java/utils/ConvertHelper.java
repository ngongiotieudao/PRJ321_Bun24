/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.math.BigDecimal;

/**
 *
 * @author TNO
 */
public class ConvertHelper {

    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: '" + input + "' is not a valid integer.");
            return 0;
        }
    }

    public static BigDecimal parseStringToBigDecimal(String input) {
        try {
          
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
          
            System.out.println("Invalid number format: " + input);
            return null; 
        }
    }
}
