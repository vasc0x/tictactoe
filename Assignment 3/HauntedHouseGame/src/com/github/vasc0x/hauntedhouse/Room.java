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
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * A class that contains and moves through the rooms of the house
 * @author adan
 */
public class Room extends Item {
    
    // Relative paths to the images
    public static final String PATH_STAR            = "/images/Star.png";
    public static final String PATH_TV              = "/images/TV.gif";
    public static final String PATH_DOWNSTAIRS      = "/images/DownStairsBackground.png";
    public static final String PATH_UPSTAIRS        = "/images/UpStairsBackground.png";
    public static final String PATH_GHOST           = "/images/Ghost.png";
    
    // The size for the JLabel star and items (squares)
    public static final int STAR_SIZE               = 100;
    public static final int ITEM_SIZE               = 90;
    
    // Size of the main window
    public static final int BACKGROUND_WIDTH        = 418;
    public static final int BACKGROUND_HEIGHT       = 655;
    
    // To scale the window and show the input to one side
    static final int VERTICAL_SCALE                 = 2;
    static final int HORIZONTAL_SCALE               = 4;    
    
    // x-y coordinates for the star
    public static final int STAR_HOUSE_ENTRANCE_X   = 220;
    public static final int STAR_HOUSE_ENTRANCE_Y   = 0;
    public static final int STAR_LIVING_ROOM_X      = 105;
    public static final int STAR_LIVING_ROOM_Y      = 170;
    public static final int STAR_DINING_ROOM_X      = 220;
    public static final int STAR_DINING_ROOM_Y      = 430;
    public static final int STAR_SECOND_FLOOR_X     = 322;
    public static final int STAR_SECOND_FLOOR_Y     = 270;
    public static final int STAR_LIVING_BATHROOM_X  = 190;
    public static final int STAR_LIVING_BATHROOM_Y  = 295;
    public static final int STAR_KITCHEN_X          = 40;
    public static final int STAR_KITCHEN_Y          = 530;
    public static final int STAR_PANTRY_X           = 10;
    public static final int STAR_PANTRY_Y           = 365;
    public static final int STAR_MASTER_BEDROOM_X   = 108;
    public static final int STAR_MASTER_BEDROOM_Y   = 102;
    public static final int STAR_MASTER_BATHROOM_X  = 260;
    public static final int STAR_MASTER_BATHROOM_Y  = 20;
    public static final int STAR_BEDROOM_2_X        = 44;
    public static final int STAR_BEDROOM_2_Y        = 420;
    public static final int STAR_BEDROOM_1_X        = 300;
    public static final int STAR_BEDROOM_1_Y        = 420;
    public static final int STAR_COMMON_BATHROOM_X  = 170;
    public static final int STAR_COMMON_BATHROOM_Y  = 540;
    
    public static final int TV_X                    = 140;
    public static final int TV_Y                    = 90;
    
    public static JFrame mainWindow;        // Main window frame
    public static JLabel labelStar;         // JLabel to display the star
    public static JLabel labelImage;        // JLabel to display items
    public static JLabel labelTV;           // JLabel to display the TV gif
    
    static int userSelection;               // Store the input from the user
    static String dialogResult;             // Store the result of the input window
    static String userName;                 // The user's name
    
    static Boolean itemExplored = false;    // To exit the main switch once an item was explored
    
    /**
     * Constructor
     * @throws IOException
     */    
    public Room () throws IOException
    {
        // Draw the main window
        showWindow (PATH_DOWNSTAIRS, STAR_HOUSE_ENTRANCE_X, STAR_HOUSE_ENTRANCE_Y);
        
        // Show the TV gif
        paintTV();
    }
    
