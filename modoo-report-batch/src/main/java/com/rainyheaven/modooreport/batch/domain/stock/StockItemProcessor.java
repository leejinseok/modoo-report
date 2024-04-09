package com.rainyheaven.modooreport.batch.domain.stock;

import com.rainyheaven.modooreport.core.db.domain.stock.Stock;
import com.rainyheaven.modooreport.core.db.domain.stock.StockRepository;
import com.rainyheaven.modooreport.core.web.stock.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockItemProcessor implements ItemProcessor<StockResponseDto.ItemDto, Stock> {

    private final StockRepository stockRepository;

    @Override
    public Stock process(StockResponseDto.ItemDto item) throws Exception {
        String issuFrmtClsfNm = item.getIssuFrmtClsfNm();
        if (issuFrmtClsfNm.contains("예탁")) {
            return null;
        }
        Optional<Stock> byIsinCode = stockRepository.findByIsinCode(item.getIsinCd());
        return byIsinCode.orElse(
                Stock.create(
                        item.getStckIssuCmpyNm(),
                        item.getIsinCdNm(),
                        item.getIsinCd()
                )
        );

    }

}
