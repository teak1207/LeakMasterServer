package com.devGong.leakmasterserver;

import com.devGong.leakmasterserver.netty.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeakMasterServerApplication {

    @Autowired
    NettyServer nettyServer;
    /**
     * 채팅 서버 프로그램 시작 지점입니다. Spring Boot 의 시작점이기도 합니다.
     * run() 에서 실제 프로그램이 시작됩니다.
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(LeakMasterServerApplication.class, args);
    }

    /**
     * CommandLineRunner 에 의해서 자동 실행되는 부분입니다.
     * 여기서 로그인에 사용할 테스트 데이터를 DB 에 입력하고 Netty Server 을 시작합니다.
     * Netty.Server.NettyServer 에서 start() 을 이용해서 서버를 시동합니다.
     *
     * @param
     * @throws Exception
     */
    public void run( ) throws  Exception{

        nettyServer.start();

    }



}