    /**
     * Starts the program
     * @throws IOException
     */    
    public static void init() throws IOException
    {
        // Display the star at the top. Gets called only once after creating the object.
        Room.repaintWindow(Room.STAR_HOUSE_ENTRANCE_X, Room.STAR_HOUSE_ENTRANCE_Y);
        
        // Get the user name
        getUsername();
        
        // Loop indefinitely until the user breaks out
        while(true)
        {
            // Display the star at the top. Gets called every time the user plays again.
            Room.repaintWindow(Room.STAR_HOUSE_ENTRANCE_X, Room.STAR_HOUSE_ENTRANCE_Y);
            paintTV();
            
            // Prompt the user to enter the House
            getUserInput (Messages.MESSAGE_ENTRANCE, 1, 3);
            
            switch (userSelection)
            {   
                case 1: // Living Room
                    Room.repaintWindow(Room.STAR_LIVING_ROOM_X, Room.STAR_LIVING_ROOM_Y);

                    livingRoom(); // Handle the Living Room and return here
                    
                    break;

                case 2: // Second Floor
                    // Destroy the window and load a new background image
                    Room.destroyWindow();
                    Room.showWindow(Room.PATH_UPSTAIRS, Room.STAR_SECOND_FLOOR_X, Room.STAR_SECOND_FLOOR_Y);

                    secondFloor(); // Handle the Second Floor and return here
                    
                    break;

                case 3: // Dining Room
                    Room.repaintWindow(Room.STAR_DINING_ROOM_X, Room.STAR_DINING_ROOM_Y);

                    diningRoom(); // Handle the Dining Room and return here

                    break;
            }
            
            // Check if user wants to play again
            userSelection = JOptionPane.showConfirmDialog (null, "Play again?", "Keep screaming!!!", JOptionPane.YES_NO_OPTION);

            if (userSelection == JOptionPane.NO_OPTION)
            {
                // Game over
                JOptionPane.showMessageDialog(null, "Good bye " + userName + "!!!");
                Room.destroyWindow();
                
                // Exit the program. Return execution to the OS
                System.exit(0);
            }
            else // Start over
            {
                itemExplored = false; // Clear the exit condition
                Room.destroyWindow(); // Clear the JFrame from memory
                Room.showWindow(Room.PATH_DOWNSTAIRS, Room.STAR_HOUSE_ENTRANCE_X, Room.STAR_HOUSE_ENTRANCE_Y); // Show the JFrame again
            }
        }
    }    
    
    /**
     * A method to display the background image
     * @param imageFile The relative path of the image to load
     * @param xPosition The x-axis coordinate
     * @param yPosition The y-axis coordinate
     * @throws IOException
     */    
    public static void showWindow (String imageFile, int xPosition, int yPosition) throws IOException
    {
        mainWindow = new JFrame(); // Main window frame
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit if the user clicks the X button
        mainWindow.setLocationRelativeTo(null); // Don't set a specific location
        
        // Get the path of the image to load
        URL url = Room.class.getResource(imageFile);
        
        // Load the image as an icon on the JLabel
        labelImage = new JLabel(new ImageIcon(url));
        labelImage.setLayout(null);
        
        // Get the path of the star image
        url = Room.class.getResource(PATH_STAR);
        
        // Load the star onto a JLabel
        labelStar = new JLabel(new ImageIcon(url));
        
        // Set the location and size of the star
        labelStar.setBounds(xPosition, yPosition, STAR_SIZE, STAR_SIZE);
        
        // Add the JLabel to the frame and display it
        mainWindow.add(labelImage);
        mainWindow.pack();
        mainWindow.setLocation(getWindowX(),getWindowY());
        mainWindow.setVisible(true);
    }
    
    /**
     * A method to load the TV gif onto a JLabel, add it to the frame and display it
     * @throws IOException
     */    
    public static void paintTV () throws IOException
    {
        // Get the path of the image to load
        URL url = Room.class.getResource(PATH_TV);
        
        labelTV = new JLabel(new ImageIcon(url));
        labelTV.setBounds(TV_X, TV_Y, ITEM_SIZE, ITEM_SIZE);
        
        labelImage.add(labelTV);
        labelImage.revalidate();
        labelImage.repaint();         
    }    
    
