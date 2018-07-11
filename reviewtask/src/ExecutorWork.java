import java.util.concurrent.*;

public class ExecutorWork {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future future=null;
    //   future= executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//
//                System.out.println("Asynchronous task:current thread is "+Thread.currentThread().getName());
//            }
//        });
//        System.out.println("Asynchronous task:current thread is "+Thread.currentThread().getName());
//        future.get();


        future=executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous task:current thread is "+Thread.currentThread().getName());
               // Thread.yield();
                Thread.sleep(10000);
                return "Callable Result";
            }
        });

        System.out.println("main thread is working");
        System.out.println("Asynchronous task:current thread is "+Thread.currentThread().getName()+" and "+"callable result is "+future.get());

        executorService.shutdown();

    }




}
