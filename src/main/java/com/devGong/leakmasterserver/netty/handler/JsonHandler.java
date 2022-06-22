package com.devGong.leakmasterserver.netty.handler;


import com.devGong.leakmasterserver.service.PreInstallChkService;
import com.devGong.leakmasterserver.service.PreInstallSetService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("jsonHandler")
@ChannelHandler.Sharable
public class JsonHandler extends SimpleChannelInboundHandler<String> {

    @Autowired
    private PreInstallChkService  preInstallChkService;
    @Autowired
    private PreInstallSetService preInstallSetService;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

        String result;
        // 접속자 채널 정보(연결 정보)
        Channel channel = ctx.channel();

    }
}
