package com.rainyheaven.modooreport.batch.domain.stock;

import com.rainyheaven.modooreport.core.web.stock.StockApi;
import com.rainyheaven.modooreport.core.web.stock.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockItemReader implements ItemReader<StockResponseDto.ItemDto>, StepExecutionListener {

    private final StockApi stockApi;

    private int CURSOR = 0;
    private final List<StockResponseDto.ItemDto> items = new ArrayList<>();

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String baseDt = currentDate.format(formatter);

        StockResponseDto dto = stockApi.fetchStock("", 20000, baseDt);
        List<StockResponseDto.ItemDto> item = dto.getResponse()
                .getBody()
                .getItems()
                .getItem();

        items.clear();
        items.addAll(item);
    }


    @Override
    public StockResponseDto.ItemDto read() {
        if (CURSOR >= items.size()) {
            return null;
        }

        StockResponseDto.ItemDto itemDto = items.get(CURSOR);
        CURSOR++;
        return itemDto;
    }

}
