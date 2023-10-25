package sorting;

import java.util.Comparator;

public class AscendingOrder implements Comparator<String> {

    @Override
    public int compare(String product1, String product2) {
        return product1.compareTo(product2);
    }

}
