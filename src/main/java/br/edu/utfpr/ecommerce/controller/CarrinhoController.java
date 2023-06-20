package br.edu.utfpr.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
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

import br.edu.utfpr.ecommerce.dto.CarrinhoDTO;
import br.edu.utfpr.ecommerce.model.Carrinho;
import br.edu.utfpr.ecommerce.model.Produto;
import br.edu.utfpr.ecommerce.repository.CarrinhoRepository;
import br.edu.utfpr.ecommerce.repository.PessoaRepository;
import br.edu.utfpr.ecommerce.repository.ProdutoRepository;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    // Criar um carrinho
    @PostMapping({ "", "/" })
    public ResponseEntity<Object> create(@RequestBody CarrinhoDTO carrinhoDTO) {
        var entity = new Carrinho();
        entity.setId(UUID.randomUUID());

        BeanUtils.copyProperties(carrinhoDTO, entity);

        var pes = pessoaRepository.findById(carrinhoDTO.getPessoaId());
        entity.setPessoa(pes.get());

        var prod = produtoRepository.findAllById(carrinhoDTO.getProductsIds());
        List<Produto> listProducts = new ArrayList<Produto>();
        prod.iterator().forEachRemaining(listProducts::add);

        entity.setProducts(listProducts);

        entity.setProducts(listProducts);

        // Calcular o total
        double total = listProducts.stream()
                .mapToDouble(Produto::getValue)
                .sum();

        entity.setTotal(total);

        carrinhoRepository.save(entity);

        return ResponseEntity.status(201).body(entity);
    }

    // Obter 1 carrinho pelo seu id
    @GetMapping("/{id}")
    public Optional<Carrinho> getCarrinho(@PathVariable String id) {
        return carrinhoRepository.findById(UUID.fromString(id));
    }

    // Obter todos os carrinhos da API
    @GetMapping({ "", "/" })
    public Iterable<Carrinho> getCarrinhos() {
        return carrinhoRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody CarrinhoDTO carrinhoDTO) {
        var car = this.carrinhoRepository.findById(id);

        if (car.isEmpty())
            return ResponseEntity.notFound().build();

        var carToUpdate = new Carrinho();
        BeanUtils.copyProperties(carrinhoDTO, carToUpdate);
        carToUpdate.setId(car.get().getId());

        var pes = pessoaRepository.findById(carrinhoDTO.getPessoaId());
        carToUpdate.setPessoa(pes.get());

        var prod = produtoRepository.findAllById(carrinhoDTO.getProductsIds());
        List<Produto> listProducts = new ArrayList<Produto>();
        prod.iterator().forEachRemaining(listProducts::add);

        carToUpdate.setProducts(listProducts);

        // Calcular o total
        double total = listProducts.stream()
                .mapToDouble(Produto::getValue)
                .sum();

        carToUpdate.setTotal(total);

        carToUpdate.setProducts(listProducts);

        return ResponseEntity.ok().body(this.carrinhoRepository.save(carToUpdate));
    }

}
