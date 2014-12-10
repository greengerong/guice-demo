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
    private List<ItemService> itemService;

    public OrderServiceImpl() {
    }

    @Inject
    public OrderServiceImpl(List<ItemService> itemService) {
        this.itemService = itemService;
    }

    @Override
    public void add(Order order) {
        LOGGER.info("add order: {}", order);
    }

    @Override
    public void remove(Order order) {
        LOGGER.info("remove order: {}", order);
    }

    @Override
    public Order get(int id) {
        itemService.get(id);
        LOGGER.info("add order: {}", id);
        return new Order(id);
    }

    public List<ItemService> getItemService() {
        return itemService;
    }
}
