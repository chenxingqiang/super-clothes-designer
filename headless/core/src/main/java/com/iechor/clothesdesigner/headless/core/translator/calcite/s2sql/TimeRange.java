package com.iechor.clothesdesigner.headless.core.translator.calcite.s2sql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeRange {

    private String start;
    private String end;
}
