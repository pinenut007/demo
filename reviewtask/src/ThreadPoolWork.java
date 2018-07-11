import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolWork {
    public static void main(String[] args) {

        //可重用固定线程数量得线程池

        ExecutorService pool= Executors.newFixedThreadPool(2);
        pool= Executors.newSingleThreadExecutor();
        pool=Executors.newCachedThreadPool();

        Thread t1=new MyThread();
        Thread t2=new MyThread();
        Thread t3=new MyThread();
        Thread t4=new MyThread();

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);

        pool.shutdown();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在执行");
    }
}
