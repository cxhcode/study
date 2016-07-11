package com.chenxinhua.study.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author chenxinhua
 * @date 2016/6/30 17:54
 */
public class Main {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        System.out.println("1 | 4 = " + (5 ^ 3));
        ZooKeeper zk = new ZooKeeper("localhost:" + 2181, 1000000, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("event = " + event.getType());
            }
        });

        // 删除子目录节点

        // 创建一个目录节点
        zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        // 创建一个子目录节点
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath",false,null)));
        zk.setData("/testRootPath","modifyParent".getBytes(),-1);

        // 取出子目录节点列表
        System.out.println(zk.getChildren("/testRootPath",true));
        // 修改子目录节点数据
        zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1);
        System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]");
        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null)));
        for (String s : zk.getChildren("/testRootPath", true)) {
            System.out.println(s);

        }
        Thread.sleep(1000000L);
        // 删除子目录节点
        zk.delete("/testRootPath/testChildPathTwo",-1);
        zk.delete("/testRootPath/testChildPathOne",-1);
        // 删除父目录节点
        zk.delete("/testRootPath",-1);
        // 关闭连接
        zk.close();

    }
}
