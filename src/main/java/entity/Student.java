package entity;

public class Student implements Comparable<tasks.astonproject.Student> {

    private final int groupNumber;
    private final double averageScore;
    private final String gradeBookNumber;

    private Student(tasks.astonproject.Student.Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.gradeBookNumber = builder.gradeBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getGradeBookNumber() {
        return gradeBookNumber;
    }

    @Override
    public int compareTo(tasks.astonproject.Student student) {
        return Integer.compare(this.groupNumber, student.groupNumber);
    }

    public static class Builder {
        private int groupNumber;
        private double averageScore;
        private String gradeBookNumber;

        public setGroupNumber(int groupNumber) {
            if (groupNumber < 0) throw new IllegalArgumentException("Номер группы должен быть положительным");
            this.groupNumber = groupNumber;
            return this;
        }

        public  setAverageScore(double averageScore) {
            if (averageScore < 0 || averageScore > 5) throw new IllegalArgumentException("Средний балл должен быть в диапазоне от 0 до 5");

            this.averageScore = averageScore;

            return this;
        }

        public setRecordBookNumber(String gradeBookNumber) {

            if (gradeBookNumber == null || gradeBookNumber.isEmpty())
                throw new IllegalArgumentException("Укажите номер зачетной книжки");
            this.gradeBookNumber = gradeBookNumber;

            return this;
        }


        public Student build() {
            if (gradeBookNumber == null || gradeBookNumber.isEmpty() || groupNumber < 0 || averageScore < 0 || averageScore > 5) {
                throw new IllegalStateException("Необходимо указать номер зачетной книжки, положительный номер группы и средний балл 0-5");
            }
            return new Student(this);
        }
    }
