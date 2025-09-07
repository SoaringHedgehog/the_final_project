package entity;

public class Bus implements Comparable<Bus> {
    private final int number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public int getNumber() { return number; }

    public String getModel() { return model; }

    public int getMileage() { return mileage; }

    @Override
    public int compareTo(Bus other) {
        int result = Integer.compare(this.number, other.number);
        if (result != 0) return result;

        result = this.model.compareTo(other.model);
        if (result != 0) return result;

        return Integer.compare(this.mileage, other.mileage);
    }

    public static class Builder {
        private int number;
        private String model;
        private int mileage;

        public Builder setNumber(int number) {
            if (number < 0) throw new IllegalArgumentException("Номер автобуса не может быть меньше нуля");
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            if (model == null || model.isEmpty()) throw new IllegalArgumentException("Укажите модель");
            this.model = model;
            return this;
        }

        public Builder setMileage(int mileage) {
            if (mileage < 0) throw new IllegalArgumentException("Пробег не может быть меньше нуля");
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            if (number < 0 || model == null || mileage < 0) {
                throw new IllegalStateException("Необходимо указать номер, модель и пробег автобуса");
            }
            return new Bus(this);
        }
    }
}