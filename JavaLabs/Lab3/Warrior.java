class Warrior extends Bee implements IBehaviour {


    
    public Warrior(int bornTime, int lifeTime, int id) {
        super("Warrior Bee", bornTime, lifeTime, id);
    }

    @Override
    public void TakeHoney() {
        System.out.println("Warrior Bee is taking honey");
    }

    @Override
    public void Fly() {
        System.out.println("Warrior Bee is flying");
    }
}