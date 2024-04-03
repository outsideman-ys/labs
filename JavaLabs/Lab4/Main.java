import java.awt.*;
import javax.swing.*;

public class Main {

    //Lower buttons
    public static JButton startButton = new JButton("Start");
    public static JButton stopButton = new JButton("Stop");
    public static JCheckBox showInfoButton = new JCheckBox("Show Info", false);
    public static JRadioButton showTimeButton = new JRadioButton("Show Time", false);
    public static JRadioButton hideTimeButton = new JRadioButton("Hide Time", true);
    public static JButton lifeTimeButton = new JButton("Set Life Time");
    public static JButton showLiveBees = new JButton("Live Bees");
    public static JButton setPriorityToThread = new JButton("Set Priority");
    public static JPanel buttonPanel = new JPanel();

    public static JButton startMoveWarrior = new JButton("rWarr ");
    public static JButton endMoveWarrior = new JButton("sWarr");
    public static JButton startMoveWorker = new JButton("rWork");
    public static JButton endMoveWorker = new JButton("sWork");


    public static JFrame frame;

    //Upper buttons
    public static JToolBar toolBar = new JToolBar();
    public static JButton changePossibillityButton = new JButton("Change Possibillity");
    public static JButton changePeriodButton = new JButton("Change Population");

    //Menu
    public static JMenuBar menubar = new JMenuBar();
    public static JMenu menu = new JMenu("Menu");
    public static JMenuItem startSimItem = new JMenuItem("Start Sim");
    public static JMenuItem stopSimItem = new JMenuItem("Stop Sim");
    public static JMenuItem showHideTimeItem = new JMenuItem("Show/Hide Time");
    public static JMenuItem changePossibillityItem = new JMenuItem("Change Possibillity");
    public static JMenuItem changePeriodItem = new JMenuItem("Change Population");
    public static JMenuItem lifeTimeItem = new JMenuItem("Change Life Time");

    public static void main(String[] args) {
        frame = new JFrame("Bee World");
        final Image icon = new ImageIcon("B:/labs/icon.jpg").getImage();
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Habitat beeWorld = new Habitat();

        startButton.setBackground(Color.BLUE);
        startButton.setPreferredSize(new Dimension(150, 30));
        startButton.setForeground(Color.WHITE);
        stopButton.setForeground(Color.WHITE);
        stopButton.setBackground(Color.RED);
        stopButton.setPreferredSize(new Dimension(150, 30));
        lifeTimeButton.setBackground(Color.DARK_GRAY);
        lifeTimeButton.setForeground(Color.WHITE);
        lifeTimeButton.setPreferredSize(new Dimension(150, 30));
        showLiveBees.setBackground(Color.LIGHT_GRAY);
        showLiveBees.setForeground(Color.WHITE);
        showLiveBees.setPreferredSize(new Dimension(150, 30));
        setPriorityToThread.setBackground(new Color(100, 200, 100));
        setPriorityToThread.setForeground(Color.WHITE);
        setPriorityToThread.setPreferredSize(new Dimension(150, 30));
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(showTimeButton);
        buttonGroup.add(hideTimeButton);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.add(showTimeButton);
        checkBoxPanel.add(hideTimeButton);

        JPanel aiButtonWarrior = new JPanel();
        JPanel aiButtonWorker = new JPanel();
        startMoveWarrior.setBackground(Color.BLACK);
        startMoveWarrior.setForeground(Color.WHITE);
        endMoveWarrior.setBackground(Color.BLACK);
        endMoveWarrior.setForeground(Color.WHITE);
        startMoveWarrior.setFont(new Font(startMoveWarrior.getFont().getName(), Font.BOLD, 11));
        endMoveWarrior.setFont(new Font(startMoveWarrior.getFont().getName(), Font.BOLD, 11));

        startMoveWorker.setBackground(Color.BLACK);
        startMoveWorker.setPreferredSize(new Dimension(70, 25));
        startMoveWorker.setForeground(Color.WHITE);
        endMoveWorker.setBackground(Color.BLACK);
        endMoveWorker.setPreferredSize(new Dimension(70, 25));
        endMoveWorker.setForeground(Color.WHITE);
        startMoveWorker.setFont(new Font(startMoveWorker.getFont().getName(), Font.BOLD, 11));
        endMoveWorker.setFont(new Font(startMoveWorker.getFont().getName(), Font.BOLD, 11));

        aiButtonWarrior.setLayout(new BoxLayout(aiButtonWarrior, BoxLayout.Y_AXIS));
        aiButtonWarrior.setPreferredSize(new Dimension(70, 50));
        aiButtonWorker.setLayout(new BoxLayout(aiButtonWorker, BoxLayout.Y_AXIS));
        aiButtonWorker.setPreferredSize(new Dimension(70, 50));

        JPanel aiGroup = new JPanel();

        startMoveWarrior.setEnabled(false);
        startMoveWorker.setEnabled(false);

        aiButtonWarrior.add(startMoveWarrior);
        aiButtonWarrior.add(endMoveWarrior);
        aiButtonWorker.add(startMoveWorker);
        aiButtonWorker.add(endMoveWorker);
        aiGroup.add(aiButtonWarrior);
        aiGroup.add(aiButtonWorker);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(lifeTimeButton);
        buttonPanel.add(showLiveBees);
        buttonPanel.add(setPriorityToThread);
        buttonPanel.add(aiGroup);
        buttonPanel.add(checkBoxPanel);
        buttonPanel.add(showInfoButton);

        toolBar.setFloatable(false);
        toolBar.add(changePossibillityButton);
        toolBar.add(changePeriodButton);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

        menu.add(startSimItem);
        menu.add(stopSimItem);
        menu.add(showHideTimeItem);
        menu.add(changePossibillityItem);
        menu.add(changePeriodItem);
        menu.add(lifeTimeItem);
        menubar.add(menu);
        
        ActionListenerSim actionListener = new ActionListenerSim(beeWorld);

        frame.setLayout(new BorderLayout());
        frame.addKeyListener(actionListener);
        frame.setJMenuBar(menubar);
        frame.getContentPane().add(toolBar,BorderLayout.NORTH);
        frame.getContentPane().add(beeWorld, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }
}