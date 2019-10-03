package fit.tdc.edu.vn.cafemanagement.data.model;

public class ProductCategory {
    private String categoryID;
    private String materialID;

    ProductCategory(String categoryID, String materialID) {
        this.categoryID = categoryID;
        this.materialID = materialID;
    }

    public static ProductCategoryBuilder builder() {
        return new ProductCategoryBuilder();
    }

    public String getCategoryID() {
        return this.categoryID;
    }

    public String getMaterialID() {
        return this.materialID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductCategory)) return false;
        final ProductCategory other = (ProductCategory) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$categoryID = this.getCategoryID();
        final Object other$categoryID = other.getCategoryID();
        if (this$categoryID == null ? other$categoryID != null : !this$categoryID.equals(other$categoryID))
            return false;
        final Object this$materialID = this.getMaterialID();
        final Object other$materialID = other.getMaterialID();
        if (this$materialID == null ? other$materialID != null : !this$materialID.equals(other$materialID))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductCategory;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $categoryID = this.getCategoryID();
        result = result * PRIME + ($categoryID == null ? 43 : $categoryID.hashCode());
        final Object $materialID = this.getMaterialID();
        result = result * PRIME + ($materialID == null ? 43 : $materialID.hashCode());
        return result;
    }

    public String toString() {
        return "ProductCategory(categoryID=" + this.getCategoryID() + ", materialID=" + this.getMaterialID() + ")";
    }

    public static class ProductCategoryBuilder {
        private String categoryID;
        private String materialID;

        ProductCategoryBuilder() {
        }

        public ProductCategoryBuilder categoryID(String categoryID) {
            this.categoryID = categoryID;
            return this;
        }

        public ProductCategoryBuilder materialID(String materialID) {
            this.materialID = materialID;
            return this;
        }

        public ProductCategory build() {
            return new ProductCategory(categoryID, materialID);
        }

        public String toString() {
            return "ProductCategory.ProductCategoryBuilder(categoryID=" + this.categoryID + ", materialID=" + this.materialID + ")";
        }
    }
}
