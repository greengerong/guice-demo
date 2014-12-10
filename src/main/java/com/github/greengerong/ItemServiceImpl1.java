package com.github.greengerong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ItemServiceImpl1 implements ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl1.class);

    @Override
    public Item get(int id) {
        LOGGER.info("get item : {}", id);
        return null;
    }
}
