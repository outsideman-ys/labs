class Worker extends Bee implements IBehaviour {

    public Worker(int bornTime, int lifeTime, int id) {
        super("Worker Bee", bornTime, lifeTime, id);
    }

    @Override
    public void Fly() {
        System.out.println("Worker Bee is flying");
    }   

    @Override
    public void TakeHoney() {
        System.out.println("Worker Bee is taking honey");
    }
}