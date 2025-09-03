package entity;

public class Bus {
    private String number;
    private String model;
    private int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public int getMileage() { return mileage; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String number;
        private String model;
        private int mileage;

        public Builder setNumber(String number) { this.number = number; return this; }
        public Builder setModel(String model) { this.model = model; return this; }
        public Builder setMileage(int mileage) { this.mileage = mileage; return this; }

        public Bus build() {
            // тут можно добавить валидацию
            if (number == null || model == null) {
                throw new IllegalStateException("Номер и модель автобуса обязательны");
            }
            return new Bus(this);
        }
    }
}
