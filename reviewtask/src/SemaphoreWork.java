import java.util.concurrent.Semaphore;

public class SemaphoreWork {
    //使用semaphore控制某任务可同时被执行得线程数量

    static Semaphore semaphore=new Semaphore(5,true);

    public static void main(String[] args) {

        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
    }

    public static void test() {

        //申请一个信号
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"进来了");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"走了");

        //释放一个请求
        semaphore.release();
    }
}
