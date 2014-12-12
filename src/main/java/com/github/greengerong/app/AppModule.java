package com.github.greengerong.app;

import com.github.greengerong.item.ItemService;
import com.github.greengerong.item.ItemServiceImpl1;
import com.github.greengerong.item.ItemServiceImpl2;
import com.github.greengerong.order.OrderService;
import com.github.greengerong.order.OrderServiceImpl;
import com.github.greengerong.price.PriceService;
import com.github.greengerong.runtime.RuntimeService;
import com.github.greengerong.runtime.RuntimeServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
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
        //TODO: bind interface
        binder.bind(OrderService.class).to(OrderServiceImpl.class).in(SINGLETON);
        //TODO: bind self class(without interface or base class)
        binder.bind(PriceService.class).in(Scopes.SINGLETON);

        //TODO: bind named instance;
        binder.bind(ItemService.class).annotatedWith(Names.named("impl1")).to(ItemServiceImpl1.class);
        binder.bind(ItemService.class).annotatedWith(Names.named("impl2")).to(ItemServiceImpl2.class);

        //TODO: bind instance not class.
        binder.bind(RuntimeService.class).toInstance(runtimeService);
    }

    @Provides
    public List<ItemService> getAllItemServices(@Named("impl1") ItemService itemService1,
                                                @Named("impl2") ItemService itemService2) {
        return of(itemService1, itemService2);
    }

}
