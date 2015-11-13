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

/**
 * A utility subclass to hold and return the outcome messages
 * @author adan
 */
public class Item {
    
    /**
     * An array with all the output messages
     */
    public static String outcomeMessage[] = {"A ghost escapes and scares you to death!",
                                             "Poltergeist! A hand appears suddenly on the scren!",
                                             "Light up by themselves and you see a death shadow!",
                                             "Smoke starts coming out of the table!",
                                             "Find some delicious soul food!",
                                             "The dishes and glasses start flying at you as soon as you open the door.\nYou get hit in the head and feel yourself start moving towards a light!",
                                             "You open it up and a recipe for chocolate devils food cake appears our of no where!",
                                             "Flies up in the air as soon as you touch it!",
                                             "See a bloody face looking back at you!",
                                             "The room suddenly steams up and you feel fingers touching the back of your neck!",
                                             "The chair starts rocking by itself with no one in it!",
                                             "See a child outside on a swing who suddenly disappears!",
                                             "The dolls start dancing on their own!",
                                             "A ghost flies out of the dresser as soon as you open it and goes right though your body!",
                                             "You find the cursed Hope Diamond and feel your doom!",
                                             "There is is a corpse that opens its eyes and looks at you!",
                                             "Rub the lamp and a genie pops out who says he’ll grant you 3 wishes!",
                                             "Suddenly hear singing in the shower, but no one is there!"};
    
    public static final String MESSAGE_TITLE = "Here's your prize!!!";
    
    /**
     * An enumeration of items to make finding the output message easier
     */
    public static enum Location 
    {
        CHEST,
        TV,
        CANDELABRA,
        TABLE,
        REFRIGERATOR,
        CABINET,
        DUSTY_RECIPE_BOX,
        BROOM,
        MIRROR,
        SHOWER,
        ROCKING_CHAIR,
        WINDOW,
        DOLL_HOUSE,
        DRESSER,
        JEWELRY_BOX,
        MASTER_BED,
        OIL_LAMP,
        MASTER_SHOWER
    }

    /**
     * Empty constructor
     */
    public Item () {}
    
    /**
     * A method to get the outcome message
     * @param item The enum to get index of and pass to the messages array
     * @return The output message
     */
    public static String getOutcome(String item)
    {
        return outcomeMessage[Location.valueOf(item).ordinal()];
    }
}
