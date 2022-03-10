package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 * 
 * @author Khushveen Kaur Umra
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }
  
  //Helper method (Finding node and offSet
  
  private class NodeInfo
  {
	  public int offS;
	  public Node node;
	  
	  public NodeInfo(Node node, int offS)
	  {
		  this.node = node;
		  this.offS = offS;
	  }
	  
	  public Node returnNode()
	  {
		  return node;
	  }
	  
	  public int returnOffset()
	  {
		  return offS;
	  }
	  
	  NodeInfo find(int pos)
	  {
		  Node current = head.next;
		  
		  int nextC = current.count;
		  
		  int previouss = 0;
		  
		  while((pos >= nextC && current.next != null))
		  {
			  previouss += current.count;
			  
			  current = current.next;
			  nextC += current.count;
		  }
		  
		  return new NodeInfo(current, pos - previouss);
	  }
  }

  @Override
  public int size()
  {
      return size;
  }
  
  @Override
  public boolean add(E item)
  {
       add(size, item);
       return true;
  }

  @Override
  public void add(int pos, E item)
  {
	  if (item == null)
	    {
	    	throw new NullPointerException();
	    }
	    
	    if(pos > size)
	    {
	    	throw new IllegalArgumentException();
	    }
	    
	    if(size == 0)
	    {
	    	Node current = head;
	        Node temp = new Node();
	        
	        current.next = temp;
	        temp.previous = current;
	        temp.next = tail;
	        
	        tail.previous = temp;
	        
	        temp.addItem(pos, item);
	        
	        size++;
	        
	        return;
	    }
	    
	    NodeInfo tempN = new NodeInfo(head, 0);
	    
	    tempN = tempN.find(pos);
	    
	    Node current = tempN.returnNode();
	    
	    int offS = tempN.returnOffset();
	    
	    if(offS == 0)
	    {
	    	if(current.previous.count != nodeSize && current.previous != head)
	    	{
	    		current = current.previous;
	    		current.addItem(item);
	    		
	    		size++;
	    		
	    		return;
	    	}
	    	
	    	if(current == tail && current.previous.count == nodeSize)
	    	{
	    		Node tempNode = new Node();
	    		Node lastNode = current.previous;
	    		
	    		tempNode.previous = lastNode;
	    		tempNode.next = current;
	    		
	    		current.previous = tempNode;
	    		
	    		lastNode.next = tempNode;
	    		
	    		tempNode.addItem(item);
	    		
	    		size++;
	    		return;
	    	}
	    }
	    
	    if(current.count < nodeSize)
	    {
	    	current.addItem(offS, item);
	    	
	    	size++;
	    	return;
	    }
	    
	    if(current.count >= nodeSize)
	    {
	    	Node tempNode = new Node();
	    	
	    	Node tempNext = current.next;
	    	
	    	tempNode.next = tempNext;
	    	
	    	current.next = tempNode;
	    	
	    	tempNode.previous = current;
	    	
	    	tempNext.previous = tempNode;
	    	
	    	while(tempNode.count != (nodeSize / 2))
	    	{
	    		tempNode.addItem(current.data[nodeSize / 2]);
	    		
	    		current.removeItem(nodeSize / 2);
	    	}
	    	
	    	if(offS <= (nodeSize / 2))
	    	{
	    		current.addItem(offS, item);
	    		size++;
	    		return;
	    	}
	    	
	    	if(offS > (nodeSize / 2))
	    	{
	    		tempNode.addItem(offS - (nodeSize / 2), item);
	    		
	    		size++;
	    		return;
	    	}
	    }
  }

  @Override
  public E remove(int pos)
  {
       if(pos >= size)
       {
    	   throw new IllegalArgumentException("Position is extremely large");
       }
       
       NodeInfo temp = new NodeInfo(head, 0);
       temp = temp.find(pos);
       
       Node current = temp.returnNode();
       
       int offS = temp.returnOffset();
       
       if(current.count == 1)
       {
    	   E itemT = current.data[offS];
    	   
    	   Node tempN = current.previous;
    	   
    	   current = current.next;
    	   
    	   tempN.next = current;
    	   
    	   current.previous = tempN;
    	   
    	   return itemT;
       }
       
       if((current.next == tail && current.previous == head) || (current.count > (nodeSize / 2)))
       {
    	   E itemT = current.data[offS];
    	   
    	   current.removeItem(offS);
    	   
    	   size--;
    	   return itemT;
       }
       
       if(current.count <= (nodeSize / 2))
       {
    	   Node thatNode = current.next;
    	   
    	   if(thatNode == null || thatNode == tail)
    	   {
    		   E itemT = current.data[offS];
    		   
    		   current.removeItem(offS);
    		   
    		   size--;
    		   
    		   return itemT;
    	   }
    	   
    	   if(thatNode.count > (nodeSize / 2))
    	   {
    		   
    		   E itemT = current.data[offS];
    		   
    		   current.removeItem(offS);
    		   
    		   current.addItem(thatNode.data[0]);
    		   
    		   thatNode.removeItem(0);
    		   
    		   size--;
    		   
    		   return itemT;
    	   }
    	   
    	   if(thatNode.count <= (nodeSize / 2))
    	   {
    		   
    		   E itemT = current.data[offS];
    		   
    		   current.removeItem(offS);
    		   
    		   size--;
    		   
    		   while(thatNode.count > 0)
    		   {
    			   current.addItem(thatNode.data[0]);
    			   thatNode.removeItem(0);
    		   }
    		   
    		   thatNode = thatNode.next;
    		   
    		   current.next = thatNode;
    		   
    		   thatNode.previous = current;
    		   
    		   return itemT;
    	   }
       }
       
       return null;
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  // TODO 
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  // TODO 
  }
  
  @Override
  public Iterator<E> iterator()
  {
      return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
       return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
       return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }
 
  private class StoutListIterator implements ListIterator<E>
  {
	// constants you possibly use ...   
	  
	  private Node current;
	  private Node removeCurrent;
	  private int removeOffS;
	  private int index;
	  private int pIndex;
	  private int offS;
	  private int poffS;
	  	  
	  
	// instance variables ... 
	  
    /**
     * Default constructor 
     */
    public StoutListIterator()
    {
    	current = head.next;
    	index = 0;
    	offS = 0;
    	pIndex = -1;
    	poffS = -1;
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    
    public StoutListIterator(int pos)
    {
    	this.index = pos;
    	
    	this.pIndex = index - 1;
    	
    	NodeInfo temp = new NodeInfo(head, 0);
    	
    	temp = temp.find(pos);
    	
    	Node curr = temp.returnNode();
    	
    	int curroffS = temp.returnOffset();
    	
    	this.current = curr;
    	this.offS = curroffS;
    	
    	if(this.offS > 0)
    	{
    		poffS = offS - 1;
    	}
    	
    	if(this.offS == 0)
    	{
    		if(current.previous != head)
    		{
    			Node tempPre = current.previous;
    			this.poffS = tempPre.count - 1;
    		}
    	}
    	
    }

    @Override
    public boolean hasNext()
    {
    	if(offS < current.count && current.data[offS] != null)
    	{
    		return true;
    	}
    	
    	else if(current.next != null || current != tail)
    	{
    		Node temp = current.next;
    		
    		if(temp.count > 0)
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }

    @Override
    public E next()
    {
    	if( ! hasNext())
    	{
    		throw new NoSuchElementException();
    	}
    	
    	if(index > size)
    	{
    		throw new RuntimeException();
    	}
    	
    	if(current.count <= offS)
    	{
    		current = current.next;
    		offS = 0;
    		pIndex = index;
    		
    		index++;
    		
    		poffS = offS;
    		removeCurrent = current;
    		removeOffS = offS;
    		
    		return current.data[offS++];
    		
    	}
    	
    	return null;
    }

    @Override
    public void remove()
    {
    	if(pIndex >= size)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	if(removeCurrent == null)
    	{
    		throw new IllegalStateException();
    	}
    	
    	if(removeCurrent.count == 1)
    	{
    		Node tempN = removeCurrent.previous;
    		removeCurrent = removeCurrent.next;
    		
    		tempN.next = removeCurrent;
    		removeCurrent.previous = tempN;
    		
    		if(removeOffS <= offS)
    		{
    			offS--;
    			index--;
    			poffS--;
    			pIndex--;
    			
    		}
    		
    		size--;
    		
    		removeCurrent = null;
    		return;
    	}
    	
    	if((removeCurrent.next == tail && removeCurrent.previous == head) || (removeCurrent.count > (nodeSize / 2)))
    	{
    		removeCurrent.removeItem(removeOffS);
    		size--;
    		
    		if(removeOffS < offS && offS > 0)
    		{
    			offS--;
    			index--;
    			poffS--;
    			pIndex--;
    		}
    		
    		removeCurrent = null;
    		return;
    	}
    	
    	if(removeCurrent.count <= (nodeSize / 2))
    	{
    		Node thatNode = removeCurrent.next;
    		
    		if(thatNode == null || thatNode == tail)
    		{
    			removeCurrent.removeItem(removeOffS);
    			size--;
    			
    			if(removeOffS < offS && offS > 0)
    			{
    				offS--;
    				index--;
    				poffS--;
    				pIndex--;
    			}
    			
    			removeCurrent = null;
    			return;
    		}
    		
    		if(thatNode.count > (nodeSize / 2))
    		{
    			removeCurrent.removeItem(removeOffS);
    			removeCurrent.addItem(thatNode.data[0]);
    			thatNode.removeItem(0);
    			size--;
    			
    			if(removeOffS < offS && offS > 0)
    			{
    				offS--;
    				index--;
    				poffS--;
    				pIndex--;
    			}
    			
    			removeCurrent = null;
    			return;
    		}
    		
    		if(thatNode.count <= (nodeSize / 2))
    		{
    			removeCurrent.removeItem(removeOffS);
    			size--;
    			
    			while(thatNode.count > 0)
    			{
    				removeCurrent.addItem(thatNode.data[0]);
    				thatNode.removeItem(0);
    			}
    			
    			thatNode = thatNode.next;
    			
    			removeCurrent.next = thatNode;
    			
    			thatNode.previous = removeCurrent;
    			
    			if(removeOffS < offS && offS >0)
    			{
    				offS--;
    				index--;
    				poffS--;
    				pIndex--;
    			}
    			
    			removeCurrent = null;
    			return;
    		}
    	}
    }

	@Override
	public void add(E arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(E e) {
		// TODO Auto-generated method stub
		
	}
    
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.

  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  // TODO
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  // TODO
  }
 

}
