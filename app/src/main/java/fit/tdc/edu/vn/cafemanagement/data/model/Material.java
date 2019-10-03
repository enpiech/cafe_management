package fit.tdc.edu.vn.cafemanagement.data.model;

public class Material {
    private String id;
    private String name;
    private long price;
    private String unitID;
    private boolean sellable;

    Material(String id, String name, long price, String unitID, boolean sellable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unitID = unitID;
        this.sellable = sellable;
    }

    public static MaterialBuilder builder() {
        return new MaterialBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getPrice() {
        return this.price;
    }

    public String getUnitID() {
        return this.unitID;
    }

    public boolean isSellable() {
        return this.sellable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Material)) return false;
        final Material other = (Material) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getPrice() != other.getPrice()) return false;
        final Object this$unitID = this.getUnitID();
        final Object other$unitID = other.getUnitID();
        if (this$unitID == null ? other$unitID != null : !this$unitID.equals(other$unitID))
            return false;
        if (this.isSellable() != other.isSellable()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Material;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final long $price = this.getPrice();
        result = result * PRIME + (int) ($price >>> 32 ^ $price);
        final Object $unitID = this.getUnitID();
        result = result * PRIME + ($unitID == null ? 43 : $unitID.hashCode());
        result = result * PRIME + (this.isSellable() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Material(id=" + this.getId() + ", name=" + this.getName() + ", price=" + this.getPrice() + ", unitID=" + this.getUnitID() + ", sellable=" + this.isSellable() + ")";
    }

    public static class MaterialBuilder {
        private String id;
        private String name;
        private long price;
        private String unitID;
        private boolean sellable;

        MaterialBuilder() {
        }

        public MaterialBuilder id(String id) {
            this.id = id;
            return this;
        }

        public MaterialBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MaterialBuilder price(long price) {
            this.price = price;
            return this;
        }

        public MaterialBuilder unitID(String unitID) {
            this.unitID = unitID;
            return this;
        }

        public MaterialBuilder sellable(boolean sellable) {
            this.sellable = sellable;
            return this;
        }

        public Material build() {
            return new Material(id, name, price, unitID, sellable);
        }

        public String toString() {
            return "Material.MaterialBuilder(id=" + this.id + ", name=" + this.name + ", price=" + this.price + ", unitID=" + this.unitID + ", sellable=" + this.sellable + ")";
        }
    }
}
