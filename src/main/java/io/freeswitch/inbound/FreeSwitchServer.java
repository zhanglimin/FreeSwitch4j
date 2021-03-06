/*
 * Copyright 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.freeswitch.inbound;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Entry point to run a socket client that a running FreeSWITCH Event Socket
 * Library module can make outbound connections to.
 * <p>
 * This class provides for what the FreeSWITCH documentation refers to as
 * 'Outbound' connections from the Event Socket module. That is, with reference
 * to the module running on the FreeSWITCH server, this client accepts an
 * outbound connection from the server module.
 * <p>
 * See <a href="http://wiki.freeswitch.org/wiki/Mod_event_socket">http://wiki.
 * freeswitch.org/wiki/Mod_event_socket</a>
 *
 * @author Arsene Tochemey GANDOTE
 */
public class FreeSwitchServer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Binding port.
     */
    private final int port;
    private final ChannelGroup allChannels = new DefaultChannelGroup("esl-socket-server");

    private final ChannelFactory channelFactory;
    private final FreeSwitchPipelineFactory pipelineFactory;

    /**
     *
     */
    public FreeSwitchServer(int port,
                            FreeSwitchPipelineFactory pipelineFactory) {
        this.port = port;
        this.pipelineFactory = pipelineFactory;
        this.channelFactory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
    }

    /**
     * start()
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);

        bootstrap.setPipelineFactory(pipelineFactory);
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        Channel serverChannel = bootstrap.bind(new InetSocketAddress(port));
        allChannels.add(serverChannel);
        log.info("SocketClient waiting for connections on port [{}] ...", port);
    }

    /**
     * stop()
     *
     * @throws InterruptedException
     */
    public void stop() throws InterruptedException {
        // Wait until the server socket is closed.
        ChannelGroupFuture future = allChannels.close();
        future.awaitUninterruptibly();
        channelFactory.releaseExternalResources();
        log.info("SocketClient stopped");
    }
}
