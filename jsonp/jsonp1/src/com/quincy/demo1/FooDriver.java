package com.quincy.demo1;

/**
 * CAS
 * @author quincy
 *
 */
public class FooDriver {
	
	public static void main(String[] args) {
		Foo f = new Foo();
		Thread t = new Thread(f);
		t.start();
		
		while(true){
			if(f.isFlag()){
				System.out.println("fffff");
				break;
			}
		}
	}

}

class Foo implements Runnable{
	
	private volatile boolean  flag = false;

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		flag = true;
		
		System.err.println("tt" + isFlag());
		
		
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}

