Exercise 1

$ cd $THIS_PROJECT/zookeeper
$ cd conf
$ cp zoo_sample.cfg zoo.cfg
$ mkdir /tmp/zookeeper
$ cd ../bin
$ ./zkServer.sh start
$ ./zkCli.sh
...
[zk: localhost:2181(CONNECTED) 0] ls /
[zookeeper]
[zk: localhost:2181(CONNECTED) 1] create /tmp content
Created /tmp
[zk: localhost:2181(CONNECTED) 2] ls /
[zookeeper, tmp]
[zk: localhost:2181(CONNECTED) 3] get /tmp
content
cZxid = 0x2
...

Run ZooKeeperEx01

[zk: localhost:2181(CONNECTED) 4] get /tmp/bbb
xxx
cZxid = 0x4
...
