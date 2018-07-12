import junior.Subject;

public class MySubject extends AbstractSubject{

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }


    public static void main(String[] args) {
        Subject sub=new MySubject();

        sub.add(new Observer1());
        sub.add(new Observer2());

        sub.operation();
    }
}
