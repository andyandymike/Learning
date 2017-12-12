package test.andy.hello;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App implements Serializable
{
    public static void main(String args[]) {


        System.out.println(Thread.currentThread().getName());
        // creating two threads for start and run method call
        Thread startThread = new Thread(new Task("start"));
        Thread runThread = new Thread(new Task("run"));
        
        Thread ThreadOne = new Thread(new Task("ThreadOne"));
        Thread ThreadTwo = new Thread(new Task("ThreadTwo"));
        Thread ThreadThree = new Thread(new Task("ThreadThree"));
        Thread ThreadFour = new Thread(new Task("ThreadFour"));
        Thread ThreadFive = new Thread(new Task("ThreadFive"));
        int[] a = new int[5];
        
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(dtf.format(ldt));
        
        Pattern p = Pattern.compile("(ab\\sc)");
        Matcher m = p.matcher("ab c c,ab c");
        while(m.find()) {
        	System.out.println("Found value: " + m.group(0));
        	System.out.println("Found value: " + m.group(1));
        }
        

        startThread.start(); // calling start method of Thread - will execute in
        // new Thread
        runThread.run(); // calling run method of Thread - will execute in
        // current Thread
        
        Integer a1 = 1;
        System.out.println(System.identityHashCode(a1));
        a1 = 2;
        System.out.println(System.identityHashCode(a1));
        
        String t = "a,b,c,,";
        String[] arr = t.split(",");
        System.out.println(arr.length);
        System.out.println(arr[2]);
        
        List<String> n = new ArrayList<String>();
        
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);
        
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        for (String item : list1) {
        if ("1".equals(item)) {
	        list1.remove(item);
	        }
        }
        for (String item : list1) {
            System.out.println(item);
            }
        
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("a", "aaa");
        map.put("b", "bbb");
        for(Entry<String, String> entry : map.entrySet()) {
        	System.out.println(entry.toString());
        }


    }


    /*
     * Simple Runnable implementation
     */
    private static class Task implements Runnable {
        private String caller;


        public Task(String caller) {
            this.caller = caller;
        }


        @Override
        public void run() {
            System.out.println("Caller: " + caller
                    + " and code on this Thread is executed by : "
                    + Thread.currentThread().getName());


        }
    }
}
