package cn.itcast;

/*
 *同步代码块
 */
class ThreadTrain01 implements Runnable {

    private int trainCount = 100;

    @Override
    public void run() {
        synchronized (this){
            while (trainCount>0){
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sale();
            }
        }
    }

    private void sale() {
            System.out.println(Thread.currentThread().getName()+"出售第"+(100-trainCount+1)+"张票");
            trainCount--;
    }
}

class ThreadTest01 {

    public static void main(String[] args) {
        ThreadTrain01 threadTrain01 = new ThreadTrain01();
        Thread thread = new Thread(threadTrain01,"窗口1");
        Thread thread2 = new Thread(threadTrain01,"窗口2");
        thread.start();
        thread2.start();
    }

}