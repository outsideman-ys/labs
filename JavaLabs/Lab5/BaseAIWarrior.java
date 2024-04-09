public class BaseAIWarrior extends BaseAI {

    private int counter = 0;
    private Warrior warriorBee;

    public BaseAIWarrior(Warrior warriorBee) {
        this.warriorBee = warriorBee;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            synchronized(Habitat.monitor) {
                if (Habitat.isRunningWarrior) {
                    Habitat.monitor.notify();
                    warriorBee.Fly();
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
            counter++;
            if (counter == 5) {
                counter = 0;
                warriorBee.changeDirection();
            }
        }
    }

    @Override
    public String toString() {
        return "BaseAIWarrior [counter=" + counter + ", warriorBee=" + warriorBee + "]";
    }

    
    
}
