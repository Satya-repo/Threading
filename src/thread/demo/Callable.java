package thread.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Callable {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service =Executors.newFixedThreadPool(3,new MyThreadFactory("Thread")) ;
		
		Future<Integer> future = service.submit(new Operation());
		
		Thread.sleep(3000);
		Future<Integer> future1 = service.submit(new Operation());
		Thread.sleep(3000);
		Future<Integer> future3 = service.submit(new Operation());
		
		 System.out.println(future.get() + "   Thread name is "+Thread.currentThread().getName());
		 
		 service.shutdown();
	}
}

class Operation implements java.util.concurrent.Callable<Integer>{
	
	public static int count = 0;
	public Integer call() throws Exception {
		System.out.println("Thread name is "+Thread.currentThread().getName() );
		for(int i = 0;i<10;i++){
			//Thread.currentThread().setName("T"+i);;
			System.out.println("Counter is "+i+" so sleeping for "+i+ "second "+ "and  Thread name is "+Thread.currentThread().getName() );
			Thread.currentThread().join();
		}
		
		return null;
	}
}



