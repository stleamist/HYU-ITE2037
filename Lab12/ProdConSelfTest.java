class ProdConSelfTest {
	private Buffer buffer;
	private Producer producer;
	private Consumer consumer;
	
	ProdConSelfTest() {
		buffer = new Buffer(15);
		producer = new Producer(buffer);
		consumer = new Consumer(buffer);
	}
	
	private class Producer extends Thread {
		private final Buffer buffer;
		
		public Producer(Buffer buffer) {
			this.buffer = buffer;
		}
		
		public void produce() throws InterruptedException {
			for (int i = 0; i < buffer.getSize(); i++) {
				buffer.add(Math.random() * 100);
			}
		}
		
		public void run() {
			try {
				produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class Consumer extends Thread {
		private final Buffer buffer;
		
		public Consumer(Buffer buffer) {
			this.buffer = buffer;
		}
		
		public void consume() throws InterruptedException {
			for (int i = 0; i < buffer.getSize(); i++) {
				buffer.remove();
			}
		}
		
		public void run() {
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	void startThread() {
		producer.start();
		consumer.start();
	}
}