package com.statravel.newtours.util;

import java.util.List;

import com.google.gson.Gson;
import com.statravel.newtours.pojo.NewTour;
import com.statravel.newtours.pojo.TourSummaryResponse;

public class TourSummaryUtil {

	public static List<NewTour> getNewTours(){
		TourSummaryResponse rs = new Gson()
				.fromJson("{ \"params\": { \"preQuery\": { \"provider\": \"gadventures\", \"code\": \"aabng\", \"lowestDeparturePrices.0\": { \"$exists\": true }, \"lastTransformed\": { \"$gte\": \"2020-07-10T10:49:00.450Z\" } }, \"postQuery\": { \"currency\": \"GBP\" }, \"skip\": 0, \"limit\": 1000, \"mode\": \"array\" }, \"length\": 10, \"data\": [ { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"924154\", \"startDate\": \"2020-10-11T00:00:00.000Z\", \"endDate\": \"2020-10-22T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2799, \"originalPrice\": 2799, \"discount\": 0, \"sku\": \"GPAAABNG201011-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2020-10-12T02:01:00\", \"endDateTime\": \"2020-10-22T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"924155\", \"startDate\": \"2020-11-08T00:00:00.000Z\", \"endDate\": \"2020-11-19T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2799, \"originalPrice\": 2799, \"discount\": 0, \"sku\": \"GPAAABNG201108-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2020-11-09T02:01:00\", \"endDateTime\": \"2020-11-19T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"924157\", \"startDate\": \"2021-01-03T00:00:00.000Z\", \"endDate\": \"2021-01-14T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2799, \"originalPrice\": 2799, \"discount\": 0, \"sku\": \"GPAAABNG210103-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-01-04T02:01:00\", \"endDateTime\": \"2021-01-14T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019181\", \"startDate\": \"2021-04-25T00:00:00.000Z\", \"endDate\": \"2021-05-06T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2799, \"originalPrice\": 2799, \"discount\": 0, \"sku\": \"GPAAABNG210425-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-04-26T02:01:00\", \"endDateTime\": \"2021-05-06T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019183\", \"startDate\": \"2021-06-06T00:00:00.000Z\", \"endDate\": \"2021-06-17T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2959, \"originalPrice\": 2959, \"discount\": 0, \"sku\": \"GPAAABNG210606-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-06-07T02:01:00\", \"endDateTime\": \"2021-06-17T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019184\", \"startDate\": \"2021-06-27T00:00:00.000Z\", \"endDate\": \"2021-07-08T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2959, \"originalPrice\": 2959, \"discount\": 0, \"sku\": \"GPAAABNG210627-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-06-28T02:01:00\", \"endDateTime\": \"2021-07-08T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019185\", \"startDate\": \"2021-07-18T00:00:00.000Z\", \"endDate\": \"2021-07-29T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2959, \"originalPrice\": 2959, \"discount\": 0, \"sku\": \"GPAAABNG210718-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-07-19T02:01:00\", \"endDateTime\": \"2021-07-29T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019186\", \"startDate\": \"2021-08-08T00:00:00.000Z\", \"endDate\": \"2021-08-19T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2959, \"originalPrice\": 2959, \"discount\": 0, \"sku\": \"GPAAABNG210808-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-08-09T02:01:00\", \"endDateTime\": \"2021-08-19T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019188\", \"startDate\": \"2021-09-19T00:00:00.000Z\", \"endDate\": \"2021-09-30T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2959, \"originalPrice\": 2959, \"discount\": 0, \"sku\": \"GPAAABNG210919-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-09-20T02:01:00\", \"endDateTime\": \"2021-09-30T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 }, { \"provider\": \"gadventures\", \"language\": \"en\", \"code\": \"aabng\", \"tourId\": \"24781\", \"title\": \"Best of Borneo\", \"geography\": { \"startRegion\": \"\", \"startCountry\": \"Malaysia\", \"startCity\": \"Kuching\", \"endRegion\": \"\", \"endCountry\": \"Malaysia\", \"endCity\": \"Kota Kinabalu\" }, \"minAge\": 12, \"maxAge\": 100, \"lastTransformed\": \"2020-07-15T06:19:56.880Z\", \"departureId\": \"1019296\", \"startDate\": \"2021-12-12T00:00:00.000Z\", \"endDate\": \"2021-12-23T00:00:00.000Z\", \"isGuaranteed\": true, \"incaTrailAvailability\": \"not-applicable\", \"roomType\": \"Standard\", \"ageGrade\": \"Adult\", \"roomCode\": \"STANDARD\", \"currency\": \"GBP\", \"salePrice\": 2799, \"originalPrice\": 2799, \"discount\": 0, \"sku\": \"GPAAABNG211212-O1\", \"sellingRegion\": \"uk\", \"startDateTime\": \"2021-12-13T02:01:00\", \"endDateTime\": \"2021-12-23T00:01:00\", \"availability\": \"Available\", \"deposit\": 200, \"duration\": 12 } ] }", TourSummaryResponse.class);
		return rs.getData();
	}
}
