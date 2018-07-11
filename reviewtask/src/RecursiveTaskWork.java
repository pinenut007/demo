import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

//有返回得join-fork类型线程池
public class RecursiveTaskWork extends RecursiveTask<Long>{

    private final static long THRESHOLD=16;
    private  long workload=0;
    public RecursiveTaskWork (long workload){
        this.workload=workload;
    }

    @Override
    protected Long compute() {
        //若工作负载大于阈值16,将任务切分为更小得任务
        if(this.workload>THRESHOLD){
            System.out.println("splitting task into tiny block: "+this.workload);

            List<RecursiveTaskWork> subTasks=new ArrayList<RecursiveTaskWork>();
            subTasks.addAll(createSubTasks());

            for(RecursiveTaskWork taskWork:subTasks){
                System.out.println("current thread: "+Thread.currentThread().getName());
                System.out.println("current thread: "+Thread.currentThread().getName());
                taskWork.fork();
            }

            long result=0;
            for(RecursiveTaskWork taskWork:subTasks){
                System.out.println("current thread: "+Thread.currentThread().getName());
                result+=taskWork.join();
                System.out.println("current thread: "+Thread.currentThread().getName());
            }

            return result;


        }else{
            System.out.println("tiny workload !!");
            return workload*3;
        }

    }

    private List<RecursiveTaskWork> createSubTasks(){

        List<RecursiveTaskWork> subtTasks=new ArrayList<RecursiveTaskWork>();

        RecursiveTaskWork subTask1=new RecursiveTaskWork(this.workload/2);
        RecursiveTaskWork subTask2=new RecursiveTaskWork(this.workload/2);

        subtTasks.add(subTask1);
        subtTasks.add(subTask2);

        return subtTasks;
    }

    public static void main(String[] args) {
        new RecursiveTaskWork(18).compute();
    }

}
