package org.example;
import javax.swing.JPanel;

public abstract class BaseAI extends JPanel implements Runnable {

    protected Thread thread;

    public BaseAI() {}

    public void setThreadPriority(String priority) {
        switch(priority) {
            case("Min"):
                thread.setPriority(Thread.MIN_PRIORITY);
            case("Normal"):
                thread.setPriority(Thread.NORM_PRIORITY);
            case("Max"):
                thread.setPriority(Thread.MAX_PRIORITY);
        }
    }

}
