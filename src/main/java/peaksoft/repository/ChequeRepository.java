package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Cheque;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {


//    Page<ChequeResponse> getAllCheques(Pageable pageable);

//    @Query("SELECT new peaksoft.dto.cheque.chequeResponse.ChequeResponse(c.id, c.createdAt, CONCAT(c.users.firstName, ' ', c.users.lastName), SUM(m.price), m.restaurant.service) " +
//            "FROM Cheque c " +
//            "JOIN c.menuItems m " +
//            "WHERE c.users.id = :userId " +
//            "GROUP BY c.id, c.createdAt, c.users.firstName, c.users.lastName, m.restaurant.service" )
//    List<ChequeResponse> findAllCheques(Long userId);

//
//    @Query("select new peaksoft.dto.cheque.chequeResponse.MenuItemChequeResponse(m.id, m.name, m.price, count(m)) from MenuItem m join m.cheques c where c.id = :chequeId group by m.id, m.name, m.price")
//    List<MenuItemChequeResponse> getFoods (Long chequeId);



}