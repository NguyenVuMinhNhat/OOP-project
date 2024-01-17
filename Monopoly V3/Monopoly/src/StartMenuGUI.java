import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenuGUI extends JFrame  {
    JButton startButton;
    int numPlayers;
    JLabel monopolyLabel;
    JPanel contentPanel;

    StartMenuGUI(Monopoly monopoly, Board board) {
        ImageIcon MonopolyIcon = new ImageIcon("Monopoly\\src\\ImageIcon\\Monopoly_game_logo.svg.png");

        startButton = new JButton("START GAME");
        startButton.setBounds(0, 0, 150, 75);
        startButton.setFocusable(false);
        startButton.setHorizontalAlignment(JButton.CENTER);
        startButton.setPreferredSize(new Dimension(200, 100));
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == startButton) {
                    monopoly.createSquare(board);
                    monopoly.initializePlayers(board);
                    monopoly.initializePlayerQueqe(board);
                    dispose();
                    new GUIV2(board, monopoly);
                    
                }
            }

        });

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

    

}
