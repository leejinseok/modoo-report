package com.rainyheaven.modooreport.batch.domain.stock;

import com.rainyheaven.modooreport.core.web.stock.StockApi;
import com.rainyheaven.modooreport.core.web.stock.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockItemReader implements ItemReader<StockResponseDto.ItemDto>, StepExecutionListener {

    private final StockApi stockApi;

    private int CURSOR = 0;
    private List<StockResponseDto.ItemDto> items = new ArrayList<>();


    @Override
    public void beforeStep(StepExecution stepExecution) {
        StockResponseDto dto = stockApi.fetchStock("", 20000);
        StockResponseDto.ResponseDto response = dto.getResponse();
        StockResponseDto.BodyDto body = response.getBody();
        List<StockResponseDto.ItemDto> item = body.getItems().getItem();
        items.clear();
        items.addAll(item);
    }


    @Override
    public StockResponseDto.ItemDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (CURSOR >= items.size()) {
            return null;
        }

        StockResponseDto.ItemDto itemDto = items.get(CURSOR);
        CURSOR++;
        return itemDto;
    }

}
