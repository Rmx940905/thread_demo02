package cn.itcast;

/*
 *volatile非原子性
 * 最后结果不为10000所以线程不同步
 */

public class VolatileNoAtomic extends Thread{
    //static 修饰的关键字,存放在静态区,只会存放一次,所有线程中都共享
    private volatile static int count = 0 ;

    @Override
    public void run() {
        for(int i= 0; i <1000; i++){
            count++;
        }
        System.out.println(getName()+","+count);
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] volatileNoAtomicsList = new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomicsList.length; i++){
            volatileNoAtomicsList[i] = new VolatileNoAtomic();
        }
        for (VolatileNoAtomic vol : volatileNoAtomicsList) {
            vol.start();
        }
    }
    /*
    //AtomicInteger原子类,计数作用,保证线程安全问题    原子类属于JDK 1.5并发包中
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for(int i= 0; i <1000; i++){
            count.incrementAndGet();
        }
        System.out.println(getName()+","+count.get());
    }
     */
}
