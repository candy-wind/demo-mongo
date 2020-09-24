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
