package fit.tdc.edu.vn.cafemanagement.data.model;

import org.jetbrains.annotations.NotNull;

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel;

public class Category extends FirestoreModel {
    private String name;

    public Category() {}

    private Category(CategoryBuilder builder) {
        super.setId(builder.id);
        this.name = builder.name;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public String getId() {
        return super.getId();
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Category)) return false;
        final Category other = (Category) o;
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
        return other instanceof Category;
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
        return "Category(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    @NotNull
    @Override
    public String getCollectionName() {
        return null;
    }

    @Override
    public void setCollectionName(@NotNull String s) {

    }

    public static class CategoryBuilder {
        private String id;
        private String name;

        public CategoryBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }

        public String toString() {
            return "Category.CategoryBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
