package cn.itcast;

/*
 * 线程死锁
 */

class ThreadTrain04 implements Runnable{
    private static int i = 0;
    private Object oj = new Object();
    public boolean flag = true;

    @Override
    public void run() {
        if (flag) {

            while (true) {
                synchronized (this) {
                    sale(oj);
                }
            }
        } else {
            // 同步函数
            while (true) {
                synchronized (oj) {
                    sale(this);
                }
            }
        }
    }

    public synchronized void sale(Object o) {
        synchronized (o) {
            System.out.println(i);
            i++;
        }
    }
}

class ThreadTest04 {

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain04 threadTrain3 = new ThreadTrain04();
        Thread t1 = new Thread(threadTrain3, "窗口①");
        Thread t2 = new Thread(threadTrain3, "窗口②");
        t1.start();
        Thread.sleep(40);
        threadTrain3.flag = false;
        t2.start();
    }

}
