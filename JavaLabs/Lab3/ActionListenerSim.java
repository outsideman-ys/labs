import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ActionListenerSim implements KeyListener {

    private Habitat beeWorld;
    private boolean isStarted = false;
    private boolean isEnded = false;
    private boolean isTimeShowed = false;
    private boolean isInfoShowed = false; 

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
                            throw new NumberFormatException("Неправильно");
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

    private void ShowTime() {
        isTimeShowed = true;
        Main.showTimeButton.setSelected(isTimeShowed);
        Main.hideTimeButton.setSelected(false);
        beeWorld.ShowTime();
    }

    private void HideTime() {
        isTimeShowed = false;
        Main.showTimeButton.setSelected(isTimeShowed);
        Main.hideTimeButton.setSelected(true);
        beeWorld.ShowTime();
    }

    private void ShowInfo() {
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

}
