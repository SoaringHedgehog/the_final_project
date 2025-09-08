package entity;


public class Student implements Comparable<Student> {

    private final int groupNumber;
    private final double averageScore;
    private final int gradeBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.gradeBookNumber = builder.gradeBookNumber;
    }

    public int getGroupNumber() { return groupNumber; }

    public double getAverageScore() { return averageScore; }

    public int getGradeBookNumber() { return gradeBookNumber; }

    @Override
    public int compareTo(Student other) {
        int result = Integer.compare(this.groupNumber, other.groupNumber);
        if (result != 0) return result;

        result = Double.compare(this.averageScore, other.averageScore);
        if (result != 0) return result;

        return Integer.compare(this.gradeBookNumber, other.gradeBookNumber);
    }

    public static class Builder {
        private int groupNumber;
        private double averageScore;
        private int gradeBookNumber;

        public Builder setGroupNumber(int groupNumber) {
            if (groupNumber <= 0) throw new IllegalArgumentException("Номер группы должен быть положительным");
            this.groupNumber = groupNumber;
            return this;
        }
        public Builder setAverageScore(double averageScore) {
            if (averageScore < 0 || averageScore > 5)
                throw new IllegalArgumentException("Средний балл должен быть в диапазоне от 0 до 5");
            this.averageScore = averageScore;
            return this;
        }
        public Builder setGradeBookNumber(int gradeBookNumber) {
            if (gradeBookNumber <= 0)
                throw new IllegalArgumentException("Номер зачетной книжки должен быть положительным");
            this.gradeBookNumber = gradeBookNumber;
            return this;
        }
        public Student build() {
            if (groupNumber <= 0 || averageScore < 0 || averageScore > 5 || gradeBookNumber <= 0) {
                throw new IllegalStateException("Необходимо указать положительный номер группы, номер зачетной книжки и средний балл 0-5");
            }
            return new Student(this);
        }
    }
}
-
