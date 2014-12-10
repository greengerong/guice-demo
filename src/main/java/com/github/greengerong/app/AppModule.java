package com.github.greengerong.app;

import com.github.greengerong.item.ItemService;
import com.github.greengerong.item.ItemServiceImpl1;
import com.github.greengerong.item.ItemServiceImpl2;
import com.github.greengerong.order.OrderService;
import com.github.greengerong.order.OrderServiceImpl;
import com.google.common.collect.ImmutableList;
import com.google.inject.*;
import com.google.inject.Provider;

import java.util.List;

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
                return ImmutableList.of(new ItemServiceImpl1(), new ItemServiceImpl2());
            }
        });
    }
}
