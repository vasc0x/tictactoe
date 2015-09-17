//******************************************************************************
// PANTHER ID: 2923148
// CLASS: COP 2210 - Fall 2015
// ASSIGNMENT #1
// DATE: 9/2/2015
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//******************************************************************************

package lotterygenerator;

import java.util.Random;        // Random seed library
import javax.swing.JOptionPane; // Dialog box library

public class Main {

    public static void main(String[] args) {
        // Variable to generate random seed
        Random r = new Random();
        
        // Array to store the fantasy numbers
        int[] fantasyNumbers = new int[5];
        
        // Lotto numbers array. Another way of declaring arrays
        int lottoNumbers[] = {0,0,0,0,0,0};
        
        // Variables to store the numbers and final output message
        String fantasyMessage = "";
        String lottoMessage   = "";
        String outputMessage  = "";
        
        // Loop 5 times to generate fantasy numbers
        for (int i = 0; i < fantasyNumbers.length; i++)
        {
            // Generate a random number from 1 to 36 (inclusive)
            fantasyNumbers[i] = 1 + r.nextInt(36);
            
            // Add each number to an output variable
            fantasyMessage = fantasyMessage + "  " + fantasyNumbers[i];
            
            // Print output to console
            System.out.println("Fantasy " + (i + 1) + ": " + fantasyNumbers[i]);
        }
        
        // Loop 6 times to generate lotto numbers
        for (int j = 0; j < lottoNumbers.length; j++)
        {
            // Generate a random number from 1 to 53 (inclusive)
            lottoNumbers[j] = 1 + r.nextInt(53);
            
            // Add each number to an output variable
            lottoMessage = lottoMessage + "  " + lottoNumbers[j];
            
            // Print output to console
            System.out.println("Lotto " + (j + 1) + ": " + lottoNumbers[j]);
        }      
        
        // Final message to show to the user
        outputMessage = "Here are your winning numbers!"
                      + "\n(Remember my student loans ;-)"
                      + "\n\nFantasy: "  + fantasyMessage
                      + "\nLotto: "  + lottoMessage
                      + "\n\nBreak a leg!!!";
        
        // Show a modal dialog with array values.
        JOptionPane.showMessageDialog(null, outputMessage);
    }
}