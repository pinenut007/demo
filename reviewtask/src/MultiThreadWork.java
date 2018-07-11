import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadWork {

    //实现有返回值得多线程
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("程序开始运行....");

        Date date1=new Date();


        int taskSize = 5;

        //创建一个线程池
        ExecutorService pool= Executors.newFixedThreadPool(taskSize);

        //创建多个有返回值得任务
        List<Future> list=new ArrayList<Future>();

        for (int i =0;i<taskSize;i++){
            Callable c = new MyCallable(i+"");

            //执行任务并且获取Future对象
            Future f = pool.submit(c);

            list.add(f);

        }

        pool.shutdown();


        //获取所有并发任务得运行结果

        for(Future f:list){
            System.out.println("结果为"+f.get().toString());
        }

        Date date2=new Date();

        System.out.println("程序运行结束，运行时间"+(date2.getTime()-date1.getTime())+"毫秒");
    }


}
//自定义得任务类
class MyCallable implements Callable<Object>{

    private String taskNum;

    MyCallable(String taskNum){
        this.taskNum=taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("任务启动......"+Thread.currentThread().getName());
        Date dateTmp1 = new Date();

        Thread.sleep(1000);

        Date dateTmp2=new Date();

        long time=dateTmp2.getTime()-dateTmp1.getTime();

        System.out.println("任务结束.....");
        return taskNum+"任务返回运行结果，当前任务时间【 "+time+" 毫秒】";
    }
}


