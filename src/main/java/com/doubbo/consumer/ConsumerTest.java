package com.doubbo.consumer;

import com.dubbo.producer.ProducerInterface;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 讲一下我的理解
 *
 * 首先生产者需要将接口以及实现类放在application容器里面，通过在xml中配置dubbo:service，
 * 服务走dubbo协议将一些接口类的一些信息注册到zookeeper中，接口类里面的方法，应用名称，版本号之类的
 * 之所以有接口类，是因为我引入的时候，只需要引入接口类就可以了，避免了服务代码的暴露，另外在xml里面配置具体的实现类，我的潜意识感觉，他也会最后根据application转换成实现类
 * dubbo://192.168.1.104:29014/com.dubbo.producer.ProducerInterface?anyhost=true&application=prodcer&dubbo=2.5.3&interface=com.dubbo.producer.ProducerInterface&methods=getName&pid=2388&revision=1.0-SNAPSHOT&side=provider&timestamp=1523674552799
 *
 * 消费者也需要初始化一个容器，连接zk，通过dubbo:reference配置发送dubbo协议，将配置的一些信息读取处理来
 * 然后通过容器和jar包的引入去实现具体的逻辑。
 * consumer://192.168.1.104/com.dubbo.producer.ProducerInterface?application=consumer&category=consumers&check=false&dubbo=2.5.3&interface=com.dubbo.producer.ProducerInterface&methods=getName&pid=8772&revision=1.0-SNAPSHOT&side=consumer&timestamp=1523676120360
 *
 * Created by lizhen on 2018/4/14.
 */
public class ConsumerTest {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        classPathXmlApplicationContext.start();
        ProducerInterface producerImplement = (ProducerInterface) classPathXmlApplicationContext.getBean("ProducerInter");
        String name = producerImplement.getName("1");
        System.out.println(name);

        System.in.read();

    }
}
