package org.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;


public class ZooKeeperEx01 {

    public static void main(String[] args) throws Exception {
        
        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, null);

        zk.create("/mynode", "bbb".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        
        System.out.println("done");
        
        zk.close();
    }
}
