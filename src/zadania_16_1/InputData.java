package zadania_16_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputData {


    public Map readData() throws IOException {
        Map<Integer, Order> orders = new LinkedHashMap<>();


        FileReader fileReader = null;
        try {
            fileReader = new FileReader("data.csv ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        BufferedReader buffRead = new BufferedReader(fileReader);
        String line = null;

        while ((line = buffRead.readLine()) != null) {
            String[] split = line.split(";");
            int id = Integer.valueOf(split[0]);
            String name = split[1];
            double price = Double.valueOf(split[2]);
            OrderType order = OrderType.valueOf(split[3]);
            orders.put(id, new Order(id, name, price, order));
        }
        buffRead.close();
        return orders;
    }
}
