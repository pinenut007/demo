package junior;

public interface Subject {

    //增加观察者
    public void add(Observer observer);
    //删除观察者
    public void del(Observer observer);
    //通知所有观察者

    public void notifyObservers();

    public void operation();
}
