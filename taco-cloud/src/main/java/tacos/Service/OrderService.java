package tacos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tacos.Exception.OrderException;
import tacos.Model.Order;
import tacos.Model.Taco;
import tacos.Repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    creates a new order
    public Order createOrder(Order order) throws OrderException {
        if(order.getUsername().isEmpty()||
                order.getAddress().isEmpty()||
                order.getPhone()==null||
                order.getTacos().isEmpty()||
        order.getTacos()==null){
            throw new OrderException("Order details cannot be empty.");
        }
        return orderRepository.save(order);
    }

//    get all order
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

//    get order by id
    public Order getOrderById(int id) throws OrderException {
        return orderRepository.findById(id).orElseThrow(()->new OrderException("Can't find order with this id: "+id));
    }

//    get order by username
    public List<Order> getOrdersByUsername(String username){
        return orderRepository.findByUsername(username);
    }

//    get latest order by number of count
    public List<Order> getLatestOrders(int count){
        return orderRepository.findAll(PageRequest.of(0,count, Sort
                .by(Sort.Direction.DESC,"id"))).getContent();
    }

//    update order by id
    public Order updateOrder(int id,Order updateOrder) throws OrderException {
        if(!orderRepository.existsById(id)){
            throw new OrderException("Order not found with this id: "+id);
        }
        return orderRepository.findById(id)
                .map(existingOrder->{
                existingOrder.setUsername(updateOrder.getUsername());
                existingOrder.setPhone(updateOrder.getPhone());
                existingOrder.setAddress(updateOrder.getAddress());
                existingOrder.setTacos(updateOrder.getTacos());
                return orderRepository.save(existingOrder);
                }).orElseThrow(()-> new OrderException("Order not found with this id: "+id));
    }

//    calculate total order price
    public long calculateOrderTotal(int orderId) throws OrderException {
        Order order = orderRepository.findById(orderId).orElseThrow(()->
                new OrderException("Order not found with id: "+orderId));

        return order.getTacos()
                .stream()
                .mapToLong(Taco::getPrice)
                .sum();
    }

//    delete order by id
    public void deleteOrder(int id) throws OrderException {
        if(!orderRepository.existsById(id)){
            throw new OrderException("Order not found to delete with this id: "+id);
        }
        orderRepository.deleteById(id);
    }

}
