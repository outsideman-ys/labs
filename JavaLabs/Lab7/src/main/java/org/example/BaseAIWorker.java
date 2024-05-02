package org.example;
public class BaseAIWorker extends BaseAI {

    private Worker workerBee;

    public BaseAIWorker(Worker workerBee) {
        this.workerBee = workerBee;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            synchronized(Habitat.monitor) {
                if (Habitat.isRunningWorker) {
                    Habitat.monitor.notify();
                    workerBee.Fly();
                }
                else {
                    try {
                        Habitat.monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public String toString() {
        return "BaseAIWorker [workerBee=" + workerBee + "]";
    }


}
