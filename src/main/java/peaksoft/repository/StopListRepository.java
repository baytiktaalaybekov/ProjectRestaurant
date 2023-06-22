package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.stopList.stopListResponse.StopListResponse;
import peaksoft.entity.StopList;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface StopListRepository extends JpaRepository<StopList, Long> {

//    @Query("select new peaksoft.dto.stopList.stopListResponse.StopListResponse(s.id,s.reason,s.date,s.menuItem.name) from StopList s")
//    Page<StopListResponse> getAllStopList(Pageable pageable);
    @Query("select new peaksoft.dto.stopList.stopListResponse.StopListResponse(s.id,s.reason,s.date,s.menuItem.name) from StopList s")
    List<StopListResponse> getAllStopList();

    @Query("select new peaksoft.dto.stopList.stopListResponse.StopListResponse(s.id,s.reason,s.date,s.menuItem.name) from StopList s where s.id= :id")
    Optional<StopListResponse> getStopListById(Long id);

    @Query("select count (*) from StopList s where s.date=:date and upper(s.menuItem.name) like upper(:menuItemName) ")
    Integer count(ZonedDateTime date,String menuItemName);
}