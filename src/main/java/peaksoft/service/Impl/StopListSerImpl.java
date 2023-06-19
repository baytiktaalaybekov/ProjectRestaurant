package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.stopList.stopListRequest.StopListRequest;
import peaksoft.dto.stopList.stopListResponse.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StopListSerImpl implements StopListService {

    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public SimpleResponse saveStopList(StopListRequest stopListRequest) {
        MenuItem menuItem1 = menuItemRepository.findById(stopListRequest.getMenuItemId()).orElseThrow(
                () -> new NotFoundException("StopList with id: " + stopListRequest.getMenuItemId()));
        if (stopListRepository.count(stopListRequest.getDate(),menuItem1.getName()) > 0) {
            throw new AlreadyExistException("This StopList already exists");
        } else {

            StopList stopList = new StopList();
            stopList.setReason(stopListRequest.getReason());
            stopList.setDate(stopListRequest.getDate());
            stopList.setMenuItem(menuItem1);
            stopListRepository.save(stopList);
        }

        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Save StopList!")
                .build();
    }

    @Override
    public List<StopListResponse> getAllStopList() {
        return stopListRepository.getAllStopList();
    }

    @Override
    public StopListResponse getStopListById(Long id) {
        return stopListRepository.getStopListById(id).orElseThrow(
                () -> new NotFoundException("StopList with id: " + id + " not found"));

    }

    @Override
    public SimpleResponse updateStopList(Long id, StopListRequest stopListRequest) {
        MenuItem menuItem1 = menuItemRepository.findById(stopListRequest.getMenuItemId()).orElseThrow(
                () -> new NotFoundException("StopList with id: " + stopListRequest.getMenuItemId()));
        StopList stopList = stopListRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("StopList with id" + id + "doesn't exists")));
        if (stopListRepository.count(stopListRequest.getDate(),menuItem1.getName()) > 0) {
            throw new AlreadyExistException("This StopList already exists");
        } else {
            stopList.setReason(stopListRequest.getReason());
            stopList.setDate(stopListRequest.getDate());
            stopListRepository.save(stopList);
        }
            return SimpleResponse
                    .builder()
                    .status(HttpStatus.OK)
                    .message("Successfully updated")
                    .build();

    }


    @Override
    public SimpleResponse deleteStopListById(Long id) {
        if (!menuItemRepository.existsById(id)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("StopList with id: %s doesn't exist", id))
                    .build();
        }
        stopListRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("StopList with id: %s successfully deleted", id))
                .build();

    }
}
