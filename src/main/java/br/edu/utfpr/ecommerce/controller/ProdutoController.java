package br.edu.utfpr.ecommerce.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.ecommerce.dto.ProdutoDTO;
import br.edu.utfpr.ecommerce.model.Produto;
// import br.edu.utfpr.ecommerce.repository.CarrinhoRepository;
import br.edu.utfpr.ecommerce.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;;

    // Criar um produto
    @PostMapping({ "", "/" })
    public ResponseEntity<Object> create(@RequestBody ProdutoDTO produtoDTO) {
        var entity = new Produto();
        entity.setId(UUID.randomUUID());

        BeanUtils.copyProperties(produtoDTO, entity);

        produtoRepository.save(entity);

        return ResponseEntity.status(201).body(entity);
    }

    // Obter 1 produto pelo seu id
    @GetMapping("/{id}")
    public Optional<Produto> getCarrinho(@PathVariable String id) {
        return produtoRepository.findById(UUID.fromString(id));
    }

    // Obter todos os carrinhos da API
    @GetMapping({ "", "/" })
    public Iterable<Produto> getCarrinhos() {
        return produtoRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody ProdutoDTO produtoDTO) {
        var prod = this.produtoRepository.findById(id);

        if (prod.isEmpty())
            return ResponseEntity.notFound().build();

        var prodToUpdate = new Produto();
        BeanUtils.copyProperties(produtoDTO, prodToUpdate);
        prodToUpdate.setId(prod.get().getId());

        return ResponseEntity.ok().body(this.produtoRepository.save(prodToUpdate));
    }
}
