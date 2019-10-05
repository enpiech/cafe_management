package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.Date;

public class Revenue {
    private String id;
    private String storeID;
    private long income;
    private long outcome;
    private Date startDate;
    private Date endDate;

    Revenue(String id, String storeID, long income, long outcome, Date startDate, Date endDate) {
        this.id = id;
        this.storeID = storeID;
        this.income = income;
        this.outcome = outcome;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static RevenueBuilder builder() {
        return new RevenueBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getStoreID() {
        return this.storeID;
    }

    public long getIncome() {
        return this.income;
    }

    public long getOutcome() {
        return this.outcome;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public void setOutcome(long outcome) {
        this.outcome = outcome;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Revenue)) return false;
        final Revenue other = (Revenue) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$storeID = this.getStoreID();
        final Object other$storeID = other.getStoreID();
        if (this$storeID == null ? other$storeID != null : !this$storeID.equals(other$storeID))
            return false;
        if (this.getIncome() != other.getIncome()) return false;
        if (this.getOutcome() != other.getOutcome()) return false;
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        if (this$startDate == null ? other$startDate != null : !this$startDate.equals(other$startDate))
            return false;
        final Object this$endDate = this.getEndDate();
        final Object other$endDate = other.getEndDate();
        if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Revenue;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $storeID = this.getStoreID();
        result = result * PRIME + ($storeID == null ? 43 : $storeID.hashCode());
        final long $income = this.getIncome();
        result = result * PRIME + (int) ($income >>> 32 ^ $income);
        final long $outcome = this.getOutcome();
        result = result * PRIME + (int) ($outcome >>> 32 ^ $outcome);
        final Object $startDate = this.getStartDate();
        result = result * PRIME + ($startDate == null ? 43 : $startDate.hashCode());
        final Object $endDate = this.getEndDate();
        result = result * PRIME + ($endDate == null ? 43 : $endDate.hashCode());
        return result;
    }

    public String toString() {
        return "RevenueRepository(id=" + this.getId() + ", storeID=" + this.getStoreID() + ", income=" + this.getIncome() + ", outcome=" + this.getOutcome() + ", startDate=" + this.getStartDate() + ", endDate=" + this.getEndDate() + ")";
    }

    public static class RevenueBuilder {
        private String id;
        private String storeID;
        private long income;
        private long outcome;
        private Date startDate;
        private Date endDate;

        RevenueBuilder() {
        }

        public RevenueBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RevenueBuilder storeID(String storeID) {
            this.storeID = storeID;
            return this;
        }

        public RevenueBuilder income(long income) {
            this.income = income;
            return this;
        }

        public RevenueBuilder outcome(long outcome) {
            this.outcome = outcome;
            return this;
        }

        public RevenueBuilder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public RevenueBuilder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Revenue build() {
            return new Revenue(id, storeID, income, outcome, startDate, endDate);
        }

        public String toString() {
            return "RevenueRepository.RevenueBuilder(id=" + this.id + ", storeID=" + this.storeID + ", income=" + this.income + ", outcome=" + this.outcome + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ")";
        }
    }
}
