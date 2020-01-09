package com.caoyx.rpc.sample.simple.client;

import com.caoyx.rpc.sample.simple.api.IUser;
import com.caoyx.rpc.sample.simple.api.UserDto;
import com.caoyx.rpc.core.enums.CallType;
import com.caoyx.rpc.core.invoker.CaoyxRpcFuture;
import com.caoyx.rpc.core.invoker.CaoyxRpcInvokerCallBack;
import com.caoyx.rpc.core.netty.client.NettyClient;
import com.caoyx.rpc.core.loadbalance.impl.RandomLoadBalance;
import com.caoyx.rpc.core.invoker.reference.CaoyxRpcReferenceBean;
import com.caoyx.rpc.core.register.RegisterConfig;
import com.caoyx.rpc.core.register.RegisterType;
import com.caoyx.rpc.core.serialization.api.SerializerAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoyixiong
 */
public class UserClient {

    public static void main(String[] args) throws Exception {
        testSync();
        testFuture();
        testCallBack();
    }

    private static void testSync() throws Exception {
        UserDto userDto = new UserDto("UserDto==> testSync");
        IUser user = init(CallType.SYNC, null);
        System.out.println(user.addUser(userDto));
    }

    private static void testFuture() throws Exception {
        IUser user = init(CallType.FUTURE, null);
        user.getUsers();
        System.out.println(CaoyxRpcFuture.getFuture().get().toString());
        UserDto userDto = new UserDto("UserDto==> testFuture");
        user.addUserVoid(userDto);
    }

    private static void testCallBack() throws Exception {
        IUser user = init(CallType.CALLBACK, new CaoyxRpcInvokerCallBack() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("testCallBack: " + result);
            }

            @Override
            public void onFail(String errorMsg) {
                System.out.println("testCallBack: " + errorMsg);
            }
        });
        user.getUsers();
    }

    private static IUser init(CallType callType, CaoyxRpcInvokerCallBack callBack) throws Exception {
        List<String> loadAddresses = new ArrayList<String>();
        loadAddresses.add("127.0.0.1:1118");
        CaoyxRpcReferenceBean rpcReferenceBean = new CaoyxRpcReferenceBean(IUser.class,
                "0",
                "caoyxRpc",
                new RegisterConfig(
                        RegisterType.NO_REGISTER.getValue(),
                        "",
                        loadAddresses),
                NettyClient.class,
                SerializerAlgorithm.HESSIAN2
                , null);
        rpcReferenceBean.setCallType(callType);
        rpcReferenceBean.setLoadBalance(new RandomLoadBalance());
        rpcReferenceBean.setCaoyxRpcInvokerCallBack(callBack);
        rpcReferenceBean.init();

        return (IUser) rpcReferenceBean.getObject();
    }
}