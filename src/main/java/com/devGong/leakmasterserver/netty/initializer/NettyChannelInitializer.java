package com.devGong.leakmasterserver.netty.initializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("nettyChannelInitializer")
public class NettyChannelInitializer extends ChannelInitializer<Channel> {


    private static final StringDecoder STRING_DECODER = new StringDecoder(CharsetUtil.UTF_8);
    private static final StringEncoder STRING_ENCODER = new StringEncoder(CharsetUtil.UTF_8);

    @Value("${netty.server.transfer.type}")
    private String transferType;
    @Value("${netty.server.transfer.maxContentLength}")
    private int transferMaxContentLength;
    @Value("${netty.server.transfer.websocket.path}")
    private String transferWebsocketPath;
    @Value("${netty.server.transfer.websocket.subProtocol}")
    private String transferWebsocketSubProtocol;
    @Value("${netty.server.transfer.websocket.allowExtensions}")
    private boolean transferWebsocketAllowExtensions;
    @Value("${netty.server.log.level.pipeline}")
    private String logLevelPipeline;

/*    @Autowired
    @Qualifier("socketHandler")
    private ChannelInboundHandlerAdapter websocketHandler;
*/
    @Autowired
    @Qualifier("jsonHandler")
    private ChannelInboundHandlerAdapter jsonHandler;

    /**
     * ?????? ??????????????? ??????.
     * Netty.Server.Configuration.NettyServerConfiguration ?????? ????????? Bean ??? ????????? ???????????? ????????? ????????? Handler ??? ??????.
     * Netty.Server.Handler.JsonHandler ?????? ?????? ????????? ?????? ??????.
     *
     * @param channel
     * @throws Exception
     */

    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline channelPipeline = channel.pipeline();

        switch (transferType) {
/*

            case "websocket":

                channelPipeline
                        .addLast(new HttpServerCodec())
                        .addLast(new HttpObjectAggregator(65536))
                        .addLast(new WebSocketServerCompressionHandler())
                        .addLast(new WebSocketServerProtocolHandler(transferWebsocketPath, transferWebsocketSubProtocol, transferWebsocketAllowExtensions))
                        .addLast(new LoggingHandler(LogLevel.valueOf(logLevelPipeline)))
                        .addLast(websocketHandler);
*/
            case "tcp":
            default:

                channelPipeline
                        .addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE))
                        .addLast(STRING_DECODER)
                        .addLast(STRING_ENCODER)
                        .addLast(new LoggingHandler(LogLevel.valueOf(logLevelPipeline)))
                        .addLast(jsonHandler);

        }
    }
}
