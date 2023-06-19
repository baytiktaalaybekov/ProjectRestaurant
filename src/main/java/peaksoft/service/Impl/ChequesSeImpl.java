package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.AssignRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.ChequeResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.service.ChequesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequesSeImpl implements ChequesService {

    private final ChequeRepository chequeRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public SimpleResponse saveCheque(ChequeRequest chequeRequest) {


        return null;
    }

    @Override
    public List<ChequeResponse> getAllCheques() {
        return chequeRepository.getAllCheques();
    }

    @Override
    public ChequeResponse getByIdCheque(Long chequeId) {
        return chequeRepository.getByIdCheque(chequeId)
                .orElseThrow(()-> new NotFoundException("Cheque with id: " +chequeId+ "Not found"));
    }

    @Override
    public SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteByIdCheque(Long chequeId) {
        chequeRepository.deleteById(chequeId);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Delete Cheque..")
                .build();
    }

    @Override
    public SimpleResponse assignChequeToMenuItem(AssignRequest assignRequest) {
        MenuItem menuItem = menuItemRepository.findById(assignRequest.getMenuItemId()).orElseThrow(
                () -> new NotFoundException("MenuItem with id: " + assignRequest.getMenuItemId() + " not found"));
        Cheque cheque = chequeRepository.findById(assignRequest.getChequeId()).orElseThrow(
                () -> new NotFoundException("Cheque with id: " + assignRequest.getChequeId() + " not Found"));
        menuItem.setCheques(List.of(cheque));
        cheque.setMenuItems(List.of(menuItem));
        menuItemRepository.save(menuItem);
        chequeRepository.save(cheque);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Assign")
                .build();
    }
}
