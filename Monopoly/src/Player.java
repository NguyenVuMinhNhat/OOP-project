
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    private String name;

    private int cash;
    private int location;
    private boolean inJail;
    private int jailTurn;
    private boolean bankrupt = false;
    private List<PropertySquare> ownedProperty = new ArrayList<>();

    public Player() {
        this.cash = 2000000;
        this.location = 1;
        this.inJail = false;
        this.jailTurn = 0;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public int getLocation() {
        return location;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getJailTurn() {
        return jailTurn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setJailTurn(int jailTurn) {
        this.jailTurn = jailTurn;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public List<PropertySquare> getOwnedProperty() {
        return ownedProperty;
    }

    public void setOwnedProperty(List<PropertySquare> ownedProperty) {
        this.ownedProperty = ownedProperty;
    }

    public int rollDice() {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        dice1.roll();
        System.out.println("Dice 1: " + dice1.getValueDice());
        dice2.roll();
        System.out.println("Dice 2: " + dice2.getValueDice());
        int totalValue = dice1.getValueDice() + dice2.getValueDice();
        if (dice1.getValueDice() == dice2.getValueDice()) {
            return totalValue = dice1.getValueDice() + dice2.getValueDice() + rollDice();
        }

        return totalValue;
    }

    private void move(Board board, int step) {
        setLocation(getLocation() + step);
    }

    public int totalNumberOfBeach() {
        int beachCount = 0;
        for (Square square : ownedProperty) {
            if (square instanceof Beach) {
                beachCount++;
            }
        }
        return beachCount;
    }

    private int propertyValue() {
        int propertyValue = 0;
        for (int i = 0; i < ownedProperty.size(); i++) {
            Square currentSquare = ownedProperty.get(i);
            if (currentSquare.getType() == SquareType.CITY) {
                City city = (City) currentSquare;
                propertyValue += city.getPropertyValue();
            } else if (currentSquare.getType() == SquareType.BEACH) {
                Beach beach = (Beach) currentSquare;
                propertyValue += beach.getPropertyValue();
            }
        }

        return propertyValue;
    }

    private int calculateTotalPropertyValue() {
        int totalPropertyValue = 0;
        for (int i = 0; i < ownedProperty.size(); i++) {
            totalPropertyValue += ownedProperty.get(i).getPropertyValue();
        }
        return totalPropertyValue;
    }

    public boolean isBankRupt() {
        if (propertyValue() == 0 && getCash() == 0) {
            return bankrupt = true;
        }
        return bankrupt = false;
    }

    public void playerHandleTurn(Board board) {
        int diceRoll = rollDice();
        move(board, diceRoll);

        interactWithSquare(board);
    }

    public void interactWithSquare(Board board) {

        Square currentSquare = board.getSquares().get(getLocation());
        if (currentSquare instanceof PropertySquare) {
            handlePropertyInteraction((PropertySquare) currentSquare, board);
        } else if (currentSquare instanceof Tax) {
            handleTaxInteraction((Tax) currentSquare, board);
        } else if (currentSquare instanceof Plane) {
            handlePlaneInteraction((Plane) currentSquare, board, this);
        } else if (currentSquare instanceof Jail) {
            handleJailInteraction((Jail) currentSquare);
        } else if (currentSquare instanceof Event) {
            handleEventInteraction((Event) currentSquare, board, this);
        }

    }

    // Properties Interaction
    private void handlePropertyInteraction(PropertySquare propertySquare, Board board) {
        if (propertySquare.isOwned() == true) { // check if property is owned or not?
            if (!ownedProperty.contains(propertySquare)) { // if property is owned by other player, then
                int payAmount = propertySquare.getVisitCost();
                System.out.println(name + "pays rent of $" + payAmount + " to " + propertySquare.getOwner().getName());
                if (getCash() < payAmount) {
                    System.out.println(name + " does not have enough money to pay!!!");
                    sellPropertiesToCoverRent(payAmount); // if player cash does not enough to pay
                }
                setCash(getCash() - payAmount);
                System.out.println(name + "'s new balance: " + getCash());
            } else {
                for (int i = 0; i < board.getSquares().size(); i++) {
                    Square square = board.getSquares().get(i);
                    if (square.getType() == SquareType.PROPERTY.CITY) {
                        PropertySquare property = (PropertySquare) square;
                        System.out.println("Do you want to upgrade this city?");
                        System.out.println("1. Yes");
                        System.out.println("0. No");
                        while (true) {
                            try {
                                System.out.println(name + " choice: ");
                                int choice = Integer.parseInt(sc.nextLine());
                                if (choice == 1) {
                                    if (getCash() >= property.getUpgradeCost()) {
                                        upgradeCity(property);
                                        break;
                                    } else {
                                        System.out.println(name + " do not have enough cash to upgrade "
                                                + property.getName() + ".");
                                    }

                                } else if (choice == 0) {
                                    System.out.println(name + " choose exit.");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Please choose valid integer!!!");
                            }
                        }
                    }
                }

            }
        } else {
            for (int i = 0; i < board.getSquares().size(); i++) {
                Square square = board.getSquares().get(i);
                if (square.getType() == SquareType.PROPERTY && square.getLocation() == getLocation()) {
                    PropertySquare property = (PropertySquare) square;
                    System.out.println(name + " landed on an unowned property. Do you want to buy it?");
                    System.out.println("1. Yes");
                    System.out.println("0. No");
                    while (true) {
                        try {
                            System.out.println("Your choice: ");
                            int choice = Integer.parseInt(sc.nextLine());
                            if (choice == 1) {
                                if (getCash() >= property.getInitialValue()) {
                                    System.out.println(name + " buy " + property.getName() + " with $"
                                            + property.getInitialValue());
                                    buyProperty(property);
                                    System.out.println("Player " + name + "successfully buy " + property.getName());
                                    System.out.println("Player " + name + " new balance: $" + getCash());
                                    break;
                                } else {
                                    System.out.println(
                                            name + " do not have enough cash to buy " + property.getName() + ".");
                                }

                            } else if (choice == 0) {
                                System.out.println("You choose exit.");
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Input valid number!!!");
                        }
                    }
                }
            }

        }

    }

    private void sellPropertiesToCoverRent(int rentAmount) {
        Scanner scanner = new Scanner(System.in);

        List<PropertySquare> selectedProperties = new ArrayList<>();

        while (true) {
            while (true) {

                for (int i = 0; i < ownedProperty.size(); i++) {
                    System.out.println("|" + (i + 1) + ". " + ownedProperty.get(i).getName() + "|Sell value: "
                            + ownedProperty.get(i).calculateSellValue() + "|");
                }

                System.out.println("Choose a property to sell (enter the corresponding number), or enter 0 to finish:");
                int choice = scanner.nextInt();

                if (choice == 0) {

                    break;
                }

                if (choice > 0 && choice <= ownedProperty.size()) {

                    PropertySquare selectedProperty = ownedProperty.get(choice - 1);
                    selectedProperties.add(selectedProperty);
                    System.out.println("Selected property: " + selectedProperty.getName());
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            }

            for (PropertySquare selectedProperty : selectedProperties) {
                int sellValue = selectedProperty.calculateSellValue();
                System.out.println(name + " sells " + selectedProperty.getName() + " for $" + sellValue);

                ownedProperty.remove(selectedProperty);
                setCash(getCash() + sellValue);
                //
            }

            if (getCash() > rentAmount) {
                System.out.println(name + " has sold enough properties to cover the fee.");

                break;
            } else {
                System.out.println("You cannot afford the fee. You are bankrupt!!!");
                setBankrupt(true);
                break;
            }
        }

    }

    private void buyProperty(PropertySquare property) {
        ownedProperty.add(property);
        setCash(getCash() - property.getInitialValue());
    }

    private void upgradeCity(PropertySquare property) {
        setCash(getCash() - property.getUpgradeCost());
        property.calculateLevel();

    }

    // Tax interaction
    public void handleTaxInteraction(Tax tax, Board board) {
        int totalTax = calculateTotalTax(board);
        if (getCash() >= totalTax) {
            System.out.println("Player " + getName() + " pay $" + totalTax + " for tax.");

        } else {
            System.out.println("Player " + name + " do not have enough money to pay tax.");
            System.out.println("Player " + name + " has to sell properties.");
            sellPropertiesToCoverRent(totalTax);
        }
        setCash(getCash() - totalTax);
        System.out.println("Player " + name + "'s new balance: $" + getCash());
    }

    private int calculateTotalTax(Board board) {
        int totalTax = 0;
        for (int i = 0; i < ownedProperty.size(); i++) {
            if (board.timer.getElapsedTime() <= 5) {
                totalTax += 5 / 100 * getCash() + 5 / 100 * calculateTotalPropertyValue();
            } else if (board.timer.getElapsedTime() > 5 && board.timer.getElapsedTime() < 10) {
                totalTax += 1 / 10 * getCash() + 1 / 10 * calculateTotalPropertyValue();
            } else {
                totalTax += 1 / 10 * getCash() + 15 / 100 * calculateTotalPropertyValue();
            }
        }
        return totalTax;
    }

    // Plane interaction
    public void handlePlaneInteraction(Plane plane, Board board, Player currentPlayer) {

        if (currentPlayer.getCash() >= 50000) {
            System.out.println("Do you want to use World Tour? Cost: $50000");
            System.out.println("1. Yes");
            System.out.println("0. No");
            while (true) {
                try {
                    System.out.println("Your choice: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {

                        plane.travel(board, currentPlayer);
                        currentPlayer.setCash(currentPlayer.getCash() - 50000);
                        System.out.println("Player " + name + " new balance: $" + getCash());
                        break;

                    } else if (choice == 0) {
                        System.out.println("You choose exit.");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Input valid number!!!");
                }
            }
        } else {
            System.out.println("You do not have enough money to use World Tour.");
        }

    }

    // Jail interaction

    public void handleJailInteraction(Jail jail) {
        System.out.println(name + " landed on Jail!");
        System.out.println(name + "are in Jail. Try to roll doubles to get out.");

        int diceRoll1 = rollDice();
        int diceRoll2 = rollDice();

        System.out.println("Dice Roll 1: " + diceRoll1);
        System.out.println("Dice Roll 2: " + diceRoll2);

        if (diceRoll1 == diceRoll2) {
            System.out.println("Congratulations! " + name + " rolled doubles and got out of Jail.");
            inJail = false;
            jailTurn = 0;
        } else {
            System.out.println("Sorry, " + name + " didn't roll doubles. You are still in Jail.");
            jailTurn++;
        }
        if (jailTurn == 3) {
            System.out.println("You have been in Jail for 3 turns. Pay $50000 to get out.");
            payJailFine(50000);
        }
    }

    private void payJailFine(int amount) {
        if (getCash() >= amount) {
            System.out.println("You paid the fine and got out of Jail.");
            setCash(getCash() - amount);
            inJail = false;
            jailTurn = 0;
        } else {
            System.out.println("You don't have enough money to pay the fine. You remain in Jail.");
        }
    }


    //Event Interaction
    public void handleEventInteraction(Event event, Board board, Player player){
        System.out.println("Player: " + name + "rolled in Event.");
        event.setWorldCup(board, player);
    }
}
