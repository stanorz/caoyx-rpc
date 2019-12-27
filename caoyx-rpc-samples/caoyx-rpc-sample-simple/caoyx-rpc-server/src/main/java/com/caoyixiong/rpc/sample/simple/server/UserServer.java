package com.caoyixiong.rpc.sample.simple.server;

import com.caoyixiong.rpc.sample.simple.api.IUser;
import com.caoyixiong.rpc.sample.simple.server.impl.UserImpl;
import com.caoyx.rpc.core.netty.server.NettyServer;
import com.caoyx.rpc.core.provider.CaoyxRpcProviderFactory;
import com.caoyx.rpc.core.register.impl.ZookeeperRegister;
import com.caoyx.rpc.core.serializer.impl.JDKSerializerImpl;

/**
 * @author caoyixiong
 */
public class UserServer {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException {
        String applicationName = "caoyxRpc-sample-simple-server";
        CaoyxRpcProviderFactory caoyxRpcProviderFactory = new CaoyxRpcProviderFactory(applicationName, NettyServer.class, JDKSerializerImpl.class, null, 0);
        caoyxRpcProviderFactory.addServiceBean(IUser.class.getName(), 0, new UserImpl());
        caoyxRpcProviderFactory.init();
    }
}