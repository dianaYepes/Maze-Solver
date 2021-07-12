package project3;


/** This class is our stack class
 * We use our DoublyLinkedList as our underlying structure :D
*This class implements PossibleLocations interface and acquires its method(s)
*
* @author Diana Yepes!
*/

public class StackOfSpaces implements PossibleLocations {
	
	
	private DoublyLinkedList<Location> structure=new DoublyLinkedList<Location>();
	
	
	@Override
	/**
	 * Our add aka "push" method for this stack. 
	 * because it is a stack, we need to "push"
	 * basically I'm gonna have the bottom of the stack be the end of the list
	 * @param s: Location to add to Stack  
	 * @throws NullPointerException if s is null
	 */
	public void add(Location s) {
		//check i null
		if(s==null) {
			throw new NullPointerException("Location cannot be null!");
		}
		//add to index 0 so recent ones are on top
		structure.add(s, 0);
	}
	
	/**
	 * Our remove aka "pop" method for this stack. 
	 * a stack is LIFO and since the top is at the front of list its easier  
	 * @throws NullPointerException if s is null
	 * @return the Location we removed.
	 */
	@Override
	public Location remove() {
		//If empty nothing to remove
		if(structure.isEmpty()) {
			return null;
		}
		//remove from top of stack
		return structure.remove(0);
	}

	
	/**
	 * This method checks if stack is empty 
	 * @return boolean: true or false depending if empty or not
	 */
	public boolean isEmpty() {
		return structure.isEmpty();
	}
	
	/** 
	 * since the list should already be in correct order of future remove calls,
	 * (the ones to be removed first should be in order first to last of list)
	 * We can call the doublylinkedlist toString method  
	 * @returns formatted String	
	 */
	public String toString() {
		return structure.toString();
	}
	
}
