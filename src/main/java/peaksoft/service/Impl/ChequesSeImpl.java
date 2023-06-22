package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.CheckOneDayUserRequest;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.CheckOneDayUserResponse;
import peaksoft.dto.restaurant.restaurantRequest.CheckOneDayRestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.CheckOneDayRestaurantResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
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
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public SimpleResponse saveCheque(ChequeRequest chequeRequest) {
        User user = userRepository.findById(chequeRequest.getUserId()).orElseThrow(
                ()-> new NotFoundException("User with id: " + chequeRequest.getUserId()+ " Not Found"));
        Cheque cheque = new Cheque();
        List<MenuItem> menuItemList = new ArrayList<>();
        int sum = 0;
        for (Long manuItem : chequeRequest.getMenuItemNames()) {
            MenuItem menuItem = menuItemRepository.findById(manuItem).orElseThrow(
                    () -> new NotFoundException("This manu item is not found"));
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
    public SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest) {
        Cheque cheque = chequeRepository.findById(chequeIdd).orElseThrow(() -> new NotFoundException("Check with id: " + chequeIdd + " not found!"));
        User user = userRepository.findById(chequeRequest.getUserId()).orElseThrow(() -> new NotFoundException("User with id: " + chequeRequest.getUserId() + " not found!"));
        List<MenuItem> menuItems = menuItemRepository.findAllById(chequeRequest.getMenuItemNames());
        cheque.setMenuItems(menuItems);
        cheque.setUser(user);
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Cheque with id: " + cheque.getId() + " is successfully updated!").build();
    }

    @Override
    public SimpleResponse deleteByIdCheque(Long chequeId) {
        Cheque cheque = chequeRepository.findById(chequeId).orElseThrow(
                () -> new NotFoundException("Cheque with id: " + chequeId + " is no exist"));
        chequeRepository.delete(cheque);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Delete Cheque..")
                .build();
    }

    @Override
    public CheckOneDayUserResponse totalSumByUser(CheckOneDayUserRequest oneDayUserRequest) {
        User user = userRepository.findById(oneDayUserRequest.getUserId()).orElseThrow(
                () -> new NotFoundException("User with id : " + oneDayUserRequest.getUserId() + "is not found!"));
        int chequeCount = 0;
        int totalAmount = 0;
        if (user.getRole().equals(Role.WAITER)) {
            for (Cheque che : user.getCheques()) {
                if (che.getCreatedAt().isEqual(oneDayUserRequest.getDate())) {
                    int service = che.getPriceAverage() * user.getRestaurant().getService() / 100;
                    totalAmount += service + che.getPriceAverage();
                    chequeCount++;
                }
            }
        } else {
            throw new AlreadyExistException("This User already exists! ");
        }
        return CheckOneDayUserResponse.builder()
                .averageGrandTotal(totalAmount)
                .numberOfCheques(chequeCount)
                .fullName(user.getFirstName() + " " + user.getLastName())
                .build();


    }

    @Override
    public CheckOneDayRestaurantResponse totalAvgByRestaurant(CheckOneDayRestaurantRequest oneDayRestaurantRequest) {
        Restaurant restaurant = restaurantRepository.findRestaurantByName(oneDayRestaurantRequest.getRestaurantName())
                .orElseThrow(() -> new NotFoundException("Restaurant with name: " + oneDayRestaurantRequest.getRestaurantName() + " is not found!"));
        int numberOfCheques = 0;
        int totalAmount = 0;
        for (User userWaiter : restaurant.getUsers()) {
            if (userWaiter.getRole().equals(Role.WAITER)) {
                for (Cheque userCheque : userWaiter.getCheques()) {
                    if (userCheque.getCreatedAt().isEqual(oneDayRestaurantRequest.getDate())) {
                        int restaurantService = userCheque.getPriceAverage() * restaurant.getService() / 100;
                        totalAmount += restaurantService + userCheque.getPriceAverage();
                        numberOfCheques++;
                    }
                }
            }
        }

        CheckOneDayRestaurantResponse chequeOfRestaurantAmountDayResponse = new CheckOneDayRestaurantResponse();
        chequeOfRestaurantAmountDayResponse.setAverageGrandTotal(totalAmount / numberOfCheques);
        chequeOfRestaurantAmountDayResponse.setDate(oneDayRestaurantRequest.getDate());
        chequeOfRestaurantAmountDayResponse.setNumberOfCheques(numberOfCheques);
        return chequeOfRestaurantAmountDayResponse;
    }

}

