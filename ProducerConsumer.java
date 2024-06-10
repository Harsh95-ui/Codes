//Producer Consumer 

class shared{
	//shared variable n 
	int x;
	char turn;// to check 

	shared(){
		turn='p';
	}

}

class thread1 extends Thread{
	shared obj;
	thread1(shared obj){
		this.obj=obj;

	}

	public void run(){		
		synchronized(obj){
		for(int i=0;i<1000;i++){			
				while(obj.turn=='c') { 				
					try{
						obj.wait();
					}
					catch(InterruptedException e){
					}
				}
				System.out.println("\n producer sets "+i+" ");		
				obj.x=i;
				obj.turn='c';
				obj.notify();
			}
		}
	}
}

class thread2 extends Thread{
	shared obj;
	thread2(shared obj){
		this.obj=obj;
	}
	public void run(){		
		synchronized(obj){
		for(int i=0;i<1000;i++){			
				while(obj.turn=='p') {				
					try{
						obj.wait();
					}
					catch(InterruptedException e){
					}
				}
				System.out.println("Consumer gets "+obj.x+" ");
				obj.turn='p';
				obj.notify();
			}
		}
	}
}

class multithreading4ProducerCon{
	public static void main(String args[]){
		shared obj=new shared();
		thread1 obj1=new thread1(obj);
		thread2 obj2=new thread2(obj);
		obj1.start();
		obj2.start();

		for(int i=0;i<100;i++){
			System.out.print("main ");
		}
	}

}
