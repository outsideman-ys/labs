class Worker extends Bee {

    private int interestPointX = 0;
    private int interestPointY = 0;
    private int bornX;
    private int bornY;
    private boolean isReached = false;
    public BaseAIWorker workerAI;
    
    public Worker(int bornTime, int lifeTime, int id, int x, int y, int speed) {
        super("Worker Bee", bornTime, lifeTime, id, x, y, speed);
        this.bornX = x;
        this.bornY = y;
        workerAI = new BaseAIWorker(this);
    }

    @Override
    public void Fly() {
        if (!isReached) {
            checkForCollision();
            if (x > interestPointX && y > interestPointY) { x-=speed; y-=speed;}
            else if (x > interestPointX && y < interestPointY) { x-=speed; y+=speed;}
            else if (x < interestPointX && y > interestPointY) { x+=speed; y-=speed;}
            else if (x < interestPointX && y < interestPointY) { x+=speed; y+=speed;}
            else if (x > interestPointX && y == interestPointY) { x-=speed;}
            else if (x == interestPointX && y > interestPointY) { y-=speed;}
            else if (x == interestPointX && y < interestPointY) { y+=speed;}
            else if (x < interestPointX && y == interestPointY) { x+=speed;}
            else isReached = true;
        }
        else if (isReached) {
            checkForCollision();
            if (Math.abs(x - bornX) < speed && Math.abs(y - bornY) < speed ) isReached = false;
            else if (x > bornX && y > bornY) { x-=speed; y-=speed;}
            else if (x > bornX && y < bornY) { x-=speed; y+=speed;}
            else if (x < bornX && y > bornY) { x+=speed; y-=speed;}
            else if (x < bornX && y < bornY) { x+=speed; y+=speed;}
            else if (x > bornX && y == bornY) { x-=speed;}
            else if (x == bornX && y > bornY) { y-=speed;}
            else if (x == bornX && y < bornY) { y+=speed;}
            else if (x < bornX && y == bornY) { x+=speed;}
        }
    }
    
    @Override
    protected void checkForCollision() {
        if (x < 0) x=0;
        if (y < 0) y=0;
    }

    @Override
    public void TakeHoney() {
        System.out.println("Worker Bee is taking honey");
    }
}