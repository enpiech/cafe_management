package fit.tdc.edu.vn.cafemanagement.data.model;

import java.time.LocalDateTime;

public class Payment {
    private String id;
    private String storeID;
    private String tableID;
    private String confirmedEmployeeID;
    private LocalDateTime paidTime;
    private String price;
    private String stateID;
    private String formatID;

    Payment(String id, String storeID, String tableID, String confirmedEmployeeID, LocalDateTime paidTime, String price, String stateID, String formatID) {
        this.id = id;
        this.storeID = storeID;
        this.tableID = tableID;
        this.confirmedEmployeeID = confirmedEmployeeID;
        this.paidTime = paidTime;
        this.price = price;
        this.stateID = stateID;
        this.formatID = formatID;
    }

    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getStoreID() {
        return this.storeID;
    }

    public String getTableID() {
        return this.tableID;
    }

    public String getConfirmedEmployeeID() {
        return this.confirmedEmployeeID;
    }

    public LocalDateTime getPaidTime() {
        return this.paidTime;
    }

    public String getPrice() {
        return this.price;
    }

    public String getStateID() {
        return this.stateID;
    }

    public String getFormatID() {
        return this.formatID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public void setConfirmedEmployeeID(String confirmedEmployeeID) {
        this.confirmedEmployeeID = confirmedEmployeeID;
    }

    public void setPaidTime(LocalDateTime paidTime) {
        this.paidTime = paidTime;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public void setFormatID(String formatID) {
        this.formatID = formatID;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Payment)) return false;
        final Payment other = (Payment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$storeID = this.getStoreID();
        final Object other$storeID = other.getStoreID();
        if (this$storeID == null ? other$storeID != null : !this$storeID.equals(other$storeID))
            return false;
        final Object this$tableID = this.getTableID();
        final Object other$tableID = other.getTableID();
        if (this$tableID == null ? other$tableID != null : !this$tableID.equals(other$tableID))
            return false;
        final Object this$confirmedEmployeeID = this.getConfirmedEmployeeID();
        final Object other$confirmedEmployeeID = other.getConfirmedEmployeeID();
        if (this$confirmedEmployeeID == null ? other$confirmedEmployeeID != null : !this$confirmedEmployeeID.equals(other$confirmedEmployeeID))
            return false;
        final Object this$paidTime = this.getPaidTime();
        final Object other$paidTime = other.getPaidTime();
        if (this$paidTime == null ? other$paidTime != null : !this$paidTime.equals(other$paidTime))
            return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price))
            return false;
        final Object this$stateID = this.getStateID();
        final Object other$stateID = other.getStateID();
        if (this$stateID == null ? other$stateID != null : !this$stateID.equals(other$stateID))
            return false;
        final Object this$formatID = this.getFormatID();
        final Object other$formatID = other.getFormatID();
        if (this$formatID == null ? other$formatID != null : !this$formatID.equals(other$formatID))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Payment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $storeID = this.getStoreID();
        result = result * PRIME + ($storeID == null ? 43 : $storeID.hashCode());
        final Object $tableID = this.getTableID();
        result = result * PRIME + ($tableID == null ? 43 : $tableID.hashCode());
        final Object $confirmedEmployeeID = this.getConfirmedEmployeeID();
        result = result * PRIME + ($confirmedEmployeeID == null ? 43 : $confirmedEmployeeID.hashCode());
        final Object $paidTime = this.getPaidTime();
        result = result * PRIME + ($paidTime == null ? 43 : $paidTime.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $stateID = this.getStateID();
        result = result * PRIME + ($stateID == null ? 43 : $stateID.hashCode());
        final Object $formatID = this.getFormatID();
        result = result * PRIME + ($formatID == null ? 43 : $formatID.hashCode());
        return result;
    }

    public String toString() {
        return "Payment(id=" + this.getId() + ", storeID=" + this.getStoreID() + ", tableID=" + this.getTableID() + ", confirmedEmployeeID=" + this.getConfirmedEmployeeID() + ", paidTime=" + this.getPaidTime() + ", price=" + this.getPrice() + ", stateID=" + this.getStateID() + ", formatID=" + this.getFormatID() + ")";
    }

    public static class PaymentBuilder {
        private String id;
        private String storeID;
        private String tableID;
        private String confirmedEmployeeID;
        private LocalDateTime paidTime;
        private String price;
        private String stateID;
        private String formatID;

        PaymentBuilder() {
        }

        public PaymentBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder storeID(String storeID) {
            this.storeID = storeID;
            return this;
        }

        public PaymentBuilder tableID(String tableID) {
            this.tableID = tableID;
            return this;
        }

        public PaymentBuilder confirmedEmployeeID(String confirmedEmployeeID) {
            this.confirmedEmployeeID = confirmedEmployeeID;
            return this;
        }

        public PaymentBuilder paidTime(LocalDateTime paidTime) {
            this.paidTime = paidTime;
            return this;
        }

        public PaymentBuilder price(String price) {
            this.price = price;
            return this;
        }

        public PaymentBuilder stateID(String stateID) {
            this.stateID = stateID;
            return this;
        }

        public PaymentBuilder formatID(String formatID) {
            this.formatID = formatID;
            return this;
        }

        public Payment build() {
            return new Payment(id, storeID, tableID, confirmedEmployeeID, paidTime, price, stateID, formatID);
        }

        public String toString() {
            return "Payment.PaymentBuilder(id=" + this.id + ", storeID=" + this.storeID + ", tableID=" + this.tableID + ", confirmedEmployeeID=" + this.confirmedEmployeeID + ", paidTime=" + this.paidTime + ", price=" + this.price + ", stateID=" + this.stateID + ", formatID=" + this.formatID + ")";
        }
    }
}
