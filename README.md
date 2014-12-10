guice-demo
==========
demo for google light ioc framework **guice**


Module:

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


Test:

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
