package fit.tdc.edu.vn.cafemanagement.data.model;

public class PaymentState {
    private String id;
    private String name;

    PaymentState(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PaymentStateBuilder builder() {
        return new PaymentStateBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PaymentState)) return false;
        final PaymentState other = (PaymentState) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaymentState;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "PaymentState(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    public static class PaymentStateBuilder {
        private String id;
        private String name;

        PaymentStateBuilder() {
        }

        public PaymentStateBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PaymentStateBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PaymentState build() {
            return new PaymentState(id, name);
        }

        public String toString() {
            return "PaymentState.PaymentStateBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
