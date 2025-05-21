package tacos.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tacos.Model.Order;
import tacos.Model.Taco;
import tacos.Repository.TacoRepository;
import tacos.Dto.OrderDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private TacoRepository tacoRepository;

//    converts order entity to orderDto
    public Order toEntity(OrderDTO dto){
        Order order = new Order();
        order.setUsername(dto.getUsername());
        order.setAddress(dto.getAddress());
        order.setPhone(dto.getPhone());
        List<Taco> tacos = dto.getTacoNames().stream()
                .map(name->tacoRepository.findByTacoName(name))
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        order.setTacos(tacos);
        return order;
    }

//    converts orderDto to order entity
    public OrderDTO toDTO(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setUsername(order.getUsername());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        List<String> tocoNames = order.getTacos().stream()
                .map(Taco::getTacoName)
                .collect(Collectors.toList());
        dto.setTacoNames(tocoNames);
        return dto;
    }

}
