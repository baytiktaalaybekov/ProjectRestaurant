package peaksoft.service;

import peaksoft.dto.AssignRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.chequeRequest.ChequeRequest;
import peaksoft.dto.cheque.chequeResponse.ChequeResponse;

import java.util.List;

public interface ChequesService {

    SimpleResponse saveCheque(ChequeRequest chequeRequest);

    List<ChequeResponse> getAllCheques();

    ChequeResponse getByIdCheque(Long chequeId);

    SimpleResponse updateCheque(Long chequeIdd, ChequeRequest chequeRequest);

    SimpleResponse deleteByIdCheque(Long chequeId);

    SimpleResponse assignChequeToMenuItem (AssignRequest assignRequest);
}
