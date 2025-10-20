package devkaik.TrabalhoFaculdade.service;

import devkaik.TrabalhoFaculdade.Model.Entity.Mesa;
import devkaik.TrabalhoFaculdade.dto.MesaRequest;
import devkaik.TrabalhoFaculdade.dto.MesaResponse;
import devkaik.TrabalhoFaculdade.exception.BusinessException;
import devkaik.TrabalhoFaculdade.exception.ResourceNotFoundException;
import devkaik.TrabalhoFaculdade.mapper.MesaMapper;
import devkaik.TrabalhoFaculdade.repository.MesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Transactional
    public MesaResponse criar(MesaRequest request) {
        mesaRepository.findByIdentificadorIgnoreCase(request.identificador()).ifPresent(m -> {
            throw new BusinessException("Já existe uma mesa cadastrada com o identificador informado.");
        });

        Mesa mesa = MesaMapper.toEntity(request);
        Mesa saved = mesaRepository.save(mesa);
        return MesaMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<MesaResponse> listarTodas() {
        return mesaRepository.findAll().stream()
                .map(MesaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MesaResponse buscarPorId(Long id) {
        return MesaMapper.toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public MesaResponse atualizar(Long id, MesaRequest request) {
        Mesa mesa = buscarEntidadePorId(id);

        mesaRepository.findByIdentificadorIgnoreCase(request.identificador())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException("Já existe outra mesa cadastrada com o identificador informado.");
                });

        MesaMapper.updateEntity(mesa, request);
        Mesa updated = mesaRepository.save(mesa);
        return MesaMapper.toResponse(updated);
    }

    @Transactional
    public void remover(Long id) {
        Mesa mesa = buscarEntidadePorId(id);
        mesaRepository.delete(mesa);
    }

    @Transactional(readOnly = true)
    public Mesa buscarEntidadePorId(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada com o id informado."));
    }
}
