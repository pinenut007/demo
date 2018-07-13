public class MultiThreadsWork {

    //4个线程对一个int变量，2个加1，2个减1，输出

  static  int j=1;  //这里加static与不加效果不同

    public synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);

    }

    class T1 implements Runnable{

        @Override
        public void run() {
            inc();
        }
    }

    public synchronized void dec(){
        j--;

        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }

    class T2 implements Runnable{

        @Override
        public void run() {
            dec();
        }
    }

    public static void main(String[] args) {
        MultiThreadsWork t=new MultiThreadsWork();

        T1 t1=new MultiThreadsWork().new T1();

        T2 t2= new MultiThreadsWork().new T2();

        for (int i=0;i<2;i++){
            Thread thread1=new Thread(t1);

            thread1.start();

            Thread thread2 = new Thread(t2);

            thread2.start();

        }
    }
}
