package main;

import java.util.Comparator;

@SuppressWarnings("hiding")
public class DList <E>
{
	
	private class Node<E>
	{
		private E data;
		private Node<E> next;
		
		public Node(E data, Node<E> next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private int size;
	
	public DList()
	{
		head = null;
		size = 0;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean add(E item)
	{
		if(head == null)
		{
			head = new Node<E>(item, null);
			size++;
			return true;
		}
		else
		{
			Node<E> p = head;		
			while(p.next != null)
			{
				p = p.next;
			}
			p.next = new Node<E>(item, null);
			size++;
			return true;
		}
	}
	
	public boolean remove(int index)
	{
		if(index == 0)
		{
			head = head.next;
			size--;
			return true;
		}
		else
		{
			Node<E> p = head;
			int cnt = 0;
			while(p.next != null)
			{
				if(cnt == index - 1)
				{
					p.next = p.next.next;
					size--;
					return true;
				}
				p = p.next;
				cnt++;
			}
		}
		return false;
	}
	
	public E get(int index)
	{
		Node<E> p = head;
		int pos = 0;
		if(index == 0)
		{
			return head.data;
		}
		while(pos < index)
		{
			p = p.next;
			pos++;
		}
		return p.data;
	}
	
	public DList<E> clone()
	{
		DList<E> t = this;
		DList<E> temp = new DList<E>();
		
		for(int i = 0; i < size; i++)
		{
			temp.add(t.get(i));
		}
		
		return temp;
	}
	
	private void clear()
	{
		head = null;
		size = 0;
	}
	
	public void sort(Comparator<E> c)
	{
		DList<E> temp = this.clone();
		this.clear();	
		
		while (temp.size() > 0)
		{
			if(this.insert(temp.get(0), c))
			{
				temp.remove(0);
			}
		}
	}	
	
	private boolean insert(E item, Comparator<E> c)
	{
		if(head == null)
		{ 
			//case new list
	        head = new Node<E>(item, null);
	        size++;
	        return true;
	    }
		else if(c.compare(item, head.data) > 0)
		{ 
			// case insert before head
	        head = new Node<E>(item, head);
	        size++;
	        return true; 
	    }
		else
		{
	        Node<E> p; 
	        for(p = head; p.next != null; p = p.next)
	        {
	        	//compare all except head
	           if(c.compare(item, p.next.data) > 0)
	           {
	              p.next = new Node<E>(item, p.next);
	              size++;
	              return true;
	           }
	         }
	         //not found: insert at the end
	         p.next = new Node<E>(item, null);
	         size++;
	         return true;
	     }
	}
}
