package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
            Node first = new Node(5, null);
            NodeList nl = new NodeList(4);

            Node second = new Node(3, null);
            Node third = new Node(2, null);
            Node fourth = new Node(1, null);

            nl.add(first);
            nl.add(second);
            nl.add(third);
            nl.add(fourth);
            if (nl.contains(first)) {
                System.out.println(first.getObject());
            }
            System.out.println(nl.contains(second));
            nl.remove(first);
            nl.remove(second);
            if (nl.contains(first)) {
                System.out.println(first);
            }


//            NodeListBetter nlb = new NodeListBetter(6);
//            nlb.add(first);
//            nlb.add(second);
//            if (nlb.contains(first)) {
//                System.out.println(first);
//            }
//            System.out.println(nlb.contains(second));
//            System.out.println(nlb.contains(third));
    }
}