package project3;

import java.util.Iterator;
import java.util.Objects;

/** This class is our main structure--> DoublyLinkedList! :D
 *This class implements Iterable<E> interface and acquires its method(s)
 *It will have all the necessary methods a data structure needs, as well as
 *an inner class *Node* used to help track references. 
 *Data Fields include size to keep track as it grows/shrinks
 *as well as list head and tail references
 *
 * @author Diana Yepes!
 */



public class DoublyLinkedList<E> implements Iterable<E>{
	
	/**
	 *The inner node class
	 *this is where the actual list "element" can be found
	 *We also include references to next/previous nodes
	 */
	public class Node{
		E obj;
		Node prev;
		Node next;
		
		public Node(E obj) {
			this.obj=obj;
		}
	}
	//structure class data fields
	private Node head=null;
	private Node tail=null;
	private int size=0;
	
	
	
	/**
	 * One of two add methods for this structure. 
	 * We add an element to the end of list
	 * @param e: type to be added  
	 * @throws ClassCastException
	 * @return boolean: true or false depending whether it was able to increment size
	 */
	public boolean add(E e) throws ClassCastException {
		//just in case 
		if(e==null) {
			return false;
		}
		Node addNode=new Node(e);
		//If list is empty, we need a unique case
		if(head==null) {
			head=addNode;
			tail=addNode;
			head.prev=null;
			tail.next=null;
		}
		//Otherwise add to end!
		else {
			tail.next=addNode;
			addNode.prev=tail;
			tail=addNode;
			tail.next=null;
		}
		//increment list size!
		size=size+1;
		return true;
	}
	
	
	
	/**
	 * Second add method for this structure. 
	 * We add an element to the specified index and move subsequent elements one to the right
	 * @param e:type to be added | pos: index of insertion  
	 * @throws ClassCastException and IndexOutOfBoundsException if pos is unreasonable
	 *  @return boolean:true or false depending whether it was able to increment size
	 */
	public boolean add(E e,int pos)throws ClassCastException {
		//check
		if(e==null) {
			return false;
		}
		//index must be within our lists reach
		if(pos<0 || pos>this.size()){
			throw new IndexOutOfBoundsException("position "+pos+"is unreasonable");
		}
		Node addNode=new Node(e);
		//Unique case if list is empty
		if(head==null && pos==0) {
			head = addNode;
			tail = addNode;
		}
		//If not empty but index 0
		else if(pos==0){
			addNode.next = head;
			head.prev = addNode;
			head = addNode;
		}
		//If index is to 1+last index (end of list)
		//Notice here we call previous add method which adds to size
		//so we decrease size to maintain it
		else if(pos==this.size()) {
			add(e);
			size=size-1;
		}
		//otherwise, we add in somewhere in the middle
		//redirect references to move over subsequent elements to the right
		else {
			Node getHead = head;
			for (int i = 1; i < pos; i++) { 
				getHead = getHead.next;
			}
			addNode.next = getHead.next;
			getHead.next = addNode;
			addNode.prev = getHead;
			addNode.next.prev = addNode;
		}
		//increment size!
		size=size+1;
		return true;
	}
	
	
	/**
	 * This method clears (removes?) all the elements in the list 
	 * In my case, I realize this particular way is terrible for memory
	 * I did not set middle elements to null so now they're floating in memory
	 * waiting for garbage collector ):
	 */
	public void clear() {
		head=null;
		tail=null;
		size=0;
	}
	
