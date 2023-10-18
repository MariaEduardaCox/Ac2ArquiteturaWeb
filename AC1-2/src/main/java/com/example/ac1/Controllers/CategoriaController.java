import com.example.ac1.model.Categoria;
import com.example.ac1.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNome(categoriaAtualizada.getNome());
                    return categoriaRepository.save(categoria);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
    }
}