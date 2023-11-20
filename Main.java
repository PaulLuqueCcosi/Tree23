package Tree23;

import Tree23.src.DTATree23;
import Tree23.src.Tree23;
import Tree23.test.TestTree23Integer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting");
        DTATree23 treeInteger = new Tree23<Integer>();
        TestTree23Integer test = new TestTree23Integer(treeInteger);
        test.testAll();
        System.out.println("Finished");

    }

}
