public class Main {
    public static void main(String[] args) {
        // Create a board
        Board board = new Board();

        // Create players
        Player player1 = new Player();
        Player player2 = new Player();

        // Set player names
        player1.setName("Player 1");
        player2.setName("Player 2");

        // Add players to the board
        board.getPlayers().add(player1);
        board.getPlayers().add(player2);

        // Set up some test scenarios
        PropertySquare property1 = new City("Property 1", 1, 50000, 1, 20000);
        PropertySquare property2 = new Beach("Property 2", 2, 200000 );
        board.getSquares().add(property1);
        board.getSquares().add(property2);

        player1.getOwnedProperty().add(property1);
        player2.getOwnedProperty().add(property2);

        // Test player interactions
        player1.playerHandleTurn(board);

        // Print player information
        System.out.println(player1.getName() + "'s Cash: $" + player1.getCash());
        System.out.println(player1.getName() + "'s Owned Properties: " + player1.getOwnedProperty());

        // Test tax interaction
        Tax tax = new Tax();
        board.getSquares().add(tax);
        player1.handleTaxInteraction(tax, board);

        // Test plane interaction
        Plane plane = new Plane();
        board.getSquares().add(plane);
        player1.handlePlaneInteraction(plane, board, player1);
    }
}
