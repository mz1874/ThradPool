package com.mz.HelloMaven;

import java.util.concurrent.*;

/**
 * 第一种线程池
 */
public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //带有缓存的
        /**
         * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());
         *public void execute(Runnable command) {
         *     if (command == null)
         *        int c = ctl.get();
         *
         *      if (workerCountOf(c) < corePoolSize) {
         *          if (addWorker(command, true))
         *          return;
         *         c = ctl.get();
         *       }
         *
         *      //进入
         *      if (isRunning(c) && workQueue.offer(command)) {
         *          int recheck = ctl.get();
         *          if (!isRunning(recheck) && remove(command))
         *          reject(command);
         *      else if (workerCountOf(recheck) == 0)
         *          addWorker(null, false);
         *       }
         *     else if (!addWorker(command, false))
         *      reject(command);
         *}
         */

        //缓存的
//        ExecutorService executorService = Executors.newCachedThreadPool();
        //固定任务的
//        ExecutorService executorService = Executors.newFixedThreadPool(5);


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        /*
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
         */
        executorService.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName());
        },0,4,TimeUnit.SECONDS);
//        for (int i = 0; i < 1000; i++) {
//            Future<String> submit = executorService.submit(
//                    () -> {
//                        System.out.println(Thread.currentThread().getName());
//                        return "张三";
//                    }
//            );
//            String result = submit.get();
//            System.out.println(result);
//
//        }
//        executorService.shutdown();
    }
}
