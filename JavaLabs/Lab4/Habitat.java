import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

class Habitat extends JPanel {
    private static List<Bee> liveBees;
    Iterator<Bee> iterator;
    private Set<Integer> idies;
    private TreeMap<Integer, Integer> idAndLiveTimeMap; 

    private Timer timer;
    private int simulationTime = 0;
    private boolean showTime = false;
    private boolean isEnded = false;
    private boolean showInfo = false;
    private int bornTimeCheck = 3;
    private int populationOfBees = 70;
    private double possibillity = 0.3;
    private int lifeTimeWarrior = 500;
    private int lifeTimeWorker = 500;
    private int numberOfWorker = 0;
    private int numberOfWarrior = 0;
    private ThreadLocalRandom random;
    private Image warriorImage;
    private Image workerImage;
    private final String SRC_WARRIOR = "B:/labs/warriorBee.png";
    private final String SRC_WORKER = "B:/labs/workerBee.png";

    public static Object monitor = new Object();
    public static boolean isRunningWarrior = true;
    public static boolean isRunningWorker = true;

    private int x;
    private int y;
    private int speed = 10;

    public void setPriorityWarrior(String priority) {
        for (Bee bee: liveBees) {
            if (bee instanceof Warrior) {
                Warrior warrior = (Warrior) bee;
                warrior.warriorAI.setThreadPriority(priority);
            }
        }
    }

    public void setPriorityWorker(String priority) {
        for (Bee bee: liveBees) {
            if (bee instanceof Worker) {
                Worker warrior = (Worker) bee;
                warrior.workerAI.setThreadPriority(priority);
            }
        }
    }

    public void setPopulation(int population) {
        this.populationOfBees = population; 
    }

    public void setPossibility(double possibillity) {
        this.possibillity = possibillity;
    }

    public void setLifeTimeWarrior(int lifeTimeWarrior) {
        this.lifeTimeWarrior = lifeTimeWarrior;
        System.out.println(this.lifeTimeWarrior);
    }

    public void setLifeTimeWorker(int lifeTimeWorker) {
        this.lifeTimeWorker = lifeTimeWorker;
        System.out.println(this.lifeTimeWorker);
    }

    public TreeMap<Integer, Integer> getIdAndLiveMap() {
        return idAndLiveTimeMap;
    }

    public Habitat() {
        liveBees = new ArrayList<>();
        iterator = liveBees.iterator();
        idies = new HashSet<>();
        idAndLiveTimeMap = new TreeMap<>();
        random = ThreadLocalRandom.current();
        LoadImgs();

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulationTime++;
                
                synchronized(monitor) {
                    if (simulationTime % bornTimeCheck == 0 && !liveBees.isEmpty()) {
                        GenerateWorker();
                    }
                    GenerateWarrior();
                }

                synchronized (liveBees) {
                    iterator = liveBees.iterator();

                    while (iterator.hasNext()) {
                        Bee bee = iterator.next();
                        if (bee.getDieTime() <= simulationTime) {
                            if (bee instanceof Warrior) numberOfWarrior--;
                            else numberOfWorker--;
                            idies.remove(bee.getId());
                            idAndLiveTimeMap.remove(bee.getId());
                            iterator.remove();
                        }
                    }
                }
                
                synchronized(monitor) {
                    repaint();
                }
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
        if (((numberOfWorker * 100)/liveBees.size()) <= populationOfBees) {
            numberOfWorker++;
            Integer id = random.nextInt(0, Integer.MAX_VALUE);
            x = random.nextInt(70, getWidth()-70);
            y = random.nextInt(70, getHeight()-70);
            Bee bee = new Worker(simulationTime, lifeTimeWorker, id, x, y, speed);
            liveBees.add(bee);
            idies.add(id);
            idAndLiveTimeMap.put(id, simulationTime);
        }
    }

    private void GenerateWarrior() {
        double number = random.nextDouble(0.0, 1.0);
        if (number <= possibillity) {
            numberOfWarrior++;
            int id = random.nextInt(0, Integer.MAX_VALUE);
            x = random.nextInt(70, getWidth()-70);
            y = random.nextInt(70, getHeight()-70);
            Bee bee = new Warrior(simulationTime, lifeTimeWarrior, id, x, y, speed);
            liveBees.add(bee);
            idies.add(id);
            idAndLiveTimeMap.put(id, simulationTime);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Bee bee : liveBees) {
            if(bee instanceof Warrior) {
                g.drawImage(warriorImage, bee.getX(), bee.getY(), 50, 50, null);
                g.drawString(bee.getName(), bee.getX(), bee.getY() + 60);
            }
            else if (bee instanceof Worker) {
                g.drawImage(workerImage, bee.getX(), bee.getY(), 50, 50, null);
                g.drawString(bee.getName(), bee.getX(), bee.getY() + 60);
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