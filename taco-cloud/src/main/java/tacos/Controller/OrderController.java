package tacos.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
        import tacos.Dto.OrderDTO;
import tacos.Mapper.OrderMapper;
import tacos.Model.Order;
import tacos.Service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper mapper;

//    create a new order
    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        Order saved = orderService.createOrder(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

//    get all order
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<OrderDTO> dtos = orderService.getAllOrder()
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get order by id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(mapper.toDTO(order));
    }

//    get order by username
    @GetMapping("/name/{username}")
    public ResponseEntity<?> getOrderByUsername(@PathVariable String username) {
        List<OrderDTO> dtos = orderService.getOrdersByUsername(username)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get latest order by number of count
    @GetMapping("/latest/{count}")
    public ResponseEntity<?> getLatestOrders(@PathVariable int count) {
        List<OrderDTO> dtos = orderService.getLatestOrders(count)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    update order by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @Valid @RequestBody OrderDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        Order updated = orderService.updateOrder(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

//    calculate total order price by id
    @GetMapping("/price/{id}")
    public ResponseEntity<?> calculateTotal(@PathVariable int id) {
        long total = orderService.calculateOrderTotal(id);
        return ResponseEntity.ok(total);
    }

//    delete order by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id); // Should check if exists inside service
        return ResponseEntity.ok("Order deleted successfully");
    }
}
