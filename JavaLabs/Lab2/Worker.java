class Worker extends Bee implements IBehaviour {
    public Worker() {
        super("Worker Bee");
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