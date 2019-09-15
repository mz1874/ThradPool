# ThradPool
线程池
# 线程池
什么是线程和进程？

**线程**：

线程是进程的一个实体，是 CPU 调度和分派的基本单位，它是比进程更小的能独立运行的基本单位。线程
自己基本上不拥有系统资源，只拥有一点在运行中必不可少的资源(如程序计数器，一组寄存器和栈)，但是
它可与同属一个进程的其他的线程共享进程所拥有的全部资源

---

**多线程**

多线程指在单个程序中可以同时运行多个不同的线程执行不同的任务。
多线程编程的目的，就是“最大限度地利用 cpu 资源”，当某一线程的处理不需要占用 cpu 而只和 io 等资源
打交道时，让需要占用 Cpu 的其他线程有其他机会获得 cpu 资源。从根本上说，这就是多线程编程的最终
目的

**线程的实现方式**

1. 实现Runnable接口() 重写Run()方法
2. 集成Thread类 
3. 实现Callable接口 重写Call() 方法
4. 使用线程池创建多线程 Funtask<?>

## 线程的声明周期


```
 public enum State {
      
        NEW,   //新建状态

        RUNNABLE, //运行状态

        BLOCKED, //阻塞状态

        WAITING, //等待状态

        TIMED_WAITING,  // 等待一定的时间

        TERMINATED;  // 终结状态
    }

```

当一个线程新被创建的时候即 New的时候即为新建状态

调用Start() 方法 由Cpu负责调度启动线程，线程的状态转换为运行状态(就绪)

调用Thread.sleep()、 Object.wait(Long l) 、Thread.join() 使线程转变为超时等待状态

调用notify() 或者 notifyall() 转换为就绪状态(运行状态)

调用其  Object.wait() 和 Object.join() 方法转换为阻塞状态

调用其Object.notify() 或 object.notifyall() 转换为 就绪状态(运行状态)

当程序代码块执行结束的话，转换状态为终结状态(TERMINATED)


---

### Join 方法的详解

**未使用Join**


```
   public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        System.out.println(Thread.currentThread().getName() + "结束");

    }
```

**使用Join**

使主线程进行了阻塞，等待新建线程执行结束才继续主线程的操作

```
 public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        System.out.println(Thread.currentThread().getName() + "结束");
    }
```

Join 源码

```
 public final synchronized void join(long millis)
    throws InterruptedException {
    
        //获取当前的时间戳
        long base = System.currentTimeMillis();
        
        long now = 0;
        //join的时间 如果小于0 的话则抛出异常
        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }
        //如果等于0的话 死循环判断当前的线程是否处于激活状态
        if (millis == 0) {
            while (isAlive()) {
                //如果是激活状态的话则进行等待
                wait(0);
            }
        } else {
            //否则的话继续判断 是否为激活状态
            while (isAlive()) {
                //传入的时间减少 当前的Now()
                long delay = millis - now;
                //如果是按小于等于0的话则退出
                if (delay <= 0) {
                    break;
                }
                //否则的话使当前的线程处于等待状态
                wait(delay);
                获取等待的时间戳
                now = System.currentTimeMillis() - base;
            }
        }
    }

```


```
java.lang.Object#wait(long) 调用的是本地的方法接口
```


```
public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join(2);
        System.out.println(Thread.currentThread().getName() + "结束");
    }
    
    main结束
    Thread-0结束
```


**启动线程的第一种方式：集成Thread类,重写run方法**

```
public class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        TestThread testThread=new TestThread();
        testThread.run();
    }
}
```

**启动多线程的第二种方法:实现Runnable接口**

Lamdba

```
public class ThreadRunnnable {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        },"Mz").start();

        System.out.println(Thread.currentThread().getName());
    }
}
```

**启动多线程的第三种方式**

实现Callable接口 Lamdba

```
public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask=new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName());
            return "张三";
        });
        Thread thread=new Thread(futureTask);
        thread.start();
        String s = futureTask.get();
        System.out.println(s);
        System.out.println("主线程结束");
    }
```

**第四种使用线程池: 单例线程池**

层级  |     名称  | 方法 | 说明 | 类型
---|---|---|---|---
1 | java.util.concurrent.Executor| java.util.concurrent.Executor#execute| 执行接口|抽象类
1 | java.util.concurrent.ExecutorService| java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable<T>)| 提交接口|接口
1 | java.util.concurrent.AbstractExecutorService| java.util.concurrent.AbstractExecutorService#submit(java.util.concur| 把执行和提交接口进行合并区别:有返回值和无返回值|抽象类





```
public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0;i<1000;i++){
        Future<String> submit = executorService.submit(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                    return "张三";
                }
        );
        String result = submit.get();
        System.out.println(result);
        }
        executorService.shutdown();
    }
}
```

**线程池运行思路**

如果当前池大小 poolSize 小于 corePoolSize ，则创建新线程执行任务
如果当前池大小 poolSize 大于 corePoolSize ，且等待队列未满，则进入等待队列
如果当前池大小 poolSize 大于 corePoolSize 且小于 maximumPoolSize ，且等待队列已满，则创建新线程
执行任务
如果当前池大小 poolSize 大于 corePoolSize 且大于 maximumPoolSize ，且等待队列已满，则调用拒绝策
略来处理该任务
线程池里的每个线程执行完任务后不会立刻退出，而是会去检查下等待队列里是否还有线程任务需要执行，
如果在 keepAliveTime 里等不到新的任务了，那么线程就会退出


**Submit 和 execute 方法**

1、有返回值和无返回值
2、Task 不一样 futuretask 一个 task 本身

