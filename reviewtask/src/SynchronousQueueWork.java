import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueWork {

    //同步队列
    public static void main(String[] args) {

        final SynchronousQueue<Integer> queue=new SynchronousQueue<Integer>();

        Thread putThread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start。。。");

                try {
                    queue.put(1);
                } catch (InterruptedException e) {

                }

                System.out.println("put thread end。。。");
            }
        });


        Thread takeThread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start。。。");

                try {
                    System.out.println("take from putThread with: "+queue.take());
                } catch (InterruptedException e) {

                }

                System.out.println("take thread end。。。");
            }
        });

        putThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        takeThread.start();
    }


}
