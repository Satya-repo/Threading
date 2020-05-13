package thread.demo;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*public class MyThreadFactory implements ThreadFactory {
	
	 AtomicInteger threadNo = new AtomicInteger(0);
	 private int num = 0;

	 public Thread newThread(Runnable r) {
		 num++;
	     String threadName = "Thread "+num;
	     System.out.println("threadName:"+threadName);
	     return new Thread(r,threadName );
	   }

}*/

 class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger count = new AtomicInteger(1);
        private final String name;

        public MyThreadFactory(String name) {
            this.name = name;
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, name + "-" +  count.getAndIncrement());
        }
    }


