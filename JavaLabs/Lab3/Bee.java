public abstract class Bee {
    protected String name;          // name that showed under the object
    protected int bornTime;
    protected int dieTime;
    protected int id;

    public Bee(String name, int bornTime, int lifeTime, int id) {
        this.name = name;
        this.bornTime = bornTime;
        this.dieTime = bornTime + lifeTime;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getDieTime() {
        return dieTime;
    }

    public int getId() {
        return id;
    }
}
