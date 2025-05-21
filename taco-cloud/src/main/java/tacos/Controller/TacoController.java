package tacos.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tacos.Dto.TacoDTO;
import tacos.Mapper.TacoMapper;
import tacos.Model.Taco;
import tacos.Service.TacoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tacos")
public class TacoController {

    @Autowired
    private TacoService tacoService;

    @Autowired
    private TacoMapper mapper;

//  create a new taco
    @PostMapping
    public ResponseEntity<?> createTaco(@Valid @RequestBody TacoDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            String error = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(error);
        }

        Taco savedTaco = tacoService.createTaco(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(savedTaco));
    }

//    get all tacos
    @GetMapping
    public ResponseEntity<?> getAllTacos() {
        List<TacoDTO> dtos = tacoService.getAllTacos()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get tacos by name
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getTacosByName(@PathVariable String name) {
        List<TacoDTO> dtos = tacoService.getTacosByName(name)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get taco by id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getTacoById(@PathVariable int id) {
        Taco taco = tacoService.getTacoById(id);
        return ResponseEntity.ok(mapper.toDTO(taco));
    }

//    get taco with ingredientname
    @GetMapping("/ingredient/{ingredientName}")
    public ResponseEntity<?> getTacoWithIngredients(@PathVariable String ingredientName) {
        List<TacoDTO> dtos = tacoService.getTacosWithIngredients(ingredientName)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get latest taco by number of count
    @GetMapping("/latest/{count}")
    public ResponseEntity<?> getLatestTacos(@PathVariable int count) {
        List<TacoDTO> dtos = tacoService.getLatestTacos(count)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//  update taco with id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaco(@PathVariable int id, @Valid @RequestBody TacoDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            String error = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(error);
        }

        Taco updated = tacoService.updateTaco(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

//    delete taco with id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaco(@PathVariable int id) {
        tacoService.deleteTaco(id); // You should check exists in service
        return ResponseEntity.ok("Taco deleted successfully");
    }
}
