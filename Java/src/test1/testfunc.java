package test1;

public class testfunc {
	private String output;
	
	public testfunc(String output){
		this.output = output;
	}
	public String helloworld(){
		return this.output;
	}
}

class mythread implements Runnable{
	private String threadname;
	private int ticketnum = 10;
	
	public mythread(String threadname){
		this.threadname = threadname;
	}
	
	public void run(){
		for(int i=0; i < 20; i++){
			if(this.ticketnum > 0){
				System.out.println("Sell ticket by " + this.threadname + ", remain tickets: " +this.ticketnum--);
			}
		}
	}
}
