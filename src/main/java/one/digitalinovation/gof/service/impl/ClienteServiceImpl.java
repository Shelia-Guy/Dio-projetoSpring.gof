package one.digitalinovation.gof.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import one.digitalinovation.gof.model.Cliente;
import one.digitalinovation.gof.model.ClienteRepository;
import one.digitalinovation.gof.model.Endereco;
import one.digitalinovation.gof.model.EnderecoRepository;

@Service
public class ClienteServiceImpl implements ClienteService {


    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public ClienteServiceImpl(ClienteRepository clienteRepository,
                            EnderecoRepository enderecoRepository,
                            ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

        @Override
        public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClientecomCep(cliente);
    }


    private void salvarClientecomCep(Cliente cliente) {
        String cep = cliente.getEnderco().getCep();
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

        public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
            if (clienteExistente.isPresent()) {
        salvarClientecomCep(cliente);
    }

    }

    @Override
    public void deletar (Long id) {
    clienteRepository.deleteById(id);

    }
}
