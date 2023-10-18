import com.example.ac1.model.Produto;
import com.example.ac1.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setCategoria(produtoAtualizado.getCategoria());
                    return produtoRepository.save(produto);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}