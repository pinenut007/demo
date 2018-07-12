public class OrderedLockWork {

    //避免死锁,现成按照一定得顺序加锁

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

        final OrderedLockWork td1=new OrderedLockWork();
        final OrderedLockWork td2=new OrderedLockWork();

        td1.flag = 1;
        td2.flag = 0;

        final Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                td1.flag = 1;
                td1.money(1);
            }
        });

        t1.start();

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                //等待t1执行完
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                td2.flag = 0;
                td1.money(0);
            }
        });

        t2.start();
    }
}
