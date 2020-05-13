package thread.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

class SimpleThreadFactory implements ThreadFactory {
   String name;
   AtomicInteger threadNo = new AtomicInteger(0);

   public SimpleThreadFactory (String name){
       this.name = name;
   }
   public Thread newThread(Runnable r) {
     String threadName = name+":"+threadNo.incrementAndGet();
     System.out.println("threadName:"+threadName);
     return new Thread(r,threadName );
   }
   public static void main(String args[]){
        SimpleThreadFactory factory = new SimpleThreadFactory("Factory Thread");
        ThreadPoolExecutor executor= new ThreadPoolExecutor(1,1,60,
                    TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1),new ThreadPoolExecutor.DiscardPolicy());


        final ExecutorService executorService = Executors.newFixedThreadPool(5,factory);

        for ( int i=0; i < 100; i++){
            executorService.submit(new Runnable(){
                 public void run(){
                    System.out.println("Thread Name in Runnable:"+Thread.currentThread().getName());
                 }
            });
        }
        executorService.shutdown();
    }
 }
