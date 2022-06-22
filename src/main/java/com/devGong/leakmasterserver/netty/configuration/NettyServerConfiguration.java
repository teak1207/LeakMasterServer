package com.devGong.leakmasterserver.netty.configuration;


import com.devGong.leakmasterserver.netty.initializer.NettyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class NettyServerConfiguration {

    @Value("tcp")
    private String transferType;
    @Value("9812")
    private int transferPort;
    @Value("1")
    private int threadCountBoss;
    @Value("1")
    private int threadCountWorker;
    @Value("ERROR")
    private String logLevelBootstrap;

    @Autowired
    private NettyChannelInitializer nettyChannelInitializer;


    /**
     * Netty Server 의 Boss Thread 설정.
     * 추후 http, udp 등의 설정이 필요할 경우 case 을 추가하여 설정을 변경할 수 있다.
     *
     * @return
     */
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {

        switch (transferType) {

            case "tcp":
            default:
                return new NioEventLoopGroup(threadCountBoss);
        }

    }

    /**
     * Netty Server 의 Worker Thread 설정.
     * 추후 http, udp 등의 설정이 필요할 경우 case 을 추가하여 설정을 변경할 수 있다.
     *
     * @return
     */
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {

        switch (transferType) {
            case "tcp":
            default:
                return new NioEventLoopGroup(threadCountWorker);
        }
    }

    @Bean
    public InetSocketAddress port() {
        return new InetSocketAddress(transferPort);
    }

    /**
     * Netty ServerBootStrap 설정.
     * LogLevel 을 지정해주고 사용자의 입력을 처리해줄 Handler 을 등록해주는데, Netty.Server.Initializer.NettyChannelInitializer 을 통해 이를 설정해준다.
     * 그리고 Transfer Type 에 따른 channel 을 등록해준다.
     */
    @Bean
    public ServerBootstrap serverBootstrap() {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup(), workerGroup())
                .handler(new LoggingHandler(LogLevel.valueOf(logLevelBootstrap)))
                .childHandler(nettyChannelInitializer);

        switch (transferType) {

//          case "websocket":
            case "tcp":
            default:

                serverBootstrap.channel(NioServerSocketChannel.class);

        }

        return serverBootstrap;

    }


}