    /**
     * A method to add the JLabel to the frame and display it
     * @param xPosition The x-axis coordinate
     * @param yPosition The y-axis coordinate
     * @throws IOException
     */    
    public static void repaintWindow (int xPosition, int yPosition) throws IOException
    {
        // Add the JLabel to the frame and display it
        labelStar.setBounds(xPosition, yPosition, STAR_SIZE, STAR_SIZE);
        labelImage.add(labelStar);
        labelImage.revalidate();
        labelImage.repaint();         
    }
    
    /**
     * A method to destroy the window
     */    
    public static void destroyWindow ()
    {
        // Hide and delete the frame from memory
        mainWindow.setVisible(false);
        mainWindow.dispose();
    }
    
    /**
     * A method to calculate the x-axis coordinate of the main window
     * @return x
     */    
    public static int getWindowX ()
    {
        // Get the width of the main window
        int width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        
        // Get the x 1/4 of the window to show the dialog off to one side
        int x = (width / HORIZONTAL_SCALE) - (BACKGROUND_WIDTH / HORIZONTAL_SCALE);
        
        return x;
    }
    
    /**
     * A method to calculate the y-axis coordinate of the main window
     * @return y
     */    
    public static int getWindowY ()
    {
        // Get the height of the main window
        int height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        
        // Get the middle of the window
        int y = (height / VERTICAL_SCALE) - (BACKGROUND_HEIGHT / VERTICAL_SCALE);
        
        return y;        
    }
    
    /**
     * A method to handle the Living Room location
     * @throws IOException
     */    
    public static void livingRoom() throws IOException
    {
        getUserInput (Messages.MESSAGE_LIVING_ROOM, 1, 3);
        
        switch (userSelection)
        {                
            case 1: // Enter the Bathroom
                Room.repaintWindow(Room.STAR_LIVING_BATHROOM_X, Room.STAR_LIVING_BATHROOM_Y);

                getUserInput(Messages.MESSAGE_LIVING_BATHROOM, 1, 3);

                if (userSelection == 1) // Look yourself in the Mirror
                {
                    showPrize(Item.Location.MIRROR.toString(), Room.PATH_GHOST);
                }
                else // Take a Shower
                {
                    showPrize(Item.Location.SHOWER.toString(), Room.PATH_GHOST);
                }

                break;

            case 2: // Open the Chest
                showPrize(Item.Location.CHEST.toString(), Room.PATH_GHOST);
                break;

            case 3: // Turn on the TV
                showPrize(Item.Location.TV.toString(), Room.PATH_GHOST);
                break;
        }
    }
    
    /**
     * A method to handle the Dining Room location
     * @throws IOException
     */      
    public static void diningRoom() throws IOException
    {
        getUserInput(Messages.MESSAGE_DINING_ROOM, 1, 3);

        switch (userSelection)
        {
            case 1: // Kitchen
                Room.repaintWindow(Room.STAR_KITCHEN_X, Room.STAR_KITCHEN_Y);

                getUserInput(Messages.MESSAGE_KITCHEN, 1, 3);

                if (userSelection == 1) // Pantry
                {
                    Room.repaintWindow(Room.STAR_PANTRY_X, Room.STAR_PANTRY_Y);

                    getUserInput(Messages.MESSAGE_PANTRY, 1, 2);

                    if (userSelection == 1) // Broom
                    {
                        showPrize(Item.Location.BROOM.toString(), Room.PATH_GHOST);
                    }
                    else // Recipe Box
                    {
                        showPrize(Item.Location.DUSTY_RECIPE_BOX.toString(), Room.PATH_GHOST);
                    }
                }
                else if (userSelection == 2) // Refrigerator
                {
                    showPrize(Item.Location.REFRIGERATOR.toString(), Room.PATH_GHOST);
                }
                else // Cabinet
                {
                    showPrize(Item.Location.CABINET.toString(), Room.PATH_GHOST);
                }                    

                break;

            case 2: // Candelabra
                showPrize(Item.Location.CANDELABRA.toString(), Room.PATH_GHOST);
                
                break;

            case 3: // Table
                showPrize(Item.Location.TABLE.toString(), Room.PATH_GHOST);
                
                break;
        }
    }    
    
