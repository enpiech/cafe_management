package fit.tdc.edu.vn.cafemanagement.data.model;

public class OderItems {
    private String paymentID;
    private String materialID;
    private int amount;

    OderItems(String paymentID, String materialID, int amount) {
        this.paymentID = paymentID;
        this.materialID = materialID;
        this.amount = amount;
    }

    public static OderItemsBuilder builder() {
        return new OderItemsBuilder();
    }

    public String getPaymentID() {
        return this.paymentID;
    }

    public String getMaterialID() {
        return this.materialID;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OderItems)) return false;
        final OderItems other = (OderItems) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$paymentID = this.getPaymentID();
        final Object other$paymentID = other.getPaymentID();
        if (this$paymentID == null ? other$paymentID != null : !this$paymentID.equals(other$paymentID))
            return false;
        final Object this$materialID = this.getMaterialID();
        final Object other$materialID = other.getMaterialID();
        if (this$materialID == null ? other$materialID != null : !this$materialID.equals(other$materialID))
            return false;
        if (this.getAmount() != other.getAmount()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OderItems;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $paymentID = this.getPaymentID();
        result = result * PRIME + ($paymentID == null ? 43 : $paymentID.hashCode());
        final Object $materialID = this.getMaterialID();
        result = result * PRIME + ($materialID == null ? 43 : $materialID.hashCode());
        result = result * PRIME + this.getAmount();
        return result;
    }

    public String toString() {
        return "OderItems(paymentID=" + this.getPaymentID() + ", materialID=" + this.getMaterialID() + ", amount=" + this.getAmount() + ")";
    }

    public static class OderItemsBuilder {
        private String paymentID;
        private String materialID;
        private int amount;

        OderItemsBuilder() {
        }

        public OderItemsBuilder paymentID(String paymentID) {
            this.paymentID = paymentID;
            return this;
        }

        public OderItemsBuilder materialID(String materialID) {
            this.materialID = materialID;
            return this;
        }

        public OderItemsBuilder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public OderItems build() {
            return new OderItems(paymentID, materialID, amount);
        }

        public String toString() {
            return "OderItems.OderItemsBuilder(paymentID=" + this.paymentID + ", materialID=" + this.materialID + ", amount=" + this.amount + ")";
        }
    }
}
