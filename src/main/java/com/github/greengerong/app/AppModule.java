package com.github.greengerong.app;

import com.github.greengerong.item.ItemService;
import com.github.greengerong.item.ItemServiceImpl1;
import com.github.greengerong.item.ItemServiceImpl2;
import com.github.greengerong.order.OrderService;
import com.github.greengerong.order.OrderServiceImpl;
import com.github.greengerong.runtime.RuntimeService;
import com.github.greengerong.runtime.RuntimeServiceImpl;
import com.google.inject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.greengerong.app.ExceptionMethodInterceptor.exception;
import static com.google.common.collect.ImmutableList.of;
import static com.google.inject.Scopes.SINGLETON;
import static com.google.inject.matcher.Matchers.any;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2014                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class AppModule implements Module {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppModule.class);
    private final RuntimeServiceImpl runtimeService;

    public AppModule(RuntimeServiceImpl runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void configure(Binder binder) {
        if (LOGGER.isDebugEnabled()) {
            binder.bindInterceptor(any(), any(), exception());
        }
        binder.bind(OrderService.class).to(OrderServiceImpl.class).in(SINGLETON);
        binder.bind(new TypeLiteral<List<ItemService>>() {

        }).toProvider(new Provider<List<ItemService>>() {
            @Override
            public List<ItemService> get() {
                return of(new ItemServiceImpl1(), new ItemServiceImpl2());
            }
        });

        binder.bind(RuntimeService.class).toInstance(runtimeService);
    }

}
