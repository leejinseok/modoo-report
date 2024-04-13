package com.rainyheaven.modooreport.api.presentation.report;

import com.rainyheaven.modooreport.api.application.config.jwt.MemberToken;
import com.rainyheaven.modooreport.api.application.domain.report.ReportService;
import com.rainyheaven.modooreport.api.presentation.common.dto.PageResponse;
import com.rainyheaven.modooreport.api.presentation.report.dto.ReportRequest;
import com.rainyheaven.modooreport.api.presentation.report.dto.ReportResponse;
import com.rainyheaven.modooreport.core.db.domain.report.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "레포트")
@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "레포트 생성", description = "레포트 생성")
    @PostMapping
    public ResponseEntity<ReportResponse> save(
            @RequestBody final ReportRequest request,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        Report save = reportService.save(request, memberToken.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ReportResponse.create(save));
    }

    @Operation(summary = "레포트 조회 (page)", description = "레포트 조회 (page)")
    @GetMapping
    public ResponseEntity<PageResponse<ReportResponse>> findPage(
            @Parameter(name = "query") @RequestParam(defaultValue = "") final String query,
            @Parameter(name = "pageNo", example = "0") final int pageNo,
            @Parameter(name = "pageSize", example = "10") final int pageSize
    ) {
        Page<Report> page = reportService.findPage(query, PageRequest.of(pageNo, pageSize));
        Page<ReportResponse> map = page.map(ReportResponse::create);
        PageResponse<ReportResponse> body = new PageResponse<>(map);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

    @Operation(summary = "레포트 수정", description = "레포트 수정")
    @PatchMapping("/{reportId}")
    public ResponseEntity<ReportResponse> update(
            @PathVariable final long reportId,
            @RequestBody final ReportRequest request,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        Report update = reportService.update(reportId, request, memberToken.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ReportResponse.create(update));
    }
}
