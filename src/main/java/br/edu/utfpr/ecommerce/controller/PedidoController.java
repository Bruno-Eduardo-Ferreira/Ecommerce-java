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

import br.edu.utfpr.ecommerce.dto.PedidoDTO;
import br.edu.utfpr.ecommerce.model.Pedido;
import br.edu.utfpr.ecommerce.repository.CarrinhoRepository;
import br.edu.utfpr.ecommerce.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    // Criar um pedido
    @PostMapping({ "", "/" })
    public ResponseEntity<Object> create(@RequestBody PedidoDTO pedidoDTO) {
        var entity = new Pedido();
        entity.setId(UUID.randomUUID());

        BeanUtils.copyProperties(pedidoDTO, entity);

        var car = carrinhoRepository.findById(pedidoDTO.getCarrinhoId());
        entity.setCarrinho(car.get());

        pedidoRepository.save(entity);

        return ResponseEntity.status(201).body(entity);
    }

    // Obter 1 pedido pelo seu id
    @GetMapping("/{id}")
    public Optional<Pedido> getPedido(@PathVariable String id) {
        return pedidoRepository.findById(UUID.fromString(id));
    }

    // Obter todos os pedidos da API
    @GetMapping({ "", "/" })
    public Iterable<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody PedidoDTO pedidoDTO) {
        var pedido = this.pedidoRepository.findById(id);

        if (pedido.isEmpty())
            return ResponseEntity.notFound().build();

        var pedToUpdate = new Pedido();
        BeanUtils.copyProperties(pedidoDTO, pedToUpdate);
        pedToUpdate.setId(pedido.get().getId());

        var car = carrinhoRepository.findById(pedidoDTO.getCarrinhoId());
        pedToUpdate.setCarrinho(car.get());

        return ResponseEntity.ok().body(this.pedidoRepository.save(pedToUpdate));
    }

}
