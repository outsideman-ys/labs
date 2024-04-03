import java.util.concurrent.ThreadLocalRandom;

class Warrior extends Bee {

    private int speedX;
    private int speedY;
    private ThreadLocalRandom random;
    private int randomNumber;
    public BaseAIWarrior warriorAI;
    
    public Warrior(int bornTime, int lifeTime, int id, int x, int y, int speed) {
        super("Warrior Bee", bornTime, lifeTime, id, x, y, speed);
        this.speedX = speed;
        this.speedY = speed;
        random = ThreadLocalRandom.current();
        warriorAI = new BaseAIWarrior(this);
    }

    @Override
    public void TakeHoney() {
        System.out.println("Warrior Bee is taking honey");
    }

    public void changeDirection() {
        randomNumber = random.nextInt(-100, 100);
        if (randomNumber < 0) {
            speedX *= -1;
        }
        else speedY *= -1;
    }

    @Override
    protected void checkForCollision() {
        if (x < 0) x = 0;
        else if (x > 1130) x = 1130;
        if (y < 0) y = 0;
        else if (y > 730) y = 730;
        
    }

    @Override
    public void Fly() {
        checkForCollision();
        x += speedX;
        y += speedY;
    }
}