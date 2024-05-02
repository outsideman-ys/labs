package org.example;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.sql.*;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ActionListenerSim implements KeyListener {

    /*
     * добавлено событие при закрытии окна (запись в конфиг) windowCloseListener
     * события для сохранения/загрузки живых пчел saveLiveBeesListener loadLiveBeesListener
     */

    private Habitat beeWorld;
    private boolean isStarted = false;
    private boolean isEnded = false;
    private boolean isTimeShowed = false;
    private boolean isInfoShowed = false; 
    private int savedSimTime = 0;
    private int savedNumberWarrior = 0;
    private int savedNumberWorker = 0;

    public boolean isTimeShowed() {
        return isTimeShowed;
    }

    public void setTimeShowed(boolean isTimeShowed) {
        this.isTimeShowed = isTimeShowed;
    }

    public boolean isInfoShowed() {
        return isInfoShowed;
    }

    public void setInfoShowed(boolean isInfoShowed) {
        this.isInfoShowed = isInfoShowed;
    }

    private WindowListener windowCloseListener;
    private ActionListener populationListener;
    private ActionListener possibilityListener;
    private ActionListener choisePossibilityListener;
    private ActionListener startListener;
    private ActionListener stopListener;
    private ActionListener infoListener;
    private ActionListener showTimeListener;
    private ActionListener hideTimeListener;
    private ActionListener menuTimeListener;
    private ActionListener changeLifeTimeListener;
    private ActionListener showLiveBeesListener;
    private ActionListener startMoveWorkerListener;
    private ActionListener endMoveWorkerListener;
    private ActionListener startMoveWarriorListener;
    private ActionListener endMoveWarriorListener;
    private ActionListener setPriorityListener;
    private ActionListener saveLiveBeesListener;
    private ActionListener loadLiveBeesListener;
    private ActionListener consoleListener;
    private ActionListener saveSQLListener;
    private ActionListener loadSQLListener;

    private final String command1 = "show(1)";
    private final String command2 = "show(2)";

    public static Connection connection;
    private static final String url = "jdbc:postgresql://localhost:5432/lab7";
    private static final String username = "username";
    private static final String password = "password";

    private String sqlWorkersInsert = "INSERT INTO workers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String sqlWarriorsInsert = "INSERT INTO warriors VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private void initActionListeners() {

        //change population to access born
        Integer[] inputValue = new Integer[1];
        populationListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(Main.frame, "Введите число:");

                if (input != null && !input.isEmpty()) {
                    try {
                        inputValue[0] = Integer.parseInt(input);

                        if (inputValue[0] > 100 || inputValue[0] <= 0) {
                            throw new NumberFormatException("Неправильно");
                        } else 
                            beeWorld.setPopulation(inputValue[0]);
                    } catch (NumberFormatException ex) {
                        beeWorld.setPopulation(70);
                        JOptionPane.showMessageDialog(Main.frame, "Неправильно.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    beeWorld.setPopulation(70);
                    JOptionPane.showMessageDialog(Main.frame, "Нет ввода.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        Double[] choices = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        Double[] selectedOption = new Double[1];
        JComboBox<Double> comboBox = new JComboBox<>(choices);

        choisePossibilityListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JComboBox<Double> combo = (JComboBox<Double>) e.getSource();
                selectedOption[0] = (Double) combo.getSelectedItem();
            }
        };

        comboBox.addActionListener(choisePossibilityListener);

        //change possibility of born
        possibilityListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Main.frame, comboBox, "Выберите вариант", JOptionPane.QUESTION_MESSAGE);
                beeWorld.setPossibility(selectedOption[0]);
            }
        };

        //starts simulation
        startListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StartSim();
            }
        };

        //stop simulation
        stopListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StopSim();
            }
        };

        infoListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowInfo();
             }
        };

        showTimeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isTimeShowed) {
                    ShowTime();
                }
            }
        };

        hideTimeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTimeShowed) {
                    HideTime();
                } else ShowTime();
            }
        };

        menuTimeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTimeShowed) {
                    HideTime();
                } else ShowTime();
            }
        };

        JPanel panel = new JPanel();
        String[] choicesBee = {"Warrior Bee", "Worker Bee"};
        JComboBox<String> comboBoxBee = new JComboBox<>(choicesBee);
        panel.add(comboBoxBee);
        JTextField textField = new JTextField(10);
        panel.add(textField);

        changeLifeTimeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, panel, "Время жизни", JOptionPane.PLAIN_MESSAGE);

                String beeKind = (String) comboBoxBee.getSelectedItem();
                String input = textField.getText();

                if (input != null && !input.isEmpty()) {
                    try {
                        inputValue[0] = Integer.parseInt(input);

                        if (inputValue[0] <= 0) {
                            throw new Exception("Неправильно, меньше нуля");
                        } else {
                            if (beeKind.equals("Warrior Bee")) {
                                beeWorld.setLifeTimeWarrior(inputValue[0]);
                            }
                            else {
                                beeWorld.setLifeTimeWorker(inputValue[0]);
                            }
                        }
                            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Main.frame, "Неправильно.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Main.frame, "Неправильно, меньше нуля", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(Main.frame, "Нет ввода.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        showLiveBeesListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String messageZeroLine = "Live Bees";
                String liveBees = "\n ";
                for (Map.Entry<Integer, Integer> entry : beeWorld.getIdAndLiveMap().entrySet()) {
                    liveBees += "Рожден " + Integer.toString(entry.getValue()) + " - Id " + Integer.toString(entry.getKey()) + "\n";
                }

                JOptionPane.showOptionDialog(Main.frame, messageZeroLine + liveBees, "INFO MESSAGE", JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
            }
        };

        startMoveWarriorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMoveWarrior();
            }
            
        };

        endMoveWarriorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMoveWarrior();
            }
            
        };

        startMoveWorkerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMoveWorker();
            }
            
        };

        endMoveWorkerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMoveWorker();
            }
            
        };

        JPanel anotherPanel = new JPanel();
        String[] choicesPriority = {"Min", "Normal", "Max"};
        JComboBox<String> comboPriority = new JComboBox<>(choicesPriority);
        JComboBox<String> comboBeeKinds = new JComboBox<>(choicesBee);
        anotherPanel.add(comboBeeKinds);
        anotherPanel.add(comboPriority);

        setPriorityListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, anotherPanel, "Установка приоритета", JOptionPane.PLAIN_MESSAGE);

                String beeKind = (String) comboBeeKinds.getSelectedItem();
                String priority = (String) comboPriority.getSelectedItem();

                if (beeKind.equals("Warrior Bee")) {
                    beeWorld.setPriorityWarrior(priority);
                }
                else {
                    beeWorld.setPriorityWorker(priority);
                }
            }
        };

        windowCloseListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try (FileInputStream fis = new FileInputStream(Main.configFile)) {
                    Properties properties = new Properties();
                    properties.load(fis);
                    properties.setProperty("bornTimeWorker", Integer.toString(beeWorld.getBornTimeCheck()));
                    properties.setProperty("showTimeOption", Boolean.toString(beeWorld.isShowTime()));
                    properties.setProperty("showInfoOption", Boolean.toString(beeWorld.isShowInfo()));
                    properties.setProperty("populationOfWorkers", Integer.toString(beeWorld.getPopulationOfBees()));
                    properties.setProperty("possibilityOfWarrior", Double.toString(beeWorld.getPossibillity()));
                    properties.setProperty("lifeTimeWarrior", Integer.toString(beeWorld.getLifeTimeWarrior()));
                    properties.setProperty("lifeTimeWorker", Integer.toString(beeWorld.getLifeTimeWorker()));
                    properties.setProperty("isWarriorFlying", Boolean.toString(Habitat.isRunningWarrior));
                    properties.setProperty("isWorkerFlying", Boolean.toString(Habitat.isRunningWorker));
                    properties.setProperty("warriorFlyPriority", beeWorld.getPriorityWarrior());
                    properties.setProperty("workerFlyPriority", beeWorld.getPriorityWorker());

                    try (FileOutputStream fos = new FileOutputStream(Main.configFile)) {
                        properties.store(fos, "Обновленный конфиг");
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                    
            }
        };

        saveLiveBeesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                File defaultDirectory = new File("savedBees.ser");
                fileChooser.setCurrentDirectory(defaultDirectory);
                fileChooser.setSelectedFile(defaultDirectory);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Hello world", "ser");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showSaveDialog(Main.frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    savedSimTime = beeWorld.getSimulationTime();
                    savedNumberWarrior = beeWorld.getNumberOfWarrior();
                    savedNumberWorker = beeWorld.getNumberOfWorker();

                    for (Bee bee : beeWorld.getLiveBees()) {
                        bee.setTimeDif(savedSimTime - bee.getBornTime());
                    }

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                        oos.writeObject(beeWorld.getLiveBees());
                        System.out.println("Serialization is done");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        loadLiveBeesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                File defaultDirectory = new File("savedBees.ser");
                fileChooser.setCurrentDirectory(defaultDirectory);
                fileChooser.setSelectedFile(defaultDirectory);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Hello world", "ser");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showDialog(Main.frame, "Load");
                if(returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {

                        List<Bee> newLiveBees = (ArrayList<Bee>) ois.readObject();
                        System.out.println("Deserialization is done");
                        if (isStarted) {
                            StopSim();
                        }
                        for (Bee bee : newLiveBees) {
                            bee.setBornTime(beeWorld.getSimulationTime() - bee.getTimeDif());
                            if (bee instanceof Worker) {
                                Worker worker = (Worker) bee;
                                worker.startFly();
                            }
                            else {
                                Warrior warrior = (Warrior) bee;
                                warrior.startFly();
                            }
                        }
                        beeWorld.setNumberOfWarrior(savedNumberWarrior);
                        beeWorld.setNumberOfWorker(savedNumberWorker);
                        beeWorld.setLiveBees(newLiveBees);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        consoleListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(Main.frame, "Console");
                dialog.setSize(1200, 800);
                    
                JTextArea dialogTextArea = new JTextArea();
                dialogTextArea.setLineWrap(true);
                dialogTextArea.setWrapStyleWord(true);
                dialog.add(new JScrollPane(dialogTextArea));

                KeyListener keyListener = new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            InputStream inputStream = new CustomInputStream(dialogTextArea.getText());
                            PrintStream printStream = new PrintStream(new CustomOutputStream(dialogTextArea));
                            PrintStream standartOut = System.out;
                            System.setOut(printStream);
                            dialogTextArea.setText("");
                            try {
                                int data;
                                int i = 0;
                                String command = "";
                                while (((data = inputStream.read()) != -1) && i < command1.length()) {
                                    if ((char)data != command1.charAt(i)) {
                                        if ((char)data != command2.charAt(i)) {
                                            System.out.print("Unknown command, try again");
                                            break;
                                        }    
                                    }
                                    i++;
                                    char symbol = (char) data;
                                    command += symbol;
                                }
                                if (command.equals(command1)) {
                                    System.out.println("Number of workers: " + beeWorld.getNumberOfWorker());
                                }
                                else if (command.equals(command2)) {
                                    System.out.println("Number of warriors: " + beeWorld.getNumberOfWarrior());
                                }
                                    
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            System.setOut(standartOut);
                        }
                    }
                };

                dialogTextArea.addKeyListener(keyListener);

                dialog.setVisible(true);
            }
        };

        saveSQLListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection = DriverManager.getConnection(url,username,password);
                    PreparedStatement statementWorker = connection.prepareStatement(sqlWorkersInsert);
                    PreparedStatement statementWarrior = connection.prepareStatement(sqlWarriorsInsert);
                    Statement statement = connection.createStatement();
                    statement.execute("DELETE FROM warriors;");
                    statement.execute("DELETE FROM workers;");
                    for (Bee bee : beeWorld.getLiveBees()) {
                        if (bee instanceof Warrior warrior) {
                            statementWarrior.setInt(1, warrior.getId());
                            statementWarrior.setString(2, warrior.getName());
                            statementWarrior.setInt(3, warrior.getBornTime());
                            statementWarrior.setInt(4, warrior.getDieTime());
                            statementWarrior.setInt(5, beeWorld.getSpeed());
                            statementWarrior.setInt(6, warrior.getTimeDif());
                            statementWarrior.setInt(7, warrior.getX());
                            statementWarrior.setInt(8, warrior.getY());
                            statementWarrior.executeUpdate();
                        }
                        else if (bee instanceof  Worker worker) {
                            statementWorker.setInt(1, worker.getId());
                            statementWorker.setString(2, worker.getName());
                            statementWorker.setInt(3, worker.getBornTime());
                            statementWorker.setInt(4, worker.getDieTime());
                            statementWorker.setInt(5, beeWorld.getSpeed());
                            statementWorker.setInt(6, worker.getTimeDif());
                            statementWorker.setInt(7, worker.getX());
                            statementWorker.setInt(8, worker.getY());
                            statementWorker.setInt(9, worker.getBornX());
                            statementWorker.setInt(10, worker.getBornY());
                            statementWorker.executeUpdate();
                        }
                    }
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showOptionDialog(Main.frame, "Save is successful", "INFO MESSAGE", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
            }
        };

        loadSQLListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String queryWorker = "SELECT * FROM workers";
                String queryWarrior = "SELECT * FROM warriors";
                try {
                    StopSim();
                    connection = DriverManager.getConnection(url,username,password);
                    Statement statement = connection.createStatement();
                    ResultSet resultSetWorker = statement.executeQuery(queryWorker);
                    ArrayList<Bee> newLiveBees = new ArrayList<>();
                    int numberWorker = 0;
                    int numberWarrior = 0;
                    while (resultSetWorker.next()) {
                        Worker worker = new Worker();
                        worker.setId(resultSetWorker.getInt(1));
                        worker.setName(resultSetWorker.getString(2));
                        worker.setBornTime(resultSetWorker.getInt(3));
                        worker.setDieTime(resultSetWorker.getInt(4));
                        worker.setSpeed(resultSetWorker.getInt(5));
                        worker.setTimeDif(resultSetWorker.getInt(6));
                        worker.setX(resultSetWorker.getInt(7));
                        worker.setY(resultSetWorker.getInt(8));
                        worker.setBornX(resultSetWorker.getInt(9));
                        worker.setBornY(resultSetWorker.getInt(10));
                        worker.setBornTime(beeWorld.getSimulationTime() - worker.getTimeDif());
                        worker.startFly();
                        newLiveBees.add(worker);
                        numberWorker++;
                    }
                    ResultSet resultSetWarrior = statement.executeQuery(queryWarrior);
                    while (resultSetWarrior.next()) {
                        Warrior warrior = new Warrior();
                        warrior.setId(resultSetWarrior.getInt(1));
                        warrior.setName(resultSetWarrior.getString(2));
                        warrior.setBornTime(resultSetWarrior.getInt(3));
                        warrior.setDieTime(resultSetWarrior.getInt(4));
                        warrior.setSpeed(resultSetWarrior.getInt(5));
                        warrior.setTimeDif(resultSetWarrior.getInt(6));
                        warrior.setX(resultSetWarrior.getInt(7));
                        warrior.setY(resultSetWarrior.getInt(8));
                        warrior.setBornTime(beeWorld.getSimulationTime() - warrior.getTimeDif());
                        warrior.setSpeedX(resultSetWarrior.getInt(5));
                        warrior.setSpeedY(resultSetWarrior.getInt(5));
                        warrior.startFly();
                        newLiveBees.add(warrior);
                        numberWarrior++;
                    }
                    connection.close();
                    beeWorld.setNumberOfWarrior(numberWarrior);
                    beeWorld.setNumberOfWorker(numberWorker);
                    beeWorld.setLiveBees(newLiveBees);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showOptionDialog(Main.frame, "Load is successful", "INFO MESSAGE", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
            }
        };
    }

    public ActionListenerSim(Habitat beeWorld) {
        this.beeWorld = beeWorld;

        initActionListeners();

        Main.showTimeButton.addActionListener(showTimeListener);
        Main.hideTimeButton.addActionListener(hideTimeListener);
        Main.showInfoButton.addActionListener(infoListener);
        Main.startButton.addActionListener(startListener);
        Main.startSimItem.addActionListener(startListener);
        Main.stopButton.addActionListener(stopListener);
        Main.stopSimItem.addActionListener(stopListener);
        Main.showHideTimeItem.addActionListener(menuTimeListener);
        Main.changePossibillityButton.addActionListener(possibilityListener);
        Main.changePossibillityItem.addActionListener(possibilityListener);
        Main.changePeriodButton.addActionListener(populationListener);
        Main.changePeriodItem.addActionListener(populationListener);
        Main.lifeTimeButton.addActionListener(changeLifeTimeListener);
        Main.lifeTimeItem.addActionListener(changeLifeTimeListener);
        Main.showLiveBees.addActionListener(showLiveBeesListener);
        Main.startMoveWarrior.addActionListener(startMoveWarriorListener);
        Main.endMoveWarrior.addActionListener(endMoveWarriorListener);
        Main.startMoveWorker.addActionListener(startMoveWorkerListener);
        Main.endMoveWorker.addActionListener(endMoveWorkerListener);
        Main.setPriorityToThread.addActionListener(setPriorityListener);
        Main.frame.addWindowListener(windowCloseListener);
        Main.saveSimItem.addActionListener(saveLiveBeesListener);
        Main.loadSimTime.addActionListener(loadLiveBeesListener);
        Main.consoleItem.addActionListener(consoleListener);
        Main.saveSQL.addActionListener(saveSQLListener);
        Main.loadSQL.addActionListener(loadSQLListener);

        InputMap inputMap = Main.buttonPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "start");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "stop");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), "showTime");

        ActionMap actionMap = Main.buttonPanel.getActionMap();
        actionMap.put("start", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartSim();
            }
        });
        actionMap.put("stop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StopSim();
            }
        });
        actionMap.put("showTime", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isTimeShowed) {
                    HideTime();
                } else ShowTime();
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 66 && !isStarted) {
            StartSim();
        } else if (e.getKeyCode() == 69 && isStarted) {
            StopSim();
        } else if (e.getKeyCode() == 84) {
            ShowTime();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    private void StartSim() {
        isEnded = false;
        isStarted = true;
        Main.startButton.setEnabled(isEnded);
        Main.stopButton.setEnabled(isStarted);
        beeWorld.StartSimulation();
    }

    private void StopSim() {
        isStarted = false;
        isEnded = true;
        Main.stopButton.setEnabled(isStarted);
        Main.startButton.setEnabled(isEnded);
        beeWorld.StopSimulation();
    }

    public void ShowTime() {
        isTimeShowed = true;
        Main.showTimeButton.setSelected(isTimeShowed);
        Main.hideTimeButton.setSelected(false);
        beeWorld.ShowTime();
    }

    public void HideTime() {
        isTimeShowed = false;
        Main.showTimeButton.setSelected(isTimeShowed);
        Main.hideTimeButton.setSelected(true);
        beeWorld.ShowTime();
    }

    public void ShowInfo() {
        if (isInfoShowed) {
            isInfoShowed = false;
            Main.showInfoButton.setSelected(isInfoShowed);
            beeWorld.ShowInfo();
        } else {
            isInfoShowed = true;
            Main.showInfoButton.setSelected(isInfoShowed);
            beeWorld.ShowInfo();
        }
    }

    public void startMoveWarrior() {
        Main.startMoveWarrior.setEnabled(false);
        Main.endMoveWarrior.setEnabled(true);
        Habitat.isRunningWarrior = true;
    }

    public void stopMoveWarrior() {
        Main.startMoveWarrior.setEnabled(true);
        Main.endMoveWarrior.setEnabled(false);
        Habitat.isRunningWarrior = false;
    }

    public void startMoveWorker() {
        Main.startMoveWorker.setEnabled(false);
        Main.endMoveWorker.setEnabled(true);
        Habitat.isRunningWorker = true;
    }

    public void stopMoveWorker() {
        Main.startMoveWorker.setEnabled(true);
        Main.endMoveWorker.setEnabled(false);
        Habitat.isRunningWorker = false;
    }

}