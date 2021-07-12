package project3;

/** This class is our queue class
 * We use our DoublyLinkedList as our underlying structure :D
*This class implements PossibleLocations interface and acquires its method(s)
*
* @author Diana Yepes!
*/


public class QueueOfSpaces implements PossibleLocations {
	
	private DoublyLinkedList<Location> structureOther=new DoublyLinkedList<Location>();
	
	
	
	@Override
	/**
	 * Our add aka "enqueue" method for this queue. 
	 * since this is a FIFO structure, we want most recent elements in front
	 * @param s: Location to add to queue 
	 * @throws NullPointerException if s is null
	 */
	public void add(Location s) {
		//check if null
		if(s==null) {
			throw new NullPointerException("Location cannot be null!");
		}
		//add as usual
		structureOther.add(s);
	}

	@Override
	/**
	 * Our remove aka "dequeue" method for this queue. 
	 * since most recent elements are in front, we remove at the front
	 * @return Location we removed
	 */
	public Location remove() {
		//If empty nothing to remove
		if(structureOther.isEmpty()) {
			return null;
		}
		
		return structureOther.remove(0);
		
	}

	
	@Override
	/** 
	 * This method checks if queue is empty
	 * @return boolean: true or false if empty
	 */
	public boolean isEmpty() {
		return structureOther.isEmpty();
	}
	
	
	/** 
	 * since the list should already be in correct order of future remove calls,
	 * (the ones to be removed first should be in order first to last of list)
	 * We can call the doublylinkedlist toString method  
	 * @returns formatted String	
	 */
	public String toString() {
		return structureOther.toString();
	}
	
	
}
