package sorting;

import java.util.Comparator;

public class DescendingOrder implements Comparator<String> {

    @Override
    public int compare(String product1, String product2) {
        return product2.compareTo(product1);
    }
}
