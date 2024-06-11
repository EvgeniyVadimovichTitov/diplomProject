package ru.gb.cardServise.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.cardServise.models.domain.CardData;

@Repository
public interface CardDataRepository extends JpaRepository <CardData, Long> {

    CardData findByCardNumber(String cardNumber);
    CardData findFirstByIsUsing(boolean isUsing);

}
