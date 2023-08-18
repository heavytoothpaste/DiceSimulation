import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiggedVersion {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Dice");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel();
        JPanel d1 = new JPanel();
        JPanel d2 = new JPanel();
        top.setLayout(new GridLayout(1, 2));
        top.add(d1);
        top.add(d2);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton reroll = new JButton("Reroll");
        reroll.setPreferredSize(new Dimension(100, 30));
        JLabel sum = new JLabel("Sum", SwingConstants.CENTER);
        sum.setHorizontalAlignment(SwingConstants.CENTER);
        bottom.add(reroll);
        bottom.add(sum);

        reroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dice1 = rig();
                int dice2 = rig();

                d1.removeAll();
                d2.removeAll();

                drawDice(d1, dice1);
                drawDice(d2, dice2);

                d1.revalidate();
                d1.repaint();
                d2.revalidate();
                d2.repaint();

                int totalSum = dice1 + dice2;
                sum.setText("Sum: " + totalSum);
            }
        });

        frame.add(top, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    private static int rig(){
        double d = Math.random();
        if(d*21<6){
            return 6;
        }
        else if (d*21 < 11){
            return 5;
        }
        else if (d*21 < 15){
            return 4;
        }
        else if (d*21<18){
            return 3;
        }
        else if (d*21 < 20){
            return 2;
        }
        else{
            return 1;
        }
    }
    private static void drawDice(JPanel panel, int numDots) {
        int squareSize = 100;
        int dotSize = 20;
        int gap = 25;

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel dicePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, squareSize - 1, squareSize - 1);

                int center = squareSize / 2;

                if (numDots == 6) {
                    g.fillOval(center - gap - dotSize / 2, center - dotSize / 2, dotSize, dotSize);
                    g.fillOval(center + gap - dotSize / 2, center - dotSize / 2, dotSize, dotSize);
                }
                if (numDots % 2 != 0) {
                    g.fillOval(center - dotSize / 2, center - dotSize / 2, dotSize, dotSize);
                }

                if (numDots >= 2) {
                    g.fillOval(center - gap - dotSize / 2, center - gap - dotSize / 2, dotSize, dotSize);
                    g.fillOval(center + gap - dotSize / 2, center + gap - dotSize / 2, dotSize, dotSize);
                }

                if (numDots >= 4) {
                    g.fillOval(center - gap - dotSize / 2, center + gap - dotSize / 2, dotSize, dotSize);
                    g.fillOval(center + gap - dotSize / 2, center - gap - dotSize / 2, dotSize, dotSize);
                }


            }
        };

        dicePanel.setPreferredSize(new Dimension(squareSize, squareSize));
        panel.add(dicePanel, gbc);
    }
}
