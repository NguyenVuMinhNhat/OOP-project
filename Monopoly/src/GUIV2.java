import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.PrintStream;
import java.util.ArrayList;


public class GUIV2 extends JPanel {
    JFrame frame = new JFrame();
    int step;
    private String userInput;

    int playerPanelWidth = 200;
    int playerPanelHeight = 100;
    ArrayList<JLabel> playerLabels = new ArrayList<>();
    JLayeredPane boardPanel;

    int numPlayers = 0;

    public static void main(String[] args) {
        Board board = new Board();
        Player player1 = new Player();
        player1.setName("Nhat");
        player1.setColor(Color.BLACK);
        Player player2 = new Player();
        player2.setName("Thang");
        player2.setColor(Color.GREEN);
        Player player3 = new Player();
        player3.setName("Vinh");
        player3.setColor(Color.BLUE);
        Player player4 = new Player();
        player4.setName("Quang");
        player4.setColor(Color.MAGENTA);
        Monopoly monopoly = new Monopoly();
        monopoly.createSquare(board);
        board.getPlayers().add(player1);
        board.getPlayers().add(player2);
        board.getPlayers().add(player3);
        board.getPlayers().add(player4);
        new GUIV2(board, monopoly);
    }

    GUIV2(Board board, Monopoly monopoly) {

        ImageIcon MonopolyIcon = new ImageIcon("Monopoly\\src\\ImageIcon\\Monopoly_game_logo.svg.png");
        frame.setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // make it read-only
        textArea.setBounds(0, 0, 350, 370);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 350, 370);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setBounds(100, 320, 350, 400);
        textPanel.add(scrollPane, BorderLayout.CENTER);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);

        JTextField choiceTextField = new JTextField();
        choiceTextField.setBounds(100, 370, 250, 30);
        textPanel.add(choiceTextField, BorderLayout.SOUTH);
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(0, 370, 100, 30);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                userInput = choiceTextField.getText();
                choiceTextField.setText("");
            }

        });
        textPanel.add(submitBtn, BorderLayout.WEST);

        for (int i = 0; i < board.getPlayers().size(); i++) {
            JPanel playerPanel = new JPanel();
            int xPosition = (i % 2 == 0) ? 0 : 1920 - playerPanelWidth;
            int yPosition = (i < 2) ? 0 : 1080 - playerPanelHeight - 40;
            playerPanel.setBounds(xPosition, yPosition, playerPanelWidth, playerPanelHeight);

            JLabel colorLabel = new JLabel();
            colorLabel.setOpaque(true);
            colorLabel.setBackground(board.getPlayers().get(i).getColor());
            colorLabel.setBounds(0, 0, 50, playerPanelHeight);

            JLabel nameLabel = new JLabel("Player name: " + board.getPlayers().get(i).getName());
            nameLabel.setBounds(60, 0, playerPanelWidth - 25, 20);

            JLabel cashLabel = new JLabel("Cash: $" + board.getPlayers().get(i).getCash());
            cashLabel.setBounds(60, 20, playerPanelWidth - 25, 20);

            playerPanel.setLayout(null);
            playerPanel.add(colorLabel);
            playerPanel.add(nameLabel);
            playerPanel.add(cashLabel);
            playerPanel.setBackground(new Color(192, 192, 192));

            frame.add(playerPanel);
        }

        boardPanel = new JLayeredPane();
        boardPanel.setBackground(new Color(179, 206, 229));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        boardPanel.setOpaque(true);
        int boardWidth = 900;
        int boardHeight = 900;
        boardPanel.setBounds((1920 - boardWidth) / 2, (1080 - boardHeight) / 2, boardWidth, boardHeight);

        // Create the centerPanel
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(125, 125, boardWidth - 250, boardHeight - 250);
        centerPanel.setBackground(new Color(62, 180, 137)); // Set your desired background color
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centerPanel.setLayout(null);
        boardPanel.add(centerPanel, JLayeredPane.DEFAULT_LAYER);

        JLabel logoLabel = new JLabel(MonopolyIcon);
        logoLabel.setBounds((centerPanel.getWidth() - MonopolyIcon.getIconWidth()) / 2,
                100, MonopolyIcon.getIconWidth(),
                MonopolyIcon.getIconHeight());
        centerPanel.add(logoLabel);

        JButton rollBtn = new JButton("Roll Dice");
        centerPanel.add(rollBtn);
        rollBtn.setBounds(250, 500, 100, 50);
        rollBtn.setFocusable(false);
        rollBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int step = rollDice();
                monopoly.setStep(step);

                frame.repaint();
            }

        });

        // Create North panel
        JPanel northPanel = createBorderPanel(boardWidth, 120);
        northPanel.setLocation(120, 0);
        northPanel.setBackground(Color.red);
        Square event = board.getSquares().get(20);
        event.setX(660);
        event.setY(1);
        event.setHeight(118);
        event.setWidth(119);
        event.setAngle(-135);
        northPanel.add(event);
        for (int i = 11; i < 20; i++) {
            Square square = board.getSquares().get(i);
            square.setX(2 + 73 * (i - 11));
            square.setY(1);
            square.setHeight(117);
            square.setWidth(71);
            square.setAngle(180);

            northPanel.add(square);
        }
        boardPanel.add(northPanel, JLayeredPane.PALETTE_LAYER);

        // Create South panel
        JPanel southPanel = createBorderPanel(boardWidth - 120, 120);
        southPanel.setLocation(0, boardHeight - 120);
        southPanel.setBackground(Color.cyan);
        southPanel.setLayout(null);
        boardPanel.add(southPanel, JLayeredPane.PALETTE_LAYER);
        Square start = board.getSquares().get(0);
        start.setX(0);
        start.setY(0);
        start.setHeight(118);
        start.setWidth(118);
        start.setAngle(45);
        southPanel.add(start);

        for (int i = 39; i >= 30; i--) {
            Square square = board.getSquares().get(i);
            square.setX(121 + 73 * (39 - i));
            square.setY(1);
            square.setHeight(118);
            square.setWidth(72);
            southPanel.add(square);
        }

        // Create East panel
        JPanel eastPanel = createBorderPanel(120, boardHeight - 120);
        eastPanel.setLocation(boardWidth - 120, 120);
        eastPanel.setBackground(Color.lightGray);
        boardPanel.add(eastPanel, JLayeredPane.PALETTE_LAYER);
        Square plane = board.getSquares().get(30);
        plane.setX(2);
        plane.setY(660);
        plane.setHeight(118);
        plane.setWidth(117);
        plane.setAngle(-45);
        eastPanel.add(plane);
        for (int i = 21; i < 30; i++) {
            Square square = board.getSquares().get(i);
            square.setX(1);
            square.setY(1 + 73 * (i - 21));
            square.setHeight(71);
            square.setWidth(118);
            square.setAngle(-90);
            eastPanel.add(square);
        }

        // Create West panel
        JPanel westPanel = createBorderPanel(120, boardHeight - 120);
        westPanel.setBackground(Color.ORANGE);
        westPanel.setLocation(0, 0);
        westPanel.setLayout(null);

        Square jail = board.getSquares().get(10);
        jail.setX(0);
        jail.setY(0);
        jail.setHeight(118);
        jail.setWidth(119);
        jail.setAngle(135);
        westPanel.add(jail);

        for (int i = 9; i >= 1; i--) {
            Square square = board.getSquares().get(i);
            square.setX(1);
            square.setY(121 + 73 * (9 - i));
            square.setHeight(71);
            square.setWidth(118);
            square.setAngle(90);
            westPanel.add(square);

        }
        boardPanel.add(westPanel);

        for (int i = 0; i < board.getPlayers().size(); i++) {
            JLabel playerLabel = new JLabel();
            Player player = board.getPlayers().get(i);
            playerLabel.setBackground(player.getColor());
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLabel.setVerticalAlignment(SwingConstants.CENTER);
            playerLabel.setBounds(2 + 30 * i, 820, 20, 30);
            playerLabel.setText("" + (i + 1));
            playerLabel.setOpaque(true);
            playerLabels.add(playerLabel);
            boardPanel.add(playerLabel, JLayeredPane.MODAL_LAYER);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null); // Centering the main frame
        frame.setResizable(false);
        frame.setTitle("Monopoly");
        frame.getContentPane().setBackground(new Color(179, 206, 229));

        frame.add(boardPanel);
        frame.add(textPanel);

        frame.setVisible(true);
    }

    public void changePlayerPosition(Board board, Player player) {
        int i = board.getPlayers().indexOf(player);

        if (1 <= player.getLocation() && player.getLocation() <= 11) {
            playerLabels.get(i).setBounds(2 + i * 30, 900 - 73 * player.getLocation(), 20, 30);
        }
        if (12 <= player.getLocation() && player.getLocation() <= 20) {
            playerLabels.get(i).setBounds(123 + ((player.getLocation() - 12) * 73), 1 + 15 * i, 20, 30);
        }
        if (21 <= player.getLocation() && player.getLocation() <= 31) {
            playerLabels.get(i).setBounds(900 - 2 - i * 20, 1 + (player.getLocation() - 20) * 73, 20, 30);
        }
        if (32 <= player.getLocation() && player.getLocation() <= 40) {
            playerLabels.get(i).setBounds(900 - 120 - 2 - 73 * (player.getLocation() - 32), 900 - (15 * i) - 1, 20, 30);
        }

    }

    public void updatePlayerPosition(Board board, Player player) {
        changePlayerPosition(board, player);
        frame.repaint();
    }

    public void removePlayerFromGUI(Board board) {
        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i).getColor() == playerLabels.get(i).getBackground()) {
                boardPanel.remove(playerLabels.get(i));
            }
        }
        frame.repaint();
    }

    
    private static JPanel createBorderPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setSize(width, height);
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        return panel;
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

            totalValue = dice1.getValueDice() + dice2.getValueDice() + rollDice();
        }

        return totalValue;
    }

    public int getUserInput() {
        // Show a modal dialog prompting the user for input
        String userInput = JOptionPane.showInputDialog(frame, "Please enter a number:");
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return -1; // Return a special value to indicate an invalid input
        }
    }


    public void setUserInput(String userInput) {
        if (userInput == null) {
            System.out.println("Please input a number");
        }
        this.userInput = userInput;
    }
}

class CustomOutputStream extends java.io.OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        // Append the text to the JTextArea
        textArea.append(String.valueOf((char) b));
        // Scroll to the bottom to always show the latest output
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}