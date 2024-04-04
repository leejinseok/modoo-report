package com.rainyheaven.modooreport.core.web.stock;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StockResponseDto {


    private ResponseDto response;

    @Getter
    @Setter
    static class ResponseDto {
        private BodyDto body;
    }

    @Getter
    @Setter
    static class BodyDto {
        private ItemsDto items;
        private Integer numOfRows;
        private Integer pageNo;
        private Integer totalCount;
    }

    @Getter
    @Setter
    static class ItemsDto {
        private List<ItemDto> item = new ArrayList<>();
    }

    @Getter
    @Setter
    static class ItemDto {
        private String basDt;
        private String crno;
        private String isinCd;
        private String stckIssuCmpyNm;
        private String isinCdNm;
        private String scrsItmsKcd;
        private String scrsItmsKcdNm;
        private String stckParPrc;
        private String issuStckCnt;
        private String lstgDt;
        private String lstgAbolDt;
        private String dpsgRegDt;
        private String dpsgCanDt;
        private String issuFrmtClsfNm;
        private String itmsShrtnCd;
    }

}
