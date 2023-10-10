package com.adouer.single;

/**
 * 懒汉式单例
 * DCL懒汉式【双重检测锁】
 */
public class LazyMan {
    private volatile static LazyMan lazyMan;
    private LazyMan () {
        System.out.println(Thread.currentThread().getName());
    }
    //DCL双重锁
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class){
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                    /**
                     *  lazyMan = new LazyMan();进行了3步
                     *  1.创建内存空间
                     *  2.构造方法初始化对象
                     *  3.内存空间指向对象
                     *
                     *  如果A线程执行 132的时候B线程进来
                     *  lazyMan ！= null，导致b线程返回了错误的lazyMan，所以加volatile避免指令重排
                     */
                }
            }
        }
        return lazyMan;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan instance = LazyMan.getInstance();
                System.out.println(instance);
            }).start();
        }
    }
}
