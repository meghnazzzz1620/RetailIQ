package com.retailiq.service;
import java.util.List;
import com.retailiq.dto.OrderRequest;
import com.retailiq.dto.OrderResponse;
import com.retailiq.entity.*;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.OrderMapper;
import com.retailiq.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockMovementRepository stockMovementRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CustomerRepository customerRepository,
            EmployeeRepository employeeRepository,
            ProductRepository productRepository,
            InventoryRepository inventoryRepository,
            StockMovementRepository stockMovementRepository,
            WarehouseRepository warehouseRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new BusinessException("Employee not found"));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new BusinessException("Warehouse not found"));

        Order order = Order.builder()
                .orderNumber("ORD-" + System.currentTimeMillis())
                .customer(customer)
                .employee(employee)
                .warehouse(warehouse)
                .paymentMethod(request.getPaymentMethod())
                .orderStatus(OrderStatus.PENDING)
                .subtotal(BigDecimal.ZERO)
                .gstAmount(BigDecimal.ZERO)
                .discount(
                        request.getDiscount() == null
                                ? BigDecimal.ZERO
                                : request.getDiscount())
                .grandTotal(BigDecimal.ZERO)
                .orderDate(LocalDateTime.now())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal gstAmount = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();
        for (var itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new BusinessException("Product not found"));

            Inventory inventory = inventoryRepository
                    .findByProductAndWarehouse(product, warehouse)
                    .orElseThrow(() ->
                            new BusinessException(
                                    "Inventory not found"));

            if (inventory.getAvailableQuantity() < itemRequest.getQuantity()) {

                throw new BusinessException(
                        "Insufficient stock for "
                                + product.getProductName());

            }

            BigDecimal unitPrice = product.getSellingPrice();

            BigDecimal total =
                    unitPrice.multiply(
                            BigDecimal.valueOf(itemRequest.getQuantity()));

            subtotal = subtotal.add(total);

            BigDecimal gst =
                    total.multiply(
                            BigDecimal.valueOf(0.18));

            gstAmount = gstAmount.add(gst);

            OrderItem orderItem = OrderItem.builder()

                    .order(savedOrder)

                    .product(product)

                    .quantity(itemRequest.getQuantity())

                    .unitPrice(unitPrice)

                    .gstPercentage(BigDecimal.valueOf(18))

                    .discount(BigDecimal.ZERO)

                    .totalPrice(total.add(gst))

                    .build();

            orderItems.add(orderItem);
            inventory.setAvailableQuantity(
                    inventory.getAvailableQuantity()
                            - itemRequest.getQuantity());

            inventoryRepository.save(inventory);
            StockMovement movement = StockMovement.builder()
                    .inventory(inventory)
                    .movementType(MovementType.STOCK_OUT)
                    .quantity(itemRequest.getQuantity())
                    .referenceNumber(savedOrder.getOrderNumber())
                    .remarks("Product sold")
                    .performedBy(
                            employee.getFirstName() + " " +
                                    employee.getLastName())
                    .movementDate(LocalDateTime.now())
                    .build();

            stockMovementRepository.save(movement);

        }
        orderItemRepository.saveAll(orderItems);
        savedOrder.setSubtotal(subtotal);

        savedOrder.setGstAmount(gstAmount);

        savedOrder.setGrandTotal(
                subtotal
                        .add(gstAmount)
                        .subtract(savedOrder.getDiscount()));

        savedOrder = orderRepository.save(savedOrder);

        return OrderMapper.toResponse(savedOrder, orderItems);

    }
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order -> {

                    List<OrderItem> items =
                            orderItemRepository.findByOrder(order);

                    return OrderMapper.toResponse(order, items);

                })
                .toList();

    }

}