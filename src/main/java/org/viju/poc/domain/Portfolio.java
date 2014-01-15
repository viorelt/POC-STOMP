package org.viju.poc.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viorelt on 1/14/14.
 */
public class Portfolio {
    private final Map<String,PortfolioPosition> positionLookup = new HashMap<String,PortfolioPosition>();


    public List<PortfolioPosition> getPositions() {
        return new ArrayList<PortfolioPosition>(positionLookup.values());
    }

    public void addPosition(PortfolioPosition position) {
        this.positionLookup.put(position.getTicker(), position);
    }

    public PortfolioPosition getPortfolioPosition(String ticker) {
        return this.positionLookup.get(ticker);
    }

    /**
     * @return the updated position or null
     */
    public PortfolioPosition buy(String ticker, int sharesToBuy) {
        PortfolioPosition position = this.positionLookup.get(ticker);
        if ((position == null) || (sharesToBuy < 1)) {
            return null;
        }
        position = new PortfolioPosition(position, sharesToBuy);
        this.positionLookup.put(ticker, position);
        return position;
    }

    /**
     * @return the updated position or null
     */
    public PortfolioPosition sell(String ticker, int sharesToSell) {
        PortfolioPosition position = this.positionLookup.get(ticker);
        if ((position == null) || (sharesToSell < 1) || (position.getShares() < sharesToSell)) {
            return null;
        }
        position = new PortfolioPosition(position, -sharesToSell);
        this.positionLookup.put(ticker, position);
        return position;
    }
}
