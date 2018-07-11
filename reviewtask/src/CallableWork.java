import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class CallableWork {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables=new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task2";
            }
        });

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task1";
            }
        });

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task3";
            }
        });

        String result = executorService.invokeAny(callables);


        System.out.println(result+" is executed");

        executorService.shutdown();
    }
}
