

import src.DTATree23;
import src.Tree23;
import test.TestTree23Integer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting");
        DTATree23 treeInteger = new Tree23<Integer>();
        // TestTree23Integer test = new TestTree23Integer(treeInteger);
        // test.testAll();
        treeInteger.insert(10);
        treeInteger.insert(5);
        treeInteger.insert(15);
        treeInteger.insert(20);
        System.out.println(treeInteger.inOrder());


        treeInteger.insert(5);
        System.out.println("Finished");

    }

}
