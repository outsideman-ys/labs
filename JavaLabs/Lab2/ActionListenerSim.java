import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ActionListenerSim implements KeyListener {

    private Habitat beeWorld;
    private boolean isStarted = false;
    private boolean isEnded = false;
    private boolean isTimeShowed = false;
    private boolean isInfoShowed = false; 
    private User user;

    public ActionListenerSim(Habitat beeWorld) {
        this.beeWorld = beeWorld;
        this.user = new User(beeWorld);
        Main.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartSim();
            }
        });

        Main.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StopSim();
            }
        });

        Main.showTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isTimeShowed) {
                    ShowTime();
                }
            }
        });

        Main.hideTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isTimeShowed) {
                    HideTime();
                }
            }
        });

        Main.showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ShowInfo();
                
            }
        });

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


        Double[] choices = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        Double[] selectedOption = new Double[1];
        JComboBox<Double> comboBox = new JComboBox<>(choices);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JComboBox<Double> combo = (JComboBox<Double>) e.getSource();
                selectedOption[0] = (Double) combo.getSelectedItem();
            }
        });

        Main.changePossibillityButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(Main.frame, comboBox, "Выберите вариант", JOptionPane.QUESTION_MESSAGE);
            user.ChangePossibillity(selectedOption[0]);
        });

        Main.changePossibillityItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(Main.frame, comboBox, "Выберите вариант", JOptionPane.QUESTION_MESSAGE);
            user.ChangePossibillity(selectedOption[0]);
        });

        Integer[] inputValue = new Integer[1];

        ActionListener periodListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(Main.frame, "Введите число:");

                if (input != null && !input.isEmpty()) {
                    try {
                        inputValue[0] = Integer.parseInt(input);

                        if (inputValue[0] > 100 || inputValue[0] <= 0) {
                            throw new NumberFormatException("Неправильно");
                        } else 
                            user.ChangePeriod(inputValue[0]);
                    } catch (NumberFormatException ex) {
                        user.ChangePeriod(70);
                        JOptionPane.showMessageDialog(Main.frame, "Неправильно.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    user.ChangePeriod(70);
                    JOptionPane.showMessageDialog(Main.frame, "Нет ввода.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        Main.changePeriodButton.addActionListener(periodListener);
        Main.changePeriodItem.addActionListener(periodListener);



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
