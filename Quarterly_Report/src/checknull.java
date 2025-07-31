
public class checknull {
	public static void main(String[] args) {
        // Example Integer array with some elements as null
        Integer[] numbers = {1, null, 3, null, 5};

        // Check if the elements in the array are null or not
        for (Integer num : numbers) {
            if (num == null) {
                System.out.println("Element is null");
            } else {
                System.out.println("Element is not null: " + num);
            }
        }
    }
}
