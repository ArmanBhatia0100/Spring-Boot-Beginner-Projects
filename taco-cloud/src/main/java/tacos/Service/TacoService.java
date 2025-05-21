package tacos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tacos.Exception.TacoNotFoundException;
import tacos.Model.Taco;
import tacos.Repository.TacoRepository;

import java.util.List;

@Service
public class TacoService {

    @Autowired
    private TacoRepository tacoRepository;

//    create a new taco
    public Taco createTaco(Taco taco) throws TacoNotFoundException {
        if(taco.getIngredients()==null||taco.getIngredients().isEmpty()){
            throw new TacoNotFoundException("Taco must have at least one Ingredient.");
        }
        return tacoRepository.save(taco);
    }

//    get all tacos
    public List<Taco> getAllTacos(){
        return tacoRepository.findAll();
    }

//    get taco by id
    public Taco getTacoById(int id){
       return tacoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Taco not found with the ID: "+id));
    }

//    get tacos by name
    public List<Taco> getTacosByName(String name){
        return tacoRepository.findByTacoName(name);
    }

//    get tacos by ingredients
    public List<Taco> getTacosWithIngredients(String ingredientName){
        return tacoRepository.findAll()
                .stream()
                .filter(t->t.getIngredients().stream()
                .anyMatch(i->i.getIngredientName().equalsIgnoreCase(ingredientName)))
                .toList();
    }

//    get latest tacos by number of count
    public List<Taco> getLatestTacos(int count){
        return tacoRepository.findAll(PageRequest.of(0,count,Sort.by(Sort.Direction.DESC," id"))).getContent();
    }

//   update taco by id
    public Taco updateTaco(int id,Taco updatedTaco) throws TacoNotFoundException {
        if(!tacoRepository.existsById(id)){
            throw new TacoNotFoundException("Taco not found to modify");
        }
        return tacoRepository.findById(id)
                .map(existingTaco-> {existingTaco.setTacoName(updatedTaco.getTacoName());
                    existingTaco.setIngredients(updatedTaco.getIngredients());
                    return  tacoRepository.save(existingTaco);
                })
                .orElseThrow(()->new TacoNotFoundException("Taco not found with id: "+id));
    }

//    delete taco by id
    public void deleteTaco(int id) throws TacoNotFoundException {
        if(!tacoRepository.existsById(id)){
            throw new TacoNotFoundException("Cannot delete. Taco not found");
        }
        tacoRepository.deleteById(id);
    }

}