    /**
     * A method to handle the Second Floor location
     * @throws IOException
     */      
    public static void secondFloor() throws IOException
    {
        Room.repaintWindow(Room.STAR_SECOND_FLOOR_X, Room.STAR_SECOND_FLOOR_Y);
        
        getUserInput(Messages.MESSAGE_UPSTAIRS, 1, 3);

        switch (userSelection)
        {
            case 1: // Enter the Master Bedroom
                Room.repaintWindow(Room.STAR_MASTER_BEDROOM_X, Room.STAR_MASTER_BEDROOM_Y);

                masterBedroom();

                break;

            default: // Enter Bedroom 1 or 2
                while (!itemExplored)
                {
                    if (userSelection == 2) // Enter the Bedroom 2
                    {
                        Room.repaintWindow(Room.STAR_BEDROOM_2_X, Room.STAR_BEDROOM_2_Y);

                        getUserInput(Messages.MESSAGE_BEDROOM_2, 1, 3);

                        switch (userSelection)
                        {
                            case 1: // Enter the Common Bathroom
                                Room.repaintWindow(Room.STAR_COMMON_BATHROOM_X, Room.STAR_COMMON_BATHROOM_Y);

                                getUserInput(Messages.MESSAGE_COMMON_BATHROOM, 1, 4);

                                if (userSelection == 1) // Enter Bedroom 1
                                {
                                    userSelection = 3;
                                    Room.repaintWindow(Room.STAR_BEDROOM_1_X, Room.STAR_BEDROOM_1_Y);
                                }
                                else if (userSelection == 2) // Go back to Bedroom 2
                                {
                                    //userSelection = 2;
                                    Room.repaintWindow(Room.STAR_BEDROOM_2_X, Room.STAR_BEDROOM_2_Y);
                                }
                                else if (userSelection == 3) // Mirror
                                {
                                    itemExplored = true;
                                    showPrize(Item.Location.SHOWER.toString(), Room.PATH_GHOST);
                                }
                                else // Shower
                                {
                                    itemExplored = true;
                                    showPrize(Item.Location.MIRROR.toString(), Room.PATH_GHOST);
                                }                                

                                break;

                            case 2: // Dresser
                                itemExplored = true;
                                showPrize(Item.Location.DRESSER.toString(), Room.PATH_GHOST);
                                
                                break;

                            case 3: // Doll House
                                itemExplored = true;
                                showPrize(Item.Location.DOLL_HOUSE.toString(), Room.PATH_GHOST);
                                
                                break;
                        }   
                    }
                    else if (userSelection == 3) // Enter Bedroom 1
                    {
                        Room.repaintWindow(Room.STAR_BEDROOM_1_X, Room.STAR_BEDROOM_1_Y);

                        getUserInput(Messages.MESSAGE_BEDROOM_1, 1, 3);

                        switch (userSelection)
                        {
                            case 1: // Enter the Common Bathroom
                                Room.repaintWindow(Room.STAR_COMMON_BATHROOM_X, Room.STAR_COMMON_BATHROOM_Y);

                                getUserInput(Messages.MESSAGE_COMMON_BATHROOM, 1, 4);

                                if (userSelection == 1) // Enter Bedroom 1
                                {
                                    userSelection = 3;
                                    Room.repaintWindow(Room.STAR_BEDROOM_1_X, Room.STAR_BEDROOM_1_Y);
                                }
                                else if (userSelection == 2) // Enter Bedroom 2
                                {
                                    Room.repaintWindow(Room.STAR_BEDROOM_2_X, Room.STAR_BEDROOM_2_Y);
                                }
                                else if (userSelection == 3) // Shower
                                {
                                    itemExplored = true;
                                    showPrize(Item.Location.SHOWER.toString(), Room.PATH_GHOST);
                                }
                                else // Mirror
                                {
                                    itemExplored = true;
                                    showPrize(Item.Location.MIRROR.toString(), Room.PATH_GHOST);                                    
                                }

                                break;

                            case 2: // Rocking Chair
                                itemExplored = true;
                                showPrize(Item.Location.ROCKING_CHAIR.toString(), Room.PATH_GHOST);
                                break;

                            case 3: // Window
                                itemExplored = true;
                                showPrize(Item.Location.WINDOW.toString(), Room.PATH_GHOST);
                                break;
                        }   
                    }
                } 

                break;
        }       
    }
      
