package com.lls.lemon.core;

/************************************
 * LemonContextFactory
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public class LemonContextFactory {

    private LemonConfiguration configuration;

    public LemonContextFactory(LemonConfiguration configuration) {
        this.configuration = configuration;
    }

    public LemonContext makeObject() {
        return new DefaultLemonContext(configuration);
    }

    public LemonConfiguration getConfiguration() {
        return configuration;
    }

}
