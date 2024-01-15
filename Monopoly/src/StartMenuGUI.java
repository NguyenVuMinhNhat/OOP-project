import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartMenuGUI extends JFrame implements ActionListener {
    JButton startButton;
    int numPlayers;
    JLabel monopolyLabel;
    JPanel contentPanel;
    

    StartMenuGUI(Monopoly monopoly) {
        ImageIcon MonopolyIcon = new ImageIcon("Monopoly\\src\\ImageIcon\\Monopoly_game_logo.svg.png");

        startButton = new JButton("START GAME");
        startButton.setBounds(0, 0, 150, 75);
        startButton.setFocusable(false);
        startButton.setHorizontalAlignment(JButton.CENTER);
        startButton.setPreferredSize(new Dimension(200, 100));
        startButton.addActionListener(this);

        monopolyLabel = new JLabel();
        monopolyLabel.setIcon(MonopolyIcon);
        monopolyLabel.setHorizontalAlignment(JLabel.CENTER);
        monopolyLabel.setVerticalAlignment(JLabel.TOP);
        monopolyLabel.setText("This game is made by Nguyen Vu Minh Nhat");
        monopolyLabel.setHorizontalTextPosition(JLabel.CENTER);
        monopolyLabel.setVerticalTextPosition(JLabel.BOTTOM);
        monopolyLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        monopolyLabel.setIconTextGap(-25);
        monopolyLabel.setPreferredSize(new Dimension(700, 300));

        JPanel logoPanel = new JPanel();
        logoPanel.add(monopolyLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton, BorderLayout.CENTER);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(logoPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        this.setIconImage(MonopolyIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(980, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Monopoly");

        this.add(contentPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {

        if (a.getSource() == startButton) {
            
            this.dispose();
            
            
        }
    }
    public void initializePlayers(Board board) {

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "How many players?", "Player number?",
                        JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    break;
                }
                numPlayers = Integer.parseInt(input);
                if (numPlayers > 1 && numPlayers <= 4) {
                    JOptionPane.showMessageDialog(null, "Number of players: " + numPlayers, "Player quantity",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else if (numPlayers == 1) {
                    JOptionPane.showMessageDialog(null, "There must be at least 2 players to start the game",
                            "ERROR: Invalid number of player!!!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "This game only allow maximum 4 players",
                            "ERROR: Invalid number of player!!!", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Please input an integer", "ERROR: Invalid input!!!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(null, "Enter player " + (i + 1) + " name: ", "Player name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                System.exit(0);
            }

            
            Color color = JColorChooser.showDialog(null, "Pick a color", Color.BLACK);
            Player player = new Player();
            player.setName(name);
            player.setColor(color);
            board.getPlayers().add(player);
        }
    }
    public void printWelcome(Board board) {
        System.out.println("---------Welcome to Monopoly---------");
        System.out.println("This game is made by Nguyen Vu Minh Nhat");
        initializePlayers(board);

        System.out.println("Let's the game begin!!!!");
    }
    public void createSquare(Board board) {
        Start Go = new Start();
        board.getSquares().add(Go);
        City HongKong = new City("Hong Kong", 2, 50000, 0, 50000);
        board.getSquares().add(HongKong);
        City Beijing = new City("Beijing", 3, 52000, 0, 50000);
        board.getSquares().add(Beijing);
        City BangKok = new City("BangKok", 4, 55000, 0, 50000);
        board.getSquares().add(BangKok);
        City LonDon = new City("London", 5, 60000, 0, 50000);
        board.getSquares().add(LonDon);
        Beach Bali = new Beach("Bali", 6, 50000);
        board.getSquares().add(Bali);
        City Singapore = new City("Singapore", 7, 65000, 0, 50000);
        board.getSquares().add(Singapore);
        City Paris = new City("Paris", 8, 70000, 0, 50000);
        board.getSquares().add(Paris);
        City Dubai = new City("Dubai", 9, 75000, 0, 50000);
        board.getSquares().add(Dubai);
        City NewYork = new City("NewYork", 10, 80000, 0, 50000);
        board.getSquares().add(NewYork);
        Jail jail = new Jail();
        board.getSquares().add(jail);
        City KualaLumpur = new City("Kuala Lumpur", 12, 100000, 0, 70000);
        board.getSquares().add(KualaLumpur);
        City Istanbul = new City("Istanbul", 13, 110000, 0, 70000);
        board.getSquares().add(Istanbul);
        City Delhi = new City("Delhi", 14, 120000, 0, 70000);
        board.getSquares().add(Delhi);
        Card chance1 = new Card("Chance", 15);
        board.getSquares().add(chance1);
        City Mumbai = new City("Mumbai", 16, 130000, 0, 70000);
        board.getSquares().add(Mumbai);
        Beach Maldives = new Beach("Maldives", 17, 50000);
        board.getSquares().add(Maldives);
        City Rome = new City("Rome", 18, 140000, 0, 70000);
        board.getSquares().add(Rome);
        City SaoPaulo = new City("Sao Paulo", 19, 150000, 0, 70000);
        board.getSquares().add(SaoPaulo);
        City Taipei = new City("Taipei", 20, 160000, 00, 70000);
        board.getSquares().add(Taipei);
        Event event = new Event("World Cup", 21, SquareType.EVENT);
        board.getSquares().add(event);
        City Seoul = new City("Seoul", 22, 170000, 0, 80000);
        board.getSquares().add(Seoul);
        Beach HaLong = new Beach("Ha Long Bay", 23, 50000);
        board.getSquares().add(HaLong);
        City Barcelona = new City("Barcelona", 24, 185000, 0, 80000);
        board.getSquares().add(Barcelona);
        City LosAngles = new City("LosAngles", 25, 200000, 0, 80000);
        board.getSquares().add(LosAngles);
        Card chance2 = new Card("Chance", 26);
        board.getSquares().add(chance2);
        City HaNoi = new City("Ha Noi", 27, 215000, 0, 80000);
        board.getSquares().add(HaNoi);
        City Milan = new City("Milan", 28, 230000, 0, 80000);
        board.getSquares().add(Milan);
        City Berlin = new City("Berlin", 29, 245000, 0, 80000);
        board.getSquares().add(Berlin);
        City MosCow = new City("Moscow", 30, 260000, 0, 80000);
        board.getSquares().add(MosCow);
        Plane plane = new Plane();
        board.getSquares().add(plane);
        Beach Miami = new Beach("Miami", 32, 50000);
        board.getSquares().add(Miami);
        City Sydney = new City("Sydney", 33, 270000, 0, 100000);
        board.getSquares().add(Sydney);
        Tax tax = new Tax();
        board.getSquares().add(tax);
        City Shanghai = new City("Shanghai", 35, 270000, 0, 100000);
        board.getSquares().add(Shanghai);
        Card chance3 = new Card("Chance", 36);
        board.getSquares().add(chance3);
        City Madrid = new City("Madrid", 37, 300000, 0, 100000);
        board.getSquares().add(Madrid);
        City Venice = new City("Venice", 38, 330000, 0, 100000);
        board.getSquares().add(Venice);
        City Osaka = new City("Osaka", 39, 360000, 0, 100000);
        board.getSquares().add(Osaka);
        City Tokyo = new City("Tokyo", 40, 360000, 0, 100000);
        board.getSquares().add(Tokyo);
    }
   
}

