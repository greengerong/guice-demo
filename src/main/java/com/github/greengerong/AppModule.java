package com.github.greengerong;

import com.google.inject.*;
import com.google.inject.Provider;
import com.google.inject.spi.*;

import javax.inject.*;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.ImmutableList.of;

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

    @Override
    public void configure(Binder binder) {
        binder.bind(OrderService.class).to(OrderServiceImpl.class);
        binder.bind(new TypeLiteral<List<ItemService>>() {

        }).toProvider(new Provider<List<ItemService>>() {
            @Override
            public List<ItemService> get() {
                return of(new ItemServiceImpl1(), new ItemServiceImpl2());
            }
        });
    }
}
