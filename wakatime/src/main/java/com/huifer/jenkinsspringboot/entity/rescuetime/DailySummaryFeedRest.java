package com.huifer.jenkinsspringboot.entity.rescuetime;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date: 2019-09-30
 */
@NoArgsConstructor
@Data
public class DailySummaryFeedRest {

    /**
     * id : 1569740400
     * date : 2019-09-29
     * productivity_pulse : 77
     * very_productive_percentage : 62.8
     * productive_percentage : 9.2
     * neutral_percentage : 13.4
     * distracting_percentage : 5.1
     * very_distracting_percentage : 9.5
     * all_productive_percentage : 72
     * all_distracting_percentage : 14.6
     * uncategorized_percentage : 3.7
     * business_percentage : 3.5
     * communication_and_scheduling_percentage : 8.3
     * social_networking_percentage : 0.2
     * design_and_composition_percentage : 2.1
     * entertainment_percentage : 3.9
     * news_percentage : 7
     * software_development_percentage : 57.2
     * reference_and_learning_percentage : 9.5
     * shopping_percentage : 0.2
     * utilities_percentage : 4.3
     * total_hours : 11.8
     * very_productive_hours : 7.41
     * productive_hours : 1.09
     * neutral_hours : 1.58
     * distracting_hours : 0.6
     * very_distracting_hours : 1.12
     * all_productive_hours : 8.5
     * all_distracting_hours : 1.72
     * uncategorized_hours : 0.44
     * business_hours : 0.41
     * communication_and_scheduling_hours : 0.98
     * social_networking_hours : 0.03
     * design_and_composition_hours : 0.24
     * entertainment_hours : 0.46
     * news_hours : 0.83
     * software_development_hours : 6.76
     * reference_and_learning_hours : 1.12
     * shopping_hours : 0.03
     * utilities_hours : 0.51
     * total_duration_formatted : 11h 48m
     * very_productive_duration_formatted : 7h 24m
     * productive_duration_formatted : 1h 5m
     * neutral_duration_formatted : 1h 34m
     * distracting_duration_formatted : 35m 50s
     * very_distracting_duration_formatted : 1h 7m
     * all_productive_duration_formatted : 8h 30m
     * all_distracting_duration_formatted : 1h 43m
     * uncategorized_duration_formatted : 26m 28s
     * business_duration_formatted : 24m 49s
     * communication_and_scheduling_duration_formatted : 58m 33s
     * social_networking_duration_formatted : 1m 41s
     * design_and_composition_duration_formatted : 14m 32s
     * entertainment_duration_formatted : 27m 47s
     * news_duration_formatted : 49m 54s
     * software_development_duration_formatted : 6h 45m
     * reference_and_learning_duration_formatted : 1h 7m
     * shopping_duration_formatted : 1m 31s
     * utilities_duration_formatted : 30m 30s
     */

    private BigDecimal id;
    private Date date;
    private BigDecimal productivityPulse;
    private BigDecimal veryProductivePercentage;
    private BigDecimal productivePercentage;
    private BigDecimal neutralPercentage;
    private BigDecimal distractingPercentage;
    private BigDecimal veryDistractingPercentage;
    private BigDecimal allProductivePercentage;
    private BigDecimal allDistractingPercentage;
    private BigDecimal uncategorizedPercentage;
    private BigDecimal businessPercentage;
    private BigDecimal communicationAndSchedulingPercentage;
    private BigDecimal socialNetworkingPercentage;
    private BigDecimal designAndCompositionPercentage;
    private BigDecimal entertainmentPercentage;
    private BigDecimal newsPercentage;
    private BigDecimal softwareDevelopmentPercentage;
    private BigDecimal referenceAndLearningPercentage;
    private BigDecimal shoppingPercentage;
    private BigDecimal utilitiesPercentage;
    private BigDecimal totalHours;
    private BigDecimal veryProductiveHours;
    private BigDecimal productiveHours;
    private BigDecimal neutralHours;
    private BigDecimal distractingHours;
    private BigDecimal veryDistractingHours;
    private BigDecimal allProductiveHours;
    private BigDecimal allDistractingHours;
    private BigDecimal uncategorizedHours;
    private BigDecimal businessHours;
    private BigDecimal communicationAndSchedulingHours;
    private BigDecimal socialNetworkingHours;
    private BigDecimal designAndCompositionHours;
    private BigDecimal entertainmentHours;
    private BigDecimal newsHours;
    private BigDecimal softwareDevelopmentHours;
    private BigDecimal referenceAndLearningHours;
    private BigDecimal shoppingHours;
    private BigDecimal utilitiesHours;
    private String totalDurationFormatted;
    private String veryProductiveDurationFormatted;
    private String productiveDurationFormatted;
    private String neutralDurationFormatted;
    private String distractingDurationFormatted;
    private String veryDistractingDurationFormatted;
    private String allProductiveDurationFormatted;
    private String allDistractingDurationFormatted;
    private String uncategorizedDurationFormatted;
    private String businessDurationFormatted;
    private String communicationAndSchedulingDurationFormatted;
    private String socialNetworkingDurationFormatted;
    private String designAndCompositionDurationFormatted;
    private String entertainmentDurationFormatted;
    private String newsDurationFormatted;
    private String softwareDevelopmentDurationFormatted;
    private String referenceAndLearningDurationFormatted;
    private String shoppingDurationFormatted;
    private String utilitiesDurationFormatted;
}
