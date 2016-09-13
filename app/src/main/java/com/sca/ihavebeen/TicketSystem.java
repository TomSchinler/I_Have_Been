package com.sca.ihavebeen;

/**
 * Created by Tom Schinler on 7/3/2015.
 */
public class TicketSystem {

    // When Parse is set up get tickets from Parse User
    private int tickets = 25;

    private final static TicketSystem instance = new TicketSystem();

    private TicketSystem() {}

    public static TicketSystem getInstance() {
        return instance;
    }

    public int getTickets() {
        return tickets;
    }

    //TODO check to see if this is backwards
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }


    // Costs 4 tickets to play the game
    public int costOfGame() {
        tickets = tickets - 4;
        return tickets;
    }

    // Winner receives 8 tickets (game cost plus 4)
    public int sweetTasteOfVictory() {
        tickets = tickets + 8;
        return tickets;
    }

}
