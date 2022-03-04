package cn.edu.sjzc;

public class AVLTreePhBookTest {
    public static void main(String[] args) throws Exception {
        AVLTreePhBook phbook = new AVLTreePhBook();
        if (phbook.PhBInsert("Alice", (long) 1234562213)) {
            System.out.println("Success insert Alice: 1234562213");
        } else {
            throw new Exception("failed test!");
        }
        if (phbook.PhBInsert("Bob", 1234561122)) {
            System.out.println("Success insert Bob: 1234561122");
        } else {
            throw new Exception("failed test!");
        }
        if (!phbook.PhBInsert("Bob", 1234561122)) {
            System.out.println("Yes. cannot insert with same person");
        } else {
            throw new Exception("failed test!");
        }
        if (phbook.PhBDelete("Bob", 1234561122)) {
            System.out.println("Yes. Delete Bob");
        } else {
            throw new Exception("failed test!");
        }
        if (phbook.PhBSearch("Bob") == null) {
            System.out.println("Yes. Bob Cannot be found.");
        } else {
            throw new Exception("failed test!");
        }
        if (phbook.PhBSearch("Alice") != null && phbook.PhBSearch("Alice").getPhone() == 1234562213) {
            System.out.println("Yes. Alice Can be found. phone number is 1234562213.");
        } else {
            throw new Exception("failed test!");
        }
        if (phbook.PhBSearch(1234562213) != null && phbook.PhBSearch("Alice").getName() == "Alice") {
            System.out.println("Yes. Alice Can be found. phone number is 1234562213.");
        } else {
            throw new Exception("failed test!");
        }
    }

}