package entity;

public class Student {
    private String groupNumber;
    private double averageScore;
    private String recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String groupNumber;
        private double averageScore;
        private String recordBookNumber;

        public Builder setGroupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder setAverageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder setRecordBookNumber(String recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            if (groupNumber == null || groupNumber.isEmpty()) {
                throw new IllegalStateException("Номер группы не может быть пустым");
            }
            if (averageScore < 0 || averageScore > 5) {
                throw new IllegalStateException("Средний балл должен быть в диапазоне 0-5");
            }
            if (recordBookNumber == null || recordBookNumber.isEmpty()) {
                throw new IllegalStateException("Номер зачетной книжки не может быть пустым");
            }
            return new Student(this);
        }
    }
}
