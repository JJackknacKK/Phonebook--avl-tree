package cn.edu.sjzc;

public class AVLTreePhBook {

    // name is key, phone is value
    class PhBookWithName implements Comparable<PhBookWithName> {
        String name;
        long phone;

        public PhBookWithName(String name, long phone) {
            this.name = name;
            this.phone = phone;
        }

        public long getPhone() {
            return this.phone;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public int compareTo(PhBookWithName other) {
            return name.compareTo(other.name);
        }
    }

    // phone is key, name is value
    class PhBookWithPhone implements Comparable<PhBookWithPhone> {
        long phone;
        String name;

        public PhBookWithPhone(long phone, String name) {
            this.name = name;
            this.phone = phone;
        }

        public long getPhone() {
            return this.phone;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public int compareTo(PhBookWithPhone other) {
            if (this.phone < other.phone)
                return -1;
            if (this.phone > other.phone)
                return 1;
            return 0;
        }

    }

    private AVLTree<PhBookWithName> phbookNameTree;
    private AVLTree<PhBookWithPhone> phbookPhoneTree;

    public AVLTreePhBook() {
        phbookNameTree = new AVLTree<PhBookWithName>();
        phbookPhoneTree = new AVLTree<PhBookWithPhone>();
    }

    // time complexity: o(logn)
    public boolean PhBInsert(String name, long phone) {
        PhBookWithName phbookWithName = new PhBookWithName(name, phone);
        if (!phbookNameTree.insert(phbookWithName)) {
            return false;
        }
        PhBookWithPhone phbookWithPhone = new PhBookWithPhone(phone, name);
        if (!phbookPhoneTree.insert(phbookWithPhone)) {
            return false;
        }
        return true;
    }

    // time complexity: o(logn)
    public boolean PhBDelete(String name, long phone) {
        PhBookWithName phbookWithName = new PhBookWithName(name, phone);
        PhBookWithPhone phbookWithPhone = new PhBookWithPhone(phone, name);
        return phbookNameTree.remove(phbookWithName) && phbookPhoneTree.remove(phbookWithPhone);
    }

    // time complexity: o(logn)
    public PhBookWithName PhBSearch(String name) {
        PhBookWithName tmp = new PhBookWithName(name, 0);
        AVLTreeNode<PhBookWithName> result = phbookNameTree.search(tmp);
        if (result == null)
            return null;
        return result.key;
    }

    // time complexity: o(logn)
    public PhBookWithPhone PhBSearch(long phone) {
        PhBookWithPhone tmp = new PhBookWithPhone(phone, "");
        AVLTreeNode<PhBookWithPhone> result = phbookPhoneTree.search(tmp);
        if (result == null)
            return null;
        return result.key;
    }

}