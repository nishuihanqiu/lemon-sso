package com.lls.lemon.core;

/************************************
 * LemonContextFactory
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public class LemonContextFactory {

    private Configuration configuration;

    public LemonContextFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public LemonContext makeObject() {
        return new DefaultLemonContext(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
