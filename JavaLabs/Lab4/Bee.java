
public abstract class Bee implements IBehaviour{
    protected String name;          // name that showed under the object
    protected int bornTime;
    protected int dieTime;
    protected Integer id;
    protected int speed;

    protected int x;
    protected int y;

    public Bee(String name, int bornTime, int lifeTime, int id, int x, int y, int speed) {
        this.name = name;
        this.bornTime = bornTime;
        this.dieTime = bornTime + lifeTime;
        this.id = id;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    protected void checkForCollision() {}

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getDieTime() {
        return dieTime;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + bornTime;
        result = prime * result + dieTime;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bee other = (Bee) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (bornTime != other.bornTime)
            return false;
        if (dieTime != other.dieTime)
            return false;
        if (id != other.id)
            return false;
        return true;
    }
}
