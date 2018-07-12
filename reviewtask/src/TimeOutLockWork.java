import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimeOutLockWork {

    //线程尝试获取锁时加上一定得时限，超过时限则放弃对锁得请求，并释放自己占有得锁
    public int flag = 1;

    private static Object o1=new Object(), o2=new Object();

    public void money(int flag){

        this.flag=flag;
        if(this.flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("当前线程是"+Thread.currentThread().getName()+"flag 值为"+this.flag);
                }
            }
        }

        if(this.flag == 0){

            synchronized (o2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1){
                    System.out.println("当前线程是"+Thread.currentThread().getName()+"flag 值为"+this.flag);
                }
            }
        }
    }

    public static void main(String[] args) {

        final Lock lock=new ReentrantLock();
        final TimeOutLockWork td1=new TimeOutLockWork();
        final TimeOutLockWork td2=new TimeOutLockWork();

        td1.flag = 1;
        td2.flag = 0;

        final Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();

                td1.flag = 1;

                //获取不到锁，就等5秒，如果5秒还是获取去不到就返回false；

                try {
                    if(lock.tryLock(5000, TimeUnit.MILLISECONDS)){
                        System.out.println("获取到锁！");
                    }else{
                        System.out.println("获取不到锁");

                        return ;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try{
                    td1.money(1);
                }catch (Exception e){
                    System.out.println(tName+"出错了");
                }finally {
                    System.out.println("当前得线程是"+Thread.currentThread().getName()+"释放锁");
                    lock.unlock();
                }


            }
        });


        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                td1.flag = 1;

                try {
                    if(lock.tryLock(5000,TimeUnit.MILLISECONDS)){
                        System.out.println(tName+"获取到锁");
                    }else{
                        System.out.println(tName+"获取不到锁");
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try{
                   td2.money(0);
                }catch (Exception e){
                    System.out.println(tName+"出错啦");
                }finally {
                    System.out.println("当前得线程是"+Thread.currentThread().getName()+"释放锁");
                    lock.unlock();
                }
            }
        });
        t2.start();

    }
}