    /**
     * A method to handle the Master Bedroom location
     * @throws IOException
     */      
    public static void masterBedroom() throws IOException
    {
        getUserInput(Messages.MESSAGE_MASTER_BEDROOM, 1, 3);
        
        switch (userSelection)
        {
            case 1: // Enter the Master Bathroom
                Room.repaintWindow(Room.STAR_MASTER_BATHROOM_X, Room.STAR_MASTER_BATHROOM_Y);

                getUserInput(Messages.MESSAGE_MASTER_BATHROOM, 1, 2);

                if (userSelection == 1) // Rub the Oil Lamp
                {
                    showPrize(Item.Location.OIL_LAMP.toString(), Room.PATH_GHOST);
                }
                else // Take a shower
                {
                    showPrize(Item.Location.MASTER_SHOWER.toString(), Room.PATH_GHOST);
                }

                itemExplored = true;

                break;

            case 2: // Open the Jewelry Box
                itemExplored = true;
                showPrize(Item.Location.JEWELRY_BOX.toString(), Room.PATH_GHOST);

                break;

            case 3: // Look un the Bed
                itemExplored = true;
                showPrize(Item.Location.MASTER_BED.toString(), Room.PATH_GHOST);

                break;
        }
    }
    
    /**
     * A method to get and sanitize user input
     * @param message The string to be shown to the user
     * @param lowerLimit The lowest selection value
     * @param upperLimit The highest selection value
     */      
    public static void getUserInput (String message, int lowerLimit, int upperLimit)
    {
        boolean isValidInput = false;
        
        do
        {
            dialogResult = JOptionPane.showInputDialog(null, "Hi " + userName + "!\n\n" + message, 
                                                       "Please make a selection", JOptionPane.INFORMATION_MESSAGE);

            if (dialogResult == null)
            {
                // User clicked the Cancel button. Game over!
                JOptionPane.showMessageDialog(null, "Good bye " + userName + "!!!");
                Room.destroyWindow();

                // Terminate
                System.exit(0);
            }

            try
            {
                userSelection = Integer.parseInt(dialogResult);
                isValidInput = true;
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null,"Please make sure to enter an\n"
                                                    + "integer number between " 
                                                    + lowerLimit + " and " + upperLimit 
                                                    + "!", "Input error", 
                                                    JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        }
        while (!isValidInput || (userSelection < lowerLimit || userSelection > upperLimit));
    }
    
    public static void getUsername()
    {
        do
        {
            dialogResult = JOptionPane.showInputDialog(null,"Please enter your name: ", "Welcome to the Haunted House Game!", JOptionPane.INFORMATION_MESSAGE);

            if (dialogResult == null)
            {
                // User clicked the Cancel button. Game over!
                JOptionPane.showMessageDialog(null, "Good bye!!!", "So long!", JOptionPane.INFORMATION_MESSAGE);
                Room.destroyWindow();

                // Terminate
                System.exit(0);
            }
            else
            {
                // Update the variable with the username
                userName = dialogResult;
            }
        }
        while (dialogResult.length() == 0);
    }
    
    public static void showPrize (String itemLocation, String imagePath)
    {
        URL url = Room.class.getResource(imagePath);
        ImageIcon icon = new ImageIcon(url);
        JOptionPane.showMessageDialog(null, Room.getOutcome(itemLocation), Item.MESSAGE_TITLE, JOptionPane.OK_OPTION, icon);
    }
}
