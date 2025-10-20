package devkaik.TrabalhoFaculdade.service;

import devkaik.TrabalhoFaculdade.Model.Entity.Mesa;
import devkaik.TrabalhoFaculdade.Model.Entity.Reserva;
import devkaik.TrabalhoFaculdade.dto.ReservaRequest;
import devkaik.TrabalhoFaculdade.dto.ReservaResponse;
import devkaik.TrabalhoFaculdade.exception.BusinessException;
import devkaik.TrabalhoFaculdade.exception.ResourceNotFoundException;
import devkaik.TrabalhoFaculdade.mapper.ReservaMapper;
import devkaik.TrabalhoFaculdade.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final MesaService mesaService;

    public ReservaService(ReservaRepository reservaRepository, MesaService mesaService) {
        this.reservaRepository = reservaRepository;
        this.mesaService = mesaService;
    }

    @Transactional
    public ReservaResponse criar(ReservaRequest request) {
        Mesa mesa = mesaService.buscarEntidadePorId(request.mesaId());
        validarCapacidadeMesa(mesa, request.numeroPessoas());

        Reserva reserva = ReservaMapper.toEntity(request, mesa);
        Reserva saved = reservaRepository.save(reserva);
        return ReservaMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ReservaResponse> listarTodas() {
        return reservaRepository.findAll().stream()
                .map(ReservaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReservaResponse buscarPorId(Long id) {
        return ReservaMapper.toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public ReservaResponse atualizar(Long id, ReservaRequest request) {
        Reserva reserva = buscarEntidadePorId(id);
        Mesa mesa = mesaService.buscarEntidadePorId(request.mesaId());
        validarCapacidadeMesa(mesa, request.numeroPessoas());

        ReservaMapper.updateEntity(reserva, request, mesa);
        Reserva updated = reservaRepository.save(reserva);
        return ReservaMapper.toResponse(updated);
    }

    @Transactional
    public void remover(Long id) {
        Reserva reserva = buscarEntidadePorId(id);
        reservaRepository.delete(reserva);
    }

    @Transactional(readOnly = true)
    public Reserva buscarEntidadePorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com o id informado."));
    }

    private void validarCapacidadeMesa(Mesa mesa, Integer numeroPessoas) {
        if (numeroPessoas != null && mesa.getCapacidade() < numeroPessoas) {
            throw new BusinessException("A quantidade de pessoas excede a capacidade da mesa selecionada.");
        }
    }
}
