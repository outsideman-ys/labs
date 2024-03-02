class Warrior extends Bee implements IBehaviour {
    public Warrior() {
        super("Warrior Bee");
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