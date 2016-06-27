package org.example;

import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperEx02 {

    public static void main(String[] args) throws Exception {
        
        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, null);

        byte[] data = zk.getData("/mynode", false, null);
        System.out.println("Content of /mynode : " + new String(data));
        
        zk.close();
    }
}
