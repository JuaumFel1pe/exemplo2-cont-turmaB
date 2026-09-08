package br.senac.tads.dsw.exemplo2.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.tads.dsw.exemplo2.model.Produto;
import br.senac.tads.dsw.exemplo2.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    //Recebendo um nome e um preço
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        
        //Usa o repository, pega 
        Produto produtoSalvo = repository.save(produto);

        //Criando um novo endereço do post
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest() //pegando a url base
            .path("/{id}") //adicionar o id
            .buildAndExpand(produtoSalvo.getId())
            .toUri();    
    
        return ResponseEntity.created(location).body(produtoSalvo);
    }
    //por padrão, assim que você faz um post ele retorna o json da modificação feita
}
