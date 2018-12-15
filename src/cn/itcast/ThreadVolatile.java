package cn.itcast;

/*
 * volatile关键字
 */

class ThreadVolatileDemo extends Thread{
    private boolean flag = true;
    //private volatile boolean flag = true; //强制刷新本地内存,与主内存同步.

    @Override
    public void run() {
        System.out.println("子线程开始执行......");
        while(flag){
        }
        System.out.println("子线程执行结束......");
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }
}

public class ThreadVolatile {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);  //有延迟不会及时刷新主内存.删掉不会出现此情况
        //将flag设置为false将主线程给更改,将本地内存刷新到主内存,而另一个线程本地内存仍然为true,所以程序无法停止
        threadVolatileDemo.setFlag(false);
    }

}
