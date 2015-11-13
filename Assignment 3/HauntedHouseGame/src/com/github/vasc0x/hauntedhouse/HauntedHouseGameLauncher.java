//******************************************************************************
// PANTHER ID: 2923148
// CLASS: COP 2210 Fall 2015
// ASSIGNMENT # 3
// DATE: 10/25/2015
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//******************************************************************************

package com.github.vasc0x.hauntedhouse;

import java.io.IOException;

/**
 * A class to test the Haunted House game
 * @author adan
 */
public class HauntedHouseGameLauncher {

    /**
     * A method to launch the Haunted House game
     * @param args Command line arguments
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {
        
        // Create a new object
        Room myRoom = new Room();
        
        // Initialize the object and launch the game
        myRoom.init();
    }   
}
