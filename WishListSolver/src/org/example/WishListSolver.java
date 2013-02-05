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
        Item item1 = new Item("aaa", 1000);
        Item item2 = new Item("bbb", 8000);
        Item item3 = new Item("ccc", 5000);
        Item item4 = new Item("ddd", 15000);
        List<Item> itemList = new ArrayList<Item>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
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
        for (Item item : solvedSolution.getItemList()) {
            if (item.isInCart() == true) {
                System.out.println(item.getName() + " : price = " + item.getPrice());
            }
        }
    }
}
