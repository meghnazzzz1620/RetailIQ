package com.retailiq.projection;

import java.math.BigDecimal;

public interface MonthlySalesProjection {

    String getMonth();

    BigDecimal getRevenue();

}