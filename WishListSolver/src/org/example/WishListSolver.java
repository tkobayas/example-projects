package org.example;
import java.util.ArrayList;
import java.util.List;

import org.drools.planner.config.SolverFactory;
import org.drools.planner.config.XmlSolverFactory;
import org.drools.planner.core.Solver;


public class WishListSolver {

    public static void main(String[] args) {
        // Build the Solver
        SolverFactory solverFactory = new XmlSolverFactory(
                "/org/example/wishListSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        // setup
        WishListSolution unsolvedSolution = new WishListSolution();
        Item item1 = new Item("北欧風フリース袖付きベスト 80cm", 1050);
        Item item2 = new Item("おかゆこがま 200ml", 1015);
        Item item3 = new Item("トロンプルイユT風ボディ/70", 3045);
        Item item4 = new Item("ベビーソファ 専用腰ベルト入り", 3670);
        Item item5 = new Item("スヌーピーストローマグ", 1260);
        Item item6 = new Item("6重ガーゼケット トドラーケット", 6300);
        List<Item> itemList = new ArrayList<Item>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);
        unsolvedSolution.setItemList(itemList);
        Bucket cart = new Bucket(true);
        Bucket dummy = new Bucket(false);
        List<Bucket> bucketList = new ArrayList<Bucket>();
        bucketList.add(cart);
        bucketList.add(dummy);
        unsolvedSolution.setBucketList(bucketList);

        // Solve the problem
        solver.setPlanningProblem(unsolvedSolution);
        solver.solve();
        WishListSolution solvedSolution = (WishListSolution) solver.getBestSolution();

        // Display the result
        System.out.println("============= Best Solution =============");
        int total = 0;
        for (Item item : solvedSolution.getItemList()) {
            if (item.isInCart() == true) {
                System.out.println(item.getName() + " : price = " + item.getPrice());
                total += item.getPrice();
            }
        }
        System.out.println("Total = " + total);
    }
}
