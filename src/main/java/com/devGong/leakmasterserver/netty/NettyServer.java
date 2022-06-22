package com.devGong.leakmasterserver.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class NettyServer {
//test
    @Autowired
    private ServerBootstrap serverBootstrap;
    @Autowired
    private InetSocketAddress port;

    private Channel channel;


    public void start() throws Exception {

        channel = serverBootstrap
                .bind(port)
                .sync()
                .channel()
                .closeFuture()
                .sync()
                .channel();

    }



    // 컨테이너에서 객체를 제거하기 전에 호출 될 메서드에 적용
    @PreDestroy
    public void stop() throws Exception {

        channel.close();
        channel.parent().close();
    }


}
