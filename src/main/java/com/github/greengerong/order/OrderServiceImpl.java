package com.github.greengerong.order;

import com.github.greengerong.item.ItemService;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private List<ItemService> itemServices;

    public OrderServiceImpl() {
    }

    @Inject
    public OrderServiceImpl(List<ItemService> itemServices) {
        this.itemServices = itemServices;
    }

    @Override
    public void add(Order order) {
    }

    @Override
    public void remove(Order order) {
    }

    @Override
    public Order get(int id) {
        for (ItemService item : itemServices) {
            item.get(id);
        }
        return new Order(id);
    }

    public List<ItemService> getItemServices() {
        return itemServices;
    }
}
