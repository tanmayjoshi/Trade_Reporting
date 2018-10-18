package com.jpmorgan.org.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.jpmorgan.org.exception.TradingException;
import com.jpmorgan.org.modal.TradeDataVO;
import com.jpmorgan.org.utilities.Constants;
import com.jpmorgan.org.utilities.Constants.TradeType;

public class ShowcaseReport implements IShowcaseReport {


	@Override
	public void generateSettlementPerday(List<TradeDataVO> tradeDataVOs) {
		System.out.println("****************Incoming(Buy) Settelment****************");
		settlementPerday(tradeDataVOs,  TradeType.BUY.getValue()).forEach((k,v)->System.out.println(MessageFormat.format(Constants.SETTLEMENT, k,v)));
		System.out.println("");
		System.out.println("****************Outgoing(Sell) Settelment****************");
		settlementPerday(tradeDataVOs, TradeType.SELL.getValue()).forEach((k,v)->System.out.println(MessageFormat.format(Constants.SETTLEMENT, k,v)));
		
	}

	@Override
	public void generateDailyRanks(List<TradeDataVO> tradeDataVOs) {
		Map<String, SortedMap<LocalDate, List<TradeDataVO>>> resultSet =getRankData(tradeDataVOs);
		resultSet.forEach((k,v) -> rankDisplayer(v, k));
	}


	/**
	 * 
	 * @param tradeDataList
	 * @param butSellFlag
	 * @return
	 * 
	 * Generate settlement report per day
	 */
	public Map<LocalDate, Double> settlementPerday(List<TradeDataVO> tradeDataList, String butSellFlag) {
		Map<LocalDate, Double> settlementPerDay = tradeDataList.stream()
				.filter(s -> s.getByeSellFlag().equalsIgnoreCase(butSellFlag)).collect(Collectors.groupingBy(
						TradeDataVO::getSettlementDate, Collectors.summingDouble(TradeDataVO::getUsdTradeAmt)));
	    return settlementPerDay;
	}

	/**
	 * 
	 * @param tradeDataList
	 * @throws TradingException
	 * 
	 * Segregate date on buy & sell type in ranking  order
	 */
	public Map<String, SortedMap<LocalDate, List<TradeDataVO>>>  getRankData(List<TradeDataVO> tradeDataList) throws TradingException {
		
		 SortedMap<LocalDate, List<TradeDataVO>> sellRankMap =  new TreeMap<LocalDate, List<TradeDataVO>>();
		 SortedMap<LocalDate, List<TradeDataVO>> buyRankMap =  new TreeMap<LocalDate, List<TradeDataVO>>();
		 Map<String, SortedMap<LocalDate, List<TradeDataVO>>> resultSet = new HashMap<>();
		 tradeDataList.sort(Comparator.comparingDouble(TradeDataVO::getUsdTradeAmt).reversed()); // Sort records in ascending order of settelement date
		 tradeDataList.forEach(data -> {
			 List<TradeDataVO> tradeDataVOs = null;
			 if(Constants.TradeType.BUY.getValue().equalsIgnoreCase(data.getByeSellFlag())) {
				 if(buyRankMap.get(data.getSettlementDate()) == null) {
					 tradeDataVOs = new ArrayList<>();		 
				 }else {
					 tradeDataVOs = buyRankMap.get(data.getSettlementDate());
				 }
				 tradeDataVOs.add(data);
				 buyRankMap.put(data.getSettlementDate(), tradeDataVOs);
			 }else if(Constants.TradeType.SELL.getValue().equalsIgnoreCase(data.getByeSellFlag())) {
				 if(sellRankMap.get(data.getSettlementDate()) == null) {
					 tradeDataVOs = new ArrayList<>();		 
				 }else {
					 tradeDataVOs = sellRankMap.get(data.getSettlementDate());
				 }
				 tradeDataVOs.add(data);
				 sellRankMap.put(data.getSettlementDate(), tradeDataVOs);
			 }
		 });
		 
		/* rankDisplayer(buyRankMap, "Buying");
		 rankDisplayer(sellRankMap, "Selling");*/
		 resultSet.put("Buying", buyRankMap);
		 resultSet.put("Selling", sellRankMap);
		 
		 return resultSet;
	}
	
	/**
	 * 
	 * @param sortedMap
	 * @param action
	 * 
	 * Display Ranks for the entities from sorted map
	 */
	public void rankDisplayer(SortedMap<LocalDate, List<TradeDataVO>> sortedMap, String action) {
		System.out.println("");
		System.out.println(MessageFormat.format(Constants.RANK_TITLE, action));
		sortedMap.forEach((key, value) -> {
			 AtomicInteger atomicInteger = new AtomicInteger(0);
			 value.forEach(g -> {
				 System.out.println(MessageFormat.format(Constants.ENTITY_USD_AMT,g.getSettlementDate(),atomicInteger.incrementAndGet(), g.getEntity(), g.getUsdTradeAmt()));
			 });
		 });
	}

}
