package org.frekele.cielo.lio.remote.client.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.OrderEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemEntity;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodSleepListener;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodSleepListener.class)
public class CieloLioPaymentRepositoryTest {

    private CieloLioPaymentRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private OrderId orderId;

    private OrderEntity order;

    private OrderItemId orderItemId2;

    private OrderItemEntity orderItem2;

    @BeforeClass
    public void init() throws Exception {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.SANDBOX;
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
        ResteasyClient client = new ResteasyClientBuilder().build();
        repository = new CieloLioPaymentRepositoryImpl(client, auth);

        order = new OrderEntity();
        order.setStatus(OrderStatusEnum.DRAFT);
        order.setNumber("12345");
        order.setReference("PEDIDO #12345");
        order.setNotes("Cliente Fulano de Tal");
        order.setPrice(BigDecimal.valueOf(325.34));
        order.setItems(new ArrayList<>());
        OrderItemEntity item = new OrderItemEntity();
        item.setSku("RTG-234-AQF-6587-C57");
        item.setName("Mesa de Formica Branca");
        item.setQuantity(1);
        item.setUnitOfMeasure("UN");
        item.setUnitPrice(BigDecimal.valueOf(325.34));
        order.getItems().add(item);

        System.out.println("new OrderCieloEntity");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order));
    }

    @Test
    public void testOrderPost() throws Exception {
        orderId = repository.orderPost(order);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderId));
    }

    @Test(dependsOnMethods = "testOrderPost")
    public void testOrderPut() throws Exception {
        order.setNotes("Joao Da Silva");
        repository.orderPut(orderId, order);
    }

    @Test(dependsOnMethods = "testOrderPut")
    public void testOrderPostItem() throws Exception {
        orderItem2 = new OrderItemEntity();
        orderItem2.setSku("XPT-456-564-34554-3453");
        orderItem2.setName("Cadeira de Madeira Branca");
        orderItem2.setQuantity(4);
        orderItem2.setUnitOfMeasure("UN");
        orderItem2.setUnitPrice(BigDecimal.valueOf(103.10));
        order.getItems().add(orderItem2);
        BigDecimal orderPrice = orderItem2.getUnitPrice().multiply(BigDecimal.valueOf(orderItem2.getQuantity())).add(order.getPrice());
        order.setPrice(orderPrice);
        System.out.println("new OrderItemEntity");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItem2));

        orderItemId2 = repository.orderPostItem(orderId, orderItem2);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderId));
    }

    @Test(dependsOnMethods = "testOrderPostItem")
    public void testOrderPutItem() throws Exception {
        orderItem2.setUnitOfMeasure("CX");
        repository.orderPutItem(orderId, orderItemId2, orderItem2);
    }

    @Test(dependsOnMethods = "testOrderPutItem")
    public void testOrderGetItem() throws Exception {
        OrderItemEntity orderItemResult = repository.orderGetItem(orderId, orderItemId2);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItemResult));
    }

    @Test(dependsOnMethods = "testOrderGetItem")
    public void testOrderGetItems() throws Exception {
        List<OrderItemEntity> resultList = repository.orderGetItems(orderId);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetItems")
    public void testOrderDeleteItem() throws Exception {
        repository.orderDeleteItem(orderId, orderItemId2);
    }

    @Test(dependsOnMethods = "testOrderDeleteItem")
    public void testOrderPutOperation() throws Exception {
        repository.orderPutOperation(orderId, OperationEnum.PLACE);
    }

    @Test(dependsOnMethods = "testOrderPutOperation")
    public void testOrderGet() throws Exception {
        OrderEntity orderResult = repository.orderGet(orderId);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResult));
    }

    @Test(dependsOnMethods = "testOrderGet")
    public void testOrderPostTransaction() throws Exception {
    }

    @Test(dependsOnMethods = "testOrderPostTransaction")
    public void testOrderGetTransactionById() throws Exception {
    }

    @Test(dependsOnMethods = "testOrderGetTransactionById")
    public void testOrderGetTransactions() throws Exception {
    }

    @Test(dependsOnMethods = "testOrderGetTransactions")
    public void testOrderGetByNumber() throws Exception {
        List<OrderEntity> resultList = repository.orderGetByNumber("12345");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByNumber")
    public void testOrderGetByReference() throws Exception {
        List<OrderEntity> resultList = repository.orderGetByReference("PEDIDO #12345");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByReference")
    public void testOrderGetByStatus() throws Exception {
        List<OrderEntity> resultList = repository.orderGetByStatus(OrderStatusEnum.ENTERED);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByStatus")
    public void testOrderGetAll() throws Exception {
        List<OrderEntity> resultList = repository.orderGetAll();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetAll")
    public void testOrderDelete() throws Exception {
        repository.orderDelete(orderId);
    }
}
