package com.github.greengerong;


public interface OrderService {

    void add(Order order);

    void remove(Order order);

    Order get(int id);
}
