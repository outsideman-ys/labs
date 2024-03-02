public class User implements IUser {

    Habitat beeWorld;

    public User(Habitat beeWorld) {
        this.beeWorld = beeWorld;
    }

    @Override
    public void ChangePossibillity(double possibillity) {
        beeWorld.setPossibillity(possibillity);
    }

    @Override
    public void ChangePeriod(int period) {
        beeWorld.setBornTime(period);
    }
    
}
