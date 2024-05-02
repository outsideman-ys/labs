package org.example;
import java.io.Serializable;

public abstract class Bee implements IBehaviour, Serializable{

    /*
     * добавлена разница с рождением и временем симуляции, чтобы потом обновлять время рождения после десериализации
     */


    protected String name;          // name that showed under the object
    protected int bornTime;
    protected int dieTime;
    protected int id;
    protected int speed;
    protected int timeDif;

    protected int x;
    protected int y;

    public Bee() {}

    public Bee(String name, int bornTime, int lifeTime, int id, int x, int y, int speed) {
        this.name = name;
        this.bornTime = bornTime;
        this.dieTime = bornTime + lifeTime;
        this.id = id;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.timeDif = dieTime - bornTime;
    }

    protected void checkForCollision() {}

    public int getTimeDif() {
        return timeDif;
    }

    public void setTimeDif(int timeDif) {
        this.timeDif = timeDif;
    }

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

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDieTime(int dieTime) {
        this.dieTime = dieTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBornTime() {
        return bornTime;
    }

    public void setBornTime(int bornTime) {
        this.bornTime = bornTime;
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
