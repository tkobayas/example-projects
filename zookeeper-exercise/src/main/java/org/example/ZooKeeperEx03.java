package org.example;

import org.apache.zookeeper.ZooKeeper;


public class ZooKeeperEx03 {

    public static void main(String[] args) throws Exception {
        
        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, null);

        zk.delete("/mynode", -1);
        
        System.out.println("done");
        
        zk.close();
    }
}
