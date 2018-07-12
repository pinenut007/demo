import junior.Observer;

public class Observer2 implements Observer{
    @Override
    public void update() {
        System.out.println("observer2 received!!");
    }
}
