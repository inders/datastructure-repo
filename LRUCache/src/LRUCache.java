import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




public class LRUCache<K, V> {

	// We'll keep the values as soft references 
	private Map<K, SoftReference<V>> softCache = new HashMap<K, SoftReference<V>>();
	private Map<V, Node> hardCache = new HashMap<V, Node>();
	private long hardCacheSize = 10;
	private static long currentHardCacheSize;
	DoublyLInkedList<V> dList = new DoublyLInkedList<V>();
	ReferenceQueue<V> refQueue = new ReferenceQueue();
	
	public void printHardCache() {
		Set<V> keySet = hardCache.keySet();
		Iterator<V> it = keySet.iterator();
		while(it.hasNext()) {
		V value = it.next();
			System.out.println("HardCache:: Value :" + value );
		}
		
	}
	
	public void printSoftCache() {
		Set<K> keySet = softCache.keySet();
		Iterator<K> it = keySet.iterator();
		while(it.hasNext()) {
		K key = it.next();
			System.out.println("SoftCache:: Key :" + key + " value:" + softCache.get(key));
		}
	}
	
	
	public LRUCache() {
		CleanUpThread<K, V> cleanupThread = new CleanUpThread<K, V>(refQueue, softCache);
		cleanupThread.start();
	}
	
	private static void setCurrentHardCacheSize(long currentHardCacheSize) {
		LRUCache.currentHardCacheSize = currentHardCacheSize;
	}

	
	private static long getCurrentHardCacheSize() {
		return currentHardCacheSize;
	}

	private long getHardCacheSize() {
		return hardCacheSize;
	}

	public void setHardCacheSize(long hardCacheSize) {
		this.hardCacheSize = hardCacheSize;
	}

	
	private boolean isHardCacheFull() {
	if	(getCurrentHardCacheSize() < getHardCacheSize())
	return false;
	else 
		return true;
		
	}
	
	private boolean isEntryInHardCache(V value) {
		if (hardCache.get(value) == null)
			return false;
		else
			return true;
	}
	
	public V get(K key) {
		// the value returned would be a soft reference
		SoftReference<V> softRef = softCache.get(key);
		//get a strong reference for this value
		// to store in hardCache
		V value = softRef.get() ;
		// not found in the cache
		// it could have been gc'd if there wasn't any strong reference
		// in hardCache for this
		if (softRef == null && 
				value == null)
			return null;
		
		// found in cache and is most recently used
		// if entryNotThereInHardCache && hardCacheNotFull
		//     add strong reference to this in hardCache at head of list 
		// else if entryNotThereInHardCache && hardCacheFull
		//     remove least recently used entry(tail) && add this entry to hard cache at head
		// else if entryThereInHardCache 
		//     remove it from it's current position and move it to head of list(most recently used)
		if ( isEntryInHardCache(value) == false  &&
				isHardCacheFull() == false) {
			System.out.println("hard cache is not full :: add to hard cache");
			// hard cache is not full
			//1. add to hard cache
			Date date = new Date();
			long timeStamp = date.getTime();
			Node node = dList.insertAtHead(value);
			hardCache.put(value, node);
			setCurrentHardCacheSize(getCurrentHardCacheSize() + 1);
		}
		else if (isEntryInHardCache(value) == false &&
				isHardCacheFull() == true)	{
			// hard cache is full
			// need to remove the least recently used one which should be at tail
			System.out.println("Hard cache is fulll, need to remove the least recently used one which should be at tail");
			Node tailNode = dList.getTail();
			dList.remove(tailNode);
			System.out.println("Removed least recently used entry :" + tailNode.getData());
			hardCache.remove(tailNode.getData());
			Node node = dList.insertAtHead(value);
			hardCache.put(value, node );
		}
		else if (isEntryInHardCache(value)) {
			// most recently used, insert on head of list
			System.out.println(" most recently used, insert on head of list ");
			Node node = hardCache.get(value);
			dList.remove(node);
			dList.insertAtHead(node);
		}
		return value;
	
	}

	private ReferenceQueue getRefQueue() {
		return refQueue;
	}


	private void setRefQueue(ReferenceQueue refQueue) {
		this.refQueue = refQueue;
	}


	public void add(K key, V value) {
		SoftReference softRef = new SoftReference(value, getRefQueue());
		synchronized(softCache) {
			softCache.put(key, softRef);
		}
	}
	
	public static void main(String[] args) {
		LRUCache<String, String> lruCache = new LRUCache<String, String>();
		//set the hard cache size to 3 for ease of debugging
		lruCache.setHardCacheSize(3);
		// fill up the cahe with 10 entries
		
		for (int i=1; i <= 5; i++) {
			lruCache.add(new String(new Long(i).toString()),new String(new Long(i).toString()) );
		}
		lruCache.printSoftCache();
		lruCache.printHardCache();
		lruCache.get(new String("1"));
		lruCache.get(new String("2"));
		lruCache.get(new String("3"));
		lruCache.get(new String("4"));
		System.out.println("Printing HardCache");
		lruCache.printHardCache();
		lruCache.get("2");
		lruCache.add(new String(new String("5")),new String(new String("5")));
		lruCache.get("5");
		System.out.println("Printing HardCache");

		lruCache.printHardCache();
		lruCache.add(new String(new String("7")),new String(new String("7")));
		lruCache.get("7");
		lruCache.printHardCache();
		//testing for OOM soft entries should be removed now
		for (int i=0; i < (1024 * 1024 ); i++) {
			lruCache.add(new String(new Long(i).toString()),new String(new Long(i).toString()) );

		}
		for (int i=0; i < (1024 * 1024 ); i++) {
			lruCache.add(new String(new Long(i).toString()),new String(new Long(i).toString()) );

		}
	}
	


}
