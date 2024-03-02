import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

class Habitat extends JPanel {
    private static Bee[] bees;
    private Timer timer;
    private int simulationTime = 0;
    private boolean showTime = false;
    private boolean isEnded = false;
    private boolean showInfo = false;
    private int bornTimeCheck = 3;
    private int populationOfBees = 70;
    private double possibillity = 0.3;
    private int numberOfWorker = 0;
    private int numberOfWarrior = 0;
    private ThreadLocalRandom random;
    private Image warriorImage;
    private Image workerImage;
    private final String SRC_WARRIOR = "B:/labs/warriorBee.png";
    private final String SRC_WORKER = "B:/labs/workerBee.png";

    public void setBornTime(int bornTime) {
        this.bornTimeCheck = bornTime; 
    }

    public void setPossibillity(double possibillity) {
        this.possibillity = possibillity;
        System.out.println(this.possibillity);
    }

    public Habitat() {
        bees = new Bee[0];
        random = ThreadLocalRandom.current();
        LoadImgs();
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulationTime++;
                if (simulationTime % bornTimeCheck == 0 && bees.length != 0) {
                    GenerateWorker();
                }
                GenerateWarrior();
                repaint();
            }
        });
    }

    public void StartSimulation() {
        timer.start();
        isEnded = false;
    }

    private void LoadImgs() {
        warriorImage = new ImageIcon(SRC_WARRIOR).getImage();
        workerImage = new ImageIcon(SRC_WORKER).getImage();
    }

    public void StopSimulation() {
        timer.stop();
        isEnded = true;
        repaint();
    }

    public void ShowTime() {
        showTime = !showTime;
        repaint();
    }

    public void ShowInfo() {
        showInfo = !showInfo;
        repaint();
    }

    private void GenerateWorker() {
        if (((numberOfWorker * 100)/bees.length) <= populationOfBees) {
            numberOfWorker++;
            AddBee(new Worker());
        }
    }

    private void GenerateWarrior() {
        double number = random.nextDouble(0.0, 1.0);
        if (number <= possibillity) {
            numberOfWarrior++;
            AddBee(new Warrior());
        }
    }

    private void AddBee(Bee bee) {
        Bee[] newBees = new Bee[bees.length + 1];
        System.arraycopy(bees, 0, newBees, 0, bees.length);
        newBees[bees.length] = bee;
        bees = newBees;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Bee bee : bees) {
            int x = random.nextInt(70, getWidth()-70);
            int y = random.nextInt(70, getHeight()-70);

            if(bee.getName() == "Warrior Bee") {
                g.drawImage(warriorImage, x, y, 50, 50, null);
                g.drawString(bee.getName(), x, y + 60);
            }
            else if (bee.getName() == "Worker Bee") {
                g.drawImage(workerImage, x, y, 50, 50, null);
                g.drawString(bee.getName(), x, y + 60);
            }
        }

        if (isEnded) {
            if (showInfo) {
                String messageZeroLine = "INFO\n";
                String messageFirstLine = "Number of workers: " + numberOfWorker + "\n";
                String messageSecondLine = "Number of warriors: " + numberOfWarrior + "\n";
                String messageThirdLine = "Simulation time: " + simulationTime + "\n";

                int result = JOptionPane.showOptionDialog(Main.frame,
                messageZeroLine + messageFirstLine + messageSecondLine + messageThirdLine, "INFO MESSAGE",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK", "CANCEL"}, "OK");

                switch(result) {
                    case 0:
                        System.out.println("Нажата кнопка 1");
                        break;
                    case 1:
                        timer.start();
                        Main.startButton.setEnabled(false);
                        Main.stopButton.setEnabled(true);
                        isEnded = false;
                        break;
                }
            }
        }

        if (showTime) {
            g.setColor(Color.BLACK);
            g.drawString("Simulation Time: " + simulationTime + " seconds", 10, 20);
        }
    }
}