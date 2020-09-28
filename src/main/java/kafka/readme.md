-----kafaka----

    1. broker:Kafka 服务器，负责消息存储和转发
    2. topic:消息类别，Kafka 按照 topic 来分类消息
    3. partition:topic 的分区，一个 topic 可以包含多个 partition，topic 消息保存在各个
    partition 上
    4. offset:消息在日志中的位置，可以理解是消息在 partition 上的偏移量，也是代表该消息的
    唯一序号
    5. Producer:消息生产者
    6. Consumer:消息消费者
    7. Consumer Group:消费者分组，每个 Consumer 必须属于一个 group
    8. Zookeeper:保存着集群 broker、topic、partition 等 meta 数据;另外，还负责 broker 故
    障发现，partition leader 选举，负载均衡等功能
    
    
----kafka特性
    高吞吐量。低延迟
    可扩展性（kafka集群支持热扩展）
    持久性。可靠性（消息被持久化到磁盘，并且支持数据备份防止数据丢失）
    容错性（允许集群的节点失败  副本数量为n，则允许n-1个节点失败）
    高并发（支持千万个客户端读写）


-----使用场景
    1.日志收集， 
    2.消息系统
    3.用户活动追从
    4.运营指标
    5.流失处理
    

-----技术优势
    可伸缩性
    容错性
    
    
-----
0.9版本之前offest存储在zk
0.9版本以后offest存储在本地


----
kakfa两种模式

        点对点的模式（消费者主动拉取）：
        处理后的消息就消失了
        
        发布订阅模式：
          1：消费者主动拉取模式
            好处 速度自己控制
            坏出 一直去拉取轮训
          
          
          2：推送模式
            消费者消费的速度不一样 （如图）
        

 启动zk  (-daemon 守护进程运行)
> bin/zookeeper-server-start.sh  -daemon config/zookeeper.properties

启动kafka
>> bin/kafka-server-start.sh -daemon config/server.properties

创建一个 topic
> bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
>



文件存储---
    kakfa存的实际数据命名为  topic-分区 （如图）
        0000.log存储的是数据
        0000.index是索引
    
    通过二分查找法找到index ，通过index找到你要访问消息的数据，根据数据的内容再去log里面吧数据定位出来。
    
    
kafka生产者-----

    分区策略
        1.-分区的原因
            方便集群的扩展 ，提高负载能力
            提高并发
        
        
        2.分区的原则
            我们需要将producer发送的数据封装成一个producerrecord对象
            
            1）指定partition的情况下，指定将致命的值作为partiton
            2）没有partiton值但是有key的情况下，将key的hash值聿topic的partiton数取余得到partiton
            3）前两个都没有 ，轮训 round-robin算法
    
    数据可靠性保证（如图-----）
        为保证producer发送的数据，能发送到指定的topic，topic的每个partiton收到producer发送的数据后，都需要向producer发送ack确认收到。如果producer收到ack
        ，就会进行下一轮的发送，否则重新发送数据
        ~~~~~~~~
        
        副本同步策略：
        
            方案一：
                半数以上同步发送ack。优点：延迟低。缺点：容忍n台故障，需要2n+1个副本
            方案二：
                全部完成同步，发送ack。优点：容忍n台节点故障，需要n+1个副本  缺点：延迟高
                
                
                采取第二种方案以后：
                    ISR
                    可查看isr的命令：
                    bin/kafka-topics.sh --describe --topic test --zookeeper localhost:2181 
                    使用isr选择选择副本级。
                    当isr中的follow同步完成以后，leader会向follower发送ack，如果follower长时间（时间的阙值由replica..lag.max.ms）为向leader同步数据则呗isr踢出
                
                3,acks应答机制
                    0：producer不等待broker的ack
                    1：producer等待boker的ack，partition的leader落盘后成功返回ack，如果在follower同步成功之前leader故障（值等待leader写完 不等待follow写完就返回）
                    -1：producer等待broker的ack，parititon的leader和follower全部落盘成功后才返回ack。但是如果在follower同步完成后，broker发送ack之前，leader发生故障
                    ，会造成数据重复
                    ----为啥数据重复-
    
    数据一致性问题
        
            
        
    
    
       