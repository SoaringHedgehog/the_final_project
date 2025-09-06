package entity;

public class Bus implements Comparable<Bus>{
    private final String number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder){
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public int getMileage() { return mileage; }

    @Override
    public int compareTo(Bus bus){
        return this.number.compareTo(bus.number);
    }

    public static class Builder {
        private String number;
        private String model;
        private int mileage;

        public Builder setNumber(String number) {
            if (number == null || number.isEmpty()) throw new IllegalArgumentException("Укажите номер");
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
            if (number == null || model == null || mileage < 0) {
                throw new IllegalStateException("Необходимо указать номер, модель и пробег автобуса");
            }
            return new Bus(this);
        }
    }
}