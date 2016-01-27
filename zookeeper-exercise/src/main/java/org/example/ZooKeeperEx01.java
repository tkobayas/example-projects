package org.example;

import static org.apache.zookeeper.ZooDefs.Ids.ANYONE_ID_UNSAFE;
import static org.apache.zookeeper.ZooDefs.Perms.ALL;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

public class ZooKeeperEx01 {

    public static void main(String[] args) throws Exception {
        
        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, null);
        List<ACL> acls = new ArrayList<ACL>();
        acls.add(new ACL(ALL, ANYONE_ID_UNSAFE));
        zk.create("/tmp/bbb", "xxx".getBytes(), acls, CreateMode.PERSISTENT);
    }
}
