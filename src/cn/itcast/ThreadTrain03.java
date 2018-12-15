package cn.itcast;
/*
 *静态同步函数
 *  所用的锁是当前文件的字节码文件 ThreadTrain03.class
 */

public class ThreadTrain03 implements Runnable{
    // 总共有一百张火车 当一变量被static修饰的话存放在永久区，当class文件被加载的时候就会被初始化。
    private static int train1Count = 100;
    private Object oj = new Object();
    public boolean flag = true;

    @Override
    public void run() {
        // 为了能够模拟程序一直在抢票的话。 where
        if (flag) {
            //执行同步代码块this锁
            while (train1Count > 0) {

                synchronized (ThreadTrain03.class) {
                    if(train1Count>0){
                        try {
                            Thread.sleep(70);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        System.out.println(Thread.currentThread().getName()+ ",出售第" + (100 - train1Count + 1) + "票");
                        train1Count--;
                    }
                }

            }
        }
        else{
            // 同步函数
            while (train1Count > 0) {

                // 出售火車票
                sale();
            }
        }

    }

    public static synchronized void sale() {

        // 同步代码块 synchronized 包裹需要线程安全的问题。
        // synchronized (oj) {
        if (train1Count > 0) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - train1Count + 1) + "票");
            train1Count--;
        }
        // }

    }
}

class ThreadTest03 {

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain03 threadTrain2 = new ThreadTrain03();
        Thread t1 = new Thread(threadTrain2, "窗口1");
        Thread t2 = new Thread(threadTrain2, "窗口2");
        t1.start();
        Thread.sleep(40);
        threadTrain2.flag=false;
        t2.start();
    }
}


