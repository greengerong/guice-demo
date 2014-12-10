package com.github.greengerong;

import com.github.greengerong.app.AppModule;
import com.github.greengerong.item.ItemService;
import com.github.greengerong.item.ItemServiceImpl1;
import com.github.greengerong.item.ItemServiceImpl2;
import com.github.greengerong.order.OrderService;
import com.github.greengerong.order.OrderServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AppModuleTest {

    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new AppModule());
    }

    @Test
    public void should_get_order_service_from_guice_module() throws Exception {
        //given
        //when
        final OrderService instance = injector.getInstance(OrderService.class);
        //then
        assertThat(instance, is(instanceOf(OrderServiceImpl.class)));
        assertThat(((OrderServiceImpl) instance).getItemService().get(0), is(instanceOf(ItemServiceImpl1.class)));
        assertThat(((OrderServiceImpl) instance).getItemService().get(1), is(instanceOf(ItemServiceImpl2.class)));
    }

    @Test
    public void should_get_all_item_service() throws Exception {
        //given

        //when
        final List<ItemService> instance = injector.getInstance(new Key<List<ItemService>>() {
        });
        //then
        assertThat(instance.size(), is(2));
        assertThat(instance.get(0), is(instanceOf(ItemServiceImpl1.class)));
        assertThat(instance.get(1), is(instanceOf(ItemServiceImpl2.class)));
    }
}