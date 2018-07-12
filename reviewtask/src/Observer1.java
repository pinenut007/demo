import junior.Observer;

public class Observer1 implements Observer{
    @Override
    public void update() {
        System.out.println("observer1 received!");
    }
}