	/**
	 * This method checks if specified element is inside it
	 * @param o: objects that we are looking for 
	 *  @return boolean:true or false depending if element was found
	 */
	public boolean contains(Object o) {
		Iterator<E> check = this.iterator();
		while(check.hasNext()) {
			E store=check.next();
			if(Objects.equals(o, store)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * This checks if two list are equal
	 * They are equal if they have same size, same type, and same elements in same order
	 * @param o: object we are checking/comparing
	*@return boolean: true or false 
	*/
		public boolean equals(Object o) {
			//check if null!
			if(o==null) {
				return false;
			}
			//check if same type!
			if(!(o instanceof DoublyLinkedList<?>)) {
				return false;
			}
			//cast o as list!
			DoublyLinkedList<E> tempList = (DoublyLinkedList<E>) o;
			//must be same size!
			if(tempList.size()!=this.size()) {
				return false;
			}
			//if they're both empty and different types
			if(tempList.size()==0 && this.size()==0) {
				return true;
			}
			
			//iterator to go through same size lists
			Iterator<E> check = this.iterator();
			Node oRef=tempList.head;
			Node thisRef=this.head;
			//start iteration
			while(check.hasNext()) {
				//if elements are not equal
				if(oRef.obj!=thisRef.obj) {
					return false;
					
				}
				//otherwise
				oRef=oRef.next;
				thisRef=thisRef.next;
				check.next();
			}
			return true;
		}
	
	
	/**
	 * This method searches for element at specified index 
	* It will find and return this element if it exists
	* @param pos: index of element we are looking for
	*@return an object:the corresponding element at pos
	*/
	public E get(int pos) {
		//checks that pos is reasonable
		if(pos<0 || pos>=this.size()) {
			return null;
		}
		//index is 0
		if(pos==0) {
			return head.obj;
		}
		//index is the last of list
		if(pos==this.size()-1) {
			return tail.obj;
		}
		//otherwise we search
		else { 
			Node getHead=head; 
			for (int i =1; i<pos; i++) { 
			getHead =getHead.next; 
			} 
			return getHead.next.obj; 
		} 
	}
	
	
	/**
	 * This method checks if list is empty
	*@return true of false depending if list size is 0
	*/
	public boolean isEmpty() {
		if(this.size()==0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * This method searches for the first occurrence of the specified element
	* @param o: element whose index we are looking for
	*@return the index of said element or -1 if not found
	*/
	public int indexOf(Object o) {
		int pos = 0;
        if (o == null) {
        	//go through list for any null elements
        	//however null is not allowed in list so really we will go through and return -1
            for (Node startRef=head; startRef != null; startRef =startRef.next) {
                if (startRef.obj == null)
                    return pos;
                pos++;
            }
            //go through list checking for lowest index of element o, if it exists
        } else {
            for ( Node startRef = head; startRef != null; startRef = startRef.next) {
                if (o.equals(startRef.obj))
                    return pos;
                pos++;
            }
        }
        //if it doesn't exist
        return -1;
	}
	
	
	/**
	 * This method removes the specified element
	 * More precisely, the first occurrence of the specified element and
	 * moves subsequent elements one to the left
	* @param o: element to be removed
	*@return boolean: true or false depending if size changes
	*/
	public boolean remove(Object o)throws ClassCastException {
		//No nulls!
		if(o==null) {
			throw new NullPointerException("bad input");
		}
		//Can't remove from empty list!
		if(this.size()==0) {
			return false;
		}
		//we call indexOf method to find element
		int index=indexOf(o);
		//If it exists we have unique cases
		if(index!=-1) {
			Node findO=head;
			//Unique case for if only one element in list
			if(this.size()==1) {
				head=null;
				tail=null;
				findO.obj=null;
				size--;
				return true;
			}
			//If more than one we go through list until we find element
			else {
				for(int i=0;i<index;i++) {
					findO=findO.next;
				}
				//get references
				Node getNext=findO.next;
				Node getPrev=findO.prev;
				//If element is at index 0
				if(getPrev==null && getNext!=null) {
					head=getNext;
					getNext.prev=getPrev;
					findO.next=null;
				}
				//if element is at end of list
				else if(getNext==null && getPrev!=null) {
					getPrev.next=getNext;
					findO.prev=null;
					tail=getPrev;
				}
				//otherwise its somewhere in the middle
				else {
					getPrev.next=getNext;
					findO.prev=null;
					getNext.prev=getPrev;
					findO.next=null;
				}
				//decrease size
				//set actual element to null
				size--;
				findO.obj=null;
				return true;
			}	
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * This method removes the element at the specified pos
	 * moves subsequent elements one to the left
	* @param pos: index of element to be removed
	*@return object/element: element that was removed
	*/
	public E remove(int pos) {
		//check that pos is reasonable
		if(pos<0 || pos>=this.size()){
			throw new IndexOutOfBoundsException("position "+pos+"is unreasonable");
		} 
		Node findO=head;
		//if only one element in list
		//then easy remove!
		if(this.size()==1) {
			final E getObj=findO.obj;
			head=null;
			tail=null;
			findO.obj=null;
			size--;
			return getObj;
		}
		//otherwise go through list until we find element
		else {
			for(int i=0;i<pos;i++) {
				findO=findO.next;
			}
			//store intial element before setting to null
			final E getObj=findO.obj;
			Node getNext=findO.next;
			Node getPrev=findO.prev;
			//unique case if element is at index 0
			if(getPrev==null && getNext!=null) {
				head=getNext;
				getNext.prev=getPrev;
				findO.next=null;
			}
			//unique case if element is at end of list
			else if(getNext==null && getPrev!=null) {
				getPrev.next=getNext;
				findO.prev=null;
				tail=getPrev;
			}//otherwise somewhere in middle
			else {
				getPrev.next=getNext;
				findO.prev=null;
				getNext.prev=getPrev;
				findO.next=null;
			}
			//decrease size
			size--;
			findO.obj=null;
			return getObj;
		}	
	}
	
	/**
	 * This method gets the size of this list
	*@return int: length of our list!
	*/
	public int size() {	
		return this.size;
	}
	
	
		

	/**
	 * This method formats the list into a nice string!
	 * Order should be in the order we added
	*@return String: a string representation of this structure
	*/
	@Override
	public String toString() {
		//Every list, even if empty starts with bracket
		String theString="[";
		Iterator<E> check = this.iterator();
		//while we have more elements
		while (check.hasNext()) {
			//turn element to Sting and add it to original string theString
			theString=theString+String.valueOf(check.next())+", ";
		}
		//We need to take care of that extra comma and space if the list has elements in it
		if(theString.length()>2) {
			theString=theString.substring(0,theString.length()-2);
		}
		return theString+"]";
	}
	
	
	/**
	 * This method is from the interface Iterable<E>
	 * It creates an Iterator for this list with designated methods
	*@return iterator
	*/
	@Override
	public Iterator<E> iterator() {
		Node node = new Node(null);
		node.next = head;
		Iterator<E> iter = new Iterator<E>() {        
			Node ptr = node;
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return ptr.next == null ? false : true; // returns false if ptr.next == null, true otherwise
			}
			
			@Override
			public E next() {
				// TODO Auto-generated method stub
				ptr = ptr.next;
				return ptr.obj;
			}	
		};
		return iter;
	}
	
	
	
}


