package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SLI {
        
        private String totalResponses;
        private String successResponses;
        private String errorResponses;
        private String successRate;
        private String avgTiming;
        private String metSuccessRateSLI;
        private String metResponseTimeSLI;
}
