package demo.homework;

public interface Condition<E> {
	
	boolean needed(E element);
	
	boolean apply(E element);

}
