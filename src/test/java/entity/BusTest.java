package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusTest {

    @Test
    void builderCreatesBusCorrectly() {
        Bus bus = new Bus.Builder()
                .setNumber(10)
                .setModel("Golden Dragon")
                .setMileage(50000)
                .build();

        assertEquals(10, bus.getNumber());
        assertEquals("Golden Dragon", bus.getModel());
        assertEquals(50000, bus.getMileage());
    }

    @Test
    void builderThrowsExceptionOnInvalidNumber() {
        Bus.Builder builder = new Bus.Builder().setModel("Golden Dragon").setMileage(10000);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setNumber(-1);
        });
        assertEquals("Номер автобуса не может быть меньше нуля", exception.getMessage());
    }

    @Test
    void builderThrowsExceptionOnEmptyModel() {
        Bus.Builder builder = new Bus.Builder().setNumber(1).setMileage(10000);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setModel("");
        });
        assertEquals("Укажите модель", exception.getMessage());
    }

    @Test
    void builderThrowsExceptionOnNegativeMileage() {
        Bus.Builder builder = new Bus.Builder().setNumber(1).setModel("Golden Dragon");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setMileage(-10);
        });
        assertEquals("Пробег не может быть меньше нуля", exception.getMessage());
    }

    @Test
    void compareToWorksCorrectly() {
        Bus b1 = new Bus.Builder().setNumber(1).setModel("Golden Dragon").setMileage(10000).build();
        Bus b2 = new Bus.Builder().setNumber(2).setModel("Mercedes").setMileage(5000).build();
        Bus b3 = new Bus.Builder().setNumber(1).setModel("Golden Dragon").setMileage(20000).build();

        assertTrue(b1.compareTo(b2) < 0);  // number сравнивается
        assertTrue(b2.compareTo(b1) > 0);
        assertTrue(b1.compareTo(b3) < 0);  // number и model равны, mileage сравнивается
    }
}
