import static java.lang.Thread.*;

public class alternateThread {
    public static void main(String[] args) {

        final Business business=new Business();
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<3;i++){
                    business.subMethod();
                }
            }
        }).start();

        //主线程

        for (int i=0;i<3;i++){
            business.mainMethod();
        }
    }
}

class Business{
    private boolean subFlag = true ;

    public synchronized void mainMethod(){

        while(subFlag){

            try{
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i< 5 ;i++){

            System.out.println(Thread.currentThread().getName() +" : main thread running loop count --"+i);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        subFlag=true;
        notify();
    }

    public synchronized void subMethod(){

        while(!subFlag){

            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i =0;i<10;i++){
            System.out.println(Thread.currentThread().getName() +" : sub thread running loop count --"+i);

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        subFlag=false;
        notify();
    }
}
