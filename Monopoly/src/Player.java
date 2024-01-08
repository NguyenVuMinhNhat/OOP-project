
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    private String name;

    private int cash;
    private int location;
    private boolean inJail;
    private int jailTurn;
    private boolean bankrupt;
    private List<PropertySquare> ownedProperty = new ArrayList<>();
    private List<Card> ownedCard = new ArrayList<>();

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

    public List<Card> getOwnedCard() {
        return ownedCard;
    }

    public void setOwnedCard(List<Card> ownedCard) {
        this.ownedCard = ownedCard;
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
            System.out.println("Player " + name + " have one more chance to roll the dices.");
            totalValue = dice1.getValueDice() + dice2.getValueDice() + rollDice();
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

    public int calculateTotalPropertyValue() {
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

        Square currentSquare = board.getSquares().get(getLocation() - 1);
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
        } else if (currentSquare instanceof Card) {
            System.out.println("Player " + name + " landed on Chance Square.");
            handleCardInteraction((Card) currentSquare, board);
        }

    }

    // Properties Interaction
    private void handlePropertyInteraction(PropertySquare propertySquare, Board board) {
        System.out.println(
                "Player " + name + " step on " + propertySquare.getName());

        if (propertySquare.isOwned()) { // check if property is owned or not?
            if (!ownedProperty.contains(propertySquare)) { // if property is owned by other player, then

                int payAmount = propertySquare.getVisitCost();
                System.out.println("|Property: " + propertySquare.getName() + "|Visit cost: "
                        + propertySquare.getVisitCost() + "|Level: " + propertySquare.getLevel() + "|");
                System.out.println(name + " pays rent of $" + payAmount + " to " + propertySquare.getOwner().getName());
                if (getCash() < payAmount) {// if player cash does not enough to pay
                    System.out.println(name + " does not have enough money to pay!!!");
                    sellPropertiesToCoverRent(payAmount);
                    if (getCash() >= payAmount) {
                        handlePropertyInteraction(propertySquare, board);
                    } else {
                        setBankrupt(true);
                    }
                }
                setCash(getCash() - payAmount);
                System.out.println(name + "'s new balance: " + getCash());
                if (getCash() > propertySquare.getPropertyValue() && propertySquare.getType() == SquareType.CITY
                        && propertySquare.getLevel() < 5) {
                    System.out.println("Do you want to buy this city?");
                    System.out.println("1. Yes");
                    System.out.println("0. No");
                    while (true) {
                        try {
                            System.out.println("Your choice:");
                            int choice = Integer.parseInt(sc.nextLine());
                            if (choice == 1) {
                                buyPropertyFromOtherPlayer(propertySquare.getOwner(), propertySquare);
                            } else if (choice == 0) {
                                System.out.println("You choose exit.");
                                break;
                            } else {
                                System.out.println("Please input 0 or 1!!!");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Please input an integer!!!");
                            ;
                        }
                    }

                } else {
                    System.out.println("Player " + getName() + " do not have enough money to buy this property from "
                            + propertySquare.getOwner().getName());
                }
            } else {
                for (int i = 0; i < board.getSquares().size(); i++) {
                    Square square = board.getSquares().get(i);
                    if (square.getType() == SquareType.PROPERTY.CITY) {
                        PropertySquare property = (PropertySquare) square;
                        if (property.getLevel() < 5) {
                            System.out.println("Upgrade cost: $" + property.getUpgradeCost());
                            System.out.println("Player " + name + " balance: $" + getCash());
                            System.out.println("Do you want to upgrade this city?");
                            System.out.println("1. Yes");
                            System.out.println("0. No");
                            while (true) {
                                try {
                                    System.out.println(name + " choice: ");
                                    int choice = Integer.parseInt(sc.nextLine());
                                    if (choice == 1) {
                                        if (getCash() >= property.getUpgradeCost() && property.getLevel() <= 5) {

                                            upgradeCity(property);

                                            System.out.println("Player " + name + " successfully upgrade "
                                                    + propertySquare.getName() + " to level "
                                                    + propertySquare.getLevel());
                                            System.out
                                                    .println("Player " + getName() + "'s new balance: $" + getCash());

                                        } else {
                                            System.out.println(name + " do not have enough cash to upgrade "
                                                    + property.getName() + ".");
                                            break;
                                        }
                                        break;
                                    } else if (choice == 0) {
                                        System.out.println(name + " choose exit.");
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Please choose valid integer!!!");
                                }
                            }
                            break;
                        } else {
                            System.out.println("This property has reached highest level.");
                            break;
                        }

                    }
                }

            }
        } else {
            System.out.println(name + " landed on an unowned property.");
            System.out.println(propertySquare.toString());
            System.out.println("Buy cost: $" + propertySquare.getInitialValue());
            System.out.println("Player " + name + " balance: $" + getCash());
            System.out.println("Do you want to buy it?");
            System.out.println("1. Yes");
            System.out.println("0. No");
            while (true) {
                try {
                    System.out.println("Your choice: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        if (getCash() >= propertySquare.getInitialValue()) {
                            System.out.println(name + " buy " + propertySquare.getName() + " with $"
                                    + propertySquare.getInitialValue());
                            buyProperty(propertySquare);
                            System.out.println("Player " + name + " successfully buy " + propertySquare.getName());
                            System.out.println("Player " + name + " new balance: $" + getCash());
                        } else {
                            System.out.println(
                                    name + " do not have enough cash to buy " + propertySquare.getName() + ".");
                        }
                        break;
                    } else if (choice == 0) {
                        System.out.println("You choose exit.");
                        break;
                    } else {
                        System.out.println("Please choose 1 or 0 !!!");
                    }
                } catch (Exception e) {
                    System.out.println("Input valid number!!!");
                }
            }
        }
    }

    private void buyPropertyFromOtherPlayer(Player otherPlayer, PropertySquare propertySquare) {
        int sellValue = propertySquare.getPropertyValue();
        System.out.println(sellValue);
        int tax = sellValue / 10;
        System.out.println(tax);

        System.out.println("Player " + getName() + " buy " + propertySquare.getName() + " from " + otherPlayer.getName()
                + " with $" + sellValue);
        System.out.println("Tax: $" + tax + " (10% property value).");

        System.out.println("Player " + getName() + " pay $" + sellValue);
        setCash(getCash() - sellValue);
        System.out.println("Player " + getName() + "'s new balance: $" + getCash());

        System.out.println("Player " + otherPlayer.getName() + " receive $" + (sellValue - tax));
        otherPlayer.setCash(otherPlayer.getCash() + (sellValue - tax));
        System.out.println("Player " + otherPlayer.getName() + "'s new balance: $" + otherPlayer.getCash());

        otherPlayer.ownedProperty.remove(propertySquare);
        ownedProperty.add(propertySquare);
        propertySquare.setOwner(this);

    }

    public void sellPropertiesToCoverRent(int rentAmount) {
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
                break;
            }

            for (PropertySquare selectedProperty : selectedProperties) {
                int sellValue = selectedProperty.calculateSellValue();
                System.out.println(name + " sells " + selectedProperty.getName() + " for $" + sellValue);

                ownedProperty.remove(selectedProperty);
                setCash(getCash() + sellValue);
                System.out.println("Player " + getName() + "'s new balacnce: $" + getCash());
                //
            }

            if (getCash() > rentAmount) {
                System.out.println(name + " has sold enough properties to cover the fee.");

                break;
            } else {
                System.out.println("Player " + name + " don't have enough money to afford the fee.");
                break;
            }
            
        }

    }

    private void buyProperty(PropertySquare property) {
        if (property.getType() == SquareType.BEACH) {
            ownedProperty.add(property);
            setCash(getCash() - property.getInitialValue());
            property.setOwned(true);
            property.setOwner(this);
            property.calculatePropertyValue(this);

            System.out.println(property.getPropertyValue());
            property.calculateVisitCost();
            System.out.println(property.getVisitCost());
        } else {
            ownedProperty.add(property);
            setCash(getCash() - property.getInitialValue());
            property.setOwned(true);
            property.setOwner(this);
            property.calculatePropertyValue();

            System.out.println(property.getPropertyValue());
            property.calculateVisitCost();
            System.out.println(property.getVisitCost());
        }

    }

    private void upgradeCity(PropertySquare property) {
        setCash(getCash() - property.getUpgradeCost());
        property.setLevel(property.getLevel() + 1);
        property.calculatePropertyValue();
        property.calculateVisitCost();
    }

    // Tax interaction
    public void handleTaxInteraction(Tax tax, Board board) {
        System.out.println("Player " + name + " step on Tax");
        tax.setTotalTax(tax.calculateTotalTax(this, board));
        Iterator<Card> iterator = ownedCard.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getCardType() == CardType.TAX_REDUCE) {
                System.out.println("Player " + name + " use Tax Reduce Card");
                System.out.println("The total tax will reduce by 50%");
                tax.setTotalTax(tax.calculateTotalTax(this, board) / 2);
                iterator.remove();
                break;
            }
        }

        if (getCash() >= tax.getTotalTax()) {
            System.out.println("Player " + getName() + " pay $" + tax.getTotalTax() + " for tax.");

        } else {
            System.out.println("Player " + name + " do not have enough money to pay tax.");
            System.out.println("Player " + name + " has to sell properties.");
            sellPropertiesToCoverRent(tax.getTotalTax());
            if (getCash() >= tax.getTotalTax()) {
                handleTaxInteraction(tax, board);
            } else {
                setBankrupt(true);
            }
        }
        setCash(getCash() - tax.getTotalTax());
        System.out.println("Player " + name + "'s new balance: $" + getCash());
    }

    // Plane interaction
    public void handlePlaneInteraction(Plane plane, Board board, Player currentPlayer) {
        System.out.println("Player " + name + " landed on World Tour");
        if (ownedProperty.size() == 0) {
            System.out.println("Player " + getName() + " do not have any property to travel");
            return;
        }
        if (currentPlayer.getCash() >= 50000) {
            System.out.println("Do you want to use World Tour? Cost: $50000");
            System.out.println("1. Yes");
            System.out.println("0. No");
            while (true) {
                try {
                    System.out.println("Your choice: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        currentPlayer.setCash(currentPlayer.getCash() - 50000);
                        System.out.println("Player " + name + " new balance: $" + getCash());
                        plane.travel(board, currentPlayer);
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
        boolean alreadyRolled = false;
        for (Card card : ownedCard) {
            if (card.getCardType() == CardType.OUT_OF_JAIL) {
                System.out.println("Player " + name + " has Out Of Jail card.");
                System.out.println("Player " + name + " can continue the game.");
                inJail = false;
            } else {
                inJail = true;
            }

        }
        if (inJail) {
            System.out.println(name + " is in Jail.");
            if (!alreadyRolled) {
                System.out.println(name + " is in Jail. Try to roll doubles to get out.");

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
            } else {
                System.out.println(
                        name + " has already rolled the dice in this turn. Wait for the next turn to roll again.");
            }
        }

        if (jailTurn == 3) {
            System.out.println("Player " + name + " have been in Jail for 3 turns. Pay $50000 to get out.");
            payJailFine(50000);
            System.out.println("Player " + getName() + "'s new balacnce: $" + getCash());
        }

    }

    private void payJailFine(int amount) {
        if (getCash() >= amount) {
            System.out.println("You paid the fine and got out of Jail.");
            setCash(getCash() - amount);
            inJail = false;
            jailTurn = 0;
        } else {
            System.out
                    .println("You don't have enough money to pay the fine. You must sell something to cover the fee.");
            sellPropertiesToCoverRent(amount);
        }
    }

    // Event Interaction
    public void handleEventInteraction(Event event, Board board, Player player) {
        System.out.println("Player: " + name + " rolled in Event.");
        event.setWorldCup(board, player);
    }

    // Card Interaction
    public void handleCardInteraction(Card card, Board board) {
        Random random = new Random();
        int num = random.nextInt(11) + 1;
        switch (num) {
            case 1: {
                card.EarthQuakeChanceCard(this);
                break;
            }
            case 2: {
                card.ReceiveMoneyChanceCard(this);
                break;
            }
            case 3: {
                card.GiveOpponentCashChanceCard(this, board);
                break;
            }
            case 4: {
                card.GiveOpponentCityChanceCard(this, board);
                break;
            }
            case 5: {
                card.GoBackThreeSpaceChanceCard(this, board);
                break;
            }
            case 6: {
                card.GoToJailChanceCard(this, board);
                break;
            }
            case 7: {
                card.GoOutOfJailChanceCard(this, board);
                break;
            }
            case 8: {
                card.ReduceTaxChanceCard(this, board);
                break;
            }
            case 9: {
                card.ChairManChanceCard(this, board);
                break;
            }
            case 10: {
                card.AdvanceToStartChanceCard(this, board);
                break;
            }
            case 11: {
                card.CollectMoneyFromOtherPlayerCommunityCard(this, board);
                break;
            }

            default:
                break;
        }
    }
}
