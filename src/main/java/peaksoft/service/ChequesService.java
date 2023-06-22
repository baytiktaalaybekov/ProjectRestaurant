package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.CheckOneDayUserRequest;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.CheckOneDayUserResponse;
import peaksoft.dto.restaurant.restaurantRequest.CheckOneDayRestaurantRequest;
import peaksoft.dto.restaurant.restaurantResponse.CheckOneDayRestaurantResponse;

public interface ChequesService {

    SimpleResponse saveCheque(ChequeRequest chequeRequest);


    SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest);

    SimpleResponse deleteByIdCheque(Long chequeId);

    CheckOneDayUserResponse totalSumByUser(CheckOneDayUserRequest oneDayUserRequest );

    CheckOneDayRestaurantResponse totalAvgByRestaurant (CheckOneDayRestaurantRequest oneDayRestaurantRequest);




}
