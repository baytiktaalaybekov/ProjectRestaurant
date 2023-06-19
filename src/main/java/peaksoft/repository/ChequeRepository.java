package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.cheque.chequeResponse.ChequeResponse;
import peaksoft.entity.Cheque;

import java.util.List;
import java.util.Optional;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {

    @Query("select new peaksoft.dto.cheque.chequeResponse.ChequeResponse(c.id,c.priceAverage,c.createdAt,c.grandTotal) from Cheque c")
    List<ChequeResponse> getAllCheques();

    @Query("select new peaksoft.dto.cheque.chequeResponse.ChequeResponse(c.id,c.priceAverage,c.createdAt,c.grandTotal) from Cheque c where c.id= :chequeId")
    Optional<ChequeResponse> getByIdCheque(Long chequeId);




}