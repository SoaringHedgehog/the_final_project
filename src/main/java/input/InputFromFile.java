package input;

public class InputFromFile {

    public static boolean validateBus(String number, String model, String mileageStr) {
        if (number == null || number.isEmpty() || model == null || model.isEmpty()) {
            return false;
        }
        try {
            int mileage = Integer.parseInt(mileageStr);
            if (mileage < 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateUser(String name, String email, String password) {
        if (name == null || name.isEmpty()) return false;
        if (email == null || email.isEmpty() || !email.contains("@")) return false;
        if (password == null || password.isEmpty()) return false;
        return true;
    }

    public static boolean validateStudent(String groupNumber, double averageScore, String recordBookNumber) {
        if (groupNumber == null || groupNumber.isEmpty()) return false;
        if (averageScore < 0 || averageScore > 5) return false;
        if (recordBookNumber == null || recordBookNumber.isEmpty()) return false;
        return true;
    }
}
