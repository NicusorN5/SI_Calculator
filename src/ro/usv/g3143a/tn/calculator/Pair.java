package ro.usv.g3143a.tn.calculator;

/**
 * Product type between T and U.
 * @param <T> First type.
 * @param <U> Second type.
 */
public class Pair<T, U> {

	//Getters and setters weren't used, as there's no side effects to R/W-ing T or U.
	//This was created since java.utils doesn't have a Pair<T,U> type, and writing one is a 2-minute task.
	
	/**
	 * First type.
	 */
	public T First;
	/**
	 * Second type.
	 */
	public U Second;
	
	/**
	 * Type constructor.
	 * @param first First value.
	 * @param second Second value.
	 */
	public Pair(T first, U second) {
		First = first;
		Second = second;
	}

}
