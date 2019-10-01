package fit.tdc.edu.vn.cafemanagement.data.model;

import java.time.LocalDateTime;

public class StockHistory {
    private String materialID;
    private long amount;
    private LocalDateTime stockTakeTime;

    StockHistory(String materialID, long amount, LocalDateTime stockTakeTime) {
        this.materialID = materialID;
        this.amount = amount;
        this.stockTakeTime = stockTakeTime;
    }

    public static StockHistoryBuilder builder() {
        return new StockHistoryBuilder();
    }

    public String getMaterialID() {
        return this.materialID;
    }

    public long getAmount() {
        return this.amount;
    }

    public LocalDateTime getStockTakeTime() {
        return this.stockTakeTime;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setStockTakeTime(LocalDateTime stockTakeTime) {
        this.stockTakeTime = stockTakeTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StockHistory)) return false;
        final StockHistory other = (StockHistory) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$materialID = this.getMaterialID();
        final Object other$materialID = other.getMaterialID();
        if (this$materialID == null ? other$materialID != null : !this$materialID.equals(other$materialID))
            return false;
        if (this.getAmount() != other.getAmount()) return false;
        final Object this$stockTakeTime = this.getStockTakeTime();
        final Object other$stockTakeTime = other.getStockTakeTime();
        if (this$stockTakeTime == null ? other$stockTakeTime != null : !this$stockTakeTime.equals(other$stockTakeTime))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StockHistory;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $materialID = this.getMaterialID();
        result = result * PRIME + ($materialID == null ? 43 : $materialID.hashCode());
        final long $amount = this.getAmount();
        result = result * PRIME + (int) ($amount >>> 32 ^ $amount);
        final Object $stockTakeTime = this.getStockTakeTime();
        result = result * PRIME + ($stockTakeTime == null ? 43 : $stockTakeTime.hashCode());
        return result;
    }

    public String toString() {
        return "StockHistory(materialID=" + this.getMaterialID() + ", amount=" + this.getAmount() + ", stockTakeTime=" + this.getStockTakeTime() + ")";
    }

    public static class StockHistoryBuilder {
        private String materialID;
        private long amount;
        private LocalDateTime stockTakeTime;

        StockHistoryBuilder() {
        }

        public StockHistoryBuilder materialID(String materialID) {
            this.materialID = materialID;
            return this;
        }

        public StockHistoryBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public StockHistoryBuilder stockTakeTime(LocalDateTime stockTakeTime) {
            this.stockTakeTime = stockTakeTime;
            return this;
        }

        public StockHistory build() {
            return new StockHistory(materialID, amount, stockTakeTime);
        }

        public String toString() {
            return "StockHistory.StockHistoryBuilder(materialID=" + this.materialID + ", amount=" + this.amount + ", stockTakeTime=" + this.stockTakeTime + ")";
        }
    }
}
