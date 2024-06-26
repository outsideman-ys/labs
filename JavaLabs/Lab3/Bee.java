
public abstract class Bee {
    protected String name;          // name that showed under the object
    protected int bornTime;
    protected int dieTime;
    protected Integer id;

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
