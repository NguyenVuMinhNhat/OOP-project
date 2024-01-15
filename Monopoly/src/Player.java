
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    private String name;
    private Color color;
    

    private int cash;
    private int location;
    private boolean inJail;

    private List<PropertySquare> ownedProperty = new ArrayList<>();
    private List<Card> ownedCard = new ArrayList<>();

    public Player() {
        this.cash = 500000;
        this.location = 1;
        this.inJail = false;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public boolean isBankrupt() {
        return ownedProperty.isEmpty() && getCash() <= 0;
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

    

    public int totalNumberOfBeach() {
        int beachCount = 0;
        for (Square square : ownedProperty) {
            if (square instanceof Beach) {
                beachCount++;
            }
        }
        return beachCount;
    }

    public int calculateTotalPropertyValue() {
        int totalPropertyValue = 0;
        for (int i = 0; i < ownedProperty.size(); i++) {
            totalPropertyValue += ownedProperty.get(i).getPropertyValue();
        }
        return totalPropertyValue;
    }


    public void interactWithSquare(Board board, GUIV2 gui) {

        Square currentSquare = board.getSquares().get(getLocation() - 1);
        if (currentSquare instanceof PropertySquare) {
            handlePropertyInteraction((PropertySquare) currentSquare, board, gui);
        } else if (currentSquare instanceof Tax) {
            handleTaxInteraction((Tax) currentSquare, board, gui);
        } else if (currentSquare instanceof Plane) {
            handlePlaneInteraction((Plane) currentSquare, board, this, gui);
        } else if (currentSquare instanceof Jail) {
            System.out.println(name + " landed on Jail!");
            setInJail(true);
           
        } else if (currentSquare instanceof Event) {
            handleEventInteraction((Event) currentSquare, board, this, gui);
        } else if (currentSquare instanceof Card) {
            System.out.println("Player " + name + " landed on Chance Square.");
            handleCardInteraction((Card) currentSquare, board, gui);
        }

    }

    // Properties Interaction
    private void handlePropertyInteraction(PropertySquare propertySquare, Board board, GUIV2 gui) {
        System.out.println(
                "Player " + name + " step on " + propertySquare.getName());

        if (propertySquare.isOwned()) { // check if property is owned or not?
            if (!ownedProperty.contains(propertySquare)) { // if property is owned by other player, then

                int payAmount = propertySquare.getVisitCost();
                System.out.println("|Property: " + propertySquare.getName() + "|Visit cost: $"
                        + propertySquare.getVisitCost() + "|Property value: $" + propertySquare.getPropertyValue()
                        + "|Level: " + propertySquare.getLevel() + "|");
                System.out.println(name + " pays rent of $" + payAmount + " to " + propertySquare.getOwner().getName());
                if (getCash() < payAmount) {// if player cash does not enough to pay
                    System.out.println(name + " does not have enough money to pay!!!");
                    sellPropertiesToCoverRent(payAmount, gui, board);
                    if (getCash() < payAmount) {
                        System.out.println("Player " + name + " is bankrupt.");
                        isBankrupt();
                    }
                }
                setCash(getCash() - payAmount);
                System.out.println("Player " + getName() + " pay: $" + payAmount);

                int commission = (payAmount * 5) / 100;
                System.out.println("Total commission (5%): $" + commission);

                propertySquare.getOwner().setCash(propertySquare.getOwner().getCash() + (payAmount - commission));
                System.out.println("Player " + propertySquare.getOwner().getName() + " receive: $"
                        + (payAmount - commission));
                System.out.println("Player " + getName() + "'s new balance: $" + getCash());
                System.out.println("Player " + propertySquare.getOwner().getName() + "'s new balance: $"
                        + propertySquare.getOwner().getCash());

                if (getCash() > propertySquare.getPropertyValue() && propertySquare.getType() == SquareType.CITY
                        && propertySquare.getLevel() < 5) {
                    System.out.println("Do you want to buy this city?");
                    System.out.println("1. Yes");
                    System.out.println("0. No");
                    while (true) {
                        try {
                            System.out.println("Your choice:");
                            int choice = gui.getUserInput();
                            if (choice == 1) {
                                buyPropertyFromOtherPlayer(propertySquare.getOwner(), propertySquare);
                            } else if (choice == 0) {
                                System.out.println("You choose exit.");
                               
                            } else {
                                System.out.println("Please input 0 or 1!!!");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Please input an integer!!!");

                        }
                    }

                } else if (getCash() > propertySquare.getPropertyValue()) {
                    System.out.println("Player " + getName() + " do not have enough money to buy "
                            + propertySquare.getName() + " property from "
                            + propertySquare.getOwner().getName());
                } else if (propertySquare.getType() == SquareType.BEACH) {
                    System.out.println("You cannot buy beach properties.");
                }
            } else {
                if (propertySquare.getType() == SquareType.CITY && propertySquare.getLevel() < 5) {
                    if (getCash() >= propertySquare.getUpgradeCost()) {

                        System.out.println("Upgrade cost: $" + propertySquare.getUpgradeCost());
                        System.out.println("Player " + name + " balance: $" + getCash());
                        System.out.println("Do you want to upgrade this city?");
                        System.out.println("1. Yes");
                        System.out.println("0. No");
                        int choice = 0;
                        while (true) {
                            try {

                                choice = gui.getUserInput();
                                if (choice == 1 || choice == 0) {
                                    System.out.println("Player " + getName() + " choice: " + choice);
                                    break;
                                } else {
                                    System.out.println("Please choose 0 or 1!!!");
                                }

                            } catch (Exception e) {
                                System.out.println("Please input an integer!!!");
                            }
                        }

                        if (choice == 1) {
                            upgradeCity(propertySquare);
                            System.out.println("Player " + name + " successfully upgrade "
                                    + propertySquare.getName() + " to level "
                                    + propertySquare.getLevel());
                            System.out
                                    .println("Player " + getName() + "'s new balance: $" + getCash());
                        } else if (choice == 0) {
                            System.out.println("Player " + getName() + " choose exit.");
                            return;
                        }

                    } else if (getCash() < propertySquare.getUpgradeCost()) {
                        System.out.println("Player " + getName() + " don't have enough money to upgrade this city.");
                    }
                } else if (propertySquare.getType() == SquareType.CITY && propertySquare.getLevel() == 5) {
                    System.out.println("This city reach highest level.");
                }

            }
        } else if (getCash() >= propertySquare.getInitialValue()) {
            System.out.println(name + " landed on an unowned property.");
            System.out.println(propertySquare.toString());
            System.out.println("Player " + name + " balance: $" + getCash());
            System.out.println("Do you want to buy it?");
            System.out.println("1. Yes");
            System.out.println("0. No");
            while (true) {
                try {
                    System.out.println("Your choice: ");
                    int choice = gui.getUserInput();
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
        int commission = sellValue / 10;
        System.out.println(commission);

        System.out.println("Player " + getName() + " buy " + propertySquare.getName() + " from " + otherPlayer.getName()
                + " with $" + sellValue);
        System.out.println("Total commission: $" + commission + " (10% property value).");

        System.out.println("Player " + getName() + " pay $" + sellValue);
        setCash(getCash() - sellValue);

        System.out.println("Player " + otherPlayer.getName() + " receive $" + (sellValue - commission));
        otherPlayer.setCash(otherPlayer.getCash() + (sellValue - commission));
        System.out.println("Player " + getName() + "'s new balance: $" + getCash());
        System.out.println("Player " + otherPlayer.getName() + "'s new balance: $" + otherPlayer.getCash());

        otherPlayer.ownedProperty.remove(propertySquare);
        ownedProperty.add(propertySquare);
        propertySquare.setOwner(this);

    }

    public void sellPropertiesToCoverRent(int rentAmount, GUIV2 gui, Board board) {

        while (getCash() < rentAmount && !ownedProperty.isEmpty()) {
            System.out.println(getName() + "'s balance: $" + getCash());
            for (int i = 0; i < ownedProperty.size(); i++) {
                System.out.printf("|%d. %20s | Sell value: %d |%n", i + 1, ownedProperty.get(i).getName(),
                        ownedProperty.get(i).calculateSellValue());
            }

            System.out.println("Choose a property to sell (enter the corresponding number):");

            int choice = -1;

            while (true) {
                try {
                    choice = gui.getUserInput();

                    if (choice == 0) {
                        break; // Exit the inner loop when the user chooses to finish
                    }

                    if (choice > 0 && choice <= ownedProperty.size()) {
                        PropertySquare selectedProperty = ownedProperty.get(choice - 1);
                        int sellValue = selectedProperty.calculateSellValue();
                        System.out.println(name + " sells " + selectedProperty.getName() + " for $" + sellValue);

                        ownedProperty.remove(selectedProperty);
                        selectedProperty.setOwned(false);
                        selectedProperty.setOwner(null);
                        if (selectedProperty instanceof City) {
                            selectedProperty.resetAllAtribute();
                            City city = (City) selectedProperty;
                            if (city.isWorldCup()) {
                                System.out.println("Player " + name + " remove World Cup from " + city.getName());
                                city.setWorldCup(false);
                                board.getEventCity().clear();
                            }
                        }

                        setCash(getCash() + sellValue);
                        System.out.println("Player " + getName() + "'s new balance: $" + getCash());
                        break; // Exit the inner loop after a valid selection
                    } else {
                        System.out.println("Invalid choice. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            if (getCash() >= rentAmount) {
                System.out.println(name + " has sold enough properties to cover the fee.");
            } else if (ownedProperty.isEmpty()) {
                System.out.println("Player " + name + " is bankrupt.");
                break;
            }
        }
    }

    private void buyProperty(PropertySquare property) {
        if (property.getType() == SquareType.BEACH) {
            ownedProperty.add(property);
            setCash(getCash() - property.getInitialValue());
            property.setLevel(property.getLevel() + 1);
            property.setOwned(true);
            property.setOwner(this);
            for (int i = 0; i < ownedProperty.size(); i++) {
                if (ownedProperty.get(i) instanceof Beach) {
                    Beach beach = (Beach) ownedProperty.get(i);
                    beach.calculatePropertyValue(this);
                    beach.calculateVisitCost();
                }
            }
            System.out.printf(
                    "|Name: %s|Location: %d|Owner: %s|Property value: %d$|Visit cost: %d$|",
                    property.getName(), property.getPosition(),
                    property.getOwner().getName(), property.getPropertyValue(), property.getVisitCost());

            System.out.println();
        } else {
            ownedProperty.add(property);
            setCash(getCash() - property.getInitialValue());
            property.setLevel(property.getLevel() + 1);
            property.setOwned(true);
            property.setOwner(this);
            property.calculatePropertyValue();

            City city = (City) property;
            property.calculateVisitCost(city.isWorldCup());
            System.out.printf(
                    "|Name: %s|Location: %d|Owner: %s|Property value: %d$|Visit cost: %d$|Upgrade cost: %d$|Level: %d|",
                    property.getName(), property.getPosition(),
                    property.getOwner().getName(), property.getPropertyValue(), property.getVisitCost(),
                    property.getUpgradeCost(),
                    property.getLevel());
            System.out.println();

        }

    }

    private void upgradeCity(PropertySquare property) {
        setCash(getCash() - property.getUpgradeCost());
        property.setLevel(property.getLevel() + 1);
        property.calculatePropertyValue();

        City city = (City) property;
        property.calculateVisitCost(city.isWorldCup());

        property.calculateUpgradeCost();
        System.out.printf(
                "|Name: %s|Location: %d|Owner: %s|Property value: %d$|Visit cost: %d$|Upgrade cost: %d$|Level: %d|",
                property.getName(), property.getPosition(),
                property.getOwner().getName(), property.getPropertyValue(), property.getVisitCost(),
                property.getUpgradeCost(),
                property.getLevel());
        System.out.println();
    }

    // Tax interaction
    public void handleTaxInteraction(Tax tax, Board board, GUIV2 gui) {
        System.out.println("Player " + name + " step on Tax");
        tax.setTotalTax(tax.calculateTotalTax(this, board));

        int payAmount = tax.getTotalTax();
        System.out.println("Tax fee: $" + payAmount);
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

        if (getCash() >= payAmount) {
            System.out.println("Player " + getName() + " pay $" + payAmount + " for tax.");

        } else {
            System.out.println("Player " + name + " do not have enough money to pay tax.");
            System.out.println("Player " + name + " has to sell properties.");
            System.out.println(name + " has sold enough properties to cover the fee.");
            sellPropertiesToCoverRent(payAmount, gui, board);
            if (getCash() >= payAmount) {
                setCash(getCash() - payAmount);
                System.out.println("Player " + getName() + "'s new balance: $" + getCash());
            } else {
                System.out.println("Player " + name + " don't have enough money to afford the fee.");
                isBankrupt();
            }
        }

    }

    // Plane interaction
    public void handlePlaneInteraction(Plane plane, Board board, Player currentPlayer, GUIV2 gui) {
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
                    int choice = gui.getUserInput();
                    if (choice == 1) {
                        currentPlayer.setCash(currentPlayer.getCash() - 50000);
                        System.out.println("Player " + name + " new balance: $" + getCash());
                        plane.travel(board, currentPlayer, gui);
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

    public void handleJailInteraction(Board board, GUIV2 gui) {

        // Check for Out of Jail card
        for (Card card : ownedCard) {
            if (card.getCardType() == CardType.OUT_OF_JAIL) {
                System.out.println("Player " + name + " has Out Of Jail card.");

                setInJail(false);
            }
        }

        if (isInJail()) {
            Jail.handlePlayerInJail(board, this, gui);
        } else {
            System.out.println("Player " + name + " can continue the game.");
        }

    }

    // Event Interaction
    public void handleEventInteraction(Event event, Board board, Player player, GUIV2 gui) {
        ArrayList<PropertySquare> cities = new ArrayList<>();
        for (PropertySquare propertySquare : player.getOwnedProperty()) {
            if (propertySquare.getType() == SquareType.CITY) {
                cities.add(propertySquare);
            }
        }
        if (cities.isEmpty()) {
            System.out.println("Player " + getName() + " didn't buy any property.");
            System.out.println("Player " + getName() + " cannot hold an event.");
            return;
        }
        System.out.println("Player: " + name + " rolled in Event.");
        event.setWorldCup(board, player, gui);
    }

    // Card Interaction
    public void handleCardInteraction(Card card, Board board, GUIV2 gui) {
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
                card.GiveOpponentCashChanceCard(this, board, gui);
                break;
            }
            case 4: {
                if (getOwnedProperty().isEmpty()) {
                    System.out.println("Player " + getName() + " do not have any city.");
                    System.out.println("Player " + getName() + " will give all player $200000.");
                    for (Player otherplayer : board.getPlayers()) {
                        if (!otherplayer.equals(this)) {
                            otherplayer.setCash(getCash() + 200000);
                            System.out.println(
                                    "Player " + otherplayer.getName() + "'s new balance: $" + otherplayer.getCash());
                        }

                    }
                    setCash(getCash() - (board.getPlayers().size() - 1) * 200000);
                    System.out.println(
                            "Player " + getName() + "'s new balance: $" + getCash());
                    return;
                }
                card.GiveOpponentCityChanceCard(this, board, gui);
                break;
            }
            case 5: {
                card.GoBackThreeSpaceChanceCard(this, board, gui);
                break;
            }
            case 6: {
                card.GoToJailChanceCard(this, board, gui);
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
                card.ChairManChanceCard(this, board, gui);
                break;
            }
            case 10: {
                card.AdvanceToStartChanceCard(this, board, gui);
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
