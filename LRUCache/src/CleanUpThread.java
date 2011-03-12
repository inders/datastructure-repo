import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class CleanUpThread <K,V>  extends Thread{

	ReferenceQueue<V> refQueue = new ReferenceQueue<V>();
	Map<K, V> cache = null;
	
	public CleanUpThread(ReferenceQueue refQueue, Map softCache) {
		this.refQueue = refQueue;
	   	cache = softCache;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			while(true) {
				try {
					synchronized(cache) {

						Reference ref = refQueue.remove();
						//find the key associated with this value
						Set <K> keys = cache.keySet();
						Iterator<K> it = keys.iterator();

						while (it.hasNext()) {
							K key = it.next();
							if (cache.get(key) == ref.get()) {
							System.out.println("CleanupThread :: cleaning key =" + key + " value = "+ cache.get(key) + " from softCache");
							cache.remove(key);
							}
						}

					}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}
	}
	
}
