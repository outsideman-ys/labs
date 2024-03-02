import java.awt.*;
import javax.swing.*;


public class Main {

    public static JButton startButton = new JButton();
    public static JButton stopButton = new JButton();
    public static JCheckBox showInfoButton = new JCheckBox("Show Info", false);
    public static JRadioButton showTimeButton = new JRadioButton("Show Time", false);
    public static JRadioButton hideTimeButton = new JRadioButton("Hide Time", true);
    public static JPanel buttonPanel = new JPanel();

    public static JFrame frame;

    public static JToolBar toolBar = new JToolBar();
    public static JButton changePossibillityButton = new JButton("Change Possibillity");
    public static JButton changePeriodButton = new JButton("Change Period");

    public static JMenuBar menubar = new JMenuBar();
    public static JMenu menu = new JMenu("Menu");
    public static JMenuItem changePossibillityItem = new JMenuItem("Change Possibillity");
    public static JMenuItem changePeriodItem = new JMenuItem("Change Period of born");

    public static void main(String[] args) {
        frame = new JFrame("Bee World");
        final Image icon = new ImageIcon("B:/labs/icon.jpg").getImage();
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Habitat beeWorld = new Habitat();

        startButton.setBackground(Color.BLUE);
        startButton.setPreferredSize(new Dimension(200, 30));
        stopButton.setBackground(Color.RED);
        stopButton.setPreferredSize(new Dimension(200, 30));
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(showTimeButton);
        buttonGroup.add(hideTimeButton);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.add(showTimeButton);
        checkBoxPanel.add(hideTimeButton);

        buttonPanel.add(startButton);
        buttonPanel.add(checkBoxPanel);
        buttonPanel.add(showInfoButton);
        buttonPanel.add(stopButton);

        toolBar.setFloatable(false);
        toolBar.add(changePossibillityButton);
        toolBar.add(changePeriodButton);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

        menu.add(changePossibillityItem);
        menu.add(changePeriodItem);
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