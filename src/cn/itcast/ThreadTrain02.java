package cn.itcast;

/*
 *同步函数
 *  所用的锁是this锁
 */
public class ThreadTrain02 implements Runnable{
    private int trainCount = 100;

    @Override
    public synchronized void run() {
        while (trainCount>0){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    private void sale() {
            System.out.println(Thread.currentThread().getName()+"出售第"+(100-trainCount+1)+"票");
            trainCount--;
    }
}

class ThreadTest02 {
    public static void main(String[] args) {
        ThreadTrain02 threadTrain02 = new ThreadTrain02();
        Thread thread = new Thread(threadTrain02,"窗口1");
        Thread thread2 = new Thread(threadTrain02,"窗口2");
        thread.start();
        thread2.start();
    }
}


