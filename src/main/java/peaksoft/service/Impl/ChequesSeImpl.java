package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.ChequeResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.entity.User;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ChequesService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequesSeImpl implements ChequesService {

    private final ChequeRepository chequeRepository;
    private final MenuItemRepository menuItemRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public SimpleResponse saveCheque(ChequeRequest chequeRequest) {
//        List<MenuItem> menuItems = new ArrayList<>();
//        User authentication = jwtUtil.getAuthentication();
//        int priceAverage = 0;
//        if (chequeRequest.getMenuItemNames().isEmpty()) {
//            throw new BadRequestException("You not must be empty ");
//        } else {
//            for (String menuItemName : chequeRequest.getMenuItemNames()) {
//                MenuItem menuItem = menuItemRepository.findMenuItemByName(menuItemName).orElseThrow();
//                priceAverage += menuItem.getPrice();
//                menuItems.add(menuItem);
//            }
//        }
//        Cheque cheque = new Cheque();
//        cheque.setUsers(authentication);
//        cheque.setMenuItems(menuItems);
//        cheque.setCreatedAt(ZonedDateTime.now());
//        cheque.setPriceAverage(priceAverage);
//        chequeRepository.save(cheque);
//        return SimpleResponse
//                .builder()
//                .status(HttpStatus.OK)
//                .message("Cheque save")
//                .build();

        User user = userRepository.findById(chequeRequest.getUserId()).orElseThrow();
        Cheque cheque = new Cheque();
        List<MenuItem> menuItemList = new ArrayList<>();
        int sum = 0;
        for (Long manuItem : chequeRequest.getMenuItemNames()) {
            MenuItem menuItem = menuItemRepository.findById(manuItem).orElseThrow(() -> new NotFoundException("This manu item is not found"));
            menuItem.addCheque(cheque);
            menuItemList.add(menuItem);
            sum += menuItem.getPrice();
        }
        cheque.setUser(user);
        cheque.setCreatedAt(ZonedDateTime.now());
        cheque.setPriceAverage(sum);
        cheque.setMenuItems(menuItemList);
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully saved!!").build();
    }

    @Override
    public List<ChequeResponse> getAllCheques() {
        return null;
    }

    @Override
    public ChequeResponse getByIdCheque(Long chequeId) {
        Cheque cheque = chequeRepository.findById(chequeId).orElseThrow
                (() -> new NotFoundException("Cheque with id: " + chequeId + " is no exist!"));
        ChequeResponse chequeResponse = new ChequeResponse();
        chequeResponse.setId(cheque.getId());
        chequeResponse.setFullName(cheque.getUsers().getFirstName() + cheque.getUsers().getLastName());
        chequeResponse.setItems(cheque.getMenuItems());
        chequeResponse.setPriceAverage(cheque.getPriceAverage());
        chequeResponse.setService(cheque.getUsers().getRestaurant().getService());
        return chequeResponse;
    }

    @Override
    public SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteByIdCheque(Long chequeId) {
        chequeRepository.findById(chequeId).orElseThrow(
                () -> new NotFoundException("Cheque with id: " + chequeId + " is no exist"));
        chequeRepository.deleteById(chequeId);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Delete Cheque..")
                .build();
    }
}

