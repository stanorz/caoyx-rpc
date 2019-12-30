package com.caoyx.rpc.core.data;

import lombok.Data;


/**
 * @author caoyixiong
 */
@Data
public class CaoyxRpcRequest extends CaoyxRpcPacket {

    private String applicationName;

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;

    private long createdTimeMills;
}